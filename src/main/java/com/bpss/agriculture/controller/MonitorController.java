package com.bpss.agriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rocky Wu
 */
@Controller
public class MonitorController {

    @RequestMapping(value = {"/", "/dashboard"})
    public String monitor(){
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
