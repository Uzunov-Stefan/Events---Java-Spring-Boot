package com.example.events.service.impl;

import lombok.Setter;
import com.example.events.model.Event;
import com.example.events.model.EventLocation;
import com.example.events.model.EventType;
import com.example.events.model.exceptions.InvalidEventIdException;
import com.example.events.model.exceptions.InvalidEventLocationIdException;
import com.example.events.repository.EventLocationRepository;
import com.example.events.repository.EventRepository;
import com.example.events.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventLocationRepository eventLocationRepository;

    public EventServiceImpl(EventRepository eventRepository, EventLocationRepository eventLocationRepository) {
        this.eventRepository = eventRepository;
        this.eventLocationRepository = eventLocationRepository;
    }

    @Override
    public List<Event> listAllEvents() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(InvalidEventIdException::new);
    }

    @Override
    public Event create(String name, String description, Double price, EventType type, Long location) {
        EventLocation eventLocation = this.eventLocationRepository.findById(location).orElseThrow(InvalidEventLocationIdException::new);
        Event event = new Event(name, description, price, type, eventLocation);

        return this.eventRepository.save(event);
    }

    @Override
    public Event update(Long id, String name, String description, Double price, EventType type, Long location) {
        Event event = this.eventRepository.findById(id).orElseThrow(InvalidEventIdException::new);
        event.setName(name);
        event.setDescription(description);
        event.setPrice(price);
        event.setType(type);
        EventLocation eventLocation = this.eventLocationRepository.findById(location).orElseThrow(InvalidEventLocationIdException::new);
        event.setLocation(eventLocation);

        return this.eventRepository.save(event);
    }

    @Override
    public Event delete(Long id) {
        Event event = this.eventRepository.findById(id).orElseThrow(InvalidEventIdException::new);
        this.eventRepository.delete(event);

        return event;
    }

    @Override
    public Event like(Long id) {
        Event event = this.eventRepository.findById(id).orElseThrow(InvalidEventIdException::new);
        int a = event.getLikes() + 1;
        event.setLikes(a);
        return this.eventRepository.save(event);
    }

    @Override
    public List<Event> listEventsWithPriceLessThanAndType(Double price, EventType type) {
        if (price != null && type != null){
            return this.eventRepository.findByPriceLessThanAndTypeLike(price,type);
        } else if (price != null) {
            return this.eventRepository.findByPriceLessThan(price);
        } else if (type != null){
            return this.eventRepository.findByTypeLike(type);
        } else {
            return this.eventRepository.findAll();
        }

    }
}

