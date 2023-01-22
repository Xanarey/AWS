package com.timur.AWS.service;

import com.timur.AWS.model.Event;

import java.util.List;

public interface EventService {

    Event getById(Long id);

    Event create(Event event);

    List<Event> getAllEvents();

    void delete(Event event);

    void update(Event event);

}
