package com.bpss.agriculture.controller;

import com.bpss.agriculture.entity.*;
import com.bpss.agriculture.service.AgricultureService;
import com.bpss.agriculture.service.ScheduleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

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
        List<Schedule> scheduleList = scheduleService.getSchedule();
        int cronSelectId = scheduleService.getCronSelect().getId();

        // 控制配置单
        model.addAttribute("scheduleList", scheduleList);
        // 监测时间间隔可选项
        model.addAttribute("cronList", cronList);
        // 选择的监测时间间隔的 id
        model.addAttribute("cronSelectId", cronSelectId);

        return "config";
    }

    @RequestMapping("/table/{page}")
    public String table(@PathVariable Integer page, Model model){

        Page<Object> startPage = PageHelper.startPage(page, 8);

        List<MotoCmdInfo> allMotoCmdInfo = agricultureService.getAllMotoCmdInfo();
        PageInfo pageInfo = new PageInfo(allMotoCmdInfo);

        int pages = pageInfo.getPages();

        model.addAttribute("allMotoCmdInfo", allMotoCmdInfo);
        model.addAttribute("page", page);
        model.addAttribute("pages", pages);

        return "table";
    }

    @PostMapping("/saveConfig")
    @ResponseBody
    public String saveConfig(@RequestBody List<ScheduleDto> scheduleDtoList){

        scheduleService.changeSchedule(scheduleDtoList);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);

        System.out.println("从前台保存配置成功，保存时间为：" + time);

        for (ScheduleDto scheduleDto : scheduleDtoList) {
            System.out.println(scheduleDto.toString());
        }

        return "200";
    }

    @PostMapping("/saveCronSelect")
    @ResponseBody
    public String saveCronSelect(@RequestBody CronSelect cronSelect){

        Integer cid = cronSelect.getCid();
        scheduleService.changeCronSelect(cid);

        System.out.println("成功保存 Cron 表达式 id 为：" + cid);

        return "200";
    }

}
