package com.mrb.pbean;

import java.util.ArrayList;

public class SmvpBean {
	private ArrayList<EntryBean> entries;
	private String title;

	public ArrayList<EntryBean> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<EntryBean> entries) {
		this.entries = entries;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
