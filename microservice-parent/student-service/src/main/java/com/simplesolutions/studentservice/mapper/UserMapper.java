package com.simplesolutions.studentservice.mapper;

import com.simplesolutions.studentservice.dto.UserRequestDTO;
import com.simplesolutions.studentservice.model.User;

/**
 * Mapper class to map {@link UserRequestDTO}
 * to {@link User}
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
public class UserMapper extends Mapper<UserRequestDTO, User> {

    @Override
    public User map(UserRequestDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .roles(userDTO.getRoles())
                .enabled(userDTO.isEnabled())
                .build();
    }
}
