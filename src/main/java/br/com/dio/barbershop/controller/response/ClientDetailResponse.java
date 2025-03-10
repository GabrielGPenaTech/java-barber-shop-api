package br.com.dio.barbershop.controller.response;

public record ClientDetailResponse(
        Long id,
        String name,
        String email,
        String phone
) {}
