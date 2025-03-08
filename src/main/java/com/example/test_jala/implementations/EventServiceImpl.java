package com.example.test_jala.implementations;

import com.example.test_jala.services.EventService;
import com.example.test_jala.dto.EventDto;
import com.example.test_jala.dto.QueryParamsDto;
import com.example.test_jala.entities.Event;
import com.example.test_jala.repositories.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDto createEvent(EventDto eventDto) {
        if(eventDto.getDate().before(new Date()))
            throw new RuntimeException("Event date must be after now");
        if(eventDto.getAvailableSeats() <= 0)
            throw new RuntimeException("Event available seats must be greater than zero");

        Event event = new Event();
        event.setDate(eventDto.getDate());
        event.setName(eventDto.getName());
        event.setAvailableSeats(eventDto.getAvailableSeats());
        event.setStatus(1);
        event = eventRepository.save(event);

        eventDto.setId(event.getId());
        return eventDto;
    }

    @Override
    public Page<EventDto> getEventsPaged(QueryParamsDto queryParamsDto) {
        Pageable page = PageRequest.of(queryParamsDto.getPage(), queryParamsDto.getPageSize(), Sort.by("date").ascending());
        Page<Event> eventPage =  eventRepository.findEvents(page);
        return eventPage.map(event -> {
            EventDto eventDto = new EventDto();
            eventDto.setId(event.getId());
            eventDto.setName(event.getName());
            eventDto.setAvailableSeats(event.getAvailableSeats());
            eventDto.setDate(event.getDate());
            return eventDto;
        });
    }
}
