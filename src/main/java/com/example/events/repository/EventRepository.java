package com.example.events.repository;

import com.example.events.model.Event;
import com.example.events.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByIdLike(Long id);
    List<Event> findByPriceLessThan(Double price);
    List<Event> findByTypeLike(EventType type);
    List<Event> findByPriceLessThanAndTypeLike(Double price, EventType type);
}
