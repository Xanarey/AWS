package com.timur.AWS.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.timur.AWS.model.Status;
import com.timur.AWS.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
    private String roleName;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.getStatus());
        userDto.setRoleName(user.getRole().name());
        return userDto;
    }
}
