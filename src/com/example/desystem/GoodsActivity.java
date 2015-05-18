package com.example.desystem;


import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TabHost;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.widget.TabHost.OnTabChangeListener;

public class GoodsActivity extends ActivityGroup{
	
	public static String TAB_TAG_GOODS = "goods";
	public static String TAB_TAG_ADD = "add";
	public static String TAB_TAG_DELETE = "delete";
	public static String TAB_TAG_CHECK = "check";
	public static String TAB_TAG_CACULATE = "caculate";
	public static TabHost mTabHost;
	
	Intent mGoodsIntent, mAddIntent, mDeleteIntent, mCheckIntent, mCaculateIntent;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goods);
		Log.i("***","MyActivity onCreate");
		
		mTabHost = (TabHost)findViewById(R.id.goodstabhost);
		
		prepareIntent();
		setUpTab();
		mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				  if (tabId.equals("tab1")) {   //第一个标签  
					  
	                }  
	                if (tabId.equals("tab2")) {   //第二个标签 
	                	
	                }  
	                if (tabId.equals("tab3")) {   //第三个标签 
	                	
	                }
				
			}
		});
		
	}
	
	
	public void prepareIntent(){
		
		
		mGoodsIntent = new Intent(GoodsActivity.this,GoodsInfoActivity.class);
		mAddIntent = new Intent(GoodsActivity.this,GoodsAddActivity.class);
		mDeleteIntent = new Intent(GoodsActivity.this,GoodsDeleteActivity.class);
		mCheckIntent = new Intent(GoodsActivity.this,GoodsCheckActivity.class);
		mCaculateIntent = new Intent(GoodsActivity.this,GoodsCaculateActivity.class);
	}
	
	
	public void setUpTab(){
		
		mTabHost.setup(this.getLocalActivityManager());//TabActivity方法
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_GOODS).setIndicator("元件信息").setContent(mGoodsIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_ADD).setIndicator("元件入库").setContent(mAddIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_DELETE).setIndicator("元件出库").setContent(mDeleteIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_CHECK).setIndicator("盘点").setContent(mCheckIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_CACULATE).setIndicator("统计").setContent(mCaculateIntent));
		
	}
	
	@Override  
	protected void onStop() {  
        Log.i("***","MyActivity onStop");  
        super.onStop();  
    }  
      
    @Override  
    protected void onPause() {  
        Log.i("***","MyActivity onPause");  
        super.onPause();  
    }  
      
    @Override  
    protected void onStart() {  
        Log.i("***","MyActivity onStart");  
        super.onPause();  
    }  
    
    @Override  
    protected void onResume() {  
        Log.i("***","MyActivity onResume"); 
        //new Thread(new ExecRunnable(searchHeader)).start();//设置标签，元件信息的更新
        super.onResume();  
    }
	

}
