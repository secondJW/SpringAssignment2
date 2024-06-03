package com.sparta.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    @Pattern(
            regexp = "^[a-z0-9]{4,10}$"
    )
    private String username;
    @NotBlank
    private String password;
}
