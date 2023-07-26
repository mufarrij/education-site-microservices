package com.simplesolutions.studentservice.service;

import com.simplesolutions.studentservice.dto.UserRequestDTO;
import com.simplesolutions.studentservice.mapper.UserMapper;
import com.simplesolutions.studentservice.model.User;
import com.simplesolutions.studentservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class to hold business logic related {@link User}
 *
 * @author Mufarrij
 * @since 24/7/2023
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
