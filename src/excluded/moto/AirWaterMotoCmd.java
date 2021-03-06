// package com.bpss.agriculture.moto;
//
// import com.bpss.agriculture.entity.GreenHouseInfo;
// import com.bpss.agriculture.entity.MotoCmdInfo;
// import com.bpss.agriculture.entity.Schedule;
// import com.bpss.agriculture.mapper.AgricultureMapper;
// import com.bpss.agriculture.mapper.ScheduleMapper;
// import com.bpss.agriculture.util.CommonUtil;
// import org.springframework.beans.factory.annotation.Autowired;
//
// /**
//  * @author Rocky Wu
//  */
// public class AirWaterMotoCmd implements MotoCmd {
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
//         Schedule schedule = scheduleMapper.getScheduleById(AIR_WATER);
//
//         float airWater = (info.getFAirWater1() + info.getFAirWater2()) / 2;
//
//         int iWetMotoStatus = agricultureMapper.getMotoInfo().getIWetMotoStatus1();
//
//         if (airWater < schedule.getLow()) {
//
//             openMotoCmd("iWetMotoStatus1", "系统自动打开喷雾器");
//
//         }
//
//         if (airWater > ((schedule.getHigh() + schedule.getLow()) / 2)) {
//
//             closeMotoCmd("iWetMotoStatus1", "系统自动关闭喷雾器");
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
//         System.out.println("自动控制系统采取了提高空气湿度的措施");
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
//         System.out.println("自动控制系统采取了降低空气湿度的措施");
//
//     }
// }
