package com.example.events.web;


import com.example.events.model.Event;
import com.example.events.model.EventType;
import com.example.events.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class EventsController {

    private final EventService eventService;

    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping({"/", "/events"})
    public String showEvents(@RequestParam(required = false) Double price,
                             @RequestParam(required = false) EventType type,
                             Model model) {
        List<Event> events;
        if (price == null && type == null) {
            events = this.eventService.listAllEvents();
        } else {
            events = this.eventService.listEventsWithPriceLessThanAndType(price, type);
        }

        model.addAttribute("events", events);

        return "list.html";
    }

    @GetMapping("/events/add")
    public String showAdd() {

        return "form.html";
    }

    @GetMapping("/events/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Event event = this.eventService.findById(id);

        model.addAttribute("event", event);

        return "form.html";
    }

    @PostMapping("/events")
    public String create(@RequestParam String name,
                         @RequestParam String description,
                         @RequestParam Double price,
                         @RequestParam EventType type,
                         @RequestParam Long location) {
        this.eventService.create(name, description, price, type, location);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam Double price,
                         @RequestParam EventType type,
                         @RequestParam Long location) {
        this.eventService.update(id, name, description, price, type, location);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.eventService.delete(id);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/like")
    public String like(@PathVariable Long id) {
        this.eventService.like(id);
        return "redirect:/events";
    }
}
