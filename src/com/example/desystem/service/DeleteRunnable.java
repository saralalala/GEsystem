package com.example.desystem.service;
import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.desysytem.domain.GoodsInfo;

public class DeleteRunnable implements Runnable{
		
		private String urlHeader;
		private Map<String, String> map;
		private Handler handler;
		
		public DeleteRunnable(String urlHeader,Map<String, String> map,Handler handler){
			this.urlHeader = urlHeader;
			this.map = map;
			this.handler = handler;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<GoodsInfo> g = null;
			String jsonString = new OperateDB().getInputStream(urlHeader , map);
			Log.e("flag",jsonString+"");
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
	}