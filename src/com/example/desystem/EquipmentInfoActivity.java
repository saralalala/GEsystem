package com.example.desystem;

import java.security.PublicKey;
import android.view.ViewGroup.LayoutParams;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.MaskFilter;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.desystem.adapter.EquipmentAdapter;
import com.example.desystem.adapter.PopupAdapter;
import com.example.desystem.domain.EquipmentInfo;
import com.example.desystem.domain.GoodsInfo;
import com.example.desystem.service.OperateDB;

public class EquipmentInfoActivity extends Activity {

	private final static int ITEM_DELETE = 1;
	private final static int ITEM_BORROW = 2;
	private final static int ITEM_RETURN = 3;
	private final static int ITEM_REPAIR = 4;
	private final static int ITEM_UPDATE = 5;

	private float displayWidth;
	private float displayHeight;

	private ArrayList<EquipmentInfo> equipmentInfos = null;//new ArrayList<EquipmentInfo>();
	private EquipmentAdapter adapter;
	private PopupAdapter roomadapter,manageradapter;
	private HashMap<String, String> map = new HashMap<String, String>();
	//private final String searchEquipment = "http://10.109.23.202/php/lab/search_equipment.php";
	private final String searchRoom = "http://10.109.23.202/php/lab/search_room.php";
	private final String searchTeacher = "http://10.109.23.202/php/lab/search_teacher.php";
	private final String deleteHeader = "http://10.109.23.202/php/lab/delete_equipment.php?";
	private final String updateHeader = "http://10.109.23.202/php/lab/update_equipment.php?";
	private final String repairHeader = "http://10.109.23.202/php/lab/add_repair.php?";
	private final String borrowHeader = "http://10.109.23.202/php/lab/borrow_equipment.php?";
	private final String returnHeader = "http://10.109.23.202/php/lab/return_equipment.php?";
	private final String select = "http://10.109.23.202/php/lab/select_equipment2.php?";//http://10.109.23.202/php/lab/select_equipment.php?";
	private final String searchLimit = "http://10.109.23.202/php/lab/selectLimit_equipment.php?";
	// private final String order =
	// "http://10.109.21.95/lab/order_equipment.php?";
	
	ImageButton addbt;
	Spinner spinnerTable;
	SearchView sv;
	boolean isFirst=true;
	ListView ls,lsroom,lsmanager;
	TextView equipmentid, equipment, spec, price, room, groupno, chargeperson,
			estate, ustate, repairstate;

	ToggleButton tbroom, tbmanager, tbselect;
	PopupWindow roomWindow, managerWindow, selectWindow;
	Spinner spinner;
	View roomView, managerView, selectView;
	RadioGroup radioe, radiou, radior;
	Button reset,ok;
	
	ArrayList<String> roomStr = new ArrayList<String>();//{ "管理员", "袁东明", "姬红强", "王勤", "陈玉波", "张一", "张二",
	//"张三" };
	ArrayList<String> managerStr = new ArrayList<String>();//{ "房间", "主楼611", "主楼511", "主楼510", "主楼711",
			//"主楼603", "主楼622", "主楼519" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.equipment_info);

		addbt = (ImageButton)findViewById(R.id.addbt);
		addbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(EquipmentInfoActivity.this,EquipmentAddActivity.class));
			}
		});
		
		
		spinnerTable = (Spinner)findViewById(R.id.spfunc);
		spinnerTable.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			 @Override
	            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	                // TODO Auto-generated method stub
				 switch (arg2) {
				 
				 case 0:
					 arg1.setVisibility(View.GONE);
					 break;
					 
				 case 1:
					startActivity(new Intent(EquipmentInfoActivity.this,EquipmentRepairInfoActivity.class));
				 	spinner.setSelection(0);
				 	break;
				 
				 case 2:
						startActivity(new Intent(EquipmentInfoActivity.this,EquipmentBorrowInfoActivity.class));
					 	spinner.setSelection(0);
					 	break;
					 	
				 case 3:
					startActivity(new Intent(EquipmentInfoActivity.this,EquipmentAddInfoActivity.class));
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
		
		sv= (SearchView)findViewById(R.id.sv);
		sv.setOnQueryTextListener(new OnQueryTextListener() {  
			  
            @Override  
            public boolean onQueryTextSubmit(String query) {  
                Log.d("%%%%%","onQueryTextSubmit()"+query);
                
                //searchLimit = searchLimit+"limit="+query;
               // new Thread(new SearchRunnable()
                return false;  
            }  
  
            @Override  
            public boolean onQueryTextChange(String newText) {  
            	
                Log.d("%%%%%", "onQueryTextChange(), newText=" + newText);  
                return false;  
            }  
              
        });  
		
		ls = (ListView) findViewById(R.id.lsequip);
		adapter = new EquipmentAdapter(EquipmentInfoActivity.this,
				equipmentInfos);
		ls.setAdapter(adapter);
		registerForContextMenu(ls);

		initUI();
		initSelection();
		initListener();
		
		new Thread(new InitRunnable()).start(); //初始化pupopwindow中ListView

	}

	public void initUI() {

		displayWidth = ((Activity) this).getWindowManager().getDefaultDisplay()
				.getWidth();
		displayHeight = ((Activity) this).getWindowManager()
				.getDefaultDisplay().getHeight();

		LayoutInflater inflater = LayoutInflater.from(this);
		roomView = inflater.inflate(R.layout.select_room, null);
		managerView = inflater.inflate(R.layout.select_manager, null);
		selectView = inflater.inflate(R.layout.select_state, null);

		roomWindow = new PopupWindow(roomView, (int) displayWidth / 4,
				LayoutParams.WRAP_CONTENT, true);
		roomWindow.setAnimationStyle(R.style.PopupWindowAnimation);
		roomWindow.setFocusable(true);// view响应touch监听后不再响应onClick()监听
		roomWindow.setOutsideTouchable(true);
		roomWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.choosearea_bg_mid));

		managerWindow = new PopupWindow(managerView, (int) displayWidth / 4,
				LayoutParams.WRAP_CONTENT, true);
		managerWindow.setAnimationStyle(R.style.PopupWindowAnimation);
		managerWindow.setFocusable(true);
		managerWindow.setOutsideTouchable(true);
		managerWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.choosearea_bg_mid));

		selectWindow = new PopupWindow(selectView, (int) displayWidth / 4,
				LayoutParams.WRAP_CONTENT, true);
		selectWindow.setAnimationStyle(R.style.PopupWindowAnimation);
		selectWindow.setFocusable(true);
		selectWindow.setOutsideTouchable(true);
		selectWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.choosearea_bg_mid));

		lsroom = (ListView) (roomView).findViewById(R.id.lsroom);
		lsmanager = (ListView) (managerView)
				.findViewById(R.id.lsmanager);


		radioe = (RadioGroup) (selectView).findViewById(R.id.radioe);
		radiou = (RadioGroup) (selectView).findViewById(R.id.radiou);
		radior = (RadioGroup) (selectView).findViewById(R.id.radior);

		reset = (Button) (selectView).findViewById(R.id.reset);
		ok = (Button) (selectView).findViewById(R.id.ok);
		
		tbroom = (ToggleButton) findViewById(R.id.room);
		tbmanager = (ToggleButton) findViewById(R.id.manager);
		tbselect = (ToggleButton) findViewById(R.id.select);// togglebutton
		spinner = (Spinner)findViewById(R.id.spsort);
	}

	public void initListener() {
		
		/*  监听ToggleButton */
		
		tbroom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				ToggleButton tg = (ToggleButton) view;

				if (tg.isChecked()) {

					roomWindow.showAsDropDown(view, 20, 0);

				} else {
					
					if (roomWindow.isShowing())
						roomWindow.dismiss();
				}
			}
		});
		
		tbmanager.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				ToggleButton tg = (ToggleButton) view;

				if (tg.isChecked()) {

					if (managerWindow != null && !managerWindow.isShowing())
						managerWindow.showAsDropDown(view, 0, 0);
				} else {

					if (managerWindow != null && managerWindow.isShowing())
						managerWindow.dismiss();
				}
			}// change state
		});

		tbselect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				ToggleButton tg = (ToggleButton) view;

				if (tg.isChecked()) {

					selectWindow.showAsDropDown(view, 0, 0);

				} else {

					if (selectWindow.isShowing())
						selectWindow.dismiss();
				}
			}
		});
		
		roomWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub

				if (tbroom.isChecked() && !roomWindow.isShowing())
					tbroom.setChecked(false);
			}
		});

		managerWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				if (tbmanager.isChecked() && !managerWindow.isShowing())
					tbmanager.setChecked(false);
			}
		});// 点击即消失，popupwindow消失设置监听

		selectWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub

				if (tbselect.isChecked() && !selectWindow.isShowing())
					tbselect.setChecked(false);
				// resetCheckBox();保存map中状态
			}
		});
		
		/*  监听PopWindow中的ListView */

		lsmanager.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arg2 == 0) {

					tbmanager.setText(managerStr.get(arg2));
					if (map.containsKey("chargeperson"))
						map.remove("chargeperson");
					managerWindow.dismiss();
					// showDialog

				} else {

					tbmanager.setText(managerStr.get(arg2));
					map.put("chargeperson", managerStr.get(arg2));//替换
					managerWindow.dismiss();

				}
				
				Log.i("##adapter1", ""+arg2);
				
				manageradapter = new PopupAdapter(EquipmentInfoActivity.this,managerStr);
				manageradapter.changeSelected(arg2);
				lsmanager.setAdapter(manageradapter);
				
				new Thread(new SelectRunnable(select, map)).start();
			}

		});

		lsroom.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arg2 == 0) {

					tbroom.setText(roomStr.get(arg2));
					if (map.containsKey("room"))
						map.remove("room");
					roomWindow.dismiss();

				} else {

					tbroom.setText(roomStr.get(arg2));
					map.put("room", roomStr.get(arg2));
					roomWindow.dismiss();
					
				}

				Log.i("##adapter1", ""+arg2);
				
				roomadapter = new PopupAdapter(EquipmentInfoActivity.this,roomStr);
				roomadapter.changeSelected(arg2);
				lsroom.setAdapter(roomadapter);

				new Thread(new SelectRunnable(select, map)).start();
			}

		});

		/*  监听PopWindow中的Button */
		
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				radioe.clearCheck();
				if (map.containsKey("estate"))
					map.remove("estate");
				radiou.clearCheck();
				if (map.containsKey("ustate"))
					map.remove("ustate");
				radior.clearCheck();
				if (map.containsKey("repairstate"))
					map.remove("repairstate");
				
				Log.i("map", ""+map.toString());
			}
		});

		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {

				int radioeId = radioe.getCheckedRadioButtonId();
				int radiouId = radiou.getCheckedRadioButtonId();
				int radiorId = radior.getCheckedRadioButtonId();
				
				switch (radioeId) {

				case R.id.radioe1:
					map.put("estate", "good");
					break;

				case R.id.radioe2:
					map.put("estate", "repair");
					break;

				case R.id.radioe3:
					map.put("estate", "bad");
					break;

				case -1:
					if (map.containsKey("estate"))
						map.remove("estate");
					break;

				default:
					break;
				}

				switch (radiouId) {

				case R.id.radiou1:
					map.put("ustate", "inuse");
					break;

				case R.id.radiou2:
					map.put("ustate", "back");
					break;

				case R.id.radiou3:
					map.put("ustate", "lend");
					break;

				case -1:
					if (map.containsKey("ustate"))
						map.remove("ustate");
					break;

				default:
					break;
				}

				switch (radiorId) {

				case R.id.radior1:
					map.put("repairstate", "factory");
					break;

				case R.id.radior2:
					map.put("repairstate", "own");
					break;

				case -1:
					if (map.containsKey("repairstate"))
						map.remove("repairstate");
					break;

				default:
					break;
				}
				selectWindow.dismiss();
				Log.i("map", ""+map.toString());
				new Thread(new SelectRunnable(select,map)).start();
			}
		});
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			  @SuppressWarnings("unchecked")
			@Override
			  
		        public void  onItemSelected(AdapterView<?> arg0, View arg1,
		  
		              int arg2, long arg3) {
				  
				  if(equipmentInfos != null){
					  
					  switch (arg2) {
					  
						case 1:
							
							//价格由高到底
							adapter.notifyDataSetChanged();
							break;
							
						case 2:
							
							Comparator comp = new Mycomparator();
							Collections.sort(equipmentInfos,comp); 
							
							for(int i = 0;i<equipmentInfos.size();i++ ){
								
								EquipmentInfo p = (EquipmentInfo)equipmentInfos.get(i);
								System.out.println(p.getPrice());
							}
							adapter = new EquipmentAdapter(EquipmentInfoActivity.this,
									equipmentInfos);
							ls.setAdapter(adapter);
							//adapter.notifyDataSetChanged();
							break;
							
						default:
							break;
						}
				  
				  }
		    
		        }
		  
		  
		        @Override
		  
		        public void  onNothingSelected(AdapterView<?> arg0) {
		  
		           // TODO Auto-generated method stub

		        }
		});

	}

	public void initSelection() {

		lsroom.setSelection(0);
		lsmanager.setSelection(0);
		tbroom.setText("房间");
		tbmanager.setText("管理员");
		radioe.clearCheck();
		radiou.clearCheck();
		radior.clearCheck();
		spinner.setSelection(0);
		map.clear();

	}
	
	public class Mycomparator implements Comparator{

		public int compare(Object o1,Object o2) {
		EquipmentInfo e1=(EquipmentInfo)o1;
		EquipmentInfo e2=(EquipmentInfo)o2; 
		
		if(e1.getPrice()<e2.getPrice())
			
			return 1;
		else
			
			return 0;
		}

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			switch (msg.what) {
			
			case 1:
				Bundle bundle1 = msg.getData();
				ArrayList list1 = bundle1.getParcelableArrayList("list");
//				equipmentInfos.clear();  
//				equipmentInfos.addAll((ArrayList<EquipmentInfo>) list1.get(0));
				equipmentInfos = (ArrayList<EquipmentInfo>) list1.get(0);

				if (equipmentInfos == null) {

					Toast.makeText(getApplicationContext(), "无法获取数据，请检查网络设置~_~",
							Toast.LENGTH_SHORT).show();
				}// 没有获得请求数据或数据没有正确解析

				else {
					
					adapter = new EquipmentAdapter(EquipmentInfoActivity.this,
							equipmentInfos);
					ls.setAdapter(adapter);
					//adapter.notifyDataSetChanged();//监听数据源对象（不是数据变量equipmentInfos）的改变

				}
				break;
				
			case 2:
				Bundle bundle2 = msg.getData();
				ArrayList list2 = bundle2.getParcelableArrayList("list");
//				equipmentInfos.clear();  
//				equipmentInfos.addAll((ArrayList<EquipmentInfo>) list2.get(0));
				equipmentInfos = (ArrayList<EquipmentInfo>) list2.get(0);

				if (equipmentInfos == null) {

					Toast.makeText(getApplicationContext(), "没有查询数据~_~",
							Toast.LENGTH_LONG).show();
					adapter = new EquipmentAdapter(EquipmentInfoActivity.this,
							equipmentInfos);
					ls.setAdapter(adapter); 
				}// 没有获得请求数据或数据没有正确解析

				else {
					adapter = new EquipmentAdapter(EquipmentInfoActivity.this,
							equipmentInfos);
					ls.setAdapter(adapter);
					//adapter.notifyDataSetChanged();

				}
				break;

			case 3:
				Bundle bundle3 = msg.getData();
				ArrayList list3 = bundle3.getParcelableArrayList("list");
//				equipmentInfos.clear();  
//				equipmentInfos.addAll((ArrayList<EquipmentInfo>) list3.get(0));
				equipmentInfos = (ArrayList<EquipmentInfo>) list3.get(0);

				if (equipmentInfos == null) {

					Toast.makeText(getApplicationContext(), "操作失败~_~",
							Toast.LENGTH_LONG).show();
				}

				else {

					Toast.makeText(getApplicationContext(), "操作成功~~",
							Toast.LENGTH_LONG).show();
					initSelection();
					adapter = new EquipmentAdapter(EquipmentInfoActivity.this,
							equipmentInfos);
					ls.setAdapter(adapter);
					//adapter.notifyDataSetChanged();

				}
				break;
				
			case 4:
				
				roomadapter = new PopupAdapter(EquipmentInfoActivity.this,roomStr);
				lsroom.setAdapter(roomadapter);
				manageradapter = new PopupAdapter(EquipmentInfoActivity.this,managerStr);
				lsmanager.setAdapter(manageradapter);
				break;

			default:
				break;
			}

		}
	};// 运行在主线程中

	
	class InitRunnable implements Runnable {


		@Override            
		public void run() {

			// TODO Auto-generated method stub
			
			OperateDB operateDB = new OperateDB();
			String jsonRoom = operateDB.getInputStream(searchRoom);
			String jsonTeacher = operateDB.getInputStream(searchTeacher);

			Log.i("##searchroom", jsonRoom + "");
			Log.i("##searchteacher", jsonTeacher + "");

			if (jsonRoom != null) {

				roomStr = operateDB.parseRoomJson(jsonRoom);

			}
			
			if (jsonTeacher != null) {

				managerStr = operateDB.parseTeacherJson(jsonTeacher);

			}

			handler.sendEmptyMessage(4);

		}
	};
	
	
	class SearchRunnable implements Runnable {

		private String urlHeader;

		public SearchRunnable(String urlHeader) {
			this.urlHeader = urlHeader;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<EquipmentInfo> g = null;
			String jsonString = operateDB.getInputStream(urlHeader);

			Log.i("##searchquipment", jsonString + "");

			if (jsonString != null) {

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
	
	class SelectRunnable implements Runnable {

		private String urlHeader;
		private Map<String, String> map;

		public SelectRunnable(String urlHeader,Map<String, String> map) {
			this.urlHeader = urlHeader;
			this.map = map;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<EquipmentInfo> g = null;
			String jsonString = "";
			if (map.size() == 0) {

				jsonString = operateDB.getInputStream(urlHeader);

			} else {

				jsonString = new OperateDB().getInputStream(urlHeader, map);
			}

			Log.i("##selectquipment", jsonString + "");

			if (jsonString != null) {

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

	class ExecRunnable implements Runnable {

		private String urlHeader;
		private Map<String, String> map;

		public ExecRunnable(String urlHeader, Map<String, String> map) {
			this.urlHeader = urlHeader;
			this.map = map;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<EquipmentInfo> g = null;
			String jsonString = "";
			if (map.size() == 0) {

				jsonString = operateDB.getInputStream(urlHeader);

			} else {

				jsonString = new OperateDB().getInputStream(urlHeader, map);
			}

			Log.i("##operatequipment", jsonString + "");

			if (jsonString != null) {

				g = operateDB.parseEquipmentJson(jsonString);

			}

			Message msg = handler.obtainMessage(3);
			Bundle bundle = new Bundle();
			ArrayList list = new ArrayList(); // 需要传递的ArrayList<Object>
			list.add(g);
			bundle.putParcelableArrayList("list", list);// list数组中的对象可以序列化
			msg.setData(bundle);
			msg.sendToTarget();// 利用handler将信息传给UI线程i
		}
	};

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		menu.add(0, ITEM_DELETE, 5, "删除");
		menu.add(0, ITEM_BORROW, 1, "借用");
		menu.add(0, ITEM_RETURN, 2, "归还");
		menu.add(0, ITEM_REPAIR, 3, "报修");
		menu.add(0, ITEM_UPDATE, 4, "盘点");
		TextView tvName = (TextView) (info.targetView).findViewById(R.id.item2);// 适配器中点击item中的组件
		menu.setHeaderTitle(tvName.getText());
		// super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo itemMenuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {

		case ITEM_DELETE:

			Map<String, String> delMap = new HashMap<String, String>();
			TextView delId = (TextView) (itemMenuInfo.targetView)
					.findViewById(R.id.item1);
			delMap.put("equipmentid", delId.getText().toString());
			new Thread(new ExecRunnable(deleteHeader, delMap)).start();
			break;

		case ITEM_REPAIR:

			repairDialog(itemMenuInfo);
			// AlertDialog.Builder builder = new
			// AlertDialog.Builder(this.getParent());

			break;

		case ITEM_BORROW:

			borrowDialog(itemMenuInfo);
			break;

		case ITEM_RETURN:

			Date curDate = new Date(System.currentTimeMillis());
			SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");

			Map<String, String> retMap = new HashMap<String, String>();
			TextView retId = (TextView) (itemMenuInfo.targetView)
					.findViewById(R.id.item1);

			retMap.put("equipmentid", retId.getText().toString());
			retMap.put("reday", dFormat.format(curDate));
			retMap.put("retime", tFormat.format(curDate));
			/* 归还 */

			new Thread(new ExecRunnable(returnHeader, retMap)).start();
			break;
		case ITEM_UPDATE:

			updateDialog(itemMenuInfo);
			// AlertDialog.Builder builder = new
			// AlertDialog.Builder(this.getParent());

			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);

	}

	public void repairDialog(AdapterContextMenuInfo itemMenuInfo) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
		builder.setTitle("设备报修");
		LayoutInflater inflater = LayoutInflater.from(this);

		View dialogView = inflater.inflate(R.layout.equipment_repair_dialog,
				null);

		final TextView dialog9 = (TextView) (dialogView)
				.findViewById(R.id.dialog9);
		TextView tv2 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item2);// 适配器中点击item中的组件
		dialog9.setText(tv2.getText());

		final TextView tv1 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item1);// 适配器中点击item中的组件
		final EditText dialog1 = (EditText) (dialogView)
				.findViewById(R.id.dialog1);
		final EditText dialog2 = (EditText) (dialogView)
				.findViewById(R.id.dialog2);

		Date curDate = new Date(System.currentTimeMillis());
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");

		dialog1.setText(dFormat.format(curDate));
		dialog2.setText(tFormat.format(curDate));

		final EditText dialog3 = (EditText) (dialogView)
				.findViewById(R.id.dialog3);
		final EditText dialog4 = (EditText) (dialogView)
				.findViewById(R.id.dialog4);
		final EditText dialog5 = (EditText) (dialogView)
				.findViewById(R.id.dialog5);
		final EditText dialog6 = (EditText) (dialogView)
				.findViewById(R.id.dialog6);
		final EditText dialog7 = (EditText) (dialogView)
				.findViewById(R.id.dialog7);
		final Spinner dialog8 = (Spinner) (dialogView)
				.findViewById(R.id.dialog8);

		builder.setView(dialogView);

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("day", dialog1.getText().toString());
				map.put("time", dialog2.getText().toString());
				map.put("equipmentid", tv1.getText().toString());
				map.put("equipment", dialog9.getText().toString());
				map.put("memo", dialog3.getText().toString());
				map.put("usertype", "");
				map.put("userid", "");// 查询用户信息表获得
				map.put("deal", dialog4.getText().toString());
				map.put("dealid", "");// ?
				map.put("result", dialog5.getText().toString());
				map.put("repday", dialog6.getText().toString());
				map.put("reptime", dialog7.getText().toString());
				map.put("repairstate", dialog8.getSelectedItem().toString());
				new Thread(new ExecRunnable(repairHeader, map)).start();
			}
		});
		builder.setNegativeButton("取消", null);
		Dialog dialog = builder.create();
		dialog.show();
	}

	public void updateDialog(AdapterContextMenuInfo itemMenuInfo) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
		builder.setTitle("更改设备信息");
		LayoutInflater inflater = LayoutInflater.from(this);

		View dialogView = inflater.inflate(R.layout.equipment_update_dialog,
				null);

		final EditText dialog1 = (EditText) (dialogView)
				.findViewById(R.id.dialog1);
		TextView tv1 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item1);// 适配器中点击item中的组件
		dialog1.setText(tv1.getText());
		final EditText dialog2 = (EditText) (dialogView)
				.findViewById(R.id.dialog2);
		TextView tv2 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item2);// 适配器中点击item中的组件
		dialog2.setText(tv2.getText());
		final EditText dialog3 = (EditText) (dialogView)
				.findViewById(R.id.dialog3);
		TextView tv3 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item3);// 适配器中点击item中的组件
		dialog3.setText(tv3.getText());
		final EditText dialog4 = (EditText) (dialogView)
				.findViewById(R.id.dialog4);
		TextView tv4 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item4);// 适配器中点击item中的组件
		dialog4.setText(tv4.getText());
		final EditText dialog5 = (EditText) (dialogView)
				.findViewById(R.id.dialog5);
		TextView tv5 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item5);// 适配器中点击item中的组件
		dialog5.setText(tv5.getText());
		final EditText dialog6 = (EditText) (dialogView)
				.findViewById(R.id.dialog6);
		TextView tv6 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item6);// 适配器中点击item中的组件
		dialog6.setText(tv6.getText());
		final EditText dialog7 = (EditText) (dialogView)
				.findViewById(R.id.dialog7);
		TextView tv7 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item7);// 适配器中点击item中的组件
		dialog7.setText(tv7.getText());
		final EditText dialog8 = (EditText) (dialogView)
				.findViewById(R.id.dialog8);
		TextView tv8 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item8);// 适配器中点击item中的组件
		dialog8.setText(tv8.getText());
		final Spinner dialog9 = (Spinner) (dialogView)
				.findViewById(R.id.dialog9);
		TextView tv9 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item9);// 适配器中点击item中的组件
		for (int i = 0; i < 5; i++) {

			if (tv9.getText().equals(dialog9.getItemAtPosition(i).toString())) {
				dialog9.setSelection(i, true);
			}

		}
		final Spinner dialog10 = (Spinner) (dialogView)
				.findViewById(R.id.dialog10);
		TextView tv10 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item10);// 适配器中点击item中的组件
		for (int i = 0; i < 3; i++) {

			if (tv10.getText().equals(dialog10.getItemAtPosition(i).toString())) {
				dialog10.setSelection(i, true);
			}

		}
		final Spinner dialog11 = (Spinner) (dialogView)
				.findViewById(R.id.dialog11);
		TextView tv11 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item11);// 适配器中点击item中的组件
		for (int i = 0; i < 2; i++) {

			if (tv11.getText().equals(dialog11.getItemAtPosition(i).toString())) {
				dialog11.setSelection(i, true);
			}

		}

		builder.setView(dialogView);

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Map<String, String> map = new HashMap<String, String>();
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
				new Thread(new ExecRunnable(updateHeader, map)).start();
			}
		});
		builder.setNegativeButton("取消", null);
		Dialog dialog = builder.create();
		dialog.show();
	}

	public void borrowDialog(AdapterContextMenuInfo itemMenuInfo) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
		builder.setTitle("设备借用");
		LayoutInflater inflater = LayoutInflater.from(this);

		View dialogView = inflater.inflate(R.layout.equipment_borrow_dialog,
				null);

		final TextView dialog1 = (TextView) (dialogView)
				.findViewById(R.id.dialog1);
		TextView tv2 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item2);// 适配器中点击item中的组件
		dialog1.setText(tv2.getText());

		final TextView tv1 = (TextView) (itemMenuInfo.targetView)
				.findViewById(R.id.item1);// 适配器中点击item中的组件
		final EditText dialog2 = (EditText) (dialogView)
				.findViewById(R.id.dialog2);
		final EditText dialog3 = (EditText) (dialogView)
				.findViewById(R.id.dialog3);
		final EditText dialog4 = (EditText) (dialogView)
				.findViewById(R.id.dialog4);

		Date curDate = new Date(System.currentTimeMillis());
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");

		dialog3.setText(dFormat.format(curDate));
		dialog4.setText(tFormat.format(curDate));

		final EditText dialog5 = (EditText) (dialogView)
				.findViewById(R.id.dialog5);
		final EditText dialog6 = (EditText) (dialogView)
				.findViewById(R.id.dialog6);
		final EditText dialog7 = (EditText) (dialogView)
				.findViewById(R.id.dialog7);
		builder.setView(dialogView);

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("equipmentid", tv1.getText().toString());
				map.put("takeperson", dialog2.getText().toString());
				map.put("disperson", "");// 经手人查询用户信息表获得
				map.put("day", dialog3.getText().toString());
				map.put("time", dialog4.getText().toString());
				map.put("reday", dialog5.getText().toString());
				map.put("retime", dialog6.getText().toString());
				map.put("memo", dialog7.getText().toString());

				new Thread(new ExecRunnable(borrowHeader, map)).start();
			}
		});
		builder.setNegativeButton("取消", null);
		Dialog dialog = builder.create();
		dialog.show();
	}

	@Override
	protected void onResume() {
		Log.i("***", "EquipmentInfoActivity onResume");
		
		initSelection();
		new Thread(new SearchRunnable(select)).start();// 设置标签，元件信息的更新
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		Log.i("***", "EquipmentInfoActivity onDestroy");
		handler.removeCallbacksAndMessages(null); // handler内存泄露
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
