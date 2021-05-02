package com.example.events.service;


import com.example.events.model.Event;
import com.example.events.model.EventType;
import com.example.events.model.exceptions.InvalidEventIdException;
import com.example.events.model.exceptions.InvalidEventLocationIdException;

import java.util.List;

public interface EventService {


    List<Event> listAllEvents();

    Event findById(Long id);

    Event create(String name, String description, Double price, EventType type, Long location);

    Event update(Long id, String name, String description, Double price, EventType type, Long location);

    Event delete(Long id);

    Event like(Long id);

    List<Event> listEventsWithPriceLessThanAndType(Double price, EventType type);
}

