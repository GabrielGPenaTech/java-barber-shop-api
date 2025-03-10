package br.com.dio.barbershop.service.query;

import br.com.dio.barbershop.entity.ScheduleEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface IScheduleQueryService {

    ScheduleEntity findById(final Long id);

    List<ScheduleEntity> findByMonth(final OffsetDateTime startAt, final OffsetDateTime endAt);

    void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt);
}
