// package com.bpss.agriculture.moto;
//
// import com.bpss.agriculture.entity.GreenHouseInfo;
// import com.bpss.agriculture.entity.MotoCmdInfo;
// import com.bpss.agriculture.entity.Schedule;
// import com.bpss.agriculture.mapper.AgricultureMapper;
// import com.bpss.agriculture.mapper.ScheduleMapper;
// import com.bpss.agriculture.util.CommonUtil;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Schedules;
//
// public class EarthMotoCmd implements MotoCmd{
//
//     @Autowired
//     AgricultureMapper agricultureMapper;
//
//     @Autowired
//     ScheduleMapper scheduleMapper;
//
//     @Override
//     public void motoCmd() {
//
//         GreenHouseInfo info = agricultureMapper.getGreenHouseInfo();
//         Schedule schedule = scheduleMapper.getScheduleById(EARTH_WATER_1);
//
//         float earthWater = (info.getFEarthWater1() + info.getFEarthWater3() / 2);
//
//         int iWaterMotoStatus1 = agricultureMapper.getMotoInfo().getIWaterMotoStatus1();
//
//         if (earthWater < schedule.getLow()) {
//
//             openMotoCmd("iWaterMotoStatus1", "系统自动打开灌溉出水阀");
//
//         }
//
//         if (earthWater > ((schedule.getHigh() + schedule.getLow()) / 2)) {
//
//             closeMotoCmd("iWaterMotoStatus1", "系统自动打开灌溉出水阀");
//
//         }
//
//     }
//
//     @Override
//     public void openMotoCmd(String vcMotoName, String vcActName) {
//
//         String vcTime = CommonUtil.getVcTime();
//
//         agricultureMapper.writeMotoCmd(
//                 new MotoCmdInfo(vcMotoName, MOTO_OPEN, vcActName, 0, "此操作由系统自动处理", vcTime, 2)
//         );
//         System.out.println("自动控制系统采取了提高土壤湿度的措施");
//
//     }
//
//     @Override
//     public void closeMotoCmd(String vcMotoName, String vcActName) {
//
//         String vcTime = CommonUtil.getVcTime();
//
//         agricultureMapper.writeMotoCmd(
//                 new MotoCmdInfo(vcMotoName, MOTO_OPEN, vcActName, 0, "此操作由系统自动处理", vcTime, 2)
//         );
//         System.out.println("自动控制系统采取了降低土壤湿度的措施");
//
//     }
// }
