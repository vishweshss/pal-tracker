package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

public interface TimeEntryRepository {
    public TimeEntry create(TimeEntry timeEntry);
    public TimeEntry find(long id);
    public TimeEntry update(long id, TimeEntry timeEntry);
    public TimeEntry delete(long id);
    public List<TimeEntry> list();
}
