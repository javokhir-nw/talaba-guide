package dev.javokhir.talabaguide.exceptions;

import lombok.Builder;

import java.util.Date;

@Builder
public record ErrorResponse(
        Date date,
        String status,
        Integer error,
        String message,
        String path
) {
}
