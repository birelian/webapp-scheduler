package net.birelian.poc.webappscheduler.service;

import net.birelian.poc.webappscheduler.model.ScheduledTask;

import java.util.Arrays;
import java.util.List;

/**
 * In a real application this class should use a DAO to get the scheduled tasks from
 * the database and scheduled tasks may have an active/inactive flag.
 */
public class ScheduledTaskService {

    public List<ScheduledTask> findAll() {
        return Arrays.asList(new ScheduledTask("net.birelian.poc.webappscheduler.schedule.MaintenanceSchedule", 22, 30, 1000 * 60 * 60 * 24));
    }
}
