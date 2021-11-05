package com.revature.services;

import com.revature.models.Event;
import com.revature.repositories.EventRepo;

import java.util.List;

public class EventServiceImpl implements EventService{

    EventRepo er;

    public EventServiceImpl(EventRepo er) {
        this.er = er;
    }

    // Basically going to pass the torch down to the repo layer for
    // these basic operations

    @Override
    public Event addEvent(Event e) {
        return er.addEvent(e);
    }

    @Override
    public Event getEvent(int id) {
        return er.getEvent(id);
    }
//
//    @Override
//    public Event getEvent(String name) {
//        return er.getEvent(name);
//    }

    @Override
    public List<Event> getAllEvents() {
        return er.getAllEvents();
    }

    @Override
    public Event updateEvent(Event change) {
        return er.updateEvent(change);
    }

    @Override
    public Event deleteEvent(int id) {
        return er.deleteEvent(id);
    }

    // Service layer methods



}