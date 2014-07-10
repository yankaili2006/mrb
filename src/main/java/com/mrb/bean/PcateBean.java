package com.mrb.bean;


public class PcateBean {

	private long cid;
	private String name;
	private String date;
	private long operid;
	private long idx;

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	/**
	 * @return the cid
	 */
	public long getCid() {
		return cid;
	}

	/**
	 * @param cid
	 *            the cid to set
	 */
	public void setCid(long cid) {
		this.cid = cid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the operid
	 */
	public long getOperid() {
		return operid;
	}

	/**
	 * @param operid
	 *            the operid to set
	 */
	public void setOperid(long operid) {
		this.operid = operid;
	}
}
