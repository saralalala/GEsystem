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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EquipmentAddActivity extends Activity{
	
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
	private String s1,s2,s3,s4,s5,s6,s7,s8;
	private Map<String, String> map = new HashMap<String, String>();
	Button ok,cancel;
	TextView title;
	ImageButton back,func;
	private final String header = "http://10.109.21.95/lab/add_equipmentbuy.php?";
	private String flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.equipment_add);

		title = (TextView)findViewById(R.id.title);
		title.setText("设备采购");
		func = (ImageButton)findViewById(R.id.functionbutton);
		func.setVisibility(View.GONE);
		
		back = (ImageButton)findViewById(R.id.backbutton);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		ok = (Button)this.findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initMap();
					
				new Thread(runnable).start();//检查设备状态
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
		
	}
	
	
	public void initMap(){

		tv1=(EditText)findViewById(R.id.et1);
		s1 = tv1.getText().toString();
		tv1.setText("");
		map.put("equipment",s1);
		tv1.setFocusable(true);
		tv1.requestFocus();
		
		tv2=(EditText)findViewById(R.id.et2);
		s2 = tv2.getText().toString();
		tv2.setText("");
		map.put("type",s2);
		tv3=(EditText)findViewById(R.id.et3);
		s3 = tv3.getText().toString();
		tv3.setText("");
		map.put("spec", s3);
		
		map.put("userid","");
		map.put("usertype","");
		
		tv4=(EditText)findViewById(R.id.et4);
		s4 = tv4.getText().toString();
		tv4.setText("");
		map.put("day", s4);
		tv5=(EditText)findViewById(R.id.et5);
		s5 = tv5.getText().toString();
		tv5.setText("");
		map.put("time", s5);
		tv6=(EditText)findViewById(R.id.et6);
		s6 = tv6.getText().toString();
		tv6.setText("");
		map.put("price", s6);
		tv7=(EditText)findViewById(R.id.et7);
		s7 = tv7.getText().toString();
		tv7.setText("");
		map.put("num", s7);
		tv8=(EditText)findViewById(R.id.et8);
		s8 = tv8.getText().toString();
		tv8.setText("");
		map.put("memo", s8);
		
	}
	
	
	public void clear(){

		tv1=(EditText)findViewById(R.id.et1);
		tv1.setText("");
		tv1.setFocusable(true);
		tv1.requestFocus();
		
		tv2=(EditText)findViewById(R.id.et2);
		tv2.setText("");

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
	
	}
	
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			String jsonFlag = operateDB.getInputStream(header , map);//解析true类型json
			
			Log.v("#equipmentadd",""+jsonFlag);
			
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
				Toast  toast = Toast.makeText(getApplicationContext(), "添加设备成功", Toast.LENGTH_LONG);
        		toast.setGravity(Gravity.CENTER, 0, 0);
        		toast.show();
				break;
			case 0:
				Toast  toast0 = Toast.makeText(getApplicationContext(), "添加设备失败", Toast.LENGTH_LONG);
        		toast0.setGravity(Gravity.CENTER, 0, 0);
        		toast0.show();
				break;
    		}
	
    	}
	};
	
	
}
