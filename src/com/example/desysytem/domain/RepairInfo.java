package com.example.desysytem.domain;

import java.sql.Date;
import java.sql.Time;

public class RepairInfo {
	private int askrepairid;
	private String day;
	private String time;
	private String equipment;
	private String memo;
	private int usertype;
	private int userid;
	private int deal;
	private int dealid;
	private String result;
	private String repday;
	private String reptime;
	private String repairstate;
	
	public RepairInfo(int askrepairid,String day,String time,String equipment,
			String memo,int usertype,int userid,int deal,int dealid,String result,
			String repday,String reptime,String repairstate){
		
		this.askrepairid = askrepairid;
		this.day = day;
		this.time = time;
		this.equipment = equipment;
		this.memo = memo;
		this.usertype = usertype;
		this.userid = userid;
		this.deal = deal;
		this.dealid = dealid;
		this.result = result;
		this.repday = repday;
		this.reptime = reptime;
		this.repairstate = repairstate;
	}
	public int getAskrepairid() {
		return askrepairid;
	}

	public void setAskrepairid(int askrepairid) {
		this.askrepairid = askrepairid;
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

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getDeal() {
		return deal;
	}

	public void setDeal(int deal) {
		this.deal = deal;
	}

	public int getDealid() {
		return dealid;
	}

	public void setDealid(int dealid) {
		this.dealid = dealid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRepday() {
		return repday;
	}

	public void setRepday(String repday) {
		this.repday = repday;
	}

	public String getReptime() {
		return reptime;
	}

	public void setReptime(String reptime) {
		this.reptime = reptime;
	}

	public String getRepairstate() {
		return repairstate;
	}

	public void setRepairstate(String repairstate) {
		this.repairstate = repairstate;
	}  
}
