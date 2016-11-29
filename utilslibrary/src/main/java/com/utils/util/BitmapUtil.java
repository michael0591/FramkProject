package com.utils.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片工具类 压缩图片
 */

public class BitmapUtil {

    public static void startCompressImg(File file,Bitmap bp){
        compressionBitmap(bp);
        outFile(file,bp);
    }

    public static  Bitmap compressImageFromFile(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;// 只读边,不读内容
        android.graphics.Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置采样率

        newOpts.inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888;// 该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        // return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        return bitmap;
    }

    public static void compressionBitmap(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();

            LogUtil.e("==options=="+options);
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            // options -= 10;
            options -= 10;
        }
    }

    public static void outFile(File file,Bitmap bp){
        // 创建文件输出流
        OutputStream os;
        try {
            os = new FileOutputStream(file);
            // 存储
            bp.compress(Bitmap.CompressFormat.JPEG, 100, os);
            // 关闭流
            os.close();
        } catch (FileNotFoundException e) {
            LogUtil.e("e-FileNotFoundException---->"+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LogUtil.e("e--IOException--->"+e.getMessage());
            e.printStackTrace();
        }
    }
}
