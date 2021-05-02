package com.example.events.service.impl;


import com.example.events.model.EventLocation;
import com.example.events.model.exceptions.InvalidEventLocationIdException;
import com.example.events.repository.EventLocationRepository;
import com.example.events.service.EventLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventLocationServiceImpl implements EventLocationService {

    private final EventLocationRepository eventLocationRepository;

    public EventLocationServiceImpl(EventLocationRepository eventLocationRepository) {
        this.eventLocationRepository = eventLocationRepository;
    }

    @Override
    public EventLocation findById(Long id) {
        return this.eventLocationRepository.findById(id).orElseThrow(InvalidEventLocationIdException::new);
    }

    @Override
    public List<EventLocation> listAll() {
        return this.eventLocationRepository.findAll();
    }

    @Override
    public EventLocation create(String name) {

        EventLocation eventLocation = new EventLocation(name);

        return this.eventLocationRepository.save(eventLocation);
    }
}
