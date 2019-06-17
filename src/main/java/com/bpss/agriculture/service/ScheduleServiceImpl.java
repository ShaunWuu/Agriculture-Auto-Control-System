package com.bpss.agriculture.service;

import com.bpss.agriculture.entity.CronVo;
import com.bpss.agriculture.entity.Schedule;
import com.bpss.agriculture.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rocky Wu
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public List<CronVo> getAllCron() {
        return scheduleMapper.getAllCron();
    }

    @Override
    public CronVo getCron(int id) {
        return scheduleMapper.getCron(id);
    }

    @Override
    public Schedule getSchedule(int id) {
        return scheduleMapper.getSchedule(id);
    }

    @Override
    public void changeSchedule(Schedule schedule) {
        scheduleMapper.changeSchedule(schedule);
    }
}
