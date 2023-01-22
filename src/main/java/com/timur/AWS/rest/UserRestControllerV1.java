package com.timur.AWS.rest;

import com.timur.AWS.dto.UserDto;
import com.timur.AWS.model.Status;
import com.timur.AWS.model.User;
import com.timur.AWS.service.UserService;
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

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('developers:register')")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Validated User user) {
        HttpHeaders headers = new HttpHeaders();
        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String time = String.valueOf(LocalDateTime.now());
        user.setCreated(time);
        user.setUpdated(time);
        this.userService.save(user);

        return new ResponseEntity<>(UserDto.fromUser(user), headers, HttpStatus.CREATED);
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
        this.userService.update(result);

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
        user.setStatus(Status.NOT_ACTIVE);
        this.userService.delete(user);

        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }
}
