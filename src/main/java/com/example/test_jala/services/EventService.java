package com.example.test_jala.services;


import com.example.test_jala.dto.EventDto;
import com.example.test_jala.dto.QueryParamsDto;
import com.example.test_jala.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public interface EventService {
    EventDto createEvent (EventDto eventDto);
    Page<EventDto> getEventsPaged (QueryParamsDto queryParamsDto);
    void reduceEventAvSeats(Long id);
    Event getEventById(Long id);
}
