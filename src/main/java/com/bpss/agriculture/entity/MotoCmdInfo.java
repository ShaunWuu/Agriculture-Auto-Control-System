package com.bpss.agriculture.entity;

import lombok.Data;

/**
 * 存储大棚内电动设备历次动作的处理状态
 *
 * @author Rocky Wu
 */
@Data
public class MotoCmdInfo {

    private int id;
    private String vcMotoName;
    private int iActCmd;
    private String vcActName;
    private int iCmdStatus;
    private String vcRemark;
    private String vcTime;
    private int iOperatorId;

    public MotoCmdInfo(String vcMotoName, int iActCmd, String vcActName, int iCmdStatus, String vcRemark, String vcTime, int iOperatorId) {
        this.vcMotoName = vcMotoName;
        this.iActCmd = iActCmd;
        this.vcActName = vcActName;
        this.iCmdStatus = iCmdStatus;
        this.vcRemark = vcRemark;
        this.vcTime = vcTime;
        this.iOperatorId = iOperatorId;
    }
}
