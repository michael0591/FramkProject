package com.leihuo.framkproject.lunbo;

import android.os.Bundle;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.leihuo.framkproject.BaseActivity;
import com.leihuo.framkproject.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.utils.util.LogUtil;

import java.util.ArrayList;


/**
 * 轮播图的简单使用
 */
public class LunBoActivity extends BaseActivity{// implements RetrofitResultInterface {

    @ViewInject(R.id.banner)
    ConvenientBanner banner;
    // 轮播图片数组集合
    private ArrayList<String> imgList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_lun_bo);
    }

    @Override
    protected void initView() {
        ViewUtils.inject(activity);
    }

    @Override
    protected void initValue() {
        imgList.add("http://www.bz55.com/uploads/allimg/150507/139-15050G63518.jpg");
        imgList.add("http://image.tianjimedia.com/uploadImages/2015/083/30/VVJ04M7P71W2.jpg");
        imgList.add("http://pic29.nipic.com/20130602/7447430_191109497000_2.jpg");
        imgList.add("http://image.tianjimedia.com/uploadImages/2014/215/51/76894E77GXH1.jpg");
        banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        },imgList)  .startTurning(2000)
                .setPageIndicator(new int[]{R.drawable.page_indicator_f, R.drawable.page_indicator_n})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //轮播图点击事件
                                LogUtil.toastMsg(activity,"点击了---"+position);
                    }
                });
    }

    @Override
    protected void initEvent() {
    }

//    @Override
//    public void onSucess(BaseReturnData baseReturnData) {
//        LogUtil.e(" activity=====>>" + new Gson().toJson(baseReturnData));
//        LogUtil.e(" activity22222:" + new GsonBuilder().serializeNulls().create().toJson(baseReturnData));
//    }
//
//    @Override
//    public void onFailure(String mes) {
//    }
//
//    @Override
//    public void onToast(String msg) {
//        LogUtil.toastMsg(activity, msg);
//        LogUtil.e(msg);
//    }

}
