package com.bpss.agriculture.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rocky Wu
 */
public class CommonUtil {

    /**
     * 获取符合数据库时间格式的时间字符串
     * @return
     */
    public static String getVcTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

}
