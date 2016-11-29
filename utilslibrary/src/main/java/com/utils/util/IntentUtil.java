package com.utils.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IntentUtil {
//	/**
//	 *
//	 *  函数名称 : dialIntent
//	 *  功能描述 :  调用电话直接拨打
//	 *
//	 */
//	public static void dialIntent(Context context,String tel){
//		Uri uri=Uri.parse("tel:"+tel);
//		Intent intent=new Intent(Intent.ACTION_CALL,uri);
//		context.startActivity(intent);
//	}
	
	/**
	 * 
	 *  函数名称 : intentNoparams
	 *  功能描述 :  不带参数跳转的跳转
	 *
	 */
	public static void intentNoparams(Context context,Class<?> cls){
		Intent intent = new Intent();
		intent.setClass(context, cls);
		context.startActivity(intent);
	}
	
	/**
	 * 
	 *  函数名称 : intentParams
	 *  功能描述 :  带参数跳转的跳转方法
	 */
	@SuppressWarnings("rawtypes")
	public static void intentParams(Context context,Class<?> cls,HashMap<String, String> map){
		Intent intent = new Intent();
		intent.setClass(context, cls);
		Iterator iter = map.entrySet().iterator();
	while (iter.hasNext()) {
	Map.Entry entry = (Map.Entry) iter.next();
		Object key = entry.getKey();
		Object value = entry.getValue();
		intent.putExtra(key.toString(), value.toString());
		}
		context.startActivity(intent);
	}

	public static void installApk(Context context,String url){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(new File(Environment
						.getExternalStorageDirectory(), url)),"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}
