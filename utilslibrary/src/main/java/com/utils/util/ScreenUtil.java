package com.utils.util;



import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;


/**
 * 屏幕相关 <br>
 * Author:ryan <br>
 * Date:2011-5-25下午01:49:24
 */
public class ScreenUtil {

	/**
	 * 将px值转换为sp值
	 *
	 * @param pxValue
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float scale = getDisplayMetrics(context).density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将sp值转换为px值
	 *
	 * @param spValue
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float scale = getDisplayMetrics(context).density;
		return (int) (spValue * scale + 0.5f);
	}

	/**
	 * dp转px
	 * @param context
	 * @param dipValue
     * @return
     */
	public static int dip2px(Context context, float dipValue) {
		final float scale = getDisplayMetrics(context).density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px转dp
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = getDisplayMetrics(context).density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 判断是否横屏
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isOrientationLandscape(Context context) {
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return true;
		}
		return false;
	}

	/**
	 * 返回屏幕尺寸
	 * 
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		return context.getResources().getDisplayMetrics();
	}

	/**
	 * 返回屏幕尺寸宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		DisplayMetrics metrics = getDisplayMetrics(context);
		return metrics.widthPixels;
	}

	/**
	 * 返回屏幕尺寸高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		DisplayMetrics metrics = getDisplayMetrics(context);
		return metrics.heightPixels;
	}
}
