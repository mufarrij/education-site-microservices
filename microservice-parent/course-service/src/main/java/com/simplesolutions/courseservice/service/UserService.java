package com.simplesolutions.courseservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simplesolutions.courseservice.dto.UserRequestDTO;
import com.simplesolutions.courseservice.mapper.UserMapper;
import com.simplesolutions.courseservice.model.User;
import com.simplesolutions.courseservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class to hold business logic related {@link User}
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public String addUser(UserRequestDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userMapper.map(userDTO));
        return "user added to system ";
    }
}
