package com.bpss.agriculture.controller;

import com.bpss.agriculture.entity.GreenHouseInfo;
import com.bpss.agriculture.entity.MotoCmdInfo;
import com.bpss.agriculture.entity.MotoInfo;
import com.bpss.agriculture.service.AgricultureService;
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
    public String config(){
        return "config";
    }

    @RequestMapping("/table")
    public String table(){
        return "table";
    }

}
