package com.retra.user_service.controller;

import com.retra.user_service.dto.AuthResponse;
import com.retra.user_service.dto.UserSetDTO;
import com.retra.user_service.exception.EmailAlreadyUsedException;
import com.retra.user_service.model.Role;
import com.retra.user_service.service.AuthService;
import com.retra.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    private static final String USER_CREATE_LOG = "User was successfully created";
    private static final String ADMIN_CREATE_LOG = "Admin was successfully created";

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> createAuthToken(@RequestBody UserSetDTO userSetDTO) {
        return new ResponseEntity<>(authService.authenticate(userSetDTO), HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserSetDTO userSetDTO) throws EmailAlreadyUsedException {
        userService.createUser(userSetDTO, Role.USER);
        log.info(USER_CREATE_LOG);
    }

    @PostMapping(path = "/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAdmin(@RequestBody UserSetDTO userSetDTO) throws EmailAlreadyUsedException {
        userService.createUser(userSetDTO, Role.ADMIN);
        log.info(ADMIN_CREATE_LOG);
    }
}
