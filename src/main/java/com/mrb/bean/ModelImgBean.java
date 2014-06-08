/**
 * Feb 25, 2011 
 * UserBean.java 
 */
package com.mrb.bean;

/**
 * @author Administrator 11:52:49 AM
 */
public class ModelImgBean {

	private long iid;
	private String model;
	private long idx;
	private String iuri;

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public long getIid() {
		return iid;
	}

	public void setIid(long iid) {
		this.iid = iid;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getIuri() {
		return iuri;
	}

	public void setIuri(String iuri) {
		this.iuri = iuri;
	}

}
