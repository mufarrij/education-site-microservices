package com.simplesolutions.studentservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simplesolutions.studentservice.mapper.UserPrincipleMapper;
import com.simplesolutions.studentservice.model.User;
import com.simplesolutions.studentservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implements {@link UserDetailsService}, utilized to fetch
 * user details from the data source using the username
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserPrincipleMapper userPrincipleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return userPrincipleMapper.map(user);
    }
}
