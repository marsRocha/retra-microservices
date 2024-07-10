package com.retra.user_service;


import java.sql.Date;
import java.time.LocalDate;

public record UserPostDTO (
    String firstName,
    String lastName,
    String email,
    String password,
    Date dob
) {}
