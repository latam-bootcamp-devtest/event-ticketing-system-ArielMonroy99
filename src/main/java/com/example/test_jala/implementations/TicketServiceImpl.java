package com.example.test_jala.implementations;

import com.example.test_jala.dto.TicketDto;
import com.example.test_jala.entities.Ticket;
import com.example.test_jala.exceptions.NotFoundException;
import com.example.test_jala.repositories.TicketRepository;
import com.example.test_jala.services.EventService;
import com.example.test_jala.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketServiceImpl implements TicketService {

    private final EventService eventService;
    private final TicketRepository ticketRepository;
    public static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    public TicketServiceImpl(EventService eventService, TicketRepository ticketRepository) {
        this.eventService = eventService;
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TicketDto bookTicket(TicketDto ticketDto) {
        eventService.reduceEventAvSeats(ticketDto.getEventId());
        Ticket ticket = new Ticket();
        ticket.setEventId(ticketDto.getEventId());
        ticket.setUserId(ticketDto.getUserId());
        ticket.setStatus(1);
        ticketRepository.save(ticket);
        ticketDto.setId(ticket.getId());
        return ticketDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void cancelBooking(Long id) {
        Ticket ticket = ticketRepository.findTicketByIdAndStatus(id,1);
        if(ticket == null) throw new NotFoundException("Ticket not found");
        eventService.restoreEventAvSeats(ticket.getEventId());
        ticket.setStatus(0);
        ticketRepository.save(ticket);
    }
}
