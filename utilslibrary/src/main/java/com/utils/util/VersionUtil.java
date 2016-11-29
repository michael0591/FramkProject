package com.utils.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 获取版本信息工具类
 */
public class VersionUtil {

    /**
     * 函数名称 : getVersionName
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param context
     * @return 修改记录：
     * 描述	：获得当前应用版本名字
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = "0.0";
        }
        return versionName;
    }

    /**
     * 函数名称 : getVersionCode
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param context
     * @return 修改记录：
     * 描述	：获得当前应用版本号
     */
    public static int getVersionCode(Context context) {
        int code = 0;
        try {
            code = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            code = 0;
        }
        return code;
    }
}
