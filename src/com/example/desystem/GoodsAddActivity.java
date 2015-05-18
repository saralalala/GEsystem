package com.example.desystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;  
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.desystem.service.OperateDB;
import com.example.desysytem.domain.ComponentInfo;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsAddActivity extends Activity{
	
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
	private String s1,s2,s3,s4,s5,s6,s7;
	private Map<String, String> map = new HashMap<String, String>();
	Button ok,cancel;
	private final String header = "http://10.109.21.95/lab/add_goods.php?";
	private String flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.goods_add);

		Log.i("***","MyActivity2 onCreate"); 
		ok = (Button)this.findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initMap();
				if((map.get("goods")).equals("")){
					Toast  toast = Toast.makeText(getApplicationContext(), "请输入正确的元件信息", Toast.LENGTH_LONG);
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
		
	}
	
	
	public void initMap(){

		tv1=(EditText)findViewById(R.id.et1);
		s1 = tv1.getText().toString();
		tv1.setText("");
		map.put("goods",s1);
		tv1.setFocusable(true);
		tv1.requestFocus();
		
		tv2=(EditText)findViewById(R.id.et2);
		s2 = tv2.getText().toString();
		tv2.setText("");
		map.put("spec",s2);
		tv3=(EditText)findViewById(R.id.et3);
		s3 = tv3.getText().toString();
		tv3.setText("");
		map.put("price", s3);
		tv4=(EditText)findViewById(R.id.et4);
		s4 = tv4.getText().toString();
		tv4.setText("");
		map.put("number", s4);
		tv5=(EditText)findViewById(R.id.et5);
		s5 = tv5.getText().toString();
		tv5.setText("");
		map.put("catalog", s5);
		tv6=(EditText)findViewById(R.id.et6);
		s6 = tv6.getText().toString();
		tv6.setText("");
		map.put("state", s6);
		tv7=(EditText)findViewById(R.id.et7);
		s7 = tv7.getText().toString();
		tv7.setText("");
		map.put("photo", s7);
		
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
		
		
		
	}
	
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			String jsonFlag = operateDB.getInputStream(header , map);//解析true类型json
			Log.v("#addflag",""+jsonFlag);
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
				Toast  toast = Toast.makeText(getApplicationContext(), "元件入库成功", Toast.LENGTH_LONG);
        		toast.setGravity(Gravity.CENTER, 0, 0);
        		toast.show();
				break;
			case 0:
				Toast  toast0 = Toast.makeText(getApplicationContext(), "元件入库失败", Toast.LENGTH_LONG);
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
