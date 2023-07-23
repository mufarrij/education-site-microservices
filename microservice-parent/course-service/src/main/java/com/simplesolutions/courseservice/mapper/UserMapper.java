package com.simplesolutions.courseservice.mapper;

import com.simplesolutions.courseservice.dto.UserRequestDTO;
import com.simplesolutions.courseservice.model.User;

/**
 * Mapper class to map {@link UserRequestDTO}
 * to {@link User}
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
public class UserMapper extends Mapper<UserRequestDTO, User>{
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
