package br.com.dio.barbershop.service;

import br.com.dio.barbershop.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity schedule);

    void delete(final long id);
}
