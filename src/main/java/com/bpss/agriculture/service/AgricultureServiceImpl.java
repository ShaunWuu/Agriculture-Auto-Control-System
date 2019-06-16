package com.bpss.agriculture.service;

import com.bpss.agriculture.entity.GreenHouseInfo;
import com.bpss.agriculture.entity.MotoCmdInfo;
import com.bpss.agriculture.entity.MotoInfo;
import com.bpss.agriculture.mapper.AgricultureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Rocky Wu
 */
@Service
public class AgricultureServiceImpl implements AgricultureService {

    @Autowired
    AgricultureMapper agricultureMapper;

    @Override
    public GreenHouseInfo getGreenHouseInfo() {
        Date date = new Date();
        GreenHouseInfo greenHouseInfo = agricultureMapper.getGreenHouseInfo();
        String vcTimeStr = greenHouseInfo.getVcTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            date = formatter.parse(vcTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vcTime = sdf.format(date);
        greenHouseInfo.setVcTime(vcTime);
        return greenHouseInfo;
    }

    @Override
    public MotoInfo getMotoInfo() {
        return agricultureMapper.getMotoInfo();
    }

    @Override
    public List<MotoCmdInfo> getLatestMotoCmdInfo() {
        return agricultureMapper.getLatestMotoCmdInfo();
    }

    @Override
    public List<MotoCmdInfo> getAllMotoCmdInfo() {
        return agricultureMapper.getAllMotoCmdInfo();
    }
}
