package com.example.desystem.service;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.desystem.domain.GoodsInfo;

public class SearchRunnable implements Runnable{
		
		private String urlHeader;
		private Handler handler;
		public SearchRunnable(String urlHeader,Handler handler){
			this.urlHeader = urlHeader;
			this.handler = handler ;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			OperateDB operateDB = new OperateDB();
			ArrayList<GoodsInfo> g = null;
			
			String jsonString = operateDB.getInputStream(urlHeader);
			Log.i("***NN",""+jsonString);
			
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
	}