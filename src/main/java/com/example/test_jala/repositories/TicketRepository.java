package com.example.test_jala.repositories;

import com.example.test_jala.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket findTicketByIdAndStatus(Long id, Integer status);
}
