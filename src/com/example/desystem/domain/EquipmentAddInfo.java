package com.example.desystem.domain;


public class EquipmentAddInfo {
	
	private int equipmentbuyid;
	private String equipment;
	private String type;
	private String spec;
	private int userid;
	private int usertype;
	private String day;
	private String time;
	private float price ;
	private int num;
	private String memo;
	
	
	public EquipmentAddInfo(int equipmentbuyid,String equipment,String type,String spec,int userid,int usertype,String day,String time,float price,int num,String memo){
		
		this.equipmentbuyid = equipmentbuyid;
		this.equipment = equipment;
		this.type = type;
		this.spec = spec;
		this.price = price;
		this.userid = userid;
		this.usertype = usertype;
		this.day = day;
		this.time = time;
		this.num = num;
		this.memo = memo;
		
	}


	public int getEquipmentbuyid() {
		return equipmentbuyid;
	}


	public void setEquipmentbuyid(int equipmentbuyid) {
		this.equipmentbuyid = equipmentbuyid;
	}


	public String getEquipment() {
		return equipment;
	}


	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSpec() {
		return spec;
	}


	public void setSpec(String spec) {
		this.spec = spec;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public int getUsertype() {
		return usertype;
	}


	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getmemo() {
		return memo;
	}


	public void setmemo(String memo) {
		this.memo = memo;
	}
	
}