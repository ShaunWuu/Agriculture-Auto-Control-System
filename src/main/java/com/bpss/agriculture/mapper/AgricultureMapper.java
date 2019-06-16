package com.bpss.agriculture.mapper;

import com.bpss.agriculture.entity.GreenHouseInfo;
import com.bpss.agriculture.entity.MotoCmdInfo;
import com.bpss.agriculture.entity.MotoInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Rocky Wu
 */
@Component
public interface AgricultureMapper {

    @Options(useGeneratedKeys = true,keyProperty = "iGreenHouseId")
    @Select("SELECT * FROM tGreenHouseInfo")
    GreenHouseInfo getGreenHouseInfo();

    @Insert("INSERT INTO " +
            "tMotoCmdInfo (vcMotoName, iActCmd, vcActName, iCmdStatus, vcRemark, vcTime, iOperatorId) " +
            "VALUES " +
            "(#{vcMotoName}, #{iActCmd}, #{vcActName}, #{iCmdStatus}, #{vcRemark}, #{vcTime}, #{iOperatorId})")
    void writeMotoCmd(MotoCmdInfo info);

    @Select("SELECT * FROM tMotoCmdInfo")
    List<MotoCmdInfo> getAllMotoCmdInfo();

    /**
     * Dashboard 中的设备操作记录简略表
     * @return MotoCmdInfo 表
     */
    // @Select("SELECT TOP 17 * FROM tMotoCmdInfo")
    @Select("SELECT * FROM tMotoCmdInfo LIMIT 17")
    List<MotoCmdInfo> getLatestMotoCmdInfo();

    @Select("SELECT * FROM tMotoInfo")
    MotoInfo getMotoInfo();

}
