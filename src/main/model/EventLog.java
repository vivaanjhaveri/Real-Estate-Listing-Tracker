package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// CITATION: Code obtained from AlarmSystem
//           URL: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/model/Event.java

// Represents a log of alarm system events.
public class EventLog implements Iterable<Event> {

    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: Prevents external construction
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: Gets instance of EventLog - creates it if it doesn't already exist.
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // EFFECTS: Adds event to event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // EFFECTS: Clears event log and logs the event.
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}