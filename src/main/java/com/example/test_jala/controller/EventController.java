package com.example.test_jala.controller;

import com.example.test_jala.services.EventService;
import com.example.test_jala.dto.EventDto;
import com.example.test_jala.dto.QueryParamsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventDto));
    }

    @GetMapping()
    public ResponseEntity<?> getEvents(@ModelAttribute QueryParamsDto queryParamsDto){
        return ResponseEntity.ok(eventService.getEventsPaged(queryParamsDto));
    }
}
