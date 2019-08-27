package io.pivotal.pal.tracker;


import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {


    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;


    public TimeEntry(){

    }



    public TimeEntry(long projectId,long userId,LocalDate localDate,int hours ){
        this.projectId = projectId;
        this.userId = userId;
        this.date = localDate;
        this.hours = hours;

    }

    public TimeEntry(long timeEntryId, long projectId,long userId, LocalDate localDate,int hours) {
        this.id = timeEntryId;
        this.projectId = projectId;
        this.userId = userId;
        this.date = localDate;
        this.hours = hours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeEntry)) return false;

        TimeEntry timeEntry = (TimeEntry) o;

        if (id != timeEntry.id) return false;
        if (projectId != timeEntry.projectId) return false;
        if (userId != timeEntry.userId) return false;
        if (hours != timeEntry.hours) return false;
        return date.equals(timeEntry.date);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + date.hashCode();
        result = 31 * result + hours;
        return result;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


}
