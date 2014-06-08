package com.mrb.pbean;

import java.util.ArrayList;

import com.mrb.bean.Brand2ShowBean;

public class BrandRespBean {
	private String code;
	private String msg;
	private Integer num;
	private ArrayList<Brand2ShowBean> blist;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public ArrayList<Brand2ShowBean> getBlist() {
		return blist;
	}

	public void setBlist(ArrayList<Brand2ShowBean> blist) {
		this.blist = blist;
	}

}
