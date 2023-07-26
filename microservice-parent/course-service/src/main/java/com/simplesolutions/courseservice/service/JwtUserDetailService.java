package com.simplesolutions.courseservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simplesolutions.courseservice.mapper.UserPrincipleMapper;
import com.simplesolutions.courseservice.model.User;
import com.simplesolutions.courseservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implements {@link UserDetailsService}, utilized to fetch
 * user details from the data source using the username
 *
 * @author Mufarrij
 * @since 22/7/2023
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
