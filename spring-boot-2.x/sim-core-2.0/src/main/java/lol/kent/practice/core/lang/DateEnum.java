package lol.kent.practice.core.lang;

/**
 * 日期枚举类
 * @author Shunqin.Chen
 */

public enum DateEnum {
    /**
     * yyyy格式
     */
    YYYY("yyyy"),
    /**
     * yyyyMM格式
     */
    YYYYMM("yyyyMM"),
    /**
     * yyyyMMdd格式
     */
    YYYYMMDD("yyyyMMdd"),
    /**
     * yyyyMMddHHmmss格式
     */
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    /**
     * yyyyMMddHHmm格式
     */
    YYYYMMDDHHMM("yyyyMMddHHmm"),
    /**
     * yyyyMMddHHmmssSSS格式
     */
    YYYYMMDDHHMMSSSSS("yyyyMMddHHmmssSSS"),
    /**
     * yyyy-MM格式
     */
    YYYYMM_BYSEP("yyyy-MM"),
    /**
     * yyyy-MM-dd格式
     */
    YYYYMMDD_BYSEP("yyyy-MM-dd"),

    /**
     * MM-dd格式
     */
    MMDD_BYSEP("MM-dd"),
    /**
     * yyyy-MM-dd HH:mm:ss格式
     */
    YYYYMMDDHHMMSS_BYSEP("yyyy-MM-dd HH:mm:ss"),

    /**
     * yyyy-MM-dd HH:mm格式
     */
    YYYYMMDDHHMM_BYSEP("yyyy-MM-dd HH:mm"),
    /**
     * yyyy-MM-dd HH:mm:ss SSS格式
     */
    YYYYMMDDHHMMSSSSS_BYSEP("yyyy-MM-dd HH:mm:ss.SSS"),
    /**
     * HHmmss格式
     */
    HHMMSS("HHmmss"),
    /**
     * HH:mm:ss格式
     */
    HHMMSS_BYSEP("HH:mm:ss"),
    /**
     * 最小年度1900
     */
    MINYEAR("1900"),
    /**
     * 最大年度9999
     */
    MAXYEAR("9999"),
    /**
     * 最小日期19000101
     */
    MINDATE("19000101"),
    /**
     * 最大日期99991231
     */
    MAXDATE("99991231"),
    /**
     * 最小日期1900-01-01
     */
    MINDATE_BYSEP("1900-01-01"),
    /**
     * 最大日期9999-12-31
     */
    MAXDATE_BYSEP("9999-12-31");
    
    /**
     * 枚举值
     */
    private String format = null;

    /**
     * 带有一个参数的构造函数
     *
     * @param format 枚举值
     */
    private DateEnum(String format) {
        this.format = format;
    }

    /**
     * 得到枚举类的值
     */
    @Override
    public String toString() {
        return format;
    }

    /**
     * 得到枚举类的值
     */
    public String getValue() {
        return format;
    }
}
