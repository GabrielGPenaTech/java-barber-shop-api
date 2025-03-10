package br.com.dio.barbershop.service;

import br.com.dio.barbershop.entity.ClientEntity;

public interface IClientService {

    ClientEntity save(final ClientEntity client);

    ClientEntity update(final ClientEntity client);

    void delete(final long id);
}
