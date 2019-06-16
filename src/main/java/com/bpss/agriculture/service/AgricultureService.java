package com.bpss.agriculture.service;

import com.bpss.agriculture.entity.GreenHouseInfo;
import com.bpss.agriculture.entity.MotoCmdInfo;
import com.bpss.agriculture.entity.MotoInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务用于修改计划任务
 *
 * @author Rocky Wu
 */
public interface AgricultureService {

    GreenHouseInfo getGreenHouseInfo();

    MotoInfo getMotoInfo();

    List<MotoCmdInfo> getLatestMotoCmdInfo();

    List<MotoCmdInfo> getAllMotoCmdInfo();

}
