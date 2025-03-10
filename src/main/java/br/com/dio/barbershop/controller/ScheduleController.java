package br.com.dio.barbershop.controller;

import br.com.dio.barbershop.controller.request.SaveScheduleRequest;
import br.com.dio.barbershop.controller.response.SaveScheduleResponse;
import br.com.dio.barbershop.controller.response.ScheduleAppointmentMonthResponse;
import br.com.dio.barbershop.mapper.IScheduleMapper;
import br.com.dio.barbershop.service.IScheduleService;
import br.com.dio.barbershop.service.query.IScheduleQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.time.ZoneOffset;

@RestController
@RequestMapping("schedules")
@AllArgsConstructor
public class ScheduleController {

    private final IScheduleService scheduleService;
    private final IScheduleQueryService scheduleQueryService;
    private final IScheduleMapper mapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request){
        var entity = mapper.toEntity(request);
        scheduleService.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable final Long id) {
        scheduleService.delete(id);
    }

    @GetMapping("{year}/{month}")
    ScheduleAppointmentMonthResponse listMonth(@PathVariable final int year, @PathVariable final int month) {
        var yearMonth = YearMonth.of(year, month);

        var startAt = yearMonth.atDay(1)
                .atTime(0, 0, 0, 0)
                .atOffset(ZoneOffset.UTC);

        var endAt = yearMonth.atEndOfMonth()
                .atTime(23, 59, 59, 999_999_999)
                .atOffset(ZoneOffset.UTC);

        var entities = scheduleQueryService.findByMonth(startAt, endAt);
        return mapper.toMonthResponse(year, month, entities);
    }
}
