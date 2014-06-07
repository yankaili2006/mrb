package com.mrb.pbean;

import java.util.ArrayList;

import com.mrb.bean.VCateBean;

public class VCateRespBean {

	private String code;
	private String msg;
	private Integer num;
	private ArrayList<VCateBean> clist;

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

	public ArrayList<VCateBean> getClist() {
		return clist;
	}

	public void setClist(ArrayList<VCateBean> clist) {
		this.clist = clist;
	}

}
