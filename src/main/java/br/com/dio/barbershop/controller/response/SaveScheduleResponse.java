package br.com.dio.barbershop.controller.response;

import java.time.OffsetDateTime;

public record SaveScheduleResponse(
        Long id,
        OffsetDateTime startAt,
        OffsetDateTime endAt,
        Long clientId
) {
}
