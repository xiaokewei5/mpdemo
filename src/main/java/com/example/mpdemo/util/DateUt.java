package com.example.mpdemo.util;

import java.lang.management.ManagementFactory;
import java.util.Date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

public class DateUt extends DateUtil{
    /**
     * 标准日期格式：yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = DatePattern.NORM_DATE_PATTERN;

    /**
     * 标准时间格式：HH:mm:ss
     */
    public static final String HH_MM_SS = DatePattern.NORM_TIME_PATTERN;

    /**
     * 标准日期时间格式，精确到分：yyyy-MM-dd HH:mm
     */
    public static final String YYYY_MM_DD_HH_MM = DatePattern.NORM_DATETIME_MINUTE_PATTERN;

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYY_MM_DD_HH_MM_SS = DatePattern.NORM_DATETIME_PATTERN;

    /**
     * 标准日期格式：yyyy年MM月dd日
     */
    public static final String YYYYNMMYDDR = DatePattern.CHINESE_DATE_PATTERN;

    /**
     * 标准日期格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String YYYYNMMYDDR_HHHMMFSSM = DatePattern.CHINESE_DATE_TIME_PATTERN;

    /**
     * 标准日期格式：yyyyMMdd
     */
    public static final String YYYYMMDD = DatePattern.PURE_DATE_PATTERN;

    /**
     * 标准日期格式：HHmmss
     */
    public static final String HHMMSS = DatePattern.PURE_TIME_PATTERN;

    /**
     * 标准日期格式：yyyyMMddHHmmss
     */
    public static final String YYYYMMDDHHMMSS = DatePattern.PURE_DATETIME_PATTERN;

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }
}
