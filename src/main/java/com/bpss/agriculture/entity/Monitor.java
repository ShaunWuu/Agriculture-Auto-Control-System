package com.bpss.agriculture.entity;

import com.bpss.agriculture.mapper.AgricultureMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rocky Wu
 */
@Setter
@Getter
public class Monitor {

    @Autowired
    AgricultureMapper agricultureMapper;
    
    GreenHouseInfo info = agricultureMapper.getGreenHouseInfo();

    private float airTemp = (info.getFAirTemp1() + info.getFAirTemp2()) / 2;
    private float airWater = (info.getFAirWater1() + info.getFAirWater2()) / 2;
    private float earthTemp1 = (info.getFEarthTemp1() + info.getFEarthTemp3()) / 2;
    private float earthTemp2 = (info.getFEarthTemp2() + info.getFEarthTemp4()) / 2;
    private float earthWater1 = info.getFEarthWater1();
    private float earthWater2 = info.getFEarthWater2();
    private float earthWater3 = info.getFEarthWater3();
    private float earthWater4 = info.getFEarthWater4();
    private float light = (info.getFLight1() + info.getFLight2()) / 2;

}
