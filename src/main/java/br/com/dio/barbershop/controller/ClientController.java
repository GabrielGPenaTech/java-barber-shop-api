package br.com.dio.barbershop.controller;

import br.com.dio.barbershop.controller.request.SaveClientRequest;
import br.com.dio.barbershop.controller.request.UpdateClientRequest;
import br.com.dio.barbershop.controller.response.ClientDetailResponse;
import br.com.dio.barbershop.controller.response.ListClientResponse;
import br.com.dio.barbershop.controller.response.SaveClientResponse;
import br.com.dio.barbershop.controller.response.UpdateClientResponse;
import br.com.dio.barbershop.mapper.IClientMapper;
import br.com.dio.barbershop.service.IClientService;
import br.com.dio.barbershop.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService clientService;
    private final IClientQueryService clientQueryService;
    private final IClientMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaveClientResponse saveClient(@RequestBody @Valid final SaveClientRequest request) {
        var entity = mapper.toEntity(request);
        clientService.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    public UpdateClientResponse updateClient(@PathVariable final long id ,@RequestBody @Valid final UpdateClientRequest request) {
        var entity = mapper.toEntity(id, request);
        clientService.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable final Long id) {
        clientService.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findClientById(@PathVariable final Long id) {
        var entity = clientQueryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListClientResponse> list() {
        var entities = clientQueryService.list();
        return mapper.toListResponse(entities);
    }
}
