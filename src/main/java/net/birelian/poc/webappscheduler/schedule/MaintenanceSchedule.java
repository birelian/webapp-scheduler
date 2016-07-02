package net.birelian.poc.webappscheduler.schedule;

import java.util.TimerTask;

/**
 * Maintenance scheduled task
 */
public class MaintenanceSchedule extends TimerTask {

    public void run() {
        System.out.println("Executing maintenance tasks");
    }
}
