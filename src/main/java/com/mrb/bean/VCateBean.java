/**
 * Feb 25, 2011 
 * UserBean.java 
 */
package com.mrb.bean;

/**
 * @author Administrator 11:52:49 AM
 */
public class VCateBean {
	
	
	private long vcid;
	private String name;
	private long date;
	private long operid;

	public long getVcid() {
		return vcid;
	}

	public void setVcid(long vcid) {
		this.vcid = vcid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getOperid() {
		return operid;
	}

	public void setOperid(long operid) {
		this.operid = operid;
	}

}
