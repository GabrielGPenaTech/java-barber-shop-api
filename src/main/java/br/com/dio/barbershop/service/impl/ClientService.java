package br.com.dio.barbershop.service.impl;

import br.com.dio.barbershop.entity.ClientEntity;
import br.com.dio.barbershop.repository.IClientRepository;
import br.com.dio.barbershop.service.IClientService;
import br.com.dio.barbershop.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;
    private final IClientQueryService clientQueryService;

    @Override
    public ClientEntity save(ClientEntity client) {
        clientQueryService.verifyEmail(client.getEmail());
        clientQueryService.verifyPhoneNumber(client.getPhone());

        return clientRepository.save(client);
    }

    @Override
    public ClientEntity update(ClientEntity client) {
        clientQueryService.verifyEmail(client.getId(), client.getEmail());
        clientQueryService.verifyPhoneNumber(client.getId(), client.getPhone());

        var saved = clientQueryService.findById(client.getId());
        saved.setName(client.getName());
        saved.setPhone(client.getPhone());
        saved.setEmail(client.getEmail());
        return clientRepository.save(saved);
    }

    @Override
    public void delete(long id) {
        clientQueryService.findById(id);
        clientRepository.deleteById(id);
    }
}
