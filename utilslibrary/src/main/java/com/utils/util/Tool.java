package com.utils.util;

/**
 * 工具类
 */
public class Tool 
{

	private static String[] units = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿" };
	private static char[] numArray = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };

	/**
	 * 将数字123变为大写的123
	 * @param num
	 * @return
     */
	public static String foematInteger(int num) {
		char[] val = String.valueOf(num).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			String m = val[i] + "";
			int n = Integer.valueOf(m);
			boolean isZero = n == 0;
			String unit = units[(len - 1) - i];
			if (unit.length()!=0){
				if (n==1){
					sb.append(unit);
				}
				else {
					sb.append(numArray[n]);
					sb.append(unit);
				}
			}
			else {
				if (n!=0) {
					sb.append(numArray[n]);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将字符串型的价格转为float型价格
	 * @param price
	 * @return
     */
	public static float stringToFloat(String price){
		if (price.indexOf(".")!=-1){
			return Float.parseFloat(price);
		}else{
			return Float.parseFloat(price+".00");
		}
	}
}
