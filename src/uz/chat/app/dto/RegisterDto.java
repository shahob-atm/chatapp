package uz.chat.app.dto;

import java.time.LocalDateTime;

public record RegisterDto(
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDateTime registrationDate
) {
}
