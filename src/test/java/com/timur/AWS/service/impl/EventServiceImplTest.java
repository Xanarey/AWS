package com.timur.AWS.service.impl;

import com.timur.AWS.model.Event;
import com.timur.AWS.model.Status;
import com.timur.AWS.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;
    private final EventServiceImpl eventService;

    public EventServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.eventService = new EventServiceImpl(eventRepository, userService);
    }

    @Test
    void create() {
        Event event = new Event();
        event.setStatus(Status.ACTIVE);

        Mockito.when(eventRepository.save(event)).thenReturn(event);

        Event s = eventService.create(event);

        assertNotNull(s);
        assertEquals(Status.ACTIVE, s.getStatus());
    }

    @Test
    void getById() {
        Event event = new Event();
        event.setCreated("3000-01-19 17:06:56");

        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event s = eventService.getById(1L);

        assertNotNull(s);
        assertEquals("3000-01-19 17:06:56", s.getCreated());
    }

    @Test
    void getAllEvents() {
        List<Event> events = new ArrayList<>();
        Event event1 = new Event();
        event1.setCreated("3000-01-19 17:06:56");
        Event event2 = new Event();
        event2.setCreated("2000-01-19 17:06:56");
        events.add(event1);
        events.add(event2);

        Mockito.when(eventRepository.findAll()).thenReturn(events);

        List<Event> eventServiceList = eventService.getAllEvents();

        assertNotNull(eventServiceList);
        assertEquals(2, eventServiceList.size());
        assertEquals("2000-01-19 17:06:56", eventServiceList.get(1).getCreated());
        assertEquals("3000-01-19 17:06:56", eventServiceList.get(0).getCreated());
    }

    @Test
    void deleteById() {
        Mockito.doNothing().when(eventRepository).delete(new Event());
        eventService.delete(new Event());
        Mockito.verify(eventRepository).delete(new Event());
    }

    @Test
    void update() {
        Event event = new Event();
        event.setCreated("3000-01-19 17:06:56");

        Mockito.when(eventRepository.save(event)).thenReturn(event);

        event.setCreated("2000-01-19 17:06:56");

        eventService.update(event);

        assertNotNull(event);
        assertEquals("2000-01-19 17:06:56", event.getCreated());
    }
}