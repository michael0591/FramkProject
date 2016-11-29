package com.leihuo.framkproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity
{
	public Activity activity;
	public MyApplication application;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		activity=this;
		application= (MyApplication)this.getApplication();
		application.getActivityManager().pushActivity(this);
		setContentView();
		initView();
		initValue();
		initEvent();
	}

	/**加载布局文件*/
	protected abstract void setContentView();
	/**初始化视图*/
	protected abstract void initView();
	/**视图数据初始化*/
	protected abstract void initValue();
	/**初始化事件*/
	protected abstract void initEvent();

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	/**释放资源*/
	protected void onDestroy()
	{
		super.onDestroy();
		application.getActivityManager().popActivity(this);
	}
}