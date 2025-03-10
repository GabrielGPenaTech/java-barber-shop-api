package br.com.dio.barbershop.controller.response;

public record UpdateClientResponse(
        Long id,
        String name,
        String email,
        String phone
) {
}
