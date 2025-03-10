package br.com.dio.barbershop.service.query.impl;

import br.com.dio.barbershop.entity.ClientEntity;
import br.com.dio.barbershop.exception.EmailAlreadyExistsException;
import br.com.dio.barbershop.exception.NotFoundException;
import br.com.dio.barbershop.exception.PhoneAlreadyExistsException;
import br.com.dio.barbershop.repository.IClientRepository;
import br.com.dio.barbershop.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final IClientRepository clientRepository;


    @Override
    public ClientEntity findById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new NotFoundException("client not found with id: " + id)
        );
    }

    @Override
    public List<ClientEntity> list() {
        return clientRepository.findAll();
    }

    @Override
    public void verifyPhoneNumber(String phoneNumber) {
        if(clientRepository.existsByPhone(phoneNumber)) {
            var message = String.format("Phone number %s is already in use", phoneNumber);
            throw new PhoneAlreadyExistsException(message);
        }
    }

    @Override
    public void verifyPhoneNumber(long id, String phoneNumber) {
        var optional = clientRepository.findByPhone(phoneNumber);
        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(), phoneNumber)) {
            var message = String.format("Phone number %s is already in use", phoneNumber);
            throw new PhoneAlreadyExistsException(message);
        }
    }

    @Override
    public void verifyEmail(String email) {
        if(clientRepository.existsByPhone(email)) {
            var message = String.format("E-mail %s is already in use", email);
            throw new EmailAlreadyExistsException(message);
        }
    }

    @Override
    public void verifyEmail(long id, String email) {
        var optional = clientRepository.findByEmail(email);
        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(), email)) {
            var message = String.format("E-mail %s is already in use", email);
            throw new PhoneAlreadyExistsException(message);
        }
    }
}
