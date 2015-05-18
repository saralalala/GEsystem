package com.example.desystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes.Name;

import com.example.desystem.EquipmentInfoActivity.ExecRunnable;
import com.example.desystem.adapter.EquipmentAdapter;
import com.example.desystem.service.OperateDB;
import com.example.desysytem.domain.EquipmentInfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

public class LoginActivity extends Activity {

	Spinner spcategory;
	Button btlogin,btexist;
	TextView name, password;
	Map<String, String> map = new HashMap<String, String>();
	private final String urlHeader = "http://10.109.21.95/lab/login.php?";
	private ProgressDialog proDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		name = (TextView) this.findViewById(R.id.etname);
		password = (TextView) this.findViewById(R.id.etpassword);

		spcategory = (Spinner) this.findViewById(R.id.spcategory);
		btlogin = (Button) findViewById(R.id.btlogin);
		btlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				
				map.put("username", name.getText().toString());
				map.put("password", password.getText().toString());
				
				switch (spcategory.getSelectedItemPosition()) {
				case 0:
					map.put("table", "1");// sysuser
					proDialog = showProgressDialog("正在登陆，请稍后...");
					new Thread(new LoginRunnable(1)).start();
					break;
				case 1:
					map.put("table", "2");// student
					proDialog = showProgressDialog("正在登陆，请稍后...");
					new Thread(new LoginRunnable(2)).start();
					break;
				case 2:
					map.put("table", "3");// teacher
					proDialog = showProgressDialog("正在登陆，请稍后...");
					new Thread(new LoginRunnable(3)).start();
					break;
				default:
					break;
				}
			}
		});
		
		btexist = (Button) findViewById(R.id.btexist);
		btexist.setOnClickListener(new OnClickListener() {

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
			switch (msg.what) {

			case 1:// sysuser
				if (msg.arg1 == -1) {

					Toast toast = Toast.makeText(getApplicationContext(),
							"登录失败~_~", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else {

					startActivity(new Intent(LoginActivity.this,
							MainTabActivity1.class));
				}
				break;

			case 2:// student

				if (msg.arg1 == -1) {

					Toast toast = Toast.makeText(getApplicationContext(),
							"登录失败~_~", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else {

					startActivity(new Intent(LoginActivity.this,
							MainTabActivity2.class));
				}
				break;

			case 3:// teacher
				if (msg.arg1 == -1) {

					Toast toast = Toast.makeText(getApplicationContext(),
							"登录失败~_~", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();

				} else {

					switch (msg.arg1) {
					case 1:
						startActivity(new Intent(LoginActivity.this,
								MainTabActivity3.class));
						break;

					case 2:
						startActivity(new Intent(LoginActivity.this,
								MainTabActivity4.class));
						break;

					default:
						Toast toast = Toast.makeText(getApplicationContext(),
								"无此教师~_~", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
						break;
					}

				}

			}

		}
	};// 运行在主线程中

	class LoginRunnable implements Runnable {

		private int table;

		public LoginRunnable(int table) {
			this.table = table;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub

			int flag = -1;
			OperateDB operateDB = new OperateDB();
			String jsonString = operateDB.getInputStream(urlHeader, map);

			if (jsonString != null) {

				flag = operateDB.parseLoginJson(jsonString);
			}
			switch (table) {

			case 1:
				Message msg1 = handler.obtainMessage(1);
				msg1.arg1 = flag;
				msg1.sendToTarget();
				break;

			case 2:
				Message msg2 = handler.obtainMessage(2);
				msg2.arg1 = flag;
				msg2.sendToTarget();
				break;

			case 3:
				Message msg3 = handler.obtainMessage(3);
				msg3.arg1 = flag;
				msg3.sendToTarget();
				break;

			}

		}
	};

	public ProgressDialog showProgressDialog(String msg) {
		ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
		dialog.setMessage(msg);
		dialog.show();
		// dialog.setCancelable(false);//点击返回键，dialog不消失
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
