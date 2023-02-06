package com.timur.AWS.utils;

import com.timur.AWS.model.Event;
import com.timur.AWS.model.Status;
import com.timur.AWS.model.User;
import com.timur.AWS.service.UserService;
import org.joda.time.LocalDateTime;

public class FileHelper {

    private static User userID;
    private static String time = String.valueOf(LocalDateTime.now());

    public static void updateFileInfoInDB(String fileName, UserService userService) {
        com.timur.AWS.model.File file = new com.timur.AWS.model.File();

        file.setName(fileName);
        file.setStatus(Status.ACTIVE);

        User user = userService.getById(userID.getId());
        Event event = new Event();

        event.setCreated(time);
        event.setUpdated(time);
        event.setStatus(Status.ACTIVE);
        event.setUser(user);
        event.setFile(file);

        user.getEvents().add(event);
        userService.update(user);
    }

    public static void setUser(User user) {
        userID = user;
    }

    public static User getUser() {
        return userID;
    }
}
