package com.utils.listviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * listview中使用到的ViewHolder
 */
public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        AutoUtils.autoSize(mConvertView);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder(context, parent, layoutId, position);

        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
        }
        return holder;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

//    /**
//     * 为ImageView设置图片
//     *
//     * @param viewId
//     * @return
//     */
//    public ViewHolder setImageByUrl(int viewId, String url) {
//        if (url.indexOf("/") == 0)
//            url = SystemConst.MIAN_URL + url;
//        setImageResource(viewId, SystemConst.LOADING_IMG);
//        ImageLoader.getInstance().displayImage(
//                url, (ImageView) getView(viewId),
//                ImageOption.createOption(SystemConst.LOADING_IMG, SystemConst.EMPTY_IMG, SystemConst.FAILED_IMG, false, true, true));
//        return this;
//    }

//    /**
//     * 为ImageView设置图片
//     *
//     * @param viewId
//     * @return
//     */
//    public ViewHolder setImageByUrl(int viewId, int imageRid, String url) {
//        if (url.indexOf("/") == 0)
//            url = SystemConst.MIAN_URL + url;
//        setImageResource(viewId, imageRid);
//        ImageLoader.getInstance().displayImage(
//                url, (ImageView) getView(viewId),
//                ImageOption.createOption(imageRid, imageRid, imageRid, false, true, true));
//        return this;
//    }

//    /**
//     * 为ImageView设置图片
//     * cfr:2015.10.9
//     * @param imageView
//     * @param url
//     */
//    public static void setImageByUrl(ImageView imageView, String url) {
//        if (url == null) return;
//        if (url.indexOf("/") == 0)
//            url = SystemConst.MIAN_URL + url;
//        imageView.setImageResource(R.color.default_bg_picture);
//        ImageLoader.getInstance().displayImage(
//                url, imageView,
//                ImageOption.createOption(R.color.default_bg_picture, R.color.default_bg_picture, R.color.default_bg_picture, false, true, true));
//    }

//    /**
//     * 为ImageView设置图片 全路径型
//     *
//     * @param viewId
//     * @return
//     */
//    public ViewHolder setImageByAllUrl(int viewId, int imageRid, String url) {
//        setImageResource(viewId, imageRid);
//        ImageLoader.getInstance().displayImage(
//                url, (ImageView) getView(viewId),
//                ImageOption.createOption(imageRid, imageRid, imageRid, false, true, true));
//        return this;
//    }

//    /**
//     * 为ImageView设置圆角图片
//     *
//     * @param viewId
//     * @return
//     */
//    public ViewHolder setImageByUrlAndRoundCorner(int viewId, String url) {
//        if (url == null) return this;
//        if (url.indexOf("/") == 0)
//            url = SystemConst.MIAN_URL + url;
//        setImageResource(viewId, SystemConst.LOADING_IMG);
//        ImageLoader.getInstance().displayImage(
//                url, (ImageView) getView(viewId),
//                ImageOption.createOption(SystemConst.LOADING_IMG, SystemConst.EMPTY_IMG, SystemConst.FAILED_IMG, true, true, true));
//        return this;
//    }

//    /**
//     * 为ImageView设置圆角图片
//     *
//     * @param imageView
//     * @param url
//     */
//    public static void setImageByUrlAndRoundCorner(ImageView imageView, String url) {
//        if (url == null) return;
//        if (url.indexOf("/") == 0)
//            url = SystemConst.MIAN_URL + url;
//        imageView.setImageResource(SystemConst.LOADING_IMG);
//        ImageLoader.getInstance().displayImage(
//                url, imageView,
//                ImageOption.createOption(SystemConst.LOADING_IMG, SystemConst.EMPTY_IMG, SystemConst.FAILED_IMG, true, true, true));
//    }

//    /**
//     * 为ImageView设置圆角图片
//     *
//     * @param imageView
//     * @param url
//     */
//    public static void setImageByUrlAndRoundCorner(ImageView imageView, int imageRid, String url) {
//        if (url.indexOf("/") == 0)
//            url = SystemConst.MIAN_URL + url;
//        imageView.setImageResource(imageRid);
//        ImageLoader.getInstance().displayImage(
//                url, imageView,
//                ImageOption.createOption(imageRid, imageRid, imageRid, true, true, true));
//    }

//    /**
//     * 为ImageView设置图片
//     *
//     * @param viewId
//     * @return
//     */
//    public ViewHolder setImageByUrl(int viewId, String url, boolean isLocal) {
//        setImageResource(viewId, SystemConst.LOADING_IMG);
//        ImageLoader.getInstance().displayImage(
//                "file://" + url, (ImageView) getView(viewId),
//                ImageOption.createOption(SystemConst.LOADING_IMG, SystemConst.EMPTY_IMG, SystemConst.FAILED_IMG, false, isLocal, true));
//        return this;
//    }
//
//    /**
//     * 为ImageView设置图片
//     *
//     * @param viewId
//     * @return
//     */
//    public ViewHolder setLocalImageByUrl(int viewId, String url)
//    {
//
//        LocalImageLoader.getInstance(3, LocalImageLoader.Type.LIFO).loadImage(url, (ImageView) getView(viewId));
//        return this;
//    }

    public int getPosition() {
        return mPosition;
    }

}
