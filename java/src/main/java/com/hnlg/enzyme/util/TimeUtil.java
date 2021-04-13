package com.hnlg.enzyme.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间处理工具
 *
 */
public class TimeUtil {

    private final static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    /**
     * 时间格式化格式
     */
    public static SimpleDateFormat ymdSDF_ = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat ymdSDFLeft = new SimpleDateFormat("yyyy/MM/dd");
    public static SimpleDateFormat ymdSDFNoCut = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat yyyyMMddHHmmss_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat yyyyMMddHHmmssLeft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static SimpleDateFormat yyyyMMddHHmmssNoCut = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat yyyyMMddHHmmssSSS_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    public static SimpleDateFormat yyyyMMddHHmmssSSSLeft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss,SSS");
    public static SimpleDateFormat yyyyMMddHHmmssSSSNoCut = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    public static int INVALID_RANGE = 60;// 合理时间差

    /**
     * 根据指定时间格式格式化系统时间
     *
     * @param format
     * @return
     */
    public static String getSysTime(String format) {
        return formatSysTime(new SimpleDateFormat(format));
    }

    /**
     * 根据指定格式格式化当前系统时间
     *
     * @param sdf
     * @return
     */
    public static String formatSysTime(SimpleDateFormat sdf) {
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 获取当前时间int格式值： yyyyMMddHHmmssSSS
     */
    public static String getTimeInt() {
        return yyyyMMddHHmmssSSSNoCut.format(Calendar.getInstance().getTime());
    }

    /**
     * 获取当前日期int格式值： yyyyMMdd
     */
    public static String getDate() {
        return ymdSDFNoCut.format(Calendar.getInstance().getTime());
    }

    /**
     * 获取当前系统时间精确值：yyyy-MM-dd HH:mm:ss,SSS
     */
    public static String getTime() {
        return yyyyMMddHHmmssSSS_.format(Calendar.getInstance().getTime());
    }

    /**
     * 根据指定格式格式化指定时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern, Locale.ENGLISH).format(date);
    }

    /**
     * 当前月份加一个月
     */
    public static Date getNextMonth() {
        return addMonth(new Date(), 1);
    }

    /**
     * 当前月份减一个月
     */
    public static Date getLastMonth() {
        return addMonth(new Date(), -1);
    }

    /**
     * 指定日期(时间)+-月
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    /**
     * 获取当前时间昨天时间
     */
    public static Date getYesterDay() {
        return getYesterDay(new Date());
    }

    /**
     * 指定时间减一天
     *
     * @param date
     * @return
     */
    public static Date getYesterDay(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取去年的当前时间
     */
    public static Date getLastYear() {
        return addYear(new Date(), -1);
    }

    /**
     * 获取明年的当前时间
     */
    public static Date getNextYear() {
        return addYear(new Date(), 1);
    }

    /**
     * 指定时间+-月
     *
     * @param date
     * @param year
     * @return
     */
    public static Date addYear(Date date, int year) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 指定时间+-指定天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDate(Date date, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 指定时间+-指定小时
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date getDateByHour(Date date, int hours) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 指定时间+=指定秒
     *
     * @param date
     * @return
     */
    public static Date getDateBySecond(Date date, int seconds) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    /**
     * 校验时间格式
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static boolean validTime(String dateStr, String pattern) {

        if (StringUtil.haveEmpty(dateStr, pattern)) {
            return false;
        }

        DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date = null;

        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            logger.error(CommonUtils.getTraceInfo() + e.getMessage());
            return false;
        }

        return dateStr.equals(df.format(date));
    }

    /**
     * 将指定时间字符串及给定的格式格式化为指定时间
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date format(String dateStr, String pattern) {

        if (StringUtil.haveEmpty(dateStr, pattern)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);

        Date date = null;

        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            logger.error(CommonUtils.getTraceInfo() + e.getMessage());
            return null;
        }

        return date;
    }

    /**
     * 将指定时间格式字符串根据给定的时间格式格式化为给定的新的时间格式
     *
     * @param dateStr
     * @param oldSdf
     * @param newSdf
     * @return
     */
    public static String format(String dateStr, SimpleDateFormat oldSdf, SimpleDateFormat newSdf) {
        if (StringUtil.isEmpty(dateStr) || StringUtil.haveEmpty(oldSdf, newSdf)) {
            return "";
        }

        Date date = null;

        try {
            date = oldSdf.parse(dateStr);
            dateStr = newSdf.format(date);
        } catch (ParseException e) {
            logger.error(CommonUtils.getTraceInfo() + e.getMessage());
            return "";
        } catch (Exception e) {
            logger.error(CommonUtils.getTraceInfo() + e.getMessage());
            return "";
        }

        return dateStr;
    }

    /**
     * 获取当前系统时间： yyyy/MM/dd HH:mm:ss
     */
    public static String getSysTimeClient() {
        return getSysTime("yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 获取当前系统时间： yyyy-MM-dd HH:mm:ss
     */
    public static String getSysTime() {
        return getSysTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前系统时间毫秒级：yyyy-MM-dd HH:mm:ss,SSS
     */
    public static String getSysTimeS() {
        return getSysTime("yyyy-MM-dd HH:mm:ss,SSS");
    }

    /**
     * 获取当前系统时间秒级长整数：yyyyMMddHHmmss
     */
    public static String getSysTimeLong() {
        return getSysTime("yyyyMMddHHmmss");
    }

    /**
     * 获取当前系统时间毫秒级长整数：yyyyMMddHHmmssSSS
     */
    public static String getSysTimeSLong() {
        return getSysTime("yyyyMMddHHmmssSSS");
    }

    /**
     * 获取当前系统日期：yyyy-MM-dd
     */
    public static String getSysdate() {
        return getSysTime("yyyy-MM-dd");
    }

    /**
     * 获取当前系统时间年月日整数：yyyyMMdd
     */
    public static String getSysdateInt() {
        return getSysTime("yyyyMMdd");
    }

    /**
     * 获取当前年份
     */
    public static String getSysYearInt() {
        return getSysTime("yyyy");
    }

    /**
     * 获取当前系统时间年月整数值：yyyyMM
     */
    public static String getSysyearmonthInt() {
        return getSysTime("yyyyMM");
    }

    /**
     * 获取当前系统时间零点
     */
    public static String getSysdateStart() {
        return (getSysdate() + " 00:00:00");
    }

    /**
     * 获取当前系统时间最当天最后一秒
     */
    public static String getSysdateEnd() {
        return (getSysdate() + " 23:59:59");
    }

    /**
     * 获得当前系统时间增加指定秒数后的时间
     *
     * @param second
     * @return
     */
    public static Date getSecond(int second) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, second);

        return cal.getTime();
    }

    /**
     * 当前线程挂起指定毫秒
     *
     * @param millis
     */

    public static void sleep(long millis) {
        if (millis < 0L) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error(CommonUtils.getTraceInfo() + e.getMessage());
        }
    }

    /**
     * 获取系统当前时间戳
     *
     * @return
     */
    public static int getSystemInt() {

        return dateToIntMillis(new Date());
    }

    /**
     * 获取时间对应时间戳
     *
     * @param date
     * @return
     */
    public static int dateToIntMillis(Date date) {

        if (date == null) {
            date = new Date();
        }
        return (int) (date.getTime() / 1000);
    }

    /**
     * 获取时间对应时间戳
     *
     * @param time
     * @return
     */
    public static int dateToIntMillis(String time) {

        return dateToIntMillis(time, yyyyMMddHHmmss_);
    }

    /**
     * 获取时间对应时间戳
     *
     * @param time
     * @param pattern
     * @return
     */
    public static int dateToIntMillis(String time, String pattern) {

        return dateToIntMillis(time, new SimpleDateFormat(pattern));
    }

    /**
     * 获取时间对应时间戳
     *
     * @param time
     * @param format
     * @return
     */
    public static int dateToIntMillis(String time, SimpleDateFormat format) {

        if (StringUtil.isEmpty(time)) {
            time = getSysTime();
        }
        if (format == null) {
            format = yyyyMMddHHmmss_;
        }

        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            return 0;
        }
        return dateToIntMillis(date);
    }

    /**
     * 时间戳转换为时间
     *
     * @param second
     * @param format
     * @return
     */
    public static String intMillisToTimeStr(Long second, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(second));
    }

    /**
     * 时间戳转换为时间
     *
     * @param second
     * @param format
     * @return
     */
    public static String intMillisToTimeStr(Long second, SimpleDateFormat format) {

        if (format == null) {
            format = yyyyMMddHHmmssLeft;
        }

        return format.format(new Date(second));
    }

    /**
     * 时间戳转换为时间
     *
     * @param second
     * @return
     */
    public static String intMillisToTimeStr(Long second) {
        if (second == 0) {
            return "";
        }
        return intMillisToTimeStr(second, yyyyMMddHHmmssLeft);
    }

    /**
     * local时间转换成UTC时间
     *
     * @param localTime
     * @return
     */
    public static Date localToUTC(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate = null;
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis = localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate = new Date(calendar.getTimeInMillis());
        return utcDate;
    }

    public static boolean checkTimesDifRange(int time, int dbTime) {
        return Math.abs(time - dbTime) > INVALID_RANGE;
    }

    /**
     * 根据一个 字符串日期 2018-08 获取一个月第一天
     *
     * @param dateStr
     * @return
     */
    public static int getMonthStartTimeStampByDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date parse = new Date();
        try {
            parse = format.parse(dateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return getTimeStamp10ByDate(c.getTime());
    }


    /**
     * 根据一个 字符串日期 2018-08 获取一个月最后一天
     *
     * @param dateStr
     * @return
     */
    public static int getMonthEndTimeStampByDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date parse = new Date();
        try {
            parse = format.parse(dateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.setTime(parse);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDayLastTimeStamp10ByDate(ca.getTime());
    }

    /**
     * 获取一个月每天的时间戳
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> getMonthEveryDay(Integer start,Integer end){
        List<Integer> list=new ArrayList<>();
        for(int i=start;i<=end;i+=86400){
            list.add(i);
        }
        return list;
    }

    /**
     * 获取一个 10位时间戳
     *
     * @param date
     * @return
     */
    public static int getTimeStamp10ByDate(Date date) {
        return (int) (date.getTime() / 1000);
    }

    /**
     * 获取某天最后一秒的时间戳  2
     *
     * @return
     */
    public static int getDayLastTimeStamp10ByDate(Date date) {
        return (int) (date.getTime() / 1000) + 86399;
    }


    /**
     * 匹配所有所有时间格式
     */
    public static Date smartFormat(String time) throws Exception {
        SimpleDateFormat datePattern = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date returnTime = null;
        try {
            returnTime = datePattern.parse(time);
        } catch (ParseException e) {
            datePattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                returnTime = datePattern.parse(time);
            } catch (ParseException e1) {
                datePattern = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try {
                    returnTime = datePattern.parse(time);
                } catch (ParseException e2) {
                    datePattern = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
                    try {
                        returnTime = datePattern.parse(time);
                    } catch (ParseException e3) {
                        datePattern = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        try {
                            returnTime = datePattern.parse(time);
                        } catch (ParseException e4) {
                            datePattern = new SimpleDateFormat("yyyy-MM-dd H:mm");
                            try {
                                returnTime = datePattern.parse(time);
                            } catch (ParseException e5) {
                                datePattern = new SimpleDateFormat("yyyy-M-dd HH:mm");
                                try {
                                    returnTime = datePattern.parse(time);
                                } catch (ParseException e6) {
                                    datePattern = new SimpleDateFormat("yyyy-M-dd H:mm");
                                    try {
                                        returnTime = datePattern.parse(time);
                                    } catch (ParseException e7) {
                                        datePattern = new SimpleDateFormat("yyyy-M-d H:mm");
                                        try {
                                            returnTime = datePattern.parse(time);
                                        } catch (ParseException e8) {
                                            throw e8;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return returnTime;
    }

    /**
     * 获取当天时间戳
     *
     * @return
     */
    public static Map<String, Integer> getTodayTimeInterval() {
        Map<String, Integer> map = new HashMap<>();
        Calendar calendar1 = Calendar.getInstance();
        //设置昨天
        calendar1.setTime(new Date());
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        map.put("start", TimeUtil.getTimeStamp10ByDate(calendar1.getTime()));
        map.put("end", TimeUtil.getTimeStamp10ByDate(calendar2.getTime()));
        return map;
    }

    /**
       * 取得指定日期所在周的第一天 
       */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime ();
    }

 /**
   * 取得指定日期所在周的最后一天 
    */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        c.set(Calendar.MINUTE, 59);
        //将秒至0
        c.set(Calendar.SECOND,59);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 59);
     return c.getTime();
     }

     /*
      * 获得当天零时零分零秒
	 * @return
       */
    public static Date initDateByDay(Date keyDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(keyDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
     /**
      * 获得当天零时零分零秒
	 * @return
       */
    public static Date entDateByDay(Date keyDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(keyDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     *
     * 获取当前月份第一天的日期 * @return 格式化后的日期
     * */
    public static Date getFirstDayOfThisMonth() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
         return c.getTime();
    }

    /** *
     * 获取当前月份最后一天 * @return 格式化的日期
     * */
    public static Date getMaxDayOfThisMonth() {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        //主要就是这个roll方法
        cal.roll(Calendar.DATE, -1);
        //将小时至0
        cal.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        cal.set(Calendar.MINUTE, 59);
        //将秒至0
        cal.set(Calendar.SECOND,59);
        //将毫秒至0
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取当天开始时间
     * @param date
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        //将毫秒至0
        dateStart.set(Calendar.MILLISECOND, 0);
        return dateStart.getTime();
    }

    /**
     *  获取当天结束时间
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }
}