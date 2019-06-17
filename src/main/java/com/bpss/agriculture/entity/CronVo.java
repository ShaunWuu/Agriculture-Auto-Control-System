package com.bpss.agriculture.entity;

import lombok.Data;

/**
 * 附带 id 和注释的包装 Cron 对象
 *
 * @author Rocky Wu
 */
@Data
public class CronVo {

    private int id;
    private String cron;
    private String note;

}
