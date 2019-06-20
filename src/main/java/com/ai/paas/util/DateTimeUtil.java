package com.ai.paas.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.Constant;

/**
 * 日期工具
 * 
 */
public class DateTimeUtil {
    private static final Logger log = LoggerFactory.getLogger(DateTimeUtil.class);
    
    private DateTimeUtil() {
        
    }
    /**
     * 获取输入年份和季度的对应时间区间<br>
     * <p>
     * 2012年第一季度:20120101-20120331;
     * <p>
     * 2012年第二季度:20120401-20120630;
     * <p>
     * 2012年第三季度:20120701-20120930;
     * <p>
     * 2012年第四季度:20121001-20121231;
     * 
     * @param int 年份
     * @param int 季度
     * @return String 返回输入年份和季度对应的时间区间
     */
    public static String getSeasonTimeInterval(int year, int season) {
        String time = null;
        switch (season) {
        case 1:
            time = year + "0101-" + year + "0331";
            break;
        case 2:
            time = year + "0401-" + year + "0630";
            break;
        case 3:
            time = year + "0701-" + year + "0930";
            break;
        case 0:
        case 4:
            time = year + "1001-" + year + "1231";
            break;
        default:
            break;
        }
        return time;
    }

    /**
     * 获取输入月份所在季度<br>
     * <p>
     * 第一季度:1-3;
     * <p>
     * 第二季度:4-6;
     * <p>
     * 第三季度:7-9;
     * <p>
     * 第四季度:10-12;
     * 
     * @param int 月份
     * @return int 返回输入月份所在季度。
     */
    public static int getCurrentSeasonTime(int month) {
        int season = 0;
        if (month >= 1 && month <= 3) {
            season = 1;
        } else if (month >= 4 && month <= 6) {
            season = 2;
        } else if (month >= 7 && month <= 9) {
            season = 3;
        } else if (month >= 10 && month <= 12) {
            season = 4;
        }
        return season;
    }

    // 取得当前时间
    public static Date getDateTime(String dateTime) {
        return java.sql.Date.valueOf(dateTime);
    }

    public static int getMonth(String dateTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDateTime(dateTime));
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getYear(String dateTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDateTime(dateTime));
        return c.get(Calendar.YEAR);
    }

    // /////////////////////////////////////////////////
    /**
     * 比较两个日期间相差的天数<br>
     * 
     * @param 日期1
     * @param 日期2
     * @return 返回两个日期相差的天数。
     */
    public static String dateDiff(Date date1, Date date2) {
        String diff = null;
        if (null != date1 && null != date2) {
            Calendar cal1 = Calendar.getInstance(Locale.CHINA);
            Calendar cal2 = Calendar.getInstance(Locale.CHINA);
            cal1.setTime(date1);
            cal2.setTime(date2);
            Calendar tmpCal1 = Calendar.getInstance(Locale.CHINA);
            tmpCal1.clear();
            tmpCal1.set(Calendar.YEAR, cal1.get(Calendar.YEAR));
            tmpCal1.set(Calendar.MONTH, cal1.get(Calendar.MONTH));
            tmpCal1.set(Calendar.DAY_OF_MONTH, cal1.get(Calendar.DAY_OF_MONTH));
            Calendar tmpCal2 = Calendar.getInstance(Locale.CHINA);
            tmpCal2.clear();
            tmpCal2.set(Calendar.YEAR, cal2.get(Calendar.YEAR));
            tmpCal2.set(Calendar.MONTH, cal2.get(Calendar.MONTH));
            tmpCal2.set(Calendar.DAY_OF_MONTH, cal2.get(Calendar.DAY_OF_MONTH));
            // diff = Double.toString(Math.abs(tmpCal1.getTimeInMillis() -
            diff = String.valueOf(
                    Math.abs(tmpCal1.getTimeInMillis() - tmpCal2.getTimeInMillis()) / (1000.0 * 60.0 * 60.0 * 24.0));

        }
        return diff;
    }

    /**
     * 两个日期字符串之间相差的天数<br>
     * 
     * @param dateStr1 日期字符串
     * @param dateStr2 日期字符串
     * @param dateFmt  日期解析格式(可空)
     * @return 返回两个日期字符串之间相差的天数。
     * @throws ParseException 如果输入的日期转换类型非法，会抛出该异常。
     */
    public static String dateDiff(String dateStr1, String dateStr2, String dateFmt) throws ParseException {
        String diff = null;
        String tmpFmt = dateFmt;
        if (null == tmpFmt)
            tmpFmt = Constant.DATE_JAVA_FORMAT;
        if (null != dateStr1 && null != dateStr2 && dateStr1.length() >= tmpFmt.length()) {
            String tmpStr1 = dateStr1.substring(0, tmpFmt.length());
            String tmpStr2 = dateStr2.substring(0, tmpFmt.length());
            SimpleDateFormat sdf = new SimpleDateFormat(tmpFmt);
            Date date1 = sdf.parse(tmpStr1);
            Date date2 = sdf.parse(tmpStr2);
            diff = DateTimeUtil.dateDiff(date1, date2);
        }
        return diff;
    }

    /**
     * 判断一个日期是否是闰年<br>
     * 
     * @param date 需要判断的日期
     * @return 如果是闰年，返回true。
     */
    public static boolean isLeap(Date date) {
        boolean isLeap = false;
        if (null != date) {
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
                isLeap = true;
        }
        return isLeap;
    }

    /**
     * 判断一个日期字符串是否为闰年<br>
     * 
     * @param dateStr 需要判断的日期
     * @param dateFmt 日期字符串解析格式
     * @return 如果是闰年，返回true。
     */
    public static boolean isLeap(String dateStr, String dateFmt) {
        boolean isLeap = false;
        String tmpFmt = dateFmt;
        if (null == tmpFmt)
            tmpFmt = Constant.DATE_JAVA_FORMAT;
        if (null != dateStr && dateStr.length() >= tmpFmt.length()) {
            try {
                String tmpStr = dateStr.substring(0, tmpFmt.length());
                SimpleDateFormat sdf = new SimpleDateFormat(tmpFmt);
                Date date = sdf.parse(tmpStr);
                isLeap = DateTimeUtil.isLeap(date);
            } catch (ParseException pe) {
                isLeap = false;
            }
        }
        return isLeap;
    }

    /**
     * 获取当前是周几<br>
     * 
     * @return 返回数字，周日：1 周一 2 ...周六:7。
     */
    public static int getWorkDay() {
        Date date = null;

        date = new Date();
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取某个日期前移或者后移天数后的周几
     * 
     * @param diffDays 偏移天数，正数前移，负数后移
     * @param date     日期
     * @return 返回数字，周日：1 周一 2 ...周六:7。
     */
    public static int getWorkDay(int diffDays, Date date) {

        Date tmpDate = null;

        if (null == date)
            tmpDate = new Date();
        else
            tmpDate = date;
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(tmpDate);
        cal.add(Calendar.DATE, diffDays);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取某个日期字符串前移或者后移天数后的周几
     * 
     * @param diffDays 偏移天数，正数前移，负数后移
     * @param dateStr  日期字符串
     * @return 返回数字，周日：1 周一 2 ...周六:7。
     * @throws ParseException 如果日期字符串格式非法，会抛出异常。
     */
    public static int getWorkDay(int diffDays, String dateStr) throws ParseException {
        return DateTimeUtil.getWorkDay(diffDays, dateStr, null);
    }

    /**
     * 获取某个日期字符串前移或者后移天数后的周几
     * 
     * @param diffDays 偏移天数，正数前移，负数后移
     * @param dateStr  日期字符串(可空)
     * @param dateFmt  日期分析格式
     * @return 返回数字，周日：1 周一 2 ...周六:7。
     * @throws ParseException
     */
    public static int getWorkDay(int diffDays, String dateStr, String dateFmt) throws ParseException {
        int ret = Constant.ERROR_RESULT;
        String dtFmt = Constant.DATE_JAVA_FORMAT;
        Date inDate = null;
        if (null != dateFmt)
            dtFmt = dateFmt;
        if (null != dateStr && dateStr.length() >= dtFmt.length()) {
            String tmpStr = dateStr.substring(0, dtFmt.length());
            SimpleDateFormat sdf = new SimpleDateFormat(dtFmt);
            inDate = sdf.parse(tmpStr);
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            cal.setTime(inDate);
            cal.add(Calendar.DATE, diffDays);
            ret = cal.get(Calendar.DAY_OF_WEEK);
        }
        return ret;
    }

    /**
     * 月份偏移
     * 
     * @param diffMonths 偏移月份，正数前移，负数后移
     * @param dateStr    日期字符串
     * @param dateFmt    日期分析格式
     * @return 偏移后日期字符串
     * @throws ParseException
     */
    public static String monthAdd(int diffMonths, String dateStr, String dateFmt) throws ParseException {
        String retDate = null;
        String dtFmt = Constant.DATE_JAVA_FORMAT;
        Date inDate = null;
        if (null != dateFmt)
            dtFmt = dateFmt;
        if (null != dateStr && dateStr.length() >= dtFmt.length()) {
            String tmpStr = dateStr.substring(0, dtFmt.length());
            SimpleDateFormat sdf = new SimpleDateFormat(dtFmt);
            inDate = sdf.parse(tmpStr);
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            cal.setTime(inDate);
            cal.add(Calendar.MONTH, diffMonths);

            sdf = new SimpleDateFormat(dtFmt);
            retDate = sdf.format(cal.getTime());
        }
        return retDate;
    }

    /**
     * 月份偏移
     * 
     * @param diffMonths 偏移月份，正数前移，负数后移
     * @param dateStr    日期字符串
     * @return 偏移后日期字符串
     * @throws ParseException
     */
    public static String monthAdd(int diffMonths, String dateStr) throws ParseException {
        return DateTimeUtil.monthAdd(diffMonths, dateStr, null);

    }

    /**
     * 月份偏移
     * 
     * @param diffMonths 偏移月份，正数前移，负数后移
     * @param date       日期
     * @return 偏移后日期字符串
     * @throws ParseException
     */
    public static String monthAdd(int diffMonths, Date date) throws ParseException {
        String dtFmt = Constant.DATE_JAVA_FORMAT;
        if (null == date)
            date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dtFmt);
        String dateStr = sdf.format(date);
        return DateTimeUtil.monthAdd(diffMonths, dateStr, null);
    }

    /**
     * 日期天数偏移
     * 
     * @param diffDays 偏移天数 正数前移，负数后移
     * @param dateStr  日期字符串
     * @param dateFmt  日期解析格式
     * @return 偏移后日期字符串
     * @throws ParseException
     */
    public static String dateAdd(int diffDays, String dateStr, String dateFmt) throws ParseException {
        String retDate = null;
        String dtFmt = Constant.DATE_JAVA_FORMAT;
        Date inDate = null;
        if (null != dateFmt)
            dtFmt = dateFmt;
        if (null != dateStr && dateStr.length() >= dtFmt.length()) {
            String tmpStr = dateStr.substring(0, dtFmt.length());
            SimpleDateFormat sdf = new SimpleDateFormat(dtFmt);
            inDate = sdf.parse(tmpStr);
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            cal.setTime(inDate);
            cal.add(Calendar.DATE, diffDays);

            sdf = new SimpleDateFormat(dtFmt);
            retDate = sdf.format(cal.getTime());
        }
        return retDate;
    }

    /**
     * 日期天数偏移
     * 
     * @param diffDays 偏移天数 正数前移，负数后移
     * @param dateStr  日期字符串
     * @return 偏移后日期字符串
     * @throws ParseException
     */
    public static String dateAdd(int diffDays, String dateStr) throws ParseException {
        return DateTimeUtil.dateAdd(diffDays, dateStr, null);
    }

    /**
     * 日期天数偏移
     * 
     * @param diffDays 偏移天数 正数前移，负数后移
     * @param date     日期
     * @return 偏移后日期字符串
     * @throws ParseException
     */
    public static String dateAdd(int diffDays, Date date) throws ParseException {
        String dtFmt = Constant.DATE_JAVA_FORMAT;
        if (null == date)
            date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dtFmt);
        String dateStr = sdf.format(date);
        return DateTimeUtil.dateAdd(diffDays, dateStr, null);
    }

    public static Timestamp getDefaultSysDate() {
        return new Timestamp((new Date().getTime()));
    }

    /**
     * 获取当前时间戳
     * 
     * @return 当前时间戳
     */
    public static Timestamp getNowTimeStamp() {
        java.util.Date date = new java.util.Date();
        return new Timestamp(date.getTime());
    }

    /**
     * 获取当前系统时间
     * 
     * @return 当前系统时间
     */
    public static java.sql.Date getNowDate() {
        java.util.Date date = new java.util.Date();
        return new java.sql.Date(date.getTime());
    }

    /**
     * 时间戳转换为字符串
     * 
     * @param date    时间戳
     * @param dateFmt 格式化格式
     * @return 时间字符串(yyyy-MM-dd)
     */
    public static String timeStampToString(Timestamp date, String dateFmt) {
        String strTemp = null;
        if (date != null) {
            String dtFmt = dateFmt;
            if (null == dtFmt)
                dtFmt = Constant.DATE_JAVA_FORMAT;
            SimpleDateFormat formatter = new SimpleDateFormat(dtFmt);
            strTemp = formatter.format(date);
        }
        return strTemp;
    }

    /**
     * 时间戳转换为字符串
     * 
     * @param date 时间戳
     * @return 时间字符串(yyyy-MM-dd)
     */
    public static String timeStampToString(Timestamp date) {
        return timeStampToString(date, null);
    }

    /**
     * 格式化日期
     * 
     * @param date    日期
     * @param dateFmt 日期格式
     * @return 时间字符串(yyyy-MM-dd)
     */
    public static String dateToString(java.util.Date date, String dateFmt) {
        String strTemp = null;
        if (date != null) {
            String dtFmt = dateFmt;
            if (null == dtFmt)
                dtFmt = Constant.DATE_JAVA_FORMAT;
            SimpleDateFormat formatter = new SimpleDateFormat(dtFmt);
            strTemp = formatter.format(date);
        }
        return strTemp;
    }

    /**
     * 格式化日期
     * 
     * @param date    日期
     * @param dateFmt 日期格式(yyyy-MM-dd HH:mm:ss)
     * @return 时间字符串(yyyyMMddHHmmss)
     */
    public static Date formatTimeString(String strTime) {
        String strTemp = null;
        if (strTime != null) {
            strTemp = strTime.substring(0, 4) + "-";
            strTemp += strTime.substring(4, 6) + "-";
            strTemp += strTime.substring(6, 8) + " ";
            strTemp += strTime.substring(8, 10) + ":";
            strTemp += strTime.substring(10, 12) + ":";
            strTemp += strTime.substring(12, strTime.length());
        }
        try {
            return stringToDate(strTemp);
        } catch (ParseException e) {
            log.error("format time error:{}", strTime, e);
        }
        return null;
    }

    /**
     * 格式化日期
     * 
     * @param date 日期
     * @return 时间字符串(yyyy-MM-dd)
     */
    public static String dateToString(java.sql.Date date) {
        return dateToString(date, null);
    }

    /**
     * 字符串转变为时间戳
     * 
     * @param strDate 日期字符串
     * @param dateFmt 日期格式
     * @return 转换后的时间戳
     * @throws ParseException
     */
    public static Timestamp stringToTimestamp(String strDate, String dateFmt) throws ParseException {
        if (strDate != null && !strDate.equals("")) {
            strDate = strDate.replaceAll("[\\u3000\\uA1A1\\u0020\\u00A0]", " ");
            // 替换中文空格160为英文空格32
            String dtFmt = dateFmt;
            if (null == dtFmt)
                dtFmt = Constant.DATE_JAVA_FORMAT;
            SimpleDateFormat formatter = new SimpleDateFormat(dtFmt);
            java.util.Date d = formatter.parse(strDate);
            return new Timestamp(d.getTime());
        } else {
            return null;
        }
    }

    /**
     * 字符串转变为日期
     * 
     * @param strDate 日期字符串
     * @return
     * @throws ParseException
     */
    public static java.util.Date stringToDate(String strDate) throws ParseException {

        return stringToDate(strDate, null);

    }

    /**
     * 字符串转变为日期
     * 
     * @param strDate   日期字符串
     * @param strFormat 日期格式
     * @return
     * @throws ParseException
     */
    public static java.util.Date stringToDate(String strDate, String strFormat) throws ParseException {
        if (strDate != null && !strDate.equals("")) {
            String dtFmt = strFormat;
            if (null == dtFmt)
                dtFmt = Constant.DATE_JAVA_FORMAT;
            SimpleDateFormat formatter = new SimpleDateFormat(dtFmt);
            return formatter.parse(strDate);
        } else {
            return null;
        }
    }

    /**
     * 输入yyyyMMddHHmmss 格式 输出yyyy-MM-dd HH:mm:ss 格式
     * 
     * @param dateString
     * @return
     */

    public static String dateTrans(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat(Constant.DATETIME_ALL_FORMAT);
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATETIME_JAVA_FORMAT);
        String toDate = "";

        try {
            Date date = df.parse(dateString);
            toDate = sdf.format(date);

        } catch (ParseException e) {
            log.error("parse date error,{}", dateString, e);
        }
        return toDate;
    }

    /**
     * 获取当前年
     * 
     * @return
     */
    public static int getCurrYear() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(DateTimeUtil.getNowDate());
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     * 
     * @return
     */
    public static int getCurrMonth() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(DateTimeUtil.getNowDate());
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前天
     * 
     * @return
     */
    public static int getCurrDay() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(DateTimeUtil.getNowDate());
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定年月可有的最大天数
     * 
     * @param strYear  年份
     * @param strMonth 月份
     * @return
     * @throws NumberFormatException
     */
    public static int getCurMonthDayNumber(String strYear, String strMonth) {
        int ret = Constant.ERROR_RESULT;
        if (Integer.parseInt(strMonth) > 0 && Integer.parseInt(strMonth) <= 12) {
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            cal.clear();
            cal.set(Integer.valueOf(strYear), Integer.valueOf(strMonth) - 1, 1);
            ret = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        return ret;
    }

    /**
     * 获取日期字符串代表上月的第一天
     * 
     * @param strDt
     * @return
     * @throws ParseException
     */
    public static String lastMnthBegDate(String strDt) throws ParseException {
        Date dt = parse(strDt);
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MONTH, -1);
        return format(cal.getTime());
    }

    /**
     * 获取日期字符串代表日期的上月第一天
     * 
     * @param strDt   日期字符串
     * @param pattern 日期格式
     * @return
     * @throws ParseException
     */
    public static String lastMnthBegDate(String strDt, String pattern) throws ParseException {
        Date dt = parse(strDt, pattern);
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MONTH, -1);
        return format(cal.getTime());
    }

    /**
     * 获取日期字符串代表的月份偏移后的第一天
     * 
     * @param strDt     日期字符串
     * @param pattern   日期格式
     * @param monthDiff 偏移月份 正数向前 负数向后
     * @return
     * @throws ParseException
     */
    public static String monthBegDate(String strDt, String pattern, int monthDiff) throws ParseException {
        Date dt = parse(strDt, pattern);
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MONTH, monthDiff);
        return format(cal.getTime());
    }

    /**
     * 获取日期字符串代表日期的上月最后一天
     * 
     * @param strDt 日期字符串
     * @return
     * @throws ParseException
     */
    public static String lastMnthEndDate(String strDt) throws ParseException {
        Date dt = parse(strDt);
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.SECOND, -1);
        return format(cal.getTime());
    }

    /**
     * 获取日期字符串代表日期的上月最后一天
     * 
     * @param strDt   日期字符串
     * @param pattern 日期格式
     * @return
     * @throws ParseException
     */
    public static String lastMnthEndDate(String strDt, String pattern) throws ParseException {
        Date dt = parse(strDt, pattern);
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.SECOND, -1);
        return format(cal.getTime());
    }

    /**
     * 获取日期字符串代表日期的偏移后的最后一天
     * 
     * @param strDt     日期字符串
     * @param pattern   日期格式
     * @param monthDiff 偏移月份 正数向前 负数向后
     * @return
     * @throws ParseException
     */
    public static String monthEndDate(String strDt, String pattern, int monthDiff) throws ParseException {
        Date dt = parse(strDt, pattern);
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MONTH, monthDiff + 1);
        cal.add(Calendar.SECOND, -1);
        return format(cal.getTime());
    }

    /**
     * 格式化日期
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        return date == null ? "" : format(date, Constant.DATE_JAVA_FORMAT);
    }

    public static String formatDateTime(Date date) {
        return format(date, Constant.DATETIME_JAVA_FORMAT);
    }

    /**
     * 格式化日期
     * 
     * @param date
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 解析日期
     * 
     * @param strDate 日期字符串
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate) throws ParseException {
        return StringUtil.isBlank(strDate) ? null : parse(strDate, Constant.DATE_JAVA_FORMAT);
    }

    /**
     * 解析日期
     * 
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        if (StringUtil.isBlank(strDate))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        return sdf.parse(strDate);
    }

    /**
     * 得到上一个月份（liang） 当跨年时没有错误
     * 
     * @param
     */
    public static String getLastMonth() {
        GregorianCalendar now = new GregorianCalendar();
        now.add(GregorianCalendar.MONTH, -1);
        return format(now.getTime(), Constant.DATE_FILE_FORMAT_SHORT);
    }

    /**
     * 将时间毫秒数转换为天<br/>
     * 需要其它时间取消注释
     * 
     * @param 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author wenglin
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        return days + "";
    }

    /**
     * 将毫秒转换为小时
     * 
     * @param mss
     * @return
     */
    public static String formatTimeDuringHour(long mss) {
        long days = mss / (1000 * 60 * 60);
        return days + "";
    }

    /**
     * 把当前日期字符串转换成需要格式的日期字符串 例如："2009-12-03 12:34:38" ==> "20091203123438"
     * 
     * @param oldTimeFomate 日期字符串
     * @param oldFormat     当前的日期字符串格式
     * @param newFormat     转换后的日期字符串格式
     * @return 转换后的格式
     */
    public static String fomateTimeS2S(String oldTime, String oldFormat, String newFormat) throws ParseException {
        Date date = null;
        String dateTime = "";
        try {
            if (!StringUtil.isBlank(oldTime) && !StringUtil.isBlank(oldFormat) && !StringUtil.isBlank(newFormat)) {
                date = parse(oldTime, oldFormat);
                dateTime = format(date, newFormat);
            }
        } catch (ParseException e) {
            log.error("parse time error,{},{},{}", oldTime, oldFormat, newFormat, e);
        }

        return dateTime;
    }

    /**
     * 校验日期格式 （暂时只支持校验“yyyy-MM-dd”格式）
     * 
     * @param strDate 校验日期字符串
     * @param dateFmt 校验格式
     * @return
     */
    public static boolean checkDate(String strDate, String dateFmt) {
        String eL = "";
        if ("yyyy-MM-dd".equals(dateFmt)) {
            eL = "^((\\d{2}(([02468][048])|([13579][26]))\\-((((0?[13578])|(1[02]))\\-((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))\\-((0?[1-9])|([1-2][0-9])|(30)))|(0?2\\-((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))\\-((((0?[13578])|(1[02]))\\-((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))\\-((0?[1-9])|([1-2][0-9])|(30)))|(0?2\\-((0?[1-9])|(1[0-9])|(2[0-8]))))))$";
        }

        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(strDate);
        return m.matches();
    }

    // http://hi.baidu.com/gglzf4/item/a93cc34b79c9760c6dc2f032
    /**
     * 对当前日期的修改，
     * 
     * @param date       将要修改的当前日期
     * @param updatetype 修改的日期的类型，用 y、M、d、h、代表 年、月、日、小时
     * @param num        增减的日期数， 正整数代表加 ， 负整数代表减
     * @param returnType 将要把日期格式成的日期格式
     * @return 指定格式的日期字符串
     */
    public static String getPreviousDate7(Date date, String updatetype, int num, String returnType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (updatetype.equalsIgnoreCase("y")) {
            calendar.add(Calendar.YEAR, num);
        } else if (updatetype.equals("M")) {
            calendar.add(Calendar.MONTH, num);
        } else if (updatetype.equalsIgnoreCase("d")) {
            calendar.add(Calendar.DAY_OF_MONTH, num);
        } else if (updatetype.equalsIgnoreCase("h")) {
            calendar.add(Calendar.HOUR, num);
        }
        return format(calendar.getTime(), returnType);
    }

    public static String formatDatemhs(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(Constant.DATETIME_JAVA_FORMAT);
        return df.format(date);
    }

    /**
     * 获取当前时间 yyyyMMdd
     * 
     * @return
     */
    public static String getSysDate() {
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(d);
    }

    /**
     * 获得当前日期的前一天
     * 
     * @return string 当前日期的前一天
     * @author kidd
     */
    public static String getLastDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(DateTimeUtil.getNowDate());
        int today = c.get(Calendar.DATE);
        c.set(Calendar.DATE, today - 1);
        return new SimpleDateFormat(Constant.DATE_FILE_FORMAT).format(c.getTime());
    }

    /**
     * str1时间和当前服务期时间比较
     * 
     * @param str1 格式
     * @param str2
     * @return
     */
    public static int compareDate(String str1) {
        int result = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat(Constant.DATETIME_JAVA_FORMAT);
            Date date = df.parse(str1);
            result = date.getTime() - new Date().getTime() >= 0 ? 1 : -1;
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    /**
     * 给指定的时间添加指定的分钟数
     * 
     * @param paramDate 时间
     * @param minutes   分钟数 例如增加一天可传 24*60
     * @return 增加后的时间
     */
    public static Date dateAddMinutes(Date paramDate, int minutes) {
        return new Date(paramDate.getTime() + minutes * 60 * 1000);
    }

    /**
     * 获得HP格式时间戳字符串，添加指定分钟数，转为格式化日期类型
     * 
     * @param strTime 时间类型字符串（如：20140307162733）
     * @param minutes 分钟数
     * @return
     * @author yeqh
     */
    public static String getSysNowTime() {
        Calendar c = Calendar.getInstance();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
    }
}
