package com.leihuo.framkproject.adapter;

import android.content.Context;

import com.leihuo.framkproject.R;
import com.utils.listviews.CommonAdapter;
import com.utils.listviews.ViewHolder;

import java.util.List;

/**
 *
 */

public class MainAdapter extends CommonAdapter<String> {

    public MainAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, String item) {
            helper.setText(R.id.textView,item);
    }
}
