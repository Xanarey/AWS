package com.timur.AWS.rest;

import com.timur.AWS.dto.EventDto;
import com.timur.AWS.model.Event;
import com.timur.AWS.service.EventService;
import com.timur.AWS.service.UserService;
import com.timur.AWS.utils.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events/")
public class EventRestControllerV1 {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventRestControllerV1(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:create')")
    public ResponseEntity<EventDto> registerUser(@RequestBody @Validated Event event) {
        HttpHeaders headers = new HttpHeaders();
        if(event == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.eventService.create(event);

        return new ResponseEntity<>(EventDto.fromEvent(event), headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:readAll')")
    public ResponseEntity<List<EventDto>> getAll() {
        List<Event> events = this.eventService.getAllEvents();

        if (events.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<EventDto> eventDtos = new ArrayList<>();

        for (Event e: events)
            eventDtos.add(EventDto.fromEvent(e));

        return new ResponseEntity<>(eventDtos, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:delete')")
    public ResponseEntity<String> delete(@PathVariable @Validated Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Event event = this.eventService.getById(id);
        if (event == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        this.eventService.delete(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('events:update')")
    public ResponseEntity<EventDto> update(@RequestBody @Validated Event event) {
        if (event == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Event result = EntityHelper.getEvent(event, eventService);
        this.eventService.update(result);

        EventDto eventDto = EventDto.fromEvent(result);

        return new ResponseEntity<>(eventDto, HttpStatus.OK);
    }


}
