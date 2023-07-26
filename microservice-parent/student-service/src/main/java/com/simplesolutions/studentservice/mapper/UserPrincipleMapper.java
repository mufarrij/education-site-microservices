package com.simplesolutions.studentservice.mapper;

import com.simplesolutions.studentservice.model.User;
import com.simplesolutions.studentservice.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class to map {@link User}
 * to {@link UserPrincipal}
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
public class UserPrincipleMapper extends Mapper<User, UserPrincipal> {
    @Override
    public UserPrincipal map(User user) {

        List<SimpleGrantedAuthority> authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return UserPrincipal.builder().
                username(user.getUsername()).
                password(user.getPassword()).
                enabled(user.isEnabled()).
                authorities(authorities).
                build();
    }
}
