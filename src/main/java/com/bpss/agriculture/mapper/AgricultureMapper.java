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

    /**
     * 大棚信息表
     * @return 大棚信息对象
     */
    @Options(useGeneratedKeys = true,keyProperty = "iGreenHouseId")
    @Select("SELECT * FROM tGreenHouseInfo")
    GreenHouseInfo getGreenHouseInfo();

    /**
     *
     * 插入的数据中 iCmdStatus == 0 时系统会进行处理
     * @param info MotoCmdInfo 对象
     */
    @Insert("INSERT INTO " +
            "tMotoCmdInfo (vcMotoName, iActCmd, vcActName, iCmdStatus, vcRemark, vcTime, iOperatorId) " +
            "VALUES " +
            "(#{vcMotoName}, #{iActCmd}, #{vcActName}, #{iCmdStatus}, #{vcRemark}, #{vcTime}, #{iOperatorId})")
    void writeMotoCmd(MotoCmdInfo info);

    /**
     * 获得所有设备操作记录
     * 数据总共大概 1000 多条
     * @return
     */
    @Select("SELECT * FROM tMotoCmdInfo")
    List<MotoCmdInfo> getAllMotoCmdInfo();

    /**
     * Dashboard 中的设备操作记录简略表
     * @return MotoCmdInfo List
     * SQL Server 使用 Select("SELECT TOP 16 * FROM tMotoCmdInfo")
     *
     */
    @Select("SELECT * FROM tMotoCmdInfo LIMIT 16")
    List<MotoCmdInfo> getLatestMotoCmdInfo();

    /**
     * 查询所有设备状态
     * @return 设备状态对象
     */
    @Select("SELECT * FROM tMotoInfo")
    MotoInfo getMotoInfo();

}
