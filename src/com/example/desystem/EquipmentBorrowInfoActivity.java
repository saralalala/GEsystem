package com.example.desystem;
import java.util.ArrayList;

import com.example.desystem.adapter.EquipmentAddAdapter;
import com.example.desystem.adapter.EquipmentBorrowAdapter;
import com.example.desystem.service.OperateDB;
import com.example.desysytem.domain.EquipmentAddInfo;
import com.example.desysytem.domain.EquipmentBorrowInfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EquipmentBorrowInfoActivity extends Activity {

	
	private ArrayList<EquipmentBorrowInfo> borrowInfos;
	private EquipmentBorrowAdapter adapter;
	private final String searchHeader = "http://10.109.21.95/lab/search_equipmentborrow.php";
	private ProgressDialog proDialog;
	ListView ls;
	ImageButton backbutton;
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.equipment_borrow_info);
		
		TextView title = (TextView)findViewById(R.id.title);
		title.setText("�豸���ñ�");
		
		ls = (ListView) findViewById(R.id.lsborrow);
		proDialog = showProgressDialog("���ڻ�ȡ�豸������Ϣ...");
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
			borrowInfos = (ArrayList<EquipmentBorrowInfo>) list.get(0);

			if (borrowInfos  == null) {

				Toast.makeText(getApplicationContext(), "����ʧ��~_~",
						Toast.LENGTH_LONG).show();
			}//û�л���������ݻ�����û����ȷ����

			else {

				adapter = new EquipmentBorrowAdapter(EquipmentBorrowInfoActivity.this, borrowInfos);
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
			ArrayList<EquipmentBorrowInfo> g = null;
			String jsonString = operateDB.getInputStream(urlHeader);
		
			Log.i("#equipmentborrowinfo",""+jsonString);
			if(jsonString !=null){
				
				g = operateDB.parseEquipmentBorrowJson(jsonString);
			}
			
			Message msg = handler.obtainMessage();
			Bundle bundle = new Bundle();
			ArrayList list = new ArrayList(); // ��Ҫ���ݵ�ArrayList<Object>									
			list.add(g);
			bundle.putParcelableArrayList("list", list);// list�����еĶ���������л�
			msg.setData(bundle);
			msg.sendToTarget();// ����handler����Ϣ����UI�߳�i
		}
	};
	
	
	 public ProgressDialog showProgressDialog(String msg) {
	  		ProgressDialog dialog = new ProgressDialog(EquipmentBorrowInfoActivity.this);
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
