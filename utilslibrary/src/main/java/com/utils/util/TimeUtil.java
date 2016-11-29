package com.utils.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtil
{
	/**
	 * 当前时间转换
	 * 
	 * @return
	 */
	public static String getCurrentTime()
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
				.getInstance().getTime());
	}

	/**
	 * 将日期转换成指定格式
	 * @param date 日期
	 * @param pattern 转换格式 eg：'yyyy-MM-dd HH:mm:ss' 2014-12-22 10:16:13
	 * @return
	 */
	public static final String formatDate(Date date, String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 在字符串中插入时间格式
	 * @param str 时间字符串 eg:20141222101613->2014-12-22 10:16:13
	 * @return
	 */
	public static String converDateString(String str)
	{
		if (StringUtil.isEmptyOrNull(str))
		{
			return "时间格式错误";
		}

		StringBuffer sb = new StringBuffer(str.trim());
		if (str.length() > 3)
		{
			sb.insert(4, '-');
		}
		if (str.length() > 6)
		{
			sb.insert(7, '-');
		}
		if (str.length() > 8)
		{
			sb.insert(10, ' ');
		}
		if (str.length() > 10)
		{
			sb.insert(13, ':');
		}
		if (str.length() > 12)
		{
			sb.insert(16, ':');
		}
		return sb.toString();
	}

}
