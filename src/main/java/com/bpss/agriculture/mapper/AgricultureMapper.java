package com.bpss.agriculture.mapper;

import com.bpss.agriculture.entity.GreenHouseInfo;
import com.bpss.agriculture.entity.MotoCmdInfo;
import com.bpss.agriculture.entity.MotoInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Rocky Wu
 */
public interface AgricultureMapper {

    @Options(useGeneratedKeys = true,keyProperty = "iGreenHouseId")
    @Select("SELECT * FROM [dbo].[tGreenHouseInfo]")
    GreenHouseInfo getGreenHouseInfo();

    @Insert("INSERT INTO " +
            "[dbo].[tMotoCmdInfo] (vcMotoName, iActCmd, vcActName, iCmdStatus, vcRemark, vcTime, iOperatorId) " +
            "VALUES " +
            "(#{vcMotoName}, #{iActCmd}, #{vcActName}, #{iCmdStatus}, #{vcRemark}, #{vcTime}, #{iOperatorId})")
    void writeMotoCmd(MotoCmdInfo info);

    @Select("SELECT * FROM tMotoCmdInfo")
    List<MotoCmdInfo> getAllMotoCmdInfo();

    @Select("SELECT * FROM tMotoCmdInfo LIMIT 100")
    List<MotoCmdInfo> getLatestMotoCmdInfo();

    @Select("SELECT * FROM tMotoInfo")
    MotoInfo getMotoInfo();

}
