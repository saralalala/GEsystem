package com.example.desystem.domain;

import java.sql.Date;
import java.sql.Time;

public class AskrepairInfo {
	private int askrepairid;
	private Date day;
	private Time time;
	private String equipment;
	private String memo;
	private int usertype;
	private int userid;
	private int deal;
	private int dealid;
	private String result;
	private Date repday;
	private Time reptime;
	private String repairstate;
	
	public int getAskrepairid() {
		return askrepairid;
	}

	public void setAskrepairid(int askrepairid) {
		this.askrepairid = askrepairid;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
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

	public Date getRepday() {
		return repday;
	}

	public void setRepday(Date repday) {
		this.repday = repday;
	}

	public Time getReptime() {
		return reptime;
	}

	public void setReptime(Time reptime) {
		this.reptime = reptime;
	}

	public String getRepairstate() {
		return repairstate;
	}

	public void setRepairstate(String repairstate) {
		this.repairstate = repairstate;
	}  
}
