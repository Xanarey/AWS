package com.timur.AWS.utils;

import com.timur.AWS.dto.UserDto;
import com.timur.AWS.model.Event;
import com.timur.AWS.model.User;
import com.timur.AWS.service.EventService;
import com.timur.AWS.service.UserService;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EntityHelper {
    public static ResponseEntity<UserDto> getUserDtoResponseEntity(User user, UserService userService) {
        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String time = String.valueOf(LocalDateTime.now());
        user.setCreated(time);
        user.setUpdated(time);
        userService.save(user);
        return null;
    }

    public static Event getEvent(Event event, EventService eventService) {
        Event result = new Event();

        if (event.getId() != null)
            result = eventService.getById(event.getId());

        if (event.getFile() != null)
            result.setFile(event.getFile());
        if (event.getUser() != null)
            result.setUser(event.getUser());
        if (event.getCreated() != null)
            result.setCreated(event.getCreated());
        if (event.getUpdated() != null)
            result.setUpdated(event.getUpdated());
        if (event.getStatus() != null)
            result.setStatus(event.getStatus());
        return result;
    }

    public static User getUser(User user, UserService userService) {
        User result = userService.getById(user.getId());

        if (user.getUsername() != null)
            result.setUsername(user.getUsername());
        if (user.getFirstName() != null)
            result.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            result.setLastName(user.getLastName());
        if (user.getEmail() != null)
            result.setEmail(user.getEmail());
        if (user.getStatus() != null)
            result.setStatus(user.getStatus());
        if (user.getRole() != null)
            result.setRole(user.getRole());

        String time = String.valueOf(LocalDateTime.now());
        result.setUpdated(time);
        userService.update(result);
        return result;
    }
}
