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
// public class LightMotoCmd implements MotoCmd {
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
//         Schedule schedule = scheduleMapper.getScheduleById(LIGHT);
//
//         float light = (info.getFLight1() + info.getFLight2()) / 2;
//
//         if (light < schedule.getLow()) {
//
//             openMotoCmd("iLightStatus1", "系统自动打开补光灯");
//         }
//
//         if (light > ((schedule.getHigh() + schedule.getLow()) / 1.8)) {
//             openMotoCmd("iLightStatus1", "系统自动关闭补光灯");
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
//                 new MotoCmdInfo(vcMotoName, MOTO_OPEN, vcActName, 0, "此操作由系统自动处理", vcTime, 2));
//         System.out.println("自动控制系统采取了提高光照度的措施");
//
//     }
//
//     @Override
//     public void closeMotoCmd(String vcMotoName, String vcActName) {
//         String vcTime = CommonUtil.getVcTime();
//
//         agricultureMapper.writeMotoCmd(
//                 new MotoCmdInfo(vcMotoName, MOTO_CLOSE, vcActName, 0, "此操作由系统自动处理", vcTime, 2)
//         );
//         System.out.println("自动控制系统采取了降低光照度的措施");
//     }
// }
