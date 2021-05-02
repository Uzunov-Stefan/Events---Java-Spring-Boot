package com.example.events.service;

import com.example.events.model.EventLocation;
import com.example.events.model.exceptions.InvalidEventLocationIdException;

import java.util.List;

public interface EventLocationService {

    EventLocation findById(Long id);

    List<EventLocation> listAll();

    EventLocation create(String name);
}
