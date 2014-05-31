package com.mrb.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mrb.util.DateUtil;

public class CodeBean {

	private long uid;
	private String phone;
	private String code;
	private String status;
	private long date;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return DateUtil.format(date);
	}

	public void setDate(long date) {
		this.date = date;
	}
}
