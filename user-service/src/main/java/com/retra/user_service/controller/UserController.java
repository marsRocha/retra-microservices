package com.retra.user_service.controller;

import com.retra.user_service.dto.UserSetDTO;
import com.retra.user_service.service.UserService;
import com.retra.user_service.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private static final String USER_CREATE_LOG = "User was successfully created";
    private static final String USER_EDIT_LOG = "User {} was edited";
    private static final String USER_DELETE_LOG = "User {} was deleted";

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserSetDTO userSetDTO) {
        userService.createUser(userSetDTO);
        log.info(USER_CREATE_LOG);
    }

    @PutMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void editUser(@RequestBody UserSetDTO userSetDTO, @PathVariable Long userId) {
        userService.editUser(userSetDTO, userId);
        log.info(USER_EDIT_LOG, userId);
    }

    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        log.info(USER_DELETE_LOG, userId);
    }
}
