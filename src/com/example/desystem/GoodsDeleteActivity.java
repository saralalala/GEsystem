package com.example.desystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.desystem.domain.GoodsInfo;
import com.example.desystem.service.OperateDB;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

public class GoodsDeleteActivity extends Activity{
	
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
	private String s1;
	private Map<String, String> map = new HashMap<String, String>();
	Button ok,cancel;
	private final String header = "http://10.109.21.95/lab/delete_goods.php?";
	private ArrayList<GoodsInfo> goodsInfos;
	String flag ="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.goods_delete);

		
		ok = (Button)this.findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initMap();
				new Thread(runnable).start();
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
		map.put("goodsid",s1);
		
	}
	
	
	public void clear(){

		tv1=(EditText)findViewById(R.id.et1);
		tv1.setText("");
		
	}
	
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			String jsonString = operateDB.getInputStream(header , map);      
			Log.v("#1delete",""+jsonString);
			if(jsonString != null){
				
				goodsInfos = operateDB.parseGoodsJson(jsonString);
				
				if(goodsInfos != null){
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
				Toast  toast = Toast.makeText(getApplicationContext(), "元件出库成功", Toast.LENGTH_LONG);
        		toast.setGravity(Gravity.CENTER, 0, 0);
        		toast.show();
				break;
			case 0:
				Toast  toast0 = Toast.makeText(getApplicationContext(), "元件出库失败", Toast.LENGTH_LONG);
        		toast0.setGravity(Gravity.CENTER, 0, 0);
        		toast0.show();
				break;
    		}
    	}
	};
	
	
}
