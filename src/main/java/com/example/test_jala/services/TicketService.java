package com.example.test_jala.services;

import com.example.test_jala.dto.TicketDto;

public interface TicketService {
    TicketDto bookTicket(TicketDto ticketDto);
    void cancelBooking(Long id);
}
