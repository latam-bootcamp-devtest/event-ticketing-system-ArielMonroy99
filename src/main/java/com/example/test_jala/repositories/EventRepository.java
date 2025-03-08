package com.example.test_jala.repositories;

import com.example.test_jala.dto.BookingDto;
import com.example.test_jala.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;


public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("select e from Event e where e.date > CURRENT_DATE ")
    Page<Event> findEvents(Pageable pageable);
    Event findEventByIdAndStatus(Long id,Integer status);

    @Query(value = "Select t.user_id as userId,t.event_id as eventId, t.id as ticket_id, e.name as name " +
            "from events e join ticket t  on t.event_id = e.event_id " +
            "where t.user_id = :userId and t.status = 1 " +
            "and e.date between :afterDate and :beforeDate and e.name ilike :filter" , nativeQuery = true)
    Page<BookingDto> findBookedEventsByUserId(Long userId, Date afterDate, Date beforeDate, String filter, Pageable pageable);
}
