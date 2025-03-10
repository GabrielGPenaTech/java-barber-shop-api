package br.com.dio.barbershop.controller.response;

public record ClientScheduleAppointmentDetailResponse(
        Long id,
        String name,
        String email,
        String phone
) {}

