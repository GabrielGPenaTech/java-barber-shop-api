package br.com.dio.barbershop.repository;

import br.com.dio.barbershop.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface IScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("SELECT s FROM ScheduleEntity s WHERE s.startAt >= :startIn AND s.endAt <= :endIn ORDER BY s.startAt, s.endAt")
    List<ScheduleEntity> getAllBetweenDates(@Param("startIn") OffsetDateTime startIn, @Param("endIn") OffsetDateTime endIn);


    @Query("SELECT COUNT(s) > 0 FROM ScheduleEntity s WHERE s.startAt >= :startIn AND s.endAt <= :endIn")
    boolean existsBetweenDates(@Param("startIn") OffsetDateTime startIn, @Param("endIn") OffsetDateTime endIn);

}
