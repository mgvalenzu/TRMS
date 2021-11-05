package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Event;
import com.revature.services.EventService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class EventController {

    EventService es;
    Gson gson = new Gson();

    //Constructor
    public EventController(EventService es) {
        this.es = es;
    }

    // Controller responsible for managing the ApP logic.
    // I.e. What isit that our application needs to be able to to do
    // in this case manage route/endpoints

    public Handler getEventById = (context) -> {

        int id = Integer.parseInt(context.pathParam("id"));
        Event d = es.getEvent(id);
        populateResult(context, d);
    };

    public Handler getAllEvents = (context) -> {
        List<Event> EventList = es.getAllEvents();

        if(EventList != null) {
            context.result(gson.toJson(EventList));
        } else {
            context.result("{}");
        }
    };

    public Handler addEvent = (context) -> {
        Event d = gson.fromJson(context.body(), Event.class);

        d = es.addEvent(d);
        populateResult(context, d);
    };

    public Handler updateEvent = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Event d = gson.fromJson(context.body(), Event.class);
        d.setId(id);
        d = es.updateEvent(d);
        populateResult(context, d);

    };

    public Handler deleteEvent = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Event d = es.deleteEvent(id);
        populateResult(context, d);
    };

    //Helper function
    private void populateResult (Context context, Event d) {
        if (d != null) {
            context.result(gson.toJson(d));
        } else {
            context.result("{}");
        }
    }
}