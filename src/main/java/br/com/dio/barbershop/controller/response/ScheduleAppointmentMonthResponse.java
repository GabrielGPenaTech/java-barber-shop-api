package br.com.dio.barbershop.controller.response;

import java.util.List;

public record ScheduleAppointmentMonthResponse(
        int year,
        int month,
        List<ClientScheduleAppointmentResponse> appointments
) {
}
