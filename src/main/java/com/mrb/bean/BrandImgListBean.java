package com.mrb.bean;

import java.util.ArrayList;

public class BrandImgListBean {

	private String btitle;
	private long bid;
	private ArrayList<String> bimgs;

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public ArrayList<String> getBimgs() {
		return bimgs;
	}

	public void setBimgs(ArrayList<String> bimgs) {
		this.bimgs = bimgs;
	}

}
