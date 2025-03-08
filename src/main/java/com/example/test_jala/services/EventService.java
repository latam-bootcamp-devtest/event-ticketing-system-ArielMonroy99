package com.example.test_jala.services;


import com.example.test_jala.dto.BookingDto;
import com.example.test_jala.dto.EventDto;
import com.example.test_jala.dto.QueryParamsDto;
import com.example.test_jala.entities.Event;
import org.springframework.data.domain.Page;

public interface EventService {
    EventDto createEvent (EventDto eventDto);
    Page<EventDto> getEventsPaged (QueryParamsDto queryParamsDto);
    void reduceEventAvSeats(Long id);
    void restoreEventAvSeats(Long id);
    Event getEventById(Long id);
    Page<BookingDto> getBookedEventsByUserId(Long userId, QueryParamsDto queryParamsDto);
}
