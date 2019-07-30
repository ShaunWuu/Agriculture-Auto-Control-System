package com.bpss.agriculture.service;

import com.bpss.agriculture.entity.Cron;
import com.bpss.agriculture.entity.Schedule;
import com.bpss.agriculture.entity.ScheduleDto;
import com.bpss.agriculture.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rocky Wu
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public List<Cron> getAllCron() {
        return scheduleMapper.getAllCron();
    }

    @Override
    public Cron getCronSelect() {
        return scheduleMapper.getCronSelect();
    }

    @Override
    public List<Schedule> getSchedule() {
        return scheduleMapper.getSchedule();
    }

    @Override
    public void changeSchedule(List<ScheduleDto> scheduleDtoList) {
        for (ScheduleDto dto : scheduleDtoList) {
            scheduleMapper.changeSchedule(dto);
        }
    }

    @Override
    public void changeCronSelect(Integer id) {
        scheduleMapper.changeCronSelect(id);
    }
}
