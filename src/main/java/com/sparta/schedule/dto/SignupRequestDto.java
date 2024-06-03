package com.sparta.schedule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String username;
    @Pattern(
            regexp = "^(?=.+[a-z])(?=.+[A-Z])(?=.+\\d)[a-zA-Z\\d]{8,15}$"

    )
    private String password;
    @Email
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
