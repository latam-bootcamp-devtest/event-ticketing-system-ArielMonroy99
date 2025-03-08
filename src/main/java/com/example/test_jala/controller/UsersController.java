package com.example.test_jala.controller;

import com.example.test_jala.dto.QueryParamsDto;
import com.example.test_jala.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {
    private final EventService eventService;

    public UsersController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{userId}/tickets")
    public ResponseEntity<?> getBookedEventsByUserId(@PathVariable Long userId, @ModelAttribute QueryParamsDto queryParamsDto){
        return ResponseEntity.ok(eventService.getBookedEventsByUserId(userId,queryParamsDto));
    }
}
