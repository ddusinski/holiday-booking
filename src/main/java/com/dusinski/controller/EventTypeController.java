package com.dusinski.controller;

import com.dusinski.model.EventType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/event-types")
public class EventTypeController {
    @GetMapping(path = "/get")
    public Iterable<EventType> getEventTypes() {
        return Arrays.asList(EventType.values());
    }
}
