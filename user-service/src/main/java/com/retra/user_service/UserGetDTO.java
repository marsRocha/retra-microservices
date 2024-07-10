package com.retra.user_service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGetDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dob;
}
