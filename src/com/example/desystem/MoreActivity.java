package com.example.desystem;

import java.util.ArrayList;
import java.util.List;

import com.example.desystem.adapter.UserFuncAdapter;
import com.example.desystem.domain.UserFuncInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MoreActivity extends Activity {

	TextView title;
	ListView userLv;
	private List<UserFuncInfo> userLis;
	private UserFuncAdapter userAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//��������
		setContentView(R.layout.user);
		
		title = (TextView)findViewById(R.id.title);
		title.setText("��");
		
		userLis = new ArrayList<UserFuncInfo>();
		userLis.add(new UserFuncInfo(R.drawable.add,"�ҵĲɹ�",R.drawable.arrow));
		userLis.add(new UserFuncInfo(R.drawable.go,"�豸����",R.drawable.arrow));
		userLis.add(new UserFuncInfo(R.drawable.repair,"�ҵı���",R.drawable.arrow));
		userLis.add(new UserFuncInfo(R.drawable.message,"�ҵ���Ϣ",R.drawable.arrow));
		userLis.add(new UserFuncInfo(R.drawable.icon_5_n,"����",R.drawable.arrow));
		
		userLv =(ListView)findViewById(R.id.userls);
		userAdapter = new UserFuncAdapter(MoreActivity.this,userLis); 
		userLv.setAdapter(userAdapter);
		userLv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				Intent intent = null;// ����Intent����
				switch (position) {
				case 0:
					
					break;
				case 1:
//					intent = new Intent(AllTestActivity.this, ShowSignalActivity.class);// ʹ��Inaccountinfo���ڳ�ʼ��Intent
//					startActivity(intent);
					break;
				case 2:
//					intent = new Intent(AllTestActivity.this, ShowLoadSpeedActivity.class);// ʹ��Accountflag���ڳ�ʼ��Intent
//					startActivity(intent);
					break;
				case 3:
//					intent = new Intent(AllTestActivity.this, ShowInterfaceSpeedActivity.class);// ʹ��Sysset���ڳ�ʼ��Intent
//					startActivity(intent);
					break;
				}
		}
		});
		
		
	}
}

