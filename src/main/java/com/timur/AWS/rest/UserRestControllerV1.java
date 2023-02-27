package com.timur.AWS.rest;

import com.timur.AWS.dto.UserDto;
import com.timur.AWS.model.Event;
import com.timur.AWS.model.Status;
import com.timur.AWS.model.User;
import com.timur.AWS.service.UserService;
import com.timur.AWS.utils.EntityHelper;
import org.joda.time.LocalDateTime;
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
@RequestMapping("/api/v1/users/")
public class UserRestControllerV1 {

    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<UserDto> getById(@PathVariable @Validated Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = this.userService.getById(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('developers:readAll')")
    public ResponseEntity<List<UserDto>> getAllCustomers() {
        List<User> users = this.userService.getAllUsers();

        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<UserDto> userDtos = new ArrayList<>();

        for (User u: users)
            userDtos.add(UserDto.fromUser(u));

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('developers:update')")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Validated User user) {
        HttpHeaders headers = new HttpHeaders();
        if (user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User result = EntityHelper.getUser(user, userService);

        return new ResponseEntity<>(UserDto.fromUser(result), headers, HttpStatus.OK);
    }



    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('developers:delete')")
    public ResponseEntity<UserDto> deleteUser(@PathVariable @Validated Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = userService.getById(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        this.userService.delete(user);

        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }

    @PatchMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('developers:deleteNotActive')")
    public ResponseEntity<UserDto> deleteUserNotActive(@PathVariable @Validated Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = userService.getById(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        user.setStatus(Status.NOT_ACTIVE);
        for (Event e: user.getEvents()) {
            e.setStatus(Status.NOT_ACTIVE);
            e.getFile().setStatus(Status.NOT_ACTIVE);
        }

        this.userService.update(user);

        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }
}
