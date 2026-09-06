package com.mickle.todoapp.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        String detailedMessage,
        LocalDateTime errorTime
) {
}
