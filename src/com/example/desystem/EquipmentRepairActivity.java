package com.example.desystem;
import java.util.HashMap;
import java.util.Map;

import com.example.desystem.service.OperateDB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EquipmentRepairActivity extends Activity{
	
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12;
	Spinner tv13;
	private String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
	private Map<String, String> map = new HashMap<String, String>();
	Button ok,cancel,calculate;
	private final String header = "http://10.109.21.95/lab/add_repair.php?";
	private String flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.equipment_repair);

		ok = (Button)this.findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initMap();
				if((map.get("equipment")).equals("")){
					Toast  toast = Toast.makeText(getApplicationContext(), "请输入正确的报修信息", Toast.LENGTH_LONG);
	        		toast.setGravity(Gravity.CENTER, 0, 0);
	        		toast.show();
					
				}else{
					
					new Thread(runnable).start();//检查设备状态
				}
				//setIntent(new Intent(GoodsAddActivity,GoodsInfoActivity.class));
				
			}
		});
		
		cancel = (Button)this.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clear();
			}
		});
		
		calculate = (Button)this.findViewById(R.id.calculate);
		calculate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//clear();
				startActivity(new Intent(EquipmentRepairActivity.this,EquipmentRepairInfoActivity.class));
			}
		});
	}
	
	
	public void initMap(){

//		tv1=(EditText)findViewById(R.id.et1);
//		s1 = tv1.getText().toString();
//		tv1.setText("");
//		map.put("askrepairid",s1);
//		tv1.setFocusable(true);
//		tv1.requestFocus();
		
		tv2=(EditText)findViewById(R.id.et2);
		s2 = tv2.getText().toString();
		tv2.setText("");
		map.put("day",s2);
		tv2.setFocusable(true);
		tv2.requestFocus();
		tv3=(EditText)findViewById(R.id.et3);
		s3 = tv3.getText().toString();
		tv3.setText("");
		map.put("time", s3);
		tv4=(EditText)findViewById(R.id.et4);
		s4 = tv4.getText().toString();
		tv4.setText("");
		map.put("equipment", s4);
		tv5=(EditText)findViewById(R.id.et5);
		s5 = tv5.getText().toString();
		tv5.setText("");
		map.put("memo", s5);
		tv6=(EditText)findViewById(R.id.et6);
		s6 = tv6.getText().toString();
		tv6.setText("");
		map.put("usertype", s6);
		tv7=(EditText)findViewById(R.id.et7);
		s7 = tv7.getText().toString();
		tv7.setText("");
		map.put("userid", s7);
		tv8=(EditText)findViewById(R.id.et8);
		s8 = tv7.getText().toString();
		tv8.setText("");
		map.put("deal", s8);
		tv9=(EditText)findViewById(R.id.et9);
		s9 = tv9.getText().toString();
		tv9.setText("");
		map.put("dealid", s9);
		tv10=(EditText)findViewById(R.id.et10);
		s10 = tv10.getText().toString();
		tv10.setText("");
		map.put("result", s10);
		tv11=(EditText)findViewById(R.id.et11);
		s11 = tv11.getText().toString();
		tv11.setText("");
		map.put("repday", s11);
		tv12=(EditText)findViewById(R.id.et12);
		s12 = tv12.getText().toString();
		tv12.setText("");
		map.put("reptime", s12);
		tv13=(Spinner)findViewById(R.id.et13);
		s13 = tv13.getSelectedItem().toString();
		//tv13.setText("");
		map.put("repairstate", s13);
		
	}
	
	
	public void clear(){

//		tv1=(EditText)findViewById(R.id.et1);
//		tv1.setText("");
//		tv1.setFocusable(true);
//		tv1.requestFocus();
		
		tv2=(EditText)findViewById(R.id.et2);
		tv2.setText("");
		tv2.setFocusable(true);
		tv2.requestFocus();

		tv3=(EditText)findViewById(R.id.et3);
		tv3.setText("");

		tv4=(EditText)findViewById(R.id.et4);
		tv4.setText("");

		tv5=(EditText)findViewById(R.id.et5);
		tv5.setText("");

		tv6=(EditText)findViewById(R.id.et6);
		tv6.setText("");

		tv7=(EditText)findViewById(R.id.et7);
		tv7.setText("");

		tv8=(EditText)findViewById(R.id.et8);
		tv8.setText("");
		
		tv9=(EditText)findViewById(R.id.et9);
		tv9.setText("");
		
		tv10=(EditText)findViewById(R.id.et10);
		tv10.setText("");
		
		tv11=(EditText)findViewById(R.id.et11);
		tv11.setText("");
		
		tv12=(EditText)findViewById(R.id.et12);
		tv12.setText("");
		
		tv13=(Spinner)findViewById(R.id.et13);
		tv13.setSelection(0);
		
		
	}
	
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			String jsonFlag = operateDB.getInputStream(header , map);//解析true类型json
			Log.v("#repair",""+jsonFlag);
			if(jsonFlag !=null){
				
				flag = operateDB.parseFlagJson(jsonFlag);
				
				if(flag.equalsIgnoreCase("true")){
					Message message = handler.obtainMessage(1);
					message.sendToTarget();
				}
			}
			else{
				Message message = handler.obtainMessage(0);
				message.sendToTarget();
			}
		}
	};
	
	
	Handler handler = new Handler(){  	   
    	@Override   
    	public void handleMessage(Message msg) {   
    		
    		super.handleMessage(msg);
    		switch (msg.what) {
			case 1:
				Toast  toast = Toast.makeText(getApplicationContext(), "设备报修成功", Toast.LENGTH_LONG);
        		toast.setGravity(Gravity.CENTER, 0, 0);
        		toast.show();
				break;
			case 0:
				Toast  toast0 = Toast.makeText(getApplicationContext(), "设备报修失败", Toast.LENGTH_LONG);
        		toast0.setGravity(Gravity.CENTER, 0, 0);
        		toast0.show();
				break;
    		}
	
    	}
	};
	
	@Override  
    protected void onStop() {  
        Log.i("***","MyActivity2 onStop");  
        super.onStop();  
    }  
      
    @Override  
    protected void onPause() {  
        Log.i("***","MyActivity2 onPause");  
        super.onPause();  
    }  
      
    @Override  
    protected void onResume() {  
        Log.i("***","MyActivity2 onResume");  
        super.onResume();  
    }
    @Override  
    protected void onStart() {  
        Log.i("***","MyActivity2 onStart");  
        super.onPause();  
    } 
	
}
