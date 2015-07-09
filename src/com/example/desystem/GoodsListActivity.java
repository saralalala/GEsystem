package com.example.desystem;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desystem.adapter.GoodsAdapter;
import com.example.desystem.domain.GoodsInfo;
import com.example.desystem.service.OperateDB;

public class GoodsListActivity extends Activity {

	private final static int ITEM_DELETE = 1;
	private final static int ITEM_UPDATE = 2;
	
	private ArrayList<GoodsInfo> goodsInfos;
	private GoodsAdapter adapter;
	private final String searchHeader = "http://10.109.21.95/lab/search_goods.php";
	private final String deleteHeader = "http://10.109.21.95/lab/delete_goods.php?";
	private final String updateHeader = "http://10.109.21.95/lab/update_goods.php?";
	
	ListView ls;
	TextView goodsid;
	TextView  goods, spec, price, number, catalog, state, photo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.goods_info);
		
		Log.i("***","MyActivity1 onCreate"); 
		
		ls = (ListView) findViewById(R.id.lsele);
		//new Thread(new ExecRunnable(searchHeader)).start();
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
				goodsInfos = (ArrayList<GoodsInfo>) list1.get(0);

				if (goodsInfos == null) {

					Toast.makeText(getApplicationContext(), "信息为空，请检查网络或者数据解析~_~",
							Toast.LENGTH_LONG).show();
				}//没有获得请求数据或数据没有正确解析

				else {

					adapter = new GoodsAdapter(GoodsListActivity.this, goodsInfos);
					ls.setAdapter(adapter);
						
				}
				break;
				
			case 2:
				Bundle bundle2 = msg.getData();
				ArrayList list2 = bundle2.getParcelableArrayList("list");
				goodsInfos = (ArrayList<GoodsInfo>) list2.get(0);

				if (goodsInfos == null) {

					Toast.makeText(getApplicationContext(), "操作失败~_~",
							Toast.LENGTH_LONG).show();
				}//没有获得请求数据或数据没有正确解析

				else {

					adapter = new GoodsAdapter(GoodsListActivity.this, goodsInfos);
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
			ArrayList<GoodsInfo> g = null;
			String jsonString = operateDB.getInputStream(urlHeader);
			Log.i("##goodssearch",""+jsonString);
			if(jsonString !=null){
				
				g = operateDB.parseGoodsJson(jsonString);
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
	
	
	class DeleteRunnable implements Runnable{
		
		private String urlHeader;
		private Map<String, String> map;
		
		public DeleteRunnable(String urlHeader,Map<String, String> map){
			this.urlHeader = urlHeader;
			this.map = map;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<GoodsInfo> g = null;
			String jsonString = new OperateDB().getInputStream(urlHeader , map);
			Log.i("##goodsdelete",""+jsonString);
			if(jsonString != null){
				
				g = operateDB.parseGoodsJson(jsonString);
				
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
		menu.add(0, ITEM_DELETE, 1, "删除该元件");
		menu.add(0,ITEM_UPDATE,2,"更新元件");
		TextView tvName = (TextView)(info.targetView).findViewById(R.id.item2);//适配器中点击item中的组件
		menu.setHeaderTitle(tvName.getText());
		//super.onCreateContextMenu(menu, v, menuInfo); 
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		
		AdapterContextMenuInfo itemMenuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		
		switch (item.getItemId()) {
		
		case ITEM_DELETE:
			
			Map<String, String> map = new HashMap<String, String>();
			TextView goods1 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item1);
			map.put("goodsid",goods1.getText().toString());
			new Thread(new DeleteRunnable(deleteHeader,map)).start();
			break;
			
		case ITEM_UPDATE:

			myDialog(itemMenuInfo);
			//AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
			
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item); 
		
	}
	
	
	public void myDialog(AdapterContextMenuInfo itemMenuInfo){

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getParent());
		builder.setTitle("更改元件信息");
		LayoutInflater inflater = LayoutInflater.from(this);  

        View dialogView = inflater.inflate(R.layout.goods_dialog_view, null);
        
        final EditText et1 = (EditText)(dialogView).findViewById(R.id.item1);
        TextView tv1 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item1);//适配器中点击item中的组件
		et1.setText(tv1.getText());
		final EditText et2 = (EditText)(dialogView).findViewById(R.id.item2);
        TextView tv2 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item2);//适配器中点击item中的组件
		et2.setText(tv2.getText());
		final EditText et3 = (EditText)(dialogView).findViewById(R.id.item3);
        TextView tv3 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item3);//适配器中点击item中的组件
		et3.setText(tv3.getText());
		final EditText et4 = (EditText)(dialogView).findViewById(R.id.item4);
        TextView tv4 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item4);//适配器中点击item中的组件
		et4.setText(tv4.getText());
		final EditText et5 = (EditText)(dialogView).findViewById(R.id.item5);
        TextView tv5 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item5);//适配器中点击item中的组件
		et5.setText(tv1.getText());
		final EditText et6 = (EditText)(dialogView).findViewById(R.id.item6);
        TextView tv6 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item6);//适配器中点击item中的组件
		et6.setText(tv1.getText());
		final EditText et7 = (EditText)(dialogView).findViewById(R.id.item7);
        TextView tv7 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item7);//适配器中点击item中的组件
		et7.setText(tv7.getText());
		final EditText et8 = (EditText)(dialogView).findViewById(R.id.item8);
        TextView tv8 = (TextView)(itemMenuInfo.targetView).findViewById(R.id.item8);//适配器中点击item中的组件
		et8.setText(tv8.getText());
		
		builder.setView(dialogView);

		builder.setPositiveButton("确定",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("goodsid", et1.getText().toString());
                map.put("goods", et2.getText().toString());
                map.put("spec", et3.getText().toString());
                map.put("price", et4.getText().toString());
                map.put("number", et5.getText().toString());
                map.put("catalog", et6.getText().toString());
                map.put("state", et7.getText().toString());
                map.put("photo", et8.getText().toString());
                new Thread(new DeleteRunnable(updateHeader,map)).start();
            }
		});
		builder.setNegativeButton("取消",null);
		Dialog dialog = builder.create();
		dialog.show();
		
	}
	
	@Override  
	protected void onStop() {  
        Log.i("***","MyActivity1 onStop");  
        super.onStop();  
    }  
      
    @Override  
    protected void onPause() {  
        Log.i("***","MyActivity1 onPause");  
        super.onPause();  
    }  
      
    @Override  
    protected void onStart() {  
        Log.i("***","MyActivity1 onStart");  
        super.onPause();  
    }  
    
    @Override  
    protected void onResume() {  
        Log.i("***","MyActivity1 onResume"); 
        new Thread(new SearchRunnable(searchHeader)).start();//设置标签，元件信息的更新
        super.onResume();  
    }
    
    @Override  
    protected void onDestroy() {  
        Log.i("***","MyActivity1 onDestroy"); 
        handler.removeCallbacksAndMessages(null); //handler内存泄露
        super.onDestroy();  
    }
	

}
