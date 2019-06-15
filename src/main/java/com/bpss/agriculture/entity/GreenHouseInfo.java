package com.bpss.agriculture.entity;

import lombok.Data;

/**
 * 大棚环境信息
 *
 * @author Rocky Wu
 */
@Data
public class GreenHouseInfo {

    private int iGreenHouseId;
    private float fAirTemp1;
    private int iAirTempAlarmFlag1;
    private float fAirWater1;
    private int iAirWaterAlarmFlag1;
    private float fAirTemp2;
    private int iAirTempAlarmFlag2;
    private float fAirWater2;
    private int iAirWaterAlarmFlag2;
    private float fEarthTemp1;
    private int iEarthTempAlarmFlag1;
    private float fEarthWater1;
    private int iEarthWaterAlarmFlag1;
    private float fEarthTemp2;
    private int iEarthTempAlarmFlag2;
    private float fEarthWater2;
    private int iEarthWaterAlarmFlag2;
    private float fEarthTemp3;
    private int iEarthTempAlarmFlag3;
    private float fEarthWater3;
    private int iEarthWaterAlarmFlag3;
    private float fEarthTemp4;
    private int iEarthTempAlarmFlag4;
    private float fEarthWater4;
    private int iEarthWaterAlarmFlag4;
    private float fCo2;
    private int iCo2AlarmFlag;
    private float fCo22;
    private int iCo2AlarmFlag2;
    private float fEC1;
    private int iECAlarmFlag1;
    private float fEC2;
    private int iECAlarmFlag2;
    private float fEC3;
    private int iECAlarmFlag3;
    private float fPH1;
    private int iPHAlarmFlag1;
    private float fPH2;
    private int iPHAlarmFlag2;
    private float fPH3;
    private int iPHAlarmFlag3;
    private float fLight1;
    private int iLightAlarmFlag1;
    private float fLight2;
    private int iLightAlarmFlag2;
    private float fWaterLevel;
    private int iWaterLevelAlarmFlag;
    private String vcTime;

}
