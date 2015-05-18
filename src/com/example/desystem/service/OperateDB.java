package com.example.desystem.service;

import java.io.BufferedReader;
import java.lang.reflect.Type;

import java.lang.StringBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.R.string;
import android.util.Log;

import com.example.desysytem.domain.ComponentInfo;
import com.example.desysytem.domain.EquipmentAddInfo;
import com.example.desysytem.domain.EquipmentBorrowInfo;
import com.example.desysytem.domain.EquipmentInfo;
import com.example.desysytem.domain.GoodsInfo;
import com.example.desysytem.domain.RepairInfo;


public class OperateDB {
	
//	private static OperateDB instance = new OperateDB();
//	private  OperateDB() {};
//	public static OperateDB getInstance(){
//		return instance ;
//	}
	
	public String getInputStream(String header,Map<String, String> rawParams) {

		StringBuffer sb = null;
		HttpClient client = null;
		HttpGet get = null;
		StringBuffer url = new StringBuffer(header);
		int cnt = rawParams.size();
		for(String key:rawParams.keySet()){
			url.append(key);
			url.append("=");
			url.append(rawParams.get(key));
			cnt--;
			if(cnt!=0){
				url.append("&");
			}
		}
		
		try {
			
			client = new DefaultHttpClient();//启用Cookie,须close()
			get = new HttpGet(url.toString());
			
			Log.i("http",url.toString());
			
			HttpResponse httpResponse = client.execute(get);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						httpResponse.getEntity().getContent(), "utf-8"));
				String str = "";
				sb = new StringBuffer();
				while ((str = br.readLine()) != null) {
					
					sb.append(str);
				}
				Log.i("http",sb.toString());
				return sb.toString();
				
			}
		} catch (Exception e) {
			e.printStackTrace();// 输出异常信息
		}finally{
			
			if(client!= null && client.getConnectionManager() != null){
				client.getConnectionManager().shutdown();	
			}
			
		}
		return null;
	}
	
	//函数重载
	public String getInputStream(String url) {
		HttpClient client = null;
		HttpGet get = null;
		try {
			client = new DefaultHttpClient();//启用Cookie,须close()
			get = new HttpGet(url);
			HttpResponse httpResponse = client.execute(get);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				Log.i("http","2");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						httpResponse.getEntity().getContent(), "utf-8"));
				String str = "";
				StringBuffer sb = new StringBuffer();
				while ((str = br.readLine()) != null) {
					sb.append(str);
				}
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();// 输出异常信息
		}finally{
			
			if(client!= null && client.getConnectionManager() != null){
				client.getConnectionManager().shutdown();	
			}
			
		}
		return null;
	}
	
	
	public ArrayList<ComponentInfo> parseComponentJson(String jsonString) {

		if(jsonString != null){
			ArrayList<ComponentInfo> comInfos = new ArrayList<ComponentInfo>();
			JSONObject jsonObj;
			try {
				jsonObj = new JSONObject(jsonString);
				JSONArray jsonLogin = jsonObj.getJSONArray("component");
				for (int i = 0; i < jsonLogin.length(); i++) {
					
					JSONObject jo = jsonLogin.getJSONObject(i);
					comInfos.add(new ComponentInfo(jo.getInt("componentid"),
							jo.getString("component"),
							jo.getString("type"),
							jo.getString("function"),
							jo.getString("mainfunc"),
							jo.getInt("vendor"),
							jo.getInt("hasdatasheet"),
							jo.getString("doctype"),
							jo.getString("docname"),
							jo.getInt("downtimes")));
					
				}
				return comInfos;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
//	public List<GoodsInfo> parseJson(String jsonString) {
//
//		JSONObject jsonObj = new JSONObject(jsonString);
//		JSONArray jsonArray = jsonObj.getJSONArray("result");
//		Gson gson = new Gson();
//		List<GoodsInfo> goodsInfos = gson.fromJson(jsonArray,new TypeToken<List<GoodsInfo>>(){}.getType());
//		
//		return goodsInfos;
//	}
	
	public  ArrayList<GoodsInfo> parseGoodsJson(String jsonString){

		if(jsonString != null){
			ArrayList<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();
			JSONObject jsonObj;
			try {
				
				jsonObj = new JSONObject(jsonString);
				JSONArray jsonLogin = jsonObj.getJSONArray("result");
				
				for (int i = 0; i < jsonLogin.length(); i++) {
					
					JSONObject jo = jsonLogin.getJSONObject(i);
					goodsInfos.add(new GoodsInfo(jo.getInt("goodsid"),
							jo.getString("goods"),
							jo.getString("spec"),
							jo.getInt("price"),
							jo.getInt("number"),
							jo.getInt("catalog"),
							jo.getInt("state"),
							jo.getString("photo").getBytes()));
					
				}
				return goodsInfos;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return null;
	}
	
	public  ArrayList<EquipmentInfo> parseEquipmentJson(String jsonString){

		if(jsonString != null){
			ArrayList<EquipmentInfo> equipmentInfos = new ArrayList<EquipmentInfo>();
			JSONObject jsonObj;
			try {
				
				jsonObj = new JSONObject(jsonString);
				JSONArray jsonLogin = jsonObj.getJSONArray("result");
				
				for (int i = 0; i < jsonLogin.length(); i++) {
					
					JSONObject jo = jsonLogin.getJSONObject(i);
					equipmentInfos.add(new EquipmentInfo(jo.getString("equipmentid"),
							jo.getString("equipment"),
							jo.getString("type"),
							jo.getString("spec"),
							(float)jo.getDouble("price"),
							jo.getInt("room"),
							jo.getInt("groupno"),
							jo.getInt("chargeperson"),
							jo.getString("estate"),
							jo.getString("ustate"),
							jo.getString("repairstate")));
					
				}
				return equipmentInfos;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return null;
	}
	
	public  ArrayList<RepairInfo> parseRepairJson(String jsonString){

		if(jsonString != null){
			ArrayList<RepairInfo> repairInfos = new ArrayList<RepairInfo>();
			JSONObject jsonObj;
			try {
				
				jsonObj = new JSONObject(jsonString);
				JSONArray jsonLogin = jsonObj.getJSONArray("result");
				
				for (int i = 0; i < jsonLogin.length(); i++) {
					
					JSONObject jo = jsonLogin.getJSONObject(i);
					repairInfos.add(new RepairInfo(jo.getInt("askrepairid"),
							jo.getString("day"),
							jo.getString("time"),
							jo.getString("equipment"),
							jo.getString("memo"),
							jo.getInt("usertype"),
							jo.getInt("userid"),
							jo.getInt("deal"),
							jo.getInt("dealid"),
							jo.getString("result"),
							jo.getString("repday"),
							jo.getString("reptime"),
							jo.getString("repairstate")));
					
				}
				Log.i("&&length",""+repairInfos.size());
				return repairInfos;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return null;
	}

	public  String parseFlagJson(String jsonString){

		if(jsonString != null){
					
			try {
				
				JSONObject jsonObj = new JSONObject(jsonString);
				String flag = jsonObj.getString("result");
				return flag;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return null;
	}
	
	public  int parseLoginJson(String jsonString){

		int flag = -1;
		if(jsonString != null){
					
			try {
				
				JSONObject jsonObj = new JSONObject(jsonString);
				flag = jsonObj.getInt("result");
				return flag;
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return flag;
	}
	
	public  ArrayList<EquipmentAddInfo> parseEquipmentAddJson(String jsonString){

		if(jsonString != null){
			ArrayList<EquipmentAddInfo> addInfos = new ArrayList<EquipmentAddInfo>();
			JSONObject jsonObj;
			try {
				
				jsonObj = new JSONObject(jsonString);
				JSONArray jsonLogin = jsonObj.getJSONArray("result");
				
				for (int i = 0; i < jsonLogin.length(); i++) {
					
					JSONObject jo = jsonLogin.getJSONObject(i);
					addInfos.add(new EquipmentAddInfo(jo.getInt("equipmentbuyid"),
							jo.getString("equipment"),
							jo.getString("type"),
							jo.getString("spec"),
							jo.getInt("userid"),
							jo.getInt("usertype"),
							jo.getString("day"),
							jo.getString("time"),
							(float)jo.getDouble("price"),
							jo.getInt("num"),
							jo.getString("memo")));
					
				}
				return addInfos;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return null;
	}

	
	public  ArrayList<EquipmentBorrowInfo> parseEquipmentBorrowJson(String jsonString){

		if(jsonString != null){
			ArrayList<EquipmentBorrowInfo> borrowInfos = new ArrayList<EquipmentBorrowInfo>();
			JSONObject jsonObj;
			try {
				
				jsonObj = new JSONObject(jsonString);
				JSONArray jsonLogin = jsonObj.getJSONArray("result");
				
				for (int i = 0; i < jsonLogin.length(); i++) {
					
					JSONObject jo = jsonLogin.getJSONObject(i);
					borrowInfos.add(new EquipmentBorrowInfo(jo.getInt("equipmentborrowid"),
							jo.getInt("equipmentid"),
							jo.getInt("takeperson"),
							jo.getInt("disperson"),
							jo.getString("day"),
							jo.getString("time"),
							jo.getString("reday"),
							jo.getString("retime"),
							jo.getString("memo")));
					
				}
				return borrowInfos;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return null;
	}
}
