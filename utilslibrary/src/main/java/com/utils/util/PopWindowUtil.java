package com.utils.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * PopWindow工具类
 */
public class PopWindowUtil {
    private PopupWindow popupWindow;
    private Context context;
    private View contentView;

    public View getContentView() {
        return contentView;
    }

    public PopWindowUtil(Context context) {
        this.context = context;
    }

    /**
     * 创建PopupWindow对象
     * @param layoutId
     * @return
     */
    public PopupWindow createPopWindow(int layoutId){
        contentView= LayoutInflater.from(context).inflate(layoutId,null);
        popupWindow=new PopupWindow(contentView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        return popupWindow;
    }

    /**
     * 显示在view下方
     * @param view
     */
    public void showAsDropDown(View view){
        popupWindow.showAsDropDown(view);
    }
}
