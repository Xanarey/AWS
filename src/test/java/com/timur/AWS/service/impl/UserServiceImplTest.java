package com.timur.AWS.service.impl;

import com.timur.AWS.model.User;
import com.timur.AWS.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private final UserServiceImpl userService;

    public UserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserServiceImpl(userRepository);
    }

    @Test
    void getById() {
        User user = new User();
        user.setFirstName("Ann");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User userR = userService.getById(1L);

        assertNotNull(userR);
        assertEquals("Ann", userR.getFirstName());
    }

    @Test
    void getAllUsers() {
        List<User> userRepoList = new ArrayList<>();
        User one = new User();
        one.setFirstName("Carl");
        User two = new User();
        two.setFirstName("Tom");
        userRepoList.add(one);
        userRepoList.add(two);

        Mockito.when(userRepository.findAll()).thenReturn(userRepoList);

        List<User> userServiceList = userService.getAllUsers();

        assertNotNull(userServiceList);
        assertEquals(2, userServiceList.size());
        assertEquals("Tom", userServiceList.get(1).getFirstName());
    }

    @Test
    void save() {
        User a = new User();
        a.setEmail("ann@gmail.com");

        Mockito.when(userRepository.save(a)).thenReturn(a);

        User s = userService.save(a);

        assertNotNull(s);
        assertEquals("ann@gmail.com", s.getEmail());
    }

    @Test
    void deleteById() {
        Mockito.doNothing().when(userRepository).delete(new User());
        userService.delete(new User());
        Mockito.verify(userRepository).delete(new User());
    }

    @Test
    void update() {
        User a = new User();
        a.setEmail("ann@gmail.com");
        Mockito.when(userRepository.save(a)).thenReturn(a);

        a.setEmail("test");

        userService.update(a);

        assertNotNull(a);
        assertEquals("test", a.getEmail());
    }
}