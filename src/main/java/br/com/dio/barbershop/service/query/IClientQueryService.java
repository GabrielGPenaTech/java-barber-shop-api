package br.com.dio.barbershop.service.query;

import br.com.dio.barbershop.entity.ClientEntity;

import java.util.List;

public interface IClientQueryService {

    ClientEntity findById(final Long id);

    List<ClientEntity> list();

    void verifyPhoneNumber(final String phoneNumber);

    void verifyPhoneNumber(final long id, final String phoneNumber);

    void verifyEmail(final String email);

    void verifyEmail(final long id, final String email);

}
