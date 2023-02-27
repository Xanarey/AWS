package com.timur.AWS.service.impl;

import com.timur.AWS.model.Event;
import com.timur.AWS.model.User;
import com.timur.AWS.repository.EventRepository;
import com.timur.AWS.service.EventService;
import com.timur.AWS.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserService userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    @Override
    public Event create(Event event) {
        log.info("IN EventServiceImpl create");
        User user = userService.getById(event.getUser().getId());
        event.setUser(user);
        return eventRepository.save(event);
    }

    @Override
    public Event getById(Long id) {
        log.info("IN EventServiceImpl getById {}", id);
        return eventRepository.findById(id).orElse(new Event());
    }

    @Override
    public List<Event> getAllEvents() {
        log.info("IN EventServiceImpl readAllEvents");
        return eventRepository.findAll();
    }

    @Override
    public void delete(Event event) {
        log.info("IN EventServiceImpl delete {}", event);
        eventRepository.delete(event);
    }

    @Override
    public void update(Event event) {
        log.info("IN EventServiceImpl update {}", event);
        eventRepository.save(event);
    }
}
