package com.simplesolutions.studentservice.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.simplesolutions.studentservice.dto.UserRequestDTO;
import com.simplesolutions.studentservice.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * This class is created only for initial admin user data loading (one-time)
 * purposes. set {app.data.loader.enabled} property in application.properties to
 * true only for initial data loading. by executing this user data loader admin user
 * created with username=admin, password=admin with all authorities
 *
 * @author Mufarrij
 * @since
 */
@ConditionalOnProperty(
        prefix = "app.data.loader",
        value = "enabled",
        matchIfMissing = false)
@Component
@RequiredArgsConstructor
public class InitUserDataLoader implements ApplicationRunner {

    private final UserService userService;

    public void run(ApplicationArguments args) {
        userService.addUser(new UserRequestDTO().builder()
                .username("admin")
                .password("admin")
                .roles("ROLE_ADMIN")
                .enabled(true).build());
    }
}