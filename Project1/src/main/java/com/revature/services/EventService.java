package com.revature.services;

import com.revature.models.Event;

import java.util.List;

public interface EventService {

    // Repository Methods
    public Event addEvent(Event e);
    public Event getEvent(int id);
//    public Event getEvent(String name); // get Event by name
    public List<Event> getAllEvents();
    public Event updateEvent(Event change);
    public Event deleteEvent(int id);

    // Other business logic methods here

}
