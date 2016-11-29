package com.utils.util;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil
{
	/**
	 * 字符串不为空
	 * @param srcStr
	 * @return
	 */
	public static final boolean isEmptyOrNull(String srcStr)
	{
		if ((srcStr == null) || (srcStr.trim().length() <= 0))
		{
			return true;
		}
		return "null".equals(srcStr);
	}

	/**
	 * 字符串不为空也不为0
	 * @param srcStr
	 * @return
	 */
	public static final boolean isEmptyOrZero(String srcStr)
	{
		return (isEmptyOrNull(srcStr)) || ("0".equals(srcStr));
	}

	/**
	 * 正则式匹配
	 * @param srcStr
	 * @param pattern
	 * @return
	 */
	public static final boolean isPattern(String srcStr, String pattern)
	{
		//生成Pattern对象并且编译一个简单的正则表达式pattern
		Pattern p = Pattern.compile(pattern);
		//用Pattern类的matcher()方法生成一个Matcher对象
		Matcher m = p.matcher(srcStr);
		//使用find()方法查找第一个匹配的对象
		return m.find();
	}

	/**
	 * 格式化输出
	 * @param num 要转换的数
	 * @param pattern 转换格式 eg："0.00"(两位小数) 
	 * @return
	 */
	public static final String fomatInteger(int num, String pattern)
	{
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(num);
	}

	/**
	 * 将对象装成字符串
	 * @param o
	 * @return
	 */
	public static final String valueOf(Object o)
	{
		return o == null ? "" : o.toString();
	}

	/**
	 * 转成gbk格式
	 * @param szSrcData 被复制字符
	 * @param nOffset 起始位置
	 * @param nLen 长度
	 * @return
	 */
	public static String encodeWithGBK(byte[] szSrcData, int nOffset, int nLen)
	{
		try
		{
			byte[] szTemp = new byte[nLen];
			System.arraycopy(szSrcData, nOffset, szTemp, 0, nLen);
			return new String(szTemp, "GBK");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 截取len-1 长度的字符串 
	 * @param srcStr
	 * @return
	 */
	public static String GetSubString(String srcStr)
	{
		int index = srcStr.indexOf(0);
		if (index != -1)
		{
			return srcStr.substring(0, index);
		}
		return srcStr;
	}

	/**
	 * 截取指定位置的字符串和其他字符串结合
	 * @param srcStr 
	 * @param nLen 位置
	 * @param formatStr 
	 * @return
	 */
	public static String getSubstrByLen(String srcStr, int nLen, String formatStr)
	{
		if (srcStr.length() > nLen)
		{
			return srcStr.substring(0, nLen) + formatStr;
		}
		return srcStr;
	}
	/**
	 * 截取指定开始和结束位置的字符串
	 * @param srcStr
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String getSubstrByBeginEnd(String srcStr, int begin, int end)
	{
		if (srcStr.length() > end && begin < end)
		{
			return srcStr.substring(begin , end);
		}
		return srcStr;
	}

	/**
	 * 验证密码的长度
	 * @return true 符合长度
	 */
	public static boolean   chickPwdLength(String pwd1){
		if (pwd1.length()<6||pwd1.length()>12) {
			return  false;
		}
		return  true;
	}

	/**验证密码的是否相同
	 * @return true 相同
	 * false  不相同
	 */
	public static boolean   isTheSamePwd(String pwd1, String pew2){
		if (pwd1.equals(pew2)) {
			return  true;
		}else {
			return  false;
		}
	}

	/**
	 * 验证密码的是否由数字和字符组成numbers and characters
	 * @return  true 由数字和字符组成
	 */
	public static boolean   makeUpNumACharacters(String pwd1) {
		String telRegex = "(?!^\\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{6,12}";
		if (TextUtils.isEmpty(pwd1)) return false;
		else if (pwd1.matches(telRegex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证邮政编码
	 * @param post
	 * @return  true 邮编正确
	 */
	public static boolean checkPost(String post){
		if(post.matches("[1-9]\\d{5}(?!\\d)")){
			return true;
		}else{
			return false;
		}
	}
}