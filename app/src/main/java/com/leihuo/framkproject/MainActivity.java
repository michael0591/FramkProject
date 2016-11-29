package com.leihuo.framkproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.leihuo.framkproject.adapter.MainAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {
    
    private List<String> dataList=new ArrayList<>();
    private List<String> classList=new ArrayList<>();
    @ViewInject(R.id.listview)
    private ListView listview;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
    }

    @Override
    protected void initView() {
        String[] array=getResources().getStringArray(R.array.main_array);
        dataList= Arrays.asList(array);
        String[] calssArray=getResources().getStringArray(R.array.main_class_array);
        classList= Arrays.asList(calssArray);
        adapter=new MainAdapter(MainActivity.this,dataList,R.layout.item_main);
        listview.setAdapter(adapter);
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initEvent() {

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClassName(activity,classList.get(position));
                startActivity(intent);
            }
        });
    }
}
