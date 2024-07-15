package com.retra.user_service.dto;

import java.sql.Date;

public record UserSetDTO(
    String firstName,
    String lastName,
    String email,
    String password,
    Date dob
) {}
