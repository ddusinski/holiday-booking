package com.dusinski.controller;

import com.dusinski.model.Event;
import com.dusinski.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping
    public String sayHello() {
        return "welcome to events";
    }

    @GetMapping(path = "/get")
    public Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping(path = "/get/{id}")
    public String getEvent(@PathVariable Long id) {
        return eventRepository.findById(id).toString();
    }

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event newEvent) {
        return eventRepository.save(newEvent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable Long id) {
        Event eventToRemove = eventRepository.findById(id).orElseThrow();
        eventToRemove.setUser(null);
        eventRepository.deleteById(id);
    }
}
