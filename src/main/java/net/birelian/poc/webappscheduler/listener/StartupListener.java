package net.birelian.poc.webappscheduler.listener;

import net.birelian.poc.webappscheduler.model.ScheduledTask;
import net.birelian.poc.webappscheduler.service.ScheduledTaskService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main application listener.
 * <p>
 * If configured in web.xml, listeners are instantiated when application starts up.
 */
public class StartupListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("Starting application");
        Timer timer = new Timer("webapp-scheduler timer", true);

        // Scheduled task service. In a real application we would use dependency injection
        ScheduledTaskService scheduledTaskService = new ScheduledTaskService();

        for (ScheduledTask scheduledTask : scheduledTaskService.findAll()) {
            createScheduledTask(timer, scheduledTask);
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down application");
    }

    private void createScheduledTask(Timer timer, ScheduledTask scheduledTask) {

        try {

            Class schedulerClass = Class.forName(scheduledTask.getQualifiedClassName());
            Date scheduledDate = getScheduledDate(scheduledTask);

            timer.scheduleAtFixedRate((TimerTask) schedulerClass.newInstance(),
                    scheduledDate, scheduledTask.getInterval());

            System.out.println("Scheduled class: " + scheduledTask.getQualifiedClassName()
                    + " at " + scheduledDate
                    + " with an interval of " + scheduledTask.getInterval() + " millis");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (InstantiationException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Date getScheduledDate(ScheduledTask scheduledTask) {

        LocalDateTime dateTime = LocalDateTime.now()
                .withHour(scheduledTask.getHour())
                .withMinute(scheduledTask.getMinute())
                .withSecond(0);

        // First execution must always be in the future.
        if (dateTime.isBefore(LocalDateTime.now())) {
            dateTime = dateTime.plusDays(1);
        }

        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
