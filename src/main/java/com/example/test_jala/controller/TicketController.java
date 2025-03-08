package com.example.test_jala.controller;

import com.example.test_jala.dto.TicketDto;
import com.example.test_jala.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    public ResponseEntity<?> bookTicker(@RequestBody TicketDto ticketDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.bookTicket(ticketDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id){
        ticketService.cancelBooking(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
