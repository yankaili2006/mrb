package com.mrb.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mrb.util.DateUtil;

public class OperateBean {
	private long opid;
	private long uid;
	private String uname;
	private String oper;
	private long date;

	public long getOpid() {
		return opid;
	}

	public void setOpid(long opid) {
		this.opid = opid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getDate() {
		return DateUtil.format(date);
	}

	public void setDate(long date) {
		this.date = date;
	}

}
