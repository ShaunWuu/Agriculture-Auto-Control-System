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

        List<Cron> cronList = scheduleService.getAllCron();
        List<Schedule> schedule = scheduleService.getSchedule();
        int cronSelectId = scheduleService.getCronSelect().getId();

        // 控制配置单
        model.addAttribute("schedule", schedule);
        // 监测时间间隔可选项
        model.addAttribute("cronList", cronList);
        // 选择的监测时间间隔的 id
        model.addAttribute("cronSelectId", cronSelectId);

        return "config";
    }

    @RequestMapping("/table")
    public String table(Model model){

        List<MotoCmdInfo> allMotoCmdInfo = agricultureService.getAllMotoCmdInfo();

        model.addAttribute("allMotoCmdInfo", allMotoCmdInfo);

        return "table";
    }

}
