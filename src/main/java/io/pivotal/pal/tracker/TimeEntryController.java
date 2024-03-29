package io.pivotal.pal.tracker;



import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {


    private TimeEntryRepository timeEntryRepository;

    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry){
        this.timeEntryRepository = timeEntryRepository;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }




    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate){
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
        //return timeEntry;
    }


    @GetMapping("/time-entries/{TIME_ENTRY_ID}")
    public ResponseEntity<TimeEntry> read(@PathVariable long TIME_ENTRY_ID) {
        TimeEntry timeEntry = timeEntryRepository.find(TIME_ENTRY_ID);
        if(timeEntry == null) {
            return new ResponseEntity<>(new TimeEntry(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
    return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }


    @PutMapping("/time-entries/{TIME_ENTRY_ID}")
    public ResponseEntity<TimeEntry>  update(@PathVariable long TIME_ENTRY_ID,@RequestBody TimeEntry expected){
        TimeEntry timeEntry=timeEntryRepository.update(TIME_ENTRY_ID,expected);

        if( timeEntry== null) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);

    }


    @DeleteMapping("/time-entries/{TIME_ENTRY_ID}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long TIME_ENTRY_ID){
        TimeEntry te = timeEntryRepository.delete(TIME_ENTRY_ID);
        return new ResponseEntity<>(te, HttpStatus.NO_CONTENT);
    }
}
