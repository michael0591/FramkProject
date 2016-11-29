package com.utils.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Map;

public class SharedUtil {
    public static String SHAREDPREFERENCE_NAME = "appinfo";


    /**
     * 获取某个字符字段的值
     *
     * @param context
     * @param key        想获取的字段名称   eg：prefs.getString("name", "")
     * @param defaultVal 默认值
     * @return
     */
    public static String getString(Context context, String key, String defaultVal) {
        return getString(context, SHAREDPREFERENCE_NAME, key, defaultVal);
    }

    public static String getString(Context context, String strFileName, String key, String defaultVal) {
        SharedPreferences prefs = context.getSharedPreferences(strFileName, 0);
        return prefs.getString(key, defaultVal);
    }

    /**
     * 保存共享数据
     *
     * @param context
     * @param key     保存的字段名称
     * @param val     值
     */
    public static void putString(Context context, String key, String val) {
        putString(context, SHAREDPREFERENCE_NAME, key, val);
    }

    /**
     * 保存共享数据
     *
     * @param context
     * @param strFileName 保存的文件
     * @param key
     * @param val
     */
    public static void putString(Context context, String strFileName, String key, String val) {
        SharedPreferences prefs = context.getSharedPreferences(strFileName, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, val);
        editor.commit();
    }

    /**
     * * 获取某个整型字段的值
     *
     * @param context
     * @param key
     * @param defaultVal
     * @return
     */
    public static int getInt(Context context, String key, int defaultVal) {
        return getInt(context, SHAREDPREFERENCE_NAME, key, defaultVal);
    }

    public static int getInt(Context context, String strFileName, String key, int defaultVal) {
        SharedPreferences prefs = context.getSharedPreferences(strFileName, 0);
        return prefs.getInt(key, defaultVal);
    }

    /**
     * 保存整型共享数据
     *
     * @param context
     * @param key
     * @param val
     */
    public static void putInt(Context context, String key, int val) {
        putInt(context, SHAREDPREFERENCE_NAME, key, val);
    }

    public static void putInt(Context context, String strFileName, String key, int val) {
        SharedPreferences prefs = context.getSharedPreferences(strFileName, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, val);
        editor.commit();
    }

    public static long getLong(Context context, String strFileName, String key, long defaultVal) {
        SharedPreferences prefs = context.getSharedPreferences(strFileName, 0);
        return prefs.getLong(key, defaultVal);
    }

    public static void putLong(Context context, String strFileName, String key, long val) {
        SharedPreferences prefs = context.getSharedPreferences(strFileName, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, val);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultVal) {
        SharedPreferences prefs = context.getSharedPreferences(
                SHAREDPREFERENCE_NAME, 0);
        return prefs.getBoolean(key, defaultVal);
    }


    public static void putBoolean(Context context, String key, boolean val) {
        SharedPreferences prefs = context.getSharedPreferences(
                SHAREDPREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, val);
        editor.commit();
    }

    public static Map<String, ?> getAll(Context context, String strFileName) {
        SharedPreferences prefs = context.getSharedPreferences(strFileName, 0);
        return prefs.getAll();
    }

    /**
     * 退出登录时删除用户登录信息
     */
    public static void clearUserData(Context context) {
        SharedPreferences userdata = context.getSharedPreferences(SHAREDPREFERENCE_NAME, 0);

        userdata.edit().clear().commit();
    }

    public static void putArrayList(Context context,ArrayList<String> list){
        SharedPreferences prefs = context.getSharedPreferences(SHAREDPREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();

        //date_size 到时得改成用户ID来作为KEY值
        editor.putInt(SharedUtil.getString(context,"id",""), list.size());
        for (int i = 0; i <list.size() ; i++) {

            editor.remove("date_"+i);
            editor.putString("date_" + i, list.get(i));
        }
        editor.commit();
    }

    public static ArrayList<String> getArrayList(Context context){
        ArrayList<String> list=new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(SHAREDPREFERENCE_NAME, 0);
        int count=prefs.getInt(SharedUtil.getString(context,"id",""),0);
        for (int i = 0; i < count; i++) {
            list.add(prefs.getString("date_"+i,""));
        }
        return list;
    }

//    /**
//     * 存储数据
//     *
//     * @param mContext
//     *            上下文
//     * @param tempName
//     *            存储名称
//     * @param tempList
//     *            数据集合
//     */
//    public static void saveOrderData(Context mContext, String tempName, List<?> tempList) {
//        SharedPreferences sps = mContext.getSharedPreferences("order", Context.MODE_PRIVATE);
//        // 创建字节输出流
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            // 创建对象输出流，并封装字节流
//            ObjectOutputStream oos = new ObjectOutputStream(baos);
//            // 将对象写入字节流
//            oos.writeObject(tempList);
//            String string=Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//
////            // 将字节流编码成base64的字符串
////             tempBase64 = new String(Base64.encodeBase64(baos.toByteArray()));
//            SharedPreferences.Editor editor = sps.edit();
//            editor.putString(tempName, string);
////            editor.putString(tempName, tempBase64);
//            editor.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (OutOfMemoryError e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//        }
//    }
//    /*
//  * SharedPreferences读取对象
//  */
//    public static ArrayList<com.mvplibrary.model.Data> getData(Context mContext, String tempName, ArrayList<com.mvplibrary.model.Data> tempList) {
//        SharedPreferences sps = mContext.getSharedPreferences("base64", Context.MODE_PRIVATE);
//        String tempBase64 = sps.getString(tempName, "");// 初值空
//        if (tempBase64.equals("")) {
//            return tempList;
//        }
//        // 读取字节
//        byte[] base64 =Base64.decode(tempBase64.getBytes(), Base64.DEFAULT);// Base64.decodeBase64(tempBase64.getBytes());
//        // 封装到字节流
//        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
//        try {
//            // 再次封装
//            ObjectInputStream ois = new ObjectInputStream(bais);
//            // 读取对象
//            tempList = (ArrayList<com.mvplibrary.model.Data>) ois.readObject();
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return tempList;
//    }

}
