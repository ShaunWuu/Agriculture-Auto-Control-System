package com.bpss.agriculture.moto;

/**
 * 定义设备自动控制命令
 *
 * @author Rocky Wu
 */
public interface MotoCmd {

    static final int AIR_TEMP = 1;
    static final int AIR_WATER = 2;
    static final int EARTH_TEMP_1 = 3;
    static final int EARTH_TEMP_2 = 4;
    static final int EARTH_WATER_1 = 5;
    static final int EARTH_WATER_2 = 6;
    static final int LIGHT = 7;

    /**
     * 打开设备的操作代码
     */
    static final int MOTO_OPEN = 0;
    /**
     * 关闭设备的操作代码
     */
    static final int MOTO_CLOSE = 1;

    /**
     * 0 代表开， 1 代表关
     * 原数据库的设计是如此
     */
    static final int CLOSE_OPEN = 0x10;
    static final int OPEN_CLOSE = 0x01;
    static final int OPEN_OPEN = 0x00;

    static final int OPEN_OPEN_OPEN = 0x000;
    static final int OPEN_OPEN_CLOSE = 0x001;
    static final int OPEN_CLOSE_OPEN = 0x010;
    static final int CLOSE_OPEN_OPEN = 0x100;
    static final int OPEN_CLOSE_CLOSE = 0x011;
    static final int CLOSE_CLOSE_OPEN = 0x110;
    static final int CLOSE_OPEN_CLOSE = 0x101;
    static final int CLOSE_CLOSE_CLOSE = 0x111;

    /**
     * 自动控制的逻辑代码
     */
    void motoCmd();

    /**
     * 进行的自动控制
     * @param vcMotoName 操作的设备名
     * @param vcActName 操作注释
     */
    void openMotoCmd(String vcMotoName, String vcActName);

    void closeMotoCmd(String vcMotoName, String vcActName);

}
