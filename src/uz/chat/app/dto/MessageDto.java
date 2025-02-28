package uz.chat.app.dto;

import java.time.LocalDateTime;

public record MessageDto(
        String text,
        LocalDateTime localDateTime,
        String fromEmail,
        String toEmail
) {
}
