package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.*;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private final HashMap<Long,TimeEntry> repo = new HashMap<>();

    private long id = 0L;
    //long timeEntryId, long projectId,long userId, LocalDate localDate,int hours
    @Override
    public TimeEntry create(TimeEntry timeEntryRec) {
        long timeEntryId = ++id;

        TimeEntry timeEntry = new TimeEntry(timeEntryId, timeEntryRec.getProjectId(), timeEntryRec.getUserId(), timeEntryRec.getDate(), timeEntryRec.getHours());

       // long newId = timeEntry.getId();

        repo.put(timeEntryId, timeEntry);
        return timeEntry;
    };

    @Override
    public TimeEntry find(long id){
        return repo.get(id);
    }


    public TimeEntry update(long id, TimeEntry timeEntryRec) {

    //    repo.remove(id);
        TimeEntry timeEntryNew = find(id);
        if(timeEntryNew == null) {
            return null;
        }
        else {

            timeEntryNew = new TimeEntry(id, timeEntryRec.getProjectId(), timeEntryRec.getUserId(), timeEntryRec.getDate(), timeEntryRec.getHours());
            //  long newId = timeEntryNew.getId();
            repo.replace(id, timeEntryNew);
            return timeEntryNew;
        }

    }
    public TimeEntry delete(long id){
        TimeEntry timeEntryNew = find(id);
        if(timeEntryNew == null) {
            return null;
        } else {
            repo.remove(id);
        }

       return timeEntryNew;

    }
    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>();
        Set ids = repo.keySet();
        for(Object id : ids) {
            list.add(repo.get(id));
        }
        return list;
    }


}
