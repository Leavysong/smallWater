package com.df.smallwater.smallwater.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	public static String gettime() {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间

		String time = formatter1.format(curDate);

		return time;
	}


	public static String get8time() {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间

		String time = formatter1.format(curDate);

		return time;
	}


	public static String get8timeaddday() {

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date curDate = new Date();// 获取当前时间

		String time = formatter1.format(curDate.getTime()+1000*60*60*24);

		return time;
	}

	public static String gettimename() {

		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddhhmmss");

		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间

		String time = formatter2.format(curDate);
		return time;
	}



	public static String gettimenamesecond() {

		SimpleDateFormat formatter2 = new SimpleDateFormat("mmss");

		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间

		String time = formatter2.format(curDate);
		return time;
	}


	public static long getCurtime() {


		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间

		return curDate.getTime();
	}




	public static Long gettime(String time) {
		time = time.replace("-", "");
		time = time.replace(" ", "");
		time = time.replace(":", "");

		return Long.valueOf(time);
	}


	// string类型转换为long类型
	// strTime要转换的String类型的时间
	// formatType时间格式
	// strTime的时间格式和formatType的时间格式必须相同
	public static long stringToLong(String strTime, String formatType) throws ParseException {
		Date date = stringToDate(strTime, formatType); // String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			long currentTime = dateToLong(date); // date类型转成long类型
			return currentTime;
		}
	}


	// string类型转换为date类型
	// strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
	// HH时mm分ss秒，
	// strTime的时间格式必须要与formatType的时间格式相同
	public static Date stringToDate(String strTime, String formatType) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		Date date = null;
		date = formatter.parse(strTime);
		return date;
	}


	// date类型转换为long类型
	// date要转换的date类型的时间
	public static long dateToLong(Date date) {
		return date.getTime();
	}

	/**
	 * 把long 转换成 日期 再转换成String类型
	 */
	public String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	/**
	 * 对比时间有没有过一年
	 * @param stime 开始时间
	 * @param ftime 结束时间
	 * @return 0可以用1不可医用
	 * @throws ParseException
	 */
	public static int dateafteryear(String fintime , String curtime) throws ParseException{

		Date findate = stringToDate(fintime, "yyyy-MM-dd hh:mm:ss");
		Date curdate = stringToDate(curtime , "yyyy-MM-dd hh:mm:ss");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(findate);
//		        calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.add(Calendar.YEAR, 1);
		Date date = calendar.getTime();

		//结束时间在当前时间之后还能用，结束时间在当前时间之后不能用  ————————cur是——————————fin——————————cur否
		if(date.after(curdate)){
			return 0 ;
		}else{
			return 1 ;
		}


	}

	public static String getdateaddyear(String fintime ) throws ParseException {

		Date findate = stringToDate(fintime, "yyyy-MM-dd hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(findate);
//		        calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.add(Calendar.YEAR, 1);
		Date date = calendar.getTime();

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


		String time = formatter1.format(date);
		return time ;
	}


}
