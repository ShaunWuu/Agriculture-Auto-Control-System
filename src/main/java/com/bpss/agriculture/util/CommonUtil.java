package com.bpss.agriculture.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rocky Wu
 */
public class CommonUtil {

    public static String getVcTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

}
