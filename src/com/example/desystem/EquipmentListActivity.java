package com.example.desystem;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desystem.adapter.EquipmentAdapter;
import com.example.desystem.domain.EquipmentInfo;
import com.example.desystem.domain.GoodsInfo;
import com.example.desystem.service.OperateDB;

public class EquipmentListActivity extends Activity {

	private final static int ITEM_DELETE = 1;
	private final static int ITEM_BORROW = 2;
	private final static int ITEM_RETURN = 3;
	private final static int ITEM_REPAIR = 4;
	private final static int ITEM_UPDATE = 5;
	
	private ArrayList<EquipmentInfo> equipmentInfos = null;
	private EquipmentAdapter adapter;
	private final String searchHeader = "http://10.109.21.95/lab/search_equipment.php";
	private final String deleteHeader = "http://10.109.21.95/lab/delete_equipment.php?";
	private final String updateHeader = "http://10.109.21.95/lab/update_equipment.php?";
	private final String repairHeader = "http://10.109.21.95/lab/add_repair.php?";
	private final String borrowHeader = "http://10.109.21.95/lab/borrow_equipment.php?";
	private final String returnHeader = "http://10.109.21.95/lab/return_equipment.php?";
	
	ListView ls;
	TextView equipmentid,equipment, spec, price, room, groupno, chargeperson, estate,ustate,repairstate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.equipment_info);
		 
		Log.i("***","EquipmentnfoActivity onCreate"); 
		
		ls = (ListView) findViewById(R.id.lsequip);
//		adapter = new EquipmentAdapter(EquipmentInfoActivity.this, equipmentInfos);
//		ls.setAdapter(adapter);
		//registerForContextMenu(ls);
		
	}

	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				Bundle bundle1 = msg.getData();
				ArrayList list1 = bundle1.getParcelableArrayList("list");
				equipmentInfos = (ArrayList<EquipmentInfo>) list1.get(0);
				
				if (equipmentInfos == null) {

					Toast.makeText(getApplicationContext(), "信息为空，请检查网络设置~_~",
							Toast.LENGTH_LONG).show();
				}//没有获得请求数据或数据没有正确解析

				else {
					adapter = new EquipmentAdapter(EquipmentListActivity.this, equipmentInfos);
					ls.setAdapter(adapter);
					//adapter.notifyDataSetChanged();
						
				}
				break;
				
			case 2:
				Bundle bundle2 = msg.getData();
				ArrayList list2 = bundle2.getParcelableArrayList("list");
				equipmentInfos = (ArrayList<EquipmentInfo>) list2.get(0);
				
				if (equipmentInfos == null) {
					
					Toast.makeText(getApplicationContext(), "操作失败~_~",
							Toast.LENGTH_LONG).show();
				}//没有获得请求数据或数据没有正确解析

				else {
					
					Toast.makeText(getApplicationContext(), "操作成功~~",
							Toast.LENGTH_LONG).show();
					adapter = new EquipmentAdapter(EquipmentListActivity.this, equipmentInfos);
					ls.setAdapter(adapter);
						
				}
				break;
				
			default:
				break;
			}
			
		}
	};//运行在主线程中
	
	
	class SearchRunnable implements Runnable{
		
		private String urlHeader;
		public SearchRunnable(String urlHeader){
			this.urlHeader = urlHeader;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<EquipmentInfo> g = null;
			String jsonString = operateDB.getInputStream(urlHeader);
		
			Log.i("&&searchequipment",""+jsonString);
			if(jsonString !=null){
				
				g = operateDB.parseEquipmentJson(jsonString);
			}

			Message msg = handler.obtainMessage(1);
			Bundle bundle = new Bundle();
			ArrayList list = new ArrayList(); // 需要传递的ArrayList<Object>									
			list.add(g);
			bundle.putParcelableArrayList("list", list);// list数组中的对象可以序列化
			msg.setData(bundle);
			msg.sendToTarget();// 利用handler将信息传给UI线程i
		}
	};
	
	
	class ExecRunnable implements Runnable{
		
		private String urlHeader;
		private Map<String, String> map;
		
		public ExecRunnable(String urlHeader,Map<String, String> map){
			this.urlHeader = urlHeader;
			this.map = map;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<EquipmentInfo> g = null;
			String jsonString = new OperateDB().getInputStream(urlHeader , map);
			Log.i("##borrowequipment",jsonString+"");
			
			if(jsonString != null){
				
				g = operateDB.parseEquipmentJson(jsonString);
				
			}
			
			Message msg = handler.obtainMessage(2);
			Bundle bundle = new Bundle();
			ArrayList list = new ArrayList(); // 需要传递的ArrayList<Object>									
			list.add(g);
			bundle.putParcelableArrayList("list", list);// list数组中的对象可以序列化
			msg.setData(bundle);
			msg.sendToTarget();// 利用handler将信息传给UI线程i
		}
	};
	
	@Override
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo){
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)menuInfo;
		menu.add(0, ITEM_DELETE, 5, "删除");
		menu.add(0,ITEM_BORROW,1,"借用");
		menu.add(0,ITEM_RETURN,2,"归还");
		menu.add(0,ITEM_REPAIR,3,"报修");
		menu.add(0,ITEM_UPDATE,4,"盘点");
		TextView tvName = (TextView)(info.targetView).findViewById(R.id.item2);//适配器中点击item中的组件
		menu.setHeaderTitle(tvName.getText());
		//super.onCreateContextMenu(menu, v, menuInfo); 
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		
		AdapterContextMenuInfo itemMenuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		
		switch (item.getItemId()) {
		
		case ITEM_DELETE:
			
			Map<String, String> delMap = new HashMap<String, String>();
			TextView delId = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item1);
			delMap.put("equipmentid",delId.getText().toString());
			new Thread(new ExecRunnable(deleteHeader,delMap)).start();
			break;
			
		case ITEM_REPAIR:

			repairDialog(itemMenuInfo);
			//AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
			
			break;
			
		case ITEM_BORROW:
			
			borrowDialog(itemMenuInfo);
			break;	
			
		case ITEM_RETURN:
			
			Date curDate = new Date(System.currentTimeMillis());
			SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");
			
			Map<String, String> retMap = new HashMap<String, String>();
			TextView retId = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item1);
			
			retMap.put("equipmentid",retId.getText().toString());
			retMap.put("reday", dFormat.format(curDate));
			retMap.put("retime",tFormat.format(curDate));
			/* 归还 */
			
			new Thread(new ExecRunnable(returnHeader,retMap)).start();
			break;
		case ITEM_UPDATE:

			updateDialog(itemMenuInfo);
			//AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
			
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item); 
		
	}
	
	
	public void repairDialog(AdapterContextMenuInfo itemMenuInfo){

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
		builder.setTitle("设备报修");
		LayoutInflater inflater = LayoutInflater.from(this);  

        View dialogView = inflater.inflate(R.layout.equipment_repair_dialog, null);
        
        

        final TextView dialog9 = (TextView)(dialogView).findViewById(R.id.dialog9);
        TextView tv2 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item2);//适配器中点击item中的组件
        dialog9.setText(tv2.getText());
        
        final TextView tv1 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item1);//适配器中点击item中的组件
        final EditText dialog1 = (EditText)(dialogView).findViewById(R.id.dialog1);
		final EditText dialog2 = (EditText)(dialogView).findViewById(R.id.dialog2);
		
		Date curDate = new Date(System.currentTimeMillis());
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");
		
		dialog1.setText(dFormat.format(curDate));
		dialog2.setText(tFormat.format(curDate));
		
		final EditText dialog3 = (EditText)(dialogView).findViewById(R.id.dialog3);
		final EditText dialog4 = (EditText)(dialogView).findViewById(R.id.dialog4);
		final EditText dialog5 = (EditText)(dialogView).findViewById(R.id.dialog5);
		final EditText dialog6 = (EditText)(dialogView).findViewById(R.id.dialog6);
		final EditText dialog7 = (EditText)(dialogView).findViewById(R.id.dialog7);
		final Spinner  dialog8 = (Spinner) (dialogView).findViewById(R.id.dialog8);
		
		builder.setView(dialogView);

		builder.setPositiveButton("确定",new DialogInterface.OnClickListener()
        {
			
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("day", dialog1.getText().toString());
                map.put("time", dialog2.getText().toString());
                map.put("equipmentid", tv1.getText().toString());
                map.put("equipment", dialog9.getText().toString());
                map.put("memo", dialog3.getText().toString());
                map.put("usertype", "");
                map.put("userid", "");//查询用户信息表获得
                map.put("deal", dialog4.getText().toString());
                map.put("dealid", "");//?
                map.put("result", dialog5.getText().toString());
                map.put("repday", dialog6.getText().toString());
                map.put("reptime", dialog7.getText().toString());
                map.put("repairstate", dialog8.getSelectedItem().toString());
                new Thread(new ExecRunnable(repairHeader,map)).start();
            }
		});
		builder.setNegativeButton("取消",null);
		Dialog dialog = builder.create();
		dialog.show();	
	}
	
	public void updateDialog(AdapterContextMenuInfo itemMenuInfo){

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
		builder.setTitle("更改设备信息");
		LayoutInflater inflater = LayoutInflater.from(this);  

        View dialogView = inflater.inflate(R.layout.equipment_update_dialog, null);
        
        final EditText dialog1 = (EditText)(dialogView).findViewById(R.id.dialog1);
        TextView tv1 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item1);//适配器中点击item中的组件
        dialog1.setText(tv1.getText());
		final EditText dialog2 = (EditText)(dialogView).findViewById(R.id.dialog2);
        TextView tv2 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item2);//适配器中点击item中的组件
        dialog2.setText(tv2.getText());
		final EditText dialog3 = (EditText)(dialogView).findViewById(R.id.dialog3);
        TextView tv3 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item3);//适配器中点击item中的组件
        dialog3.setText(tv3.getText());
		final EditText dialog4 = (EditText)(dialogView).findViewById(R.id.dialog4);
        TextView tv4 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item4);//适配器中点击item中的组件
        dialog4.setText(tv4.getText());
		final EditText dialog5 = (EditText)(dialogView).findViewById(R.id.dialog5);
        TextView tv5 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item5);//适配器中点击item中的组件
        dialog5.setText(tv5.getText());
		final EditText dialog6 = (EditText)(dialogView).findViewById(R.id.dialog6);
        TextView tv6 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item6);//适配器中点击item中的组件
        dialog6.setText(tv6.getText());
		final EditText dialog7 = (EditText)(dialogView).findViewById(R.id.dialog7);
        TextView tv7 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item7);//适配器中点击item中的组件
        dialog7.setText(tv7.getText());
		final EditText dialog8 = (EditText)(dialogView).findViewById(R.id.dialog8);
        TextView tv8 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item8);//适配器中点击item中的组件
        dialog8.setText(tv8.getText());
		final Spinner dialog9 = (Spinner)(dialogView).findViewById(R.id.dialog9);
        TextView tv9 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item9);//适配器中点击item中的组件
        for(int i=0;i<5;i++){
        	
        	if(tv9.getText().equals(dialog9.getItemAtPosition(i).toString())){
        		dialog9.setSelection(i, true);
        	}
        		
        }
		final Spinner dialog10 = (Spinner)(dialogView).findViewById(R.id.dialog10);
        TextView tv10 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item10);//适配器中点击item中的组件
        for(int i=0;i<3;i++){
        	
        	if(tv10.getText().equals(dialog10.getItemAtPosition(i).toString())){
        		dialog10.setSelection(i, true);
        	}
        		
        }
		final Spinner dialog11 = (Spinner)(dialogView).findViewById(R.id.dialog11);
        TextView tv11 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item11);//适配器中点击item中的组件
        for(int i=0;i<2;i++){
        	
        	if(tv11.getText().equals(dialog11.getItemAtPosition(i).toString())){
        		dialog11.setSelection(i, true);
        	}
        		
        }
		
		builder.setView(dialogView);

		builder.setPositiveButton("确定",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("equipmentid", dialog1.getText().toString());
                map.put("equipment", dialog2.getText().toString());
                map.put("type", dialog3.getText().toString());
                map.put("spec", dialog4.getText().toString());
                map.put("price", dialog5.getText().toString());
                map.put("room", dialog6.getText().toString());
                map.put("groupno", dialog7.getText().toString());
                map.put("chargeperson", dialog8.getText().toString());
                map.put("estate", dialog9.getSelectedItem().toString());
                map.put("ustate", dialog10.getSelectedItem().toString());
                map.put("repairstate", dialog11.getSelectedItem().toString());
                new Thread(new ExecRunnable(updateHeader,map)).start();
            }
		});
		builder.setNegativeButton("取消",null);
		Dialog dialog = builder.create();
		dialog.show();	
	}
	
    
	public void borrowDialog(AdapterContextMenuInfo itemMenuInfo){

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
		builder.setTitle("设备借用");
		LayoutInflater inflater = LayoutInflater.from(this);  

        View dialogView = inflater.inflate(R.layout.equipment_borrow_dialog, null);
        
        

        final TextView dialog1 = (TextView)(dialogView).findViewById(R.id.dialog1);
        TextView tv2 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item2);//适配器中点击item中的组件
        dialog1.setText(tv2.getText());
        
        final TextView tv1 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item1);//适配器中点击item中的组件
        final EditText dialog2 = (EditText)(dialogView).findViewById(R.id.dialog2);
		final EditText dialog3 = (EditText)(dialogView).findViewById(R.id.dialog3);
		final EditText dialog4 = (EditText)(dialogView).findViewById(R.id.dialog4);
		
		Date curDate = new Date(System.currentTimeMillis());
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");
		
		dialog3.setText(dFormat.format(curDate));
		dialog4.setText(tFormat.format(curDate));
		
		final EditText dialog5 = (EditText)(dialogView).findViewById(R.id.dialog5);
		final EditText dialog6 = (EditText)(dialogView).findViewById(R.id.dialog6);
		final EditText dialog7 = (EditText)(dialogView).findViewById(R.id.dialog7);
		builder.setView(dialogView);

		builder.setPositiveButton("确定",new DialogInterface.OnClickListener()
        {
			
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("equipmentid", tv1.getText().toString());
                map.put("takeperson", dialog2.getText().toString());
                map.put("disperson", "");//经手人查询用户信息表获得
                map.put("day", dialog3.getText().toString());
                map.put("time", dialog4.getText().toString());
                map.put("reday", dialog5.getText().toString());
                map.put("retime", dialog6.getText().toString());
                map.put("memo", dialog7.getText().toString());
       
                new Thread(new ExecRunnable(borrowHeader,map)).start();
            }
		});
		builder.setNegativeButton("取消",null);
		Dialog dialog = builder.create();
		dialog.show();	
	}
	
	
    @Override  
    protected void onResume() {  
    	Log.i("***","EquipmentInfoActivity onResume"); 
        new Thread(new SearchRunnable(searchHeader)).start();//设置标签，元件信息的更新
        super.onResume();  
    }
    
    @Override  
    protected void onDestroy() {  
        Log.i("***","EquipmentInfoActivity onDestroy"); 
        handler.removeCallbacksAndMessages(null); //handler内存泄露
        super.onDestroy();  
    }
	

}
