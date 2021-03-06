package com.bpss.agriculture.service;

import com.bpss.agriculture.entity.Cron;
import com.bpss.agriculture.entity.Schedule;
import com.bpss.agriculture.entity.ScheduleDto;

import java.util.HashMap;
import java.util.List;

/**
 * @author Rocky Wu
 */
public interface ScheduleService {

    List<Cron> getAllCron();

    Cron getCronSelect();

    List<Schedule> getSchedule();

    void changeSchedule(List<ScheduleDto> scheduleDtoList);

    void changeCronSelect(Integer id);
}
