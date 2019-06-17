package com.bpss.agriculture.controller;

import com.bpss.agriculture.entity.*;
import com.bpss.agriculture.service.AgricultureService;
import com.bpss.agriculture.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Rocky Wu
 */
@Controller
public class MonitorController {

    @Autowired
    AgricultureService agricultureService;

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = {"/", "/dashboard"})
    public String monitor(Model model){
        GreenHouseInfo greenHouseInfo = agricultureService.getGreenHouseInfo();
        MotoInfo motoInfo = agricultureService.getMotoInfo();
        List<MotoCmdInfo> latestMotoCmdInfo = agricultureService.getLatestMotoCmdInfo();

        // 大棚状态信息
        model.addAttribute("greenHouseInfo", greenHouseInfo);
        // 设备状态信息
        model.addAttribute("motoInfo", motoInfo);
        // 最新指令
        model.addAttribute("latestMotoCmdInfo", latestMotoCmdInfo);

        return "dashboard";
    }

    @RequestMapping("/config")
    public String config(Model model){
        List<CronVo> cronList = scheduleService.getAllCron();
        Schedule schedule = scheduleService.getSchedule(1);
        int cid = schedule.getCid();
        String cronNote = scheduleService.getCron(cid).getNote();

        model.addAttribute(cronList);
        model.addAttribute(schedule);
        model.addAttribute(cronNote);

        return "config";
    }

    @RequestMapping("/table")
    public String table(){
        return "table";
    }

}
