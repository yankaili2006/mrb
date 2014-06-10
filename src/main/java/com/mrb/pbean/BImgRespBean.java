package com.mrb.pbean;

import java.util.ArrayList;

import com.mrb.bean.BrandImgListBean;

public class BImgRespBean {

	private String code;
	private String msg;
	private int num;
	private ArrayList<BrandImgListBean> bimgs;

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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<BrandImgListBean> getBimgs() {
		return bimgs;
	}

	public void setBimgs(ArrayList<BrandImgListBean> bimgs) {
		this.bimgs = bimgs;
	}

}
