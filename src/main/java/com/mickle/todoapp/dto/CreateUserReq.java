package com.mickle.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserReq(
        @NotBlank
        @Size(min = 3, max = 50)
        String username
) {
}
