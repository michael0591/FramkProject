package com.utils.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }
    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        // TODO this could be acquired from the pool too
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }
    @Override public String getId() {
        return getClass().getName();
    }


    /**
     * 在下面，你可以自定义圆角的大小，使用方式也非常简单
     * glideRequest.load("https://www.baidu.com/img/bdlogo.png").transform(new GlideRoundTransform(context)).into(imageView);
     * glideRequest.load("https://www.baidu.com/img/bdlogo.png").transform(new GlideRoundTransform(context, 10)).into(imageView);
     */
//    private static float radius = 0f;
//    public GlideRoundTransform(Context context) {
//        this(context, 4);
//    }
//    public GlideRoundTransform(Context context, int dp) {
//        super(context);
//        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
//    }
//    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
//        return roundCrop(pool, toTransform);
//    }
//    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
//        if (source == null) return null;
//        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//        if (result == null) {
//            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//        }
//        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
//        paint.setAntiAlias(true);
//        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
//        canvas.drawRoundRect(rectF, radius, radius, paint);
//        return result;
//    }
//    @Override public String getId() {
//        return getClass().getName() + Math.round(radius);
//    }
}
