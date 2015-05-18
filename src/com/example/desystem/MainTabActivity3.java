package com.example.desystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.ActivityGroup;
import android.content.Intent;

public class MainTabActivity3 extends ActivityGroup{
	
	public static String TAB_TAG_HOME = "home";
	public static String TAB_TAG_ELEMENT = "element";
	public static String TAB_TAG_DEVICE = "device";
	//public static String TAB_TAG_USER = "user";
	public static String TAB_TAG_MORE = "me";
	public static TabHost mTabHost;
	
	
	RadioButton mRad1, mRad2, mRad3, mRad4, mRad5;
	Intent mHomeIntent, mElementIntent, mEquipmentIntent, mUserIntent, mMoreIntent;
	View view1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_tab);
		
		mTabHost = (TabHost)findViewById(R.id.roottabhost);
		
		prepareIntent();
		setUpTab();
	}
	
	
	public void prepareIntent(){
		
		
		mHomeIntent = new Intent(MainTabActivity3.this,HomeActivity.class);
		mElementIntent = new Intent(MainTabActivity3.this,GoodsActivity.class);
		mEquipmentIntent = new Intent(MainTabActivity3.this,EquipmentListActivity.class);
		mUserIntent = new Intent(MainTabActivity3.this,UserActivity.class);
		mMoreIntent = new Intent(MainTabActivity3.this,MoreActivity.class);
	}
	
	
	public void setUpTab(){
		
		mTabHost.setup(this.getLocalActivityManager());
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_HOME).setIndicator("",getResources().getDrawable(R.drawable.icon_1_c)).setContent(mHomeIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_ELEMENT).setIndicator("", getResources().getDrawable(R.drawable.icon_2_n)).setContent(mElementIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_DEVICE).setIndicator("", getResources().getDrawable(R.drawable.icon_3_n)).setContent(mEquipmentIntent));
		//mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_USER).setIndicator("", getResources().getDrawable(R.drawable.icon_4_n)).setContent(mUserIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_MORE).setIndicator("", getResources().getDrawable(R.drawable.icon_4_n)).setContent(mMoreIntent));
		
	}
	
}
