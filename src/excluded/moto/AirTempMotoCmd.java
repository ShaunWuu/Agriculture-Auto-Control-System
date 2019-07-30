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
// public class AirTempMotoCmd implements MotoCmd {
//
//     @Autowired
//     AgricultureMapper agricultureMapper;
//
//     @Autowired
//     ScheduleMapper scheduleMapper;
//
//     private Integer status= 0x00;
//
//     @Override
//     public void motoCmd() {
//
//         GreenHouseInfo info = agricultureMapper.getGreenHouseInfo();
//         Schedule schedule = scheduleMapper.getScheduleById(AIR_TEMP);
//
//         float airTemp = (info.getFAirTemp1() + info.getFAirTemp2()) / 2;
//
//         int iHotMotoStatus1 = agricultureMapper.getMotoInfo().getIHotMotoStatus1();
//         int iHotMotoStatus2 = agricultureMapper.getMotoInfo().getIHotMotoStatus2();
//
//         if (iHotMotoStatus1 == 0) {
//             status += 0x10;
//         }
//
//         if (iHotMotoStatus2 == 0) {
//             status += 0x01;
//         }
//
//         if (airTemp < schedule.getLow()) {
//
//             switch (status){
//                 case OPEN_OPEN: {
//                     openMotoCmd("iHotMotoStatus1", "系统自动打开暖风机1");
//                     openMotoCmd("iHotMotoStatus2", "系统自动打开暖风机2");
//                     break;
//                 }
//                 case OPEN_CLOSE: {
//                     openMotoCmd("iHotMotoStatus2", "系统自动打开暖风机2");
//                     break;
//                 }
//                 case CLOSE_OPEN: {
//                     openMotoCmd("iHotMotoStatus1", "系统自动打开暖风机1");
//                     break;
//                 }
//                 default: {
//                     System.out.println("所有暖风机均已打开，请考虑手动提高空气温度");
//                 }
//             }
//
//
//         }
//
//         if (airTemp > ((schedule.getHigh() + schedule.getLow()) / 2)) {
//
//             switch (status){
//                 case OPEN_OPEN: {
//                     closeMotoCmd("iHotMotoStatus1", "系统自动关闭暖风机1");
//                     closeMotoCmd("iHotMotoStatus2", "系统自动关闭暖风机2");
//                     break;
//                 }
//                 case OPEN_CLOSE: {
//                     closeMotoCmd("iHotMotoStatus2", "系统自动关闭暖风机2");
//                     break;
//                 }
//                 case CLOSE_OPEN: {
//                     closeMotoCmd("iHotMotoStatus1", "系统自动关闭暖风机1");
//                     break;
//                 }
//                 default: {
//                     System.out.println("所有暖风机均已关闭，请考虑手动降低空气温度");
//                 }
//             }
//
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
//         System.out.println("自动控制系统采取了提高空气温度的措施");
//
//     }
//
//     @Override
//     public void closeMotoCmd(String vcMotoName, String vcActName) {
//
//         String vcTime = CommonUtil.getVcTime();
//
//         agricultureMapper.writeMotoCmd(
//                 new MotoCmdInfo(vcMotoName, MOTO_CLOSE, vcActName, 0, "此操作由系统自动处理", vcTime, 2)
//         );
//         System.out.println("自动控制系统采取了降低空气温度的措施");
//
//     }
// }
