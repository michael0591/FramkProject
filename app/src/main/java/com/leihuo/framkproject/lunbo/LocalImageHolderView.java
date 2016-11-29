package com.leihuo.framkproject.lunbo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.leihuo.framkproject.R;

/**
 * 商品详情的轮播图
 */
public class LocalImageHolderView implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lun_bo, null);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }

    @Override
    public void UpdateUI(Context context, final int position, String url) {
        Glide.with(context).load(url)
                .error(R.drawable.design_fab_background)
                .into(imageView);
    }
}