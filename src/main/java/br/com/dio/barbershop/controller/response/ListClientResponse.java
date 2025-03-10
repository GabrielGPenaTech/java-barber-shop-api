package br.com.dio.barbershop.controller.response;

public record ListClientResponse(
        Long id,
        String name,
        String email,
        String phone
) {
}
