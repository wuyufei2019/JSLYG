package com.cczu.sys.comm.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author jason
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTimeHms() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
	/**
	 * 获取一天的开始时间
	 * @param date
	 * @return  yyyy-MM-dd 00:00:00
	 */
	public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取一天的结束时间
	 * @param date
	 * @return yyyy-MM-dd 23:59:59
	 */
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 判断字符串是否是日期
	 * @param timeString
	 * @return
	 */
	public static boolean isDate(String timeString){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try{
			format.parse(timeString);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * 格式化时间
	 * @param timestamp
	 * @return
	 */
	public static String dateFormat(Date timestamp){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}
	
	/**
	 * 获取系统时间Timestamp
	 * @return
	 */
	public static Timestamp getSysTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * 获取系统时间Timestamp
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Timestamp getSystemTime()
	{
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		java.sql.Timestamp times = java.sql.Timestamp.valueOf(nowTime);
		return times;
	}
	
	/**
	 * 获取系统时间Timestamp
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Timestamp getTimestampFromStr(String datetime)
	{
		return Timestamp.valueOf(datetime);
	}
	
	/**
	 * 获取系统时间Date
	 * @return
	 */
	public static Date getSysDate(){
		return new Date();
	}
	
	/**
	 * 生成时间随机数 
	 * @return
	 */
	public static String getDateRandom(){
		String s=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return s;
	}
	
	
	/** 
	 * 获取现在时间,这个好用 
	 *  
	 * @return返回长时间格式 yyyy-MM-dd HH:mm:ss 
	 */  
	public static Date getSqlDate() {  
	    Date sqlDate = new java.sql.Date(new Date().getTime());  
	    return sqlDate;  
	}  
	  
	/** 
	 * 获取现在时间 
	 *  
	 * @return返回长时间格式 yyyy-MM-dd HH:mm:ss 
	 */  
	public static Date getNowDate() {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(currentTime);  
	    ParsePosition pos = new ParsePosition(8);  
	    Date currentTime_2 = formatter.parse(dateString, pos);  
	    return currentTime_2;  
	}  
	  
	/** 
	 * 获取现在时间 
	 *  
	 * @return返回短时间格式 yyyy-MM-dd 
	 */  
	public static Date getNowDateShort() {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(currentTime);  
	    ParsePosition pos = new ParsePosition(8);  
	    Date currentTime_2 = formatter.parse(dateString, pos);  
	    return currentTime_2;  
	}  
	  
	/** 
	 * 获取现在时间 
	 *  
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss 
	 */  
	public static String getStringDate() {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(currentTime);  
	    return dateString;  
	}  
	  
	/** 
	 * 获取现在时间 
	 *  
	 * @return 返回短时间字符串格式yyyy-MM-dd 
	 */  
	public static String getStringDateShort() {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(currentTime);  
	    return dateString;  
	}  
	  
	/** 
	 * 获取时间 小时:分;秒 HH:mm:ss 
	 *  
	 * @return 
	 */  
	public static String getTimeShort() {  
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
	    Date currentTime = new Date();  
	    String dateString = formatter.format(currentTime);  
	    return dateString;  
	}  
	  
	/** 
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss 
	 *  
	 * @param strDate 
	 * @return 
	 */  
	public static Date strToDateLong(String strDate) {  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    ParsePosition pos = new ParsePosition(0);  
	    Date strtodate = formatter.parse(strDate, pos);  
	    return strtodate;  
	}  
	  
	/** 
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss 
	 *  
	 * @param dateDate 
	 * @return 
	 */  
	public static String dateToStrLong(java.util.Date dateDate) {  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(dateDate);  
	    return dateString;  
	}  
	  
	/** 
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd 
	 *  
	 * @param dateDate 
	 * @param k 
	 * @return 
	 */  
	public static String dateToStr(java.util.Date dateDate) {  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(dateDate);  
	    return dateString;  
	}  
	  
	/** 
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd 
	 *  
	 * @param strDate 
	 * @return 
	 */  
	public static Date strToDate(String strDate) {  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    ParsePosition pos = new ParsePosition(0);  
	    Date strtodate = formatter.parse(strDate, pos);  
	    return strtodate;  
	}  
	  
	/** 
	 * 得到现在时间 
	 *  
	 * @return 
	 */  
	public static Date getNow() {  
	    Date currentTime = new Date();  
	    return currentTime;  
	}  
	  
	/** 
	 * 提取一个月中的最后一天 
	 *  
	 * @param day 
	 * @return 
	 */  
	public static Date getLastDate(long day) {  
	    Date date = new Date();  
	    long date_3_hm = date.getTime() - 3600000 * 34 * day;  
	    Date date_3_hm_date = new Date(date_3_hm);  
	    return date_3_hm_date;  
	}  
	  
	/** 
	 * 得到现在时间 
	 *  
	 * @return 字符串 yyyyMMdd HHmmss 
	 */  
	public static String getStringToday() {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");  
	    String dateString = formatter.format(currentTime);  
	    return dateString;  
	}  
	  
	/** 
	 * 得到现在小时 
	 */  
	public static String getHour() {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(currentTime);  
	    String hour;  
	    hour = dateString.substring(11, 13);  
	    return hour;  
	}  
	  
	/** 
	 * 得到现在分钟 
	 *  
	 * @return 
	 */  
	public static String getTimeMin() {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(currentTime);  
	    String min;  
	    min = dateString.substring(14, 16);  
	    return min;  
	}  
	  
	/** 
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。 
	 *  
	 * @param sformat 
	 *            yyyyMMddhhmmss 
	 * @return 
	 */  
	public static String getUserDate(String sformat) {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat(sformat);  
	    String dateString = formatter.format(currentTime);  
	    return dateString;  
	}  
	  
	/** 
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟 
	 */  
	public static String getTwoHour(String st1, String st2) {  
	    String[] kk = null;  
	    String[] jj = null;  
	    kk = st1.split(":");  
	    jj = st2.split(":");  
	    if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))  
	        return "0";  
	    else {  
	        double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])  
	                / 60;  
	        double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])  
	                / 60;  
	        if ((y - u) > 0)  
	            return y - u + "";  
	        else  
	            return "0";  
	    }  
	}  
	  
	/** 
	 * 得到二个日期间的间隔天数 
	 */  
	public static String getTwoDay(String sj1, String sj2) {  
	    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
	    long day = 0;  
	    try {  
	        java.util.Date date = myFormatter.parse(sj1);  
	        java.util.Date mydate = myFormatter.parse(sj2);  
	        day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);  
	    } catch (Exception e) {  
	        return "";  
	    }  
	    return day + "";  
	}  
	  
	/** 
	 * 时间前推或后推分钟,其中JJ表示分钟. 
	 */  
	public static String getPreTime(String sj1, String jj) {  
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String mydate1 = "";  
	    try {  
	        Date date1 = format.parse(sj1);  
	        long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;  
	        date1.setTime(Time * 1000);  
	        mydate1 = format.format(date1);  
	    } catch (Exception e) {  
	    }  
	    return mydate1;  
	}  
	  
	/** 
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数 
	 */  
	public static String getNextDay(String nowdate, String delay) {  
	    try {  
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	        String mdate = "";  
	        Date d = strToDate(nowdate);  
	        long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24  
	                * 60 * 60;  
	        d.setTime(myTime * 1000);  
	        mdate = format.format(d);  
	        return mdate;  
	    } catch (Exception e) {  
	        return "";  
	    }  
	}  
	  
	/** 
	 * 判断是否润年 
	 *  
	 * @param ddate 
	 * @return 
	 */  
	public static boolean isLeapYear(String ddate) {  
	    /** 
	     * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年 
	     * 3.能被4整除同时能被100整除则不是闰年 
	     */  
	    Date d = strToDate(ddate);  
	    GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();  
	    gc.setTime(d);  
	    int year = gc.get(Calendar.YEAR);  
	    if ((year % 400) == 0)  
	        return true;  
	    else if ((year % 4) == 0) {  
	        if ((year % 100) == 0)  
	            return false;  
	        else  
	            return true;  
	    } else  
	        return false;  
	}  
	  
	/** 
	 * 返回美国时间格式 26 Apr 2006 
	 *  
	 * @param str 
	 * @return 
	 */  
	public static String getEDate(String str) {  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    ParsePosition pos = new ParsePosition(0);  
	    Date strtodate = formatter.parse(str, pos);  
	    String j = strtodate.toString();  
	    String[] k = j.split(" ");  
	    return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);  
	}  
	  
	/** 
	 * 获取一个月的最后一天 
	 *  
	 * @param dat 
	 * @return 
	 */  
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd  
	    String str = dat.substring(0, 8);  
	    String month = dat.substring(5, 7);  
	    int mon = Integer.parseInt(month);  
	    if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8  
	            || mon == 10 || mon == 12) {  
	        str += "31";  
	    } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {  
	        str += "30";  
	    } else {  
	        if (isLeapYear(dat)) {  
	            str += "29";  
	        } else {  
	            str += "28";  
	        }  
	    }  
	    return str;  
	}  
	  
	/** 
	 * 判断二个时间是否在同一个周 
	 *  
	 * @param date1 
	 * @param date2 
	 * @return 
	 */  
	public static boolean isSameWeekDates(Date date1, Date date2) {  
	    Calendar cal1 = Calendar.getInstance();  
	    Calendar cal2 = Calendar.getInstance();  
	    cal1.setTime(date1);  
	    cal2.setTime(date2);  
	    int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);  
	    if (0 == subYear) {  
	        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2  
	                .get(Calendar.WEEK_OF_YEAR))  
	            return true;  
	    } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {  
	        // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周  
	        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2  
	                .get(Calendar.WEEK_OF_YEAR))  
	            return true;  
	    } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {  
	        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2  
	                .get(Calendar.WEEK_OF_YEAR))  
	            return true;  
	    }  
	    return false;  
	}  
	  
	/** 
	 * 产生周序列,即得到当前时间所在的年度是第几周 
	 *  
	 * @return 
	 */  
	public static String getSeqWeek() {  
	    Calendar c = Calendar.getInstance(Locale.CHINA);  
	    String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));  
	    if (week.length() == 1)  
	        week = "0" + week;  
	    String year = Integer.toString(c.get(Calendar.YEAR));  
	    return year + week;  
	}  
	  
	/** 
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号 
	 *  
	 * @param sdate 
	 * @param num 
	 * @return 
	 */  
	public static String getWeek(String sdate, String num) {  
	    // 再转换为时间  
	    Date dd = strToDate(sdate);  
	    Calendar c = Calendar.getInstance();  
	    c.setTime(dd);  
	    if (num.equals("1")) // 返回星期一所在的日期  
	        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
	    else if (num.equals("2")) // 返回星期二所在的日期  
	        c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);  
	    else if (num.equals("3")) // 返回星期三所在的日期  
	        c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);  
	    else if (num.equals("4")) // 返回星期四所在的日期  
	        c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);  
	    else if (num.equals("5")) // 返回星期五所在的日期  
	        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);  
	    else if (num.equals("6")) // 返回星期六所在的日期  
	        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);  
	    else if (num.equals("0")) // 返回星期日所在的日期  
	        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
	    return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());  
	}  
	  
	/** 
	 * 根据一个日期，返回是星期几的字符串 
	 *  
	 * @param sdate 
	 * @return 
	 */  
	public static String getWeek(String sdate) {  
	    // 再转换为时间  
	    Date date = strToDate(sdate);  
	    Calendar c = Calendar.getInstance();  
	    c.setTime(date);  
	    // int hour=c.get(Calendar.DAY_OF_WEEK);  
	    // hour中存的就是星期几了，其范围 1~7  
	    // 1=星期日 7=星期六，其他类推  
	    return new SimpleDateFormat("EEEE").format(c.getTime());  
	}  
	  
	public static String getWeekStr(String sdate) {  
	    String str = "";  
	    str = getWeek(sdate);  
	    if ("1".equals(str)) {  
	        str = "星期日";  
	    } else if ("2".equals(str)) {  
	        str = "星期一";  
	    } else if ("3".equals(str)) {  
	        str = "星期二";  
	    } else if ("4".equals(str)) {  
	        str = "星期三";  
	    } else if ("5".equals(str)) {  
	        str = "星期四";  
	    } else if ("6".equals(str)) {  
	        str = "星期五";  
	    } else if ("7".equals(str)) {  
	        str = "星期六";  
	    }  
	    return str;  
	}  
	  
	/** 
	 * 两个时间之间的天数 
	 *  
	 * @param date1 
	 * @param date2 
	 * @return 
	 */  
	public static long getDays(String date1, String date2) {  
	    if (date1 == null || date1.equals(""))  
	        return 0;  
	    if (date2 == null || date2.equals(""))  
	        return 0;  
	    // 转换为标准时间  
	    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
	    java.util.Date date = null;  
	    java.util.Date mydate = null;  
	    try {  
	        date = myFormatter.parse(date1);  
	        mydate = myFormatter.parse(date2);  
	    } catch (Exception e) {  
	    }  
	    long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);  
	    return day;  
	}  
	  
	/** 
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间 
	 * 此函数返回该日历第一行星期日所在的日期 
	 *  
	 * @param sdate 
	 * @return 
	 */  
	public static String getNowMonth(String sdate) {  
	    // 取该时间所在月的一号  
	    sdate = sdate.substring(0, 8) + "01";  
	    // 得到这个月的1号是星期几  
	    Date date = strToDate(sdate);  
	    Calendar c = Calendar.getInstance();  
	    c.setTime(date);  
	    int u = c.get(Calendar.DAY_OF_WEEK);  
	    String newday = getNextDay(sdate, (1 - u) + "");  
	    return newday;  
	}  
	  
	/** 
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数 
	 *  
	 * @param k 
	 *            表示是取几位随机数，可以自己定 
	 */  
	public static String getNo(int k) {  
	    return getUserDate("yyyyMMddhhmmss") + getRandom(k);  
	}  
	  
	/** 
	 * 返回一个随机数 
	 *  
	 * @param i 
	 * @return 
	 */  
	public static String getRandom(int i) {  
	    Random jjj = new Random();  
	    // int suiJiShu = jjj.nextInt(9);  
	    if (i == 0)  
	        return "";  
	    String jj = "";  
	    for (int k = 0; k < i; k++) {  
	        jj = jj + jjj.nextInt(9);  
	    }  
	    return jj;  
	}  
	  
	/** 
	 * @param args 
	 */  
	public static boolean RightDate(String date) {  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	    ;  
	    if (date == null)  
	        return false;  
	    if (date.length() > 10) {  
	        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	    } else {  
	        sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    }  
	    try {  
	        sdf.parse(date);  
	    } catch (ParseException pe) {  
	        return false;  
	    }  
	    return true;  
	}  
	  
	/*************************************************************************** 
	 * //nd=1表示返回的值中包含年度 //yf=1表示返回的值中包含月份 //rq=1表示返回的值中包含日期 //format表示返回的格式 1 
	 * 以年月日中文返回 2 以横线-返回 // 3 以斜线/返回 4 以缩写不带其它符号形式返回 // 5 以点号.返回 
	 **************************************************************************/  
	public static String getStringDateMonth(String sdate, String nd, String yf,  
	        String rq, String format) {  
	    Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(currentTime);  
	    String s_nd = dateString.substring(0, 4); // 年份  
	    String s_yf = dateString.substring(5, 7); // 月份  
	    String s_rq = dateString.substring(8, 10); // 日期  
	    String sreturn = "";  
	    if (sdate == null || sdate.equals("") || !isDate(sdate)) { // 处理空值情况  
	        if (nd.equals("1")) {  
	            sreturn = s_nd;  
	            // 处理间隔符  
	            if (format.equals("1"))  
	                sreturn = sreturn + "年";  
	            else if (format.equals("2"))  
	                sreturn = sreturn + "-";  
	            else if (format.equals("3"))  
	                sreturn = sreturn + "/";  
	            else if (format.equals("5"))  
	                sreturn = sreturn + ".";  
	        }  
	        // 处理月份  
	        if (yf.equals("1")) {  
	            sreturn = sreturn + s_yf;  
	            if (format.equals("1"))  
	                sreturn = sreturn + "月";  
	            else if (format.equals("2"))  
	                sreturn = sreturn + "-";  
	            else if (format.equals("3"))  
	                sreturn = sreturn + "/";  
	            else if (format.equals("5"))  
	                sreturn = sreturn + ".";  
	        }  
	        // 处理日期  
	        if (rq.equals("1")) {  
	            sreturn = sreturn + s_rq;  
	            if (format.equals("1"))  
	                sreturn = sreturn + "日";  
	        }  
	    } else {  
	        // 不是空值，也是一个合法的日期值，则先将其转换为标准的时间格式  
	        // sdate = roc.util.RocDate.getOKDate(sdate);  
	        s_nd = sdate.substring(0, 4); // 年份  
	        s_yf = sdate.substring(5, 7); // 月份  
	        s_rq = sdate.substring(8, 10); // 日期  
	        if (nd.equals("1")) {  
	            sreturn = s_nd;  
	            // 处理间隔符  
	            if (format.equals("1"))  
	                sreturn = sreturn + "年";  
	            else if (format.equals("2"))  
	                sreturn = sreturn + "-";  
	            else if (format.equals("3"))  
	                sreturn = sreturn + "/";  
	            else if (format.equals("5"))  
	                sreturn = sreturn + ".";  
	        }  
	        // 处理月份  
	        if (yf.equals("1")) {  
	            sreturn = sreturn + s_yf;  
	            if (format.equals("1"))  
	                sreturn = sreturn + "月";  
	            else if (format.equals("2"))  
	                sreturn = sreturn + "-";  
	            else if (format.equals("3"))  
	                sreturn = sreturn + "/";  
	            else if (format.equals("5"))  
	                sreturn = sreturn + ".";  
	        }  
	        // 处理日期  
	        if (rq.equals("1")) {  
	            sreturn = sreturn + s_rq;  
	            if (format.equals("1"))  
	                sreturn = sreturn + "日";  
	        }  
	    }  
	    return sreturn;  
	}  
	  
	public static String getNextMonthDay(String sdate, int m) {  
	    sdate = getOKDate(sdate);  
	    int year = Integer.parseInt(sdate.substring(0, 4));  
	    int month = Integer.parseInt(sdate.substring(5, 7));  
	    month = month + m;  
	    if (month < 0) {  
	        month = month + 12;  
	        year = year - 1;  
	    } else if (month > 12) {  
	        month = month - 12;  
	        year = year + 1;  
	    }  
	    String smonth = "";  
	    if (month < 10)  
	        smonth = "0" + month;  
	    else  
	        smonth = "" + month;  
	    return year + "-" + smonth + "-10";  
	}  
	  
	public static String getOKDate(String sdate) {  
	    if (sdate == null || sdate.equals(""))  
	        return getStringDateShort();  
	    if (!isDate(sdate)) {  
	        sdate = getStringDateShort();  
	    }  
	    // 将“/”转换为“-”  
	    sdate = sdate.replace("/", "-");  
	    // 如果只有8位长度，则要进行转换  
	    if (sdate.length() == 8)  
	        sdate = sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-"  
	                + sdate.substring(6, 8);  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    ParsePosition pos = new ParsePosition(0);  
	    Date strtodate = formatter.parse(sdate, pos);  
	    String dateString = formatter.format(strtodate);  
	    return dateString;  
	}  
	
	/**
	 * 将String日期转化成 java.sql.Date
	 * @param str 
	 * @return  java.sql.Date
	 */
	public static java.sql.Date getSqlDate(String dataStr) {
		return java.sql.Date.valueOf(dataStr);
	}
	
    /** 
     * 返回unix时间戳 (1970年至今的秒数) 
     * @return 
     */  
    public static long getUnixStamp(){  
            return System.currentTimeMillis()/1000;  
    }  
      
    /** 
     * 得到昨天的日期 
     * @return 
     */  
    public static String getYestoryDate() {  
            Calendar calendar = Calendar.getInstance();    
            calendar.add(Calendar.DATE,-1);  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            String yestoday = sdf.format(calendar.getTime());  
            return yestoday;  
    }  
      
    /** 
     * 得到今天的日期 
     * @return 
     */  
    public static  String getTodayDate(){  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            String date = sdf.format(new Date());  
            return date;  
    }  
      
    /** 
     * 时间戳转化为时间格式 
     * @param timeStamp 
     * @return 
     */  
    public static String timeStampToStr(long timeStamp) {  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            String date = sdf.format(timeStamp * 1000);  
            return date;  
    }  
      
    /** 
     * 得到日期   yyyy-MM-dd 
     * @param timeStamp  时间戳 
     * @return 
     */  
    public static String formatDate(long timeStamp) {     
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            String date = sdf.format(timeStamp*1000);  
            return date;  
    }  
      
    /** 
     * 得到时间  HH:mm:ss 
     * @param timeStamp   时间戳 
     * @return 
     */  
    public static String getTime(long timeStamp) {    
            String time = null;  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            String date = sdf.format(timeStamp * 1000);  
            String[] split = date.split("\\s");  
            if ( split.length > 1 ){  
                    time = split[1];  
            }  
            return time;  
    }  
      
    /** 
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前 
     *  
     * @param timeStamp 
     * @return 
     */  
    public static String convertTimeToFormat(long timeStamp) {  
            long curTime =System.currentTimeMillis() / (long) 1000 ;  
            long time = curTime - timeStamp;  

            if (time < 60 && time >= 0) {  
                    return "刚刚";  
            } else if (time >= 60 && time < 3600) {  
                    return time / 60 + "分钟前";  
            } else if (time >= 3600 && time < 3600 * 24) {  
                    return time / 3600 + "小时前";  
            } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {  
                    return time / 3600 / 24 + "天前";  
            } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {  
                    return time / 3600 / 24 / 30 + "个月前";  
            } else if (time >= 3600 * 24 * 30 * 12) {  
                    return time / 3600 / 24 / 30 / 12 + "年前";  
            } else {  
                    return "刚刚";  
            }  
    }  
      
    /** 
     * 将一个时间戳转换成提示性时间字符串，(多少分钟) 
     *  
     * @param timeStamp 
     * @return 
     */  
    public static String timeStampToFormat(long timeStamp) {  
            long curTime =System.currentTimeMillis() / (long) 1000 ;  
            long time = curTime - timeStamp;  
            return time/60 + "";  
    }

	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	public static Date getNextWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}


	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//		try {  
//	         System.out.print(Integer.valueOf(getTwoDay("2006-11-03 12:22:10",  
//	         "2006-11-02 11:22:09")));  
//	    } catch (Exception e) {  
//	        throw new Exception();  
//	    }  
		System.out.println(timeStampToStr(Long.valueOf("1472193000")));
		
		System.out.println(new java.util.Date(1472193000));
		
		String time = "1472193000";

		Date date = new Date(Long.valueOf(time)*1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sdf.format(date);
		System.out.println(dateStr);
	}
}
