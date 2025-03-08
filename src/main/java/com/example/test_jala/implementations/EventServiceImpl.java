package com.example.test_jala.implementations;

import com.example.test_jala.dto.BookingDto;
import com.example.test_jala.exceptions.BadRequestException;
import com.example.test_jala.exceptions.ConflictException;
import com.example.test_jala.exceptions.NotFoundException;
import com.example.test_jala.services.EventService;
import com.example.test_jala.dto.EventDto;
import com.example.test_jala.dto.QueryParamsDto;
import com.example.test_jala.entities.Event;
import com.example.test_jala.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    public static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

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

    @Override
    public void reduceEventAvSeats(Long id) {
        Event event = getEventById(id);
        if(event.getAvailableSeats() < 1) throw new ConflictException("Event has no available seats");
        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);

    }

    @Override
    public void restoreEventAvSeats(Long id) {
        Event event = getEventById(id);
        if(event.getDate().before(new Date())) throw new BadRequestException("Event has already passed");
        event.setAvailableSeats(event.getAvailableSeats() + 1);
        eventRepository.save(event);
    }

    @Override
    public Event getEventById(Long id) {
        logger.info("Event id {}",id );
        Event event = eventRepository.findEventByIdAndStatus(id, 1);
        if(event == null) throw new NotFoundException("Resource not found");
        return event;
    }

    @Override
    public Page<BookingDto> getBookedEventsByUserId(Long userId, QueryParamsDto queryParamsDto) {

        Pageable pageable ;
        if(queryParamsDto.getDirection().equals("ASC")){
            pageable = PageRequest.of(queryParamsDto.getPage(), queryParamsDto.getPageSize(),Sort.by(queryParamsDto.getSort()).ascending());
        } else {
            pageable = PageRequest.of(queryParamsDto.getPage(), queryParamsDto.getPageSize(),Sort.by(queryParamsDto.getSort()).descending());
        }
        return eventRepository.findBookedEventsByUserId(
                                userId,
                                queryParamsDto.getAfterDate(),
                                queryParamsDto.getBeforeDate(),
                        "%"+queryParamsDto.getFilter()+"%",pageable);
    }
}
