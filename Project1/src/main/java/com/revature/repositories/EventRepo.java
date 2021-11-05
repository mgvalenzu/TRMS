package com.revature.repositories;

import com.revature.models.Event;

import java.util.List;

public interface EventRepo {

    public Event addEvent(Event e);
    public List<Event> getAllEvents();
    public Event getEvent(int id);
    public Event updateEvent(Event change);
    public Event deleteEvent(int id);
}
