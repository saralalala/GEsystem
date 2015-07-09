package com.example.desystem;
import java.util.ArrayList;

import com.example.desystem.adapter.GoodsAdapter;
import com.example.desystem.adapter.RepairAdapter;
import com.example.desystem.domain.AskrepairInfo;
import com.example.desystem.domain.GoodsInfo;
import com.example.desystem.domain.RepairInfo;
import com.example.desystem.service.OperateDB;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EquipmentRepairInfoActivity extends Activity {

	
	private ArrayList<RepairInfo> repairInfos;
	private RepairAdapter adapter;
	private final String searchHeader = "http://10.109.21.95/lab/search_repair.php";
	private ProgressDialog proDialog;
	ListView ls;
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13;
	ImageButton backbutton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.repair_info);
		
		ls = (ListView) findViewById(R.id.lsrepair);
		proDialog = showProgressDialog("���ڻ�ȡ������Ϣ...");
		new Thread(new SearchRunnable(searchHeader)).start();
		
		backbutton = (ImageButton)this.findViewById(R.id.backbutton);
		backbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			proDialog.cancel();
			
			Bundle bundle = msg.getData();
			ArrayList list = bundle.getParcelableArrayList("list");
			repairInfos = (ArrayList<RepairInfo>) list.get(0);

			if (repairInfos  == null) {

				Toast.makeText(getApplicationContext(), "����ʧ��~_~",
						Toast.LENGTH_LONG).show();
			}//û�л���������ݻ�����û����ȷ����

			else {

				adapter = new RepairAdapter(EquipmentRepairInfoActivity.this, repairInfos);
				ls.setAdapter(adapter);
					
			}
		}
	};//���������߳���
	
	
class SearchRunnable implements Runnable{
		
		private String urlHeader;
		public SearchRunnable(String urlHeader){
			this.urlHeader = urlHeader;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<RepairInfo> g = null;
			String jsonString = operateDB.getInputStream(urlHeader);
		
			Log.i("&&searchrepair",""+jsonString);
			if(jsonString !=null){
				
				g = operateDB.parseRepairJson(jsonString);
			}
			Log.i("&&search",""+g);
			Message msg = handler.obtainMessage(1);
			Bundle bundle = new Bundle();
			ArrayList list = new ArrayList(); // ��Ҫ���ݵ�ArrayList<Object>									
			list.add(g);
			bundle.putParcelableArrayList("list", list);// list�����еĶ���������л�
			msg.setData(bundle);
			msg.sendToTarget();// ����handler����Ϣ����UI�߳�i
		}
	};
	
	
	 public ProgressDialog showProgressDialog(String msg) {
	  		ProgressDialog dialog = new ProgressDialog(EquipmentRepairInfoActivity.this);
	  		dialog.setMessage(msg);
	  		dialog.show();
	  		//dialog.setCancelable(false);//������ؼ���dialog����ʧ
	  		dialog.setCanceledOnTouchOutside(false);
	  		return dialog;
	  	} 
	 
    @Override  
    protected void onResume() {  
        Log.i("***","EquipmentActivity onResume"); 
        //new Thread(new SearchRunnable(searchHeader)).start();//���ñ�ǩ��Ԫ����Ϣ�ĸ���
        super.onResume();  
    }

}
