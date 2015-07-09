package com.example.desystem;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.R.integer;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TabHost.OnTabChangeListener;

public class EquipmentActivity extends ActivityGroup{
	
	public static String TAB_TAG_EQUIPMENT = "equipment";
	public static String TAB_TAG_BORROW = "borrow";
	public static String TAB_TAG_ADD = "add";
	public static String TAB_TAG_REPAIR = "repair";
	public static String TAB_TAG_CACULATE = "caculate";
	public static TabHost mTabHost;
	
	Intent mEquipmentIntent, mBorrowIntent, mRepairIntent, mAddIntent, mCaculateIntent;
	
	ImageButton addbt;
	Spinner spinner;
	SearchView sv;
	boolean isFirst=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_equipment);
		mTabHost = (TabHost)findViewById(R.id.equipmenttabhost);
		
		addbt = (ImageButton)findViewById(R.id.addbt);
		addbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(EquipmentActivity.this,EquipmentAddActivity.class));
			}
		});
		
		
		spinner = (Spinner)mTabHost.findViewById(R.id.spfunc);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			 @Override
	            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	                // TODO Auto-generated method stub
				 switch (arg2) {
				 
				 case 0:
					 arg1.setVisibility(View.GONE);
					 break;
					 
				 case 1:
					startActivity(new Intent(EquipmentActivity.this,EquipmentRepairInfoActivity.class));
				 	spinner.setSelection(0);
				 	break;
				 
				 case 2:
						startActivity(new Intent(EquipmentActivity.this,EquipmentBorrowInfoActivity.class));
					 	spinner.setSelection(0);
					 	break;
					 	
				 case 3:
					startActivity(new Intent(EquipmentActivity.this,EquipmentAddInfoActivity.class));
					 spinner.setSelection(0);
					 break;

				 default:
				 	break;
				 	
				 }
	                
	            }
		
			 @Override
			 	public void onNothingSelected(AdapterView<?> arg0) {
				 	// TODO Auto-generated method stub
				 	Log.e("TAG","never come to here too");
			 }
             
         });
		
		sv= (SearchView)mTabHost.findViewById(R.id.sv);
		sv.setOnQueryTextListener(new OnQueryTextListener() {  
			  
            @Override  
            public boolean onQueryTextSubmit(String query) {  
                Log.d("%%%%%","onQueryTextSubmit()"+query);  
                return false;  
            }  
  
            @Override  
            public boolean onQueryTextChange(String newText) {  
            	
                Log.d("%%%%%", "onQueryTextChange(), newText=" + newText);  
                return false;  
            }  
              
        });  
		//sv.setSubmitButtonEnabled(true);
		prepareIntent();
		setUpTab();
		
	}
	
	
	public void prepareIntent(){
		
		
		mEquipmentIntent = new Intent(EquipmentActivity.this,EquipmentInfoActivity.class);
		//mBorrowIntent = new Intent(EquipmentActivity.this,EquipmentInfoActivity.class);
		//mAddIntent = new Intent(EquipmentActivity.this,EquipmentInfoActivity.class);
		//mRepairIntent = new Intent(EquipmentActivity.this,EquipmentRepairActivity.class);
		//mCaculateIntent = new Intent(EquipmentActivity.this,EquipmentCaculateActivity.class);
	}
	
	
	public void setUpTab(){
		
		mTabHost.setup(this.getLocalActivityManager());//TabActivity方法
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_EQUIPMENT).setIndicator("").setContent(mEquipmentIntent));
		//mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_BORROW).setIndicator("排序       ▼").setContent(mBorrowIntent));
		//mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_ADD).setIndicator("筛选    ▼").setContent(mAddIntent));
		//mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_REPAIR).setIndicator("报修").setContent(mRepairIntent));
		//mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_CACULATE).setIndicator("统计").setContent(mCaculateIntent));
		
	}
	
      
    @Override  
    protected void onPause() {  
        Log.i("***","EquipmentActivity onPause");  
        super.onPause();  
    }  
    
    @Override  
    protected void onResume() {  
        Log.i("***","EquipmentActivity onResume"); 
        //new Thread(new ExecRunnable(searchHeader)).start();//设置标签，元件信息的更新
        super.onResume();  
    }
	

}
