package com.bpss.agriculture.service;

import com.bpss.agriculture.entity.CronVo;
import com.bpss.agriculture.entity.Schedule;

import java.util.List;

/**
 * @author Rocky Wu
 */
public interface ScheduleService {

    List<CronVo> getAllCron();

    CronVo getCron(int id);

    Schedule getSchedule(int id);

    void changeSchedule(Schedule schedule);

}
