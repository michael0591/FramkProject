package com.utils.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份证验证工具类
 */
public class IDCheckUtil
{
	/**
	 * 校验身份证号的合法性 <br>
	 * （现在仅检查长度，已经包含内容合法性，不检验真实性）
	 * 
	 * @param idCard
	 *            身份证号 18位身份证 年多了19 和 最后一位校验位
	 * 			前两位地区   7-14出生日期  17 性别，最后一位校验位
	 * @return true 合法 false 不合法
	 */
	public static boolean checkIdCard(String idCard)
	{
		String regEx = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
		Matcher matcherObj = Pattern.compile(regEx).matcher(idCard);

		if (matcherObj.matches())
		{
			// 地区验证
			Map<String, String> map = GetAreaCode();
			if (map.get(idCard.substring(0, 2)) == null)
			{
				return false;
			}
			// 出生日期验证
			if (!checkIDEndTime(idCard))
			{
				return false;
			}
			if (idCard.length() == 18)
			{
				// 现在计算校验码是否正确。
				int sigma = 0;
				// 系统数表
				Integer[] coeTable = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
						5, 8, 4, 2 };
				// 校验码表
				String[] codeTable = { "1", "0", "X", "9", "8", "7", "6", "5",
						"4", "3", "2" };

				// 将身份证每一位乘以系数表中的系数，结果相加。
				for (int i = 0; i < 17; i++)
				{
					int ai = Integer.parseInt(idCard.substring(i, i + 1));
					int wi = coeTable[i];
					sigma += ai * wi;
				}
				// 结果取 11 的余数
				int number = sigma % 11;
				// 使用余数做索引，取校验码。
				String check_number = codeTable[number];
				if (idCard.substring(17).equalsIgnoreCase(check_number))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	// 检测时间格式
	private static boolean checkIDEndTime(String idno)
	{
		try
		{
			int year = 0;
			int month = 0;
			int day = 0;
			// 提取年、月、日
			// 15位身份证 年少了19
			if (idno.length() == 15)
			{
				year = Integer.parseInt("19" + idno.substring(6, 8));
				month = Integer.parseInt(idno.substring(8, 10));
				day = Integer.parseInt(idno.substring(10, 12));
			}
			else
			{
				year = Integer.parseInt(idno.substring(6, 10));
				month = Integer.parseInt(idno.substring(10, 12));
				day = Integer.parseInt(idno.substring(12, 14));
			}
			// 检测年
			if (year < 1900 || year > 3000)
			{
				return false;
			}
			// 检测月
			if (month < 1 || month > 12)
			{
				return false;
			}
			// 检测日
			if (day < 1 || day > 31)
			{
				return false;
			}
			// 小月
			if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day > 30)
			{
				return false;
			}
			// 2月
			if (month == 2)
			{
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				{
					if (day > 29)
					{
						return false;
					}
				}
				else
				{
					if (day > 28)
					{
						return false;
					}
				}
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	private static Map<String, String> GetAreaCode()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("11", "北京");
		map.put("12", "天津");
		map.put("13", "河北");
		map.put("14", "山西");
		map.put("15", "内蒙古");
		map.put("21", "辽宁");
		map.put("22", "吉林");
		map.put("23", "黑龙江");
		map.put("31", "上海");
		map.put("32", "江苏");
		map.put("33", "浙江");
		map.put("34", "安徽");
		map.put("35", "福建");
		map.put("36", "江西");
		map.put("37", "山东");
		map.put("41", "河南");
		map.put("42", "湖北");
		map.put("43", "湖南");
		map.put("44", "广东");
		map.put("45", "广西");
		map.put("46", "海南");
		map.put("50", "重庆");
		map.put("51", "四川");
		map.put("52", "贵州");
		map.put("53", "云南");
		map.put("54", "西藏");
		map.put("61", "陕西");
		map.put("62", "甘肃");
		map.put("63", "青海");
		map.put("64", "宁夏");
		map.put("65", "新疆");
		map.put("71", "台湾");
		map.put("81", "香港");
		map.put("82", "澳门");
		map.put("91", "国外");
		return map;
	}
}