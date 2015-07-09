package com.example.desystem.domain;

import android.graphics.Shader.TileMode;

public class EquipmentInfo {
	
	private String equipmentid;
	private String equipment;
	private String type;
	private String spec;
	private float price;
	private String  room;
	private int groupno;
	private String chargeperson;
	private String estate;
	private String ustate;
	private String repairstate;
	
	
	public EquipmentInfo(String equipmentid,String equipment,String type,String spec,float price,String room,int groupno,String chargeperson,String estate,String ustate,String repairstate){
		
		this.equipmentid = equipmentid;
		this.equipment = equipment;
		this.type = type;
		this.spec = spec;
		this.price = price;
		this.room = room;
		this.groupno = groupno;
		this.chargeperson = chargeperson;
		this.estate = estate;
		this.ustate = ustate;
		this.repairstate = repairstate;
		
	}
	
	public String getEquipmentid() {
		return equipmentid;
	}


	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
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


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public int getGroupno() {
		return groupno;
	}


	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}


	public String getChargeperson() {
		return chargeperson;
	}


	public void setChargeperson(String chargeperson) {
		this.chargeperson = chargeperson;
	}


	public String getEstate() {
		return estate;
	}


	public void setEstate(String estate) {
		this.estate = estate;
	}


	public String getUstate() {
		return ustate;
	}


	public void setUstate(String ustate) {
		this.ustate = ustate;
	}


	public String getRepairstate() {
		return repairstate;
	}


	public void setRepairstate(String repairstate) {
		this.repairstate = repairstate;
	}

}