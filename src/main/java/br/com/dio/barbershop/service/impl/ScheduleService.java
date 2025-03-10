package br.com.dio.barbershop.service.impl;

import br.com.dio.barbershop.entity.ScheduleEntity;
import br.com.dio.barbershop.repository.IScheduleRepository;
import br.com.dio.barbershop.service.IScheduleService;
import br.com.dio.barbershop.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleQueryService scheduleQueryService;
    private final IScheduleRepository scheduleRepository;

    @Override
    public ScheduleEntity save(ScheduleEntity schedule) {
        scheduleQueryService.verifyIfScheduleExists(schedule.getStartAt(), schedule.getEndAt());

        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(long id) {
        scheduleQueryService.findById(id);
        scheduleRepository.deleteById(id);
    }
}
