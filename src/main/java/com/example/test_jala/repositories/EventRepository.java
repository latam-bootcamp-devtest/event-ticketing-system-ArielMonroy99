package com.example.test_jala.repositories;

import com.example.test_jala.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("select e from Event e where e.date > CURRENT_DATE ")
    Page<Event> findEvents(Pageable pageable);
}
