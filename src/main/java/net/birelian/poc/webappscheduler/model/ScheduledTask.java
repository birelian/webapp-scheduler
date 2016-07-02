package net.birelian.poc.webappscheduler.model;

/**
 * Represents an scheduled task
 */
public class ScheduledTask {

    private String qualifiedClassName;
    private int hour;
    private int minute;
    private long interval; // Interval between executions in millis.

    /**
     * Default constructor
     */
    public ScheduledTask() {
    }

    /**
     * All attributes constructor
     *
     * @param className Class name
     * @param hour      Hour
     * @param minute    Minute
     * @param interval  Time interval
     */
    public ScheduledTask(String className, int hour, int minute, long interval) {
        this.qualifiedClassName = className;
        this.hour = hour;
        this.minute = minute;
        this.interval = interval;
    }

    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    public void setQualifiedClassName(String qualifiedClassName) {
        this.qualifiedClassName = qualifiedClassName;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}
