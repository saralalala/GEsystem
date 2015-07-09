package com.example.desystem.domain;

import android.R.integer;

public class RoomInfo{
	
	int roomid;
	String room;
	String roomtype;
	int seat;
	String phone;
	String passwd;
	String ip;
	int open;
	
	public RoomInfo(int roomid, String room, String roomtype, int seat,
			String phone, String passwd, String ip, int open) {
		
		super();
		this.roomid = roomid;
		this.room = room;
		this.roomtype = roomtype;
		this.seat = seat;
		this.phone = phone;
		this.passwd = passwd;
		this.ip = ip;
		this.open = open;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}
	
	
	
}