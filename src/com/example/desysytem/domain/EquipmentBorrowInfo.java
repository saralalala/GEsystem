package com.example.desysytem.domain;

public class EquipmentBorrowInfo {
	
	private int equipmentborrowid;
	private int equipmentid;
	private int takeperson;
	private int disperson;
	private String day;
	private String time;
	private String reday;
	private String retime;
	private String memo;
	public EquipmentBorrowInfo(int equipmentborrowid, int equipmentid,
			int takeperson, int disperson, String day, String time,
			String reday, String retime, String memo) {
		super();
		this.equipmentborrowid = equipmentborrowid;
		this.equipmentid = equipmentid;
		this.takeperson = takeperson;
		this.disperson = disperson;
		this.day = day;
		this.time = time;
		this.reday = reday;
		this.retime = retime;
		this.memo = memo;
	}
	public int getEquipmentborrowid() {
		return equipmentborrowid;
	}
	public void setEquipmentborrowid(int equipmentborrowid) {
		this.equipmentborrowid = equipmentborrowid;
	}
	public int getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(int equipmentid) {
		this.equipmentid = equipmentid;
	}
	public int getTakeperson() {
		return takeperson;
	}
	public void setTakeperson(int takeperson) {
		this.takeperson = takeperson;
	}
	public int getDisperson() {
		return disperson;
	}
	public void setDisperson(int disperson) {
		this.disperson = disperson;
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
	public String getReday() {
		return reday;
	}
	public void setReday(String reday) {
		this.reday = reday;
	}
	public String getRetime() {
		return retime;
	}
	public void setRetime(String retime) {
		this.retime = retime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}