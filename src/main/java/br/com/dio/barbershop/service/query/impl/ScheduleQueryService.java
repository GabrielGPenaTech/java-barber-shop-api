package br.com.dio.barbershop.service.query.impl;

import br.com.dio.barbershop.entity.ScheduleEntity;
import br.com.dio.barbershop.exception.NotFoundException;
import br.com.dio.barbershop.exception.ScheduleInUseException;
import br.com.dio.barbershop.repository.IScheduleRepository;
import br.com.dio.barbershop.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository scheduleRepository;

    @Override
    public ScheduleEntity findById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Schedule not found")
        );
    }

    @Override
    public List<ScheduleEntity> findByMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return scheduleRepository.getAllBetweenDates(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if (scheduleRepository.existsBetweenDates(startAt, endAt)) {
            var message = "Already exists a schedule for this date";
            throw new ScheduleInUseException(message);
        }
    }
}
