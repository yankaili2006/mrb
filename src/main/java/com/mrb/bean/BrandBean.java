/**
 * Feb 25, 2011 
 * UserBean.java 
 */
package com.mrb.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mrb.util.DateUtil;

/**
 * @author Administrator 11:52:49 AM
 */
public class BrandBean {

	private long pid;
	private long bid;
	private String btitle;
	private String binfo;
	private String iuri;
	private String name;
	private String price;
	private String function;
	private String summary;
	private String date;
	private long operid;

	/**
	 * @return the pid
	 */
	public long getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(long pid) {
		this.pid = pid;
	}

	/**
	 * @return the bid
	 */
	public long getBid() {
		return bid;
	}

	/**
	 * @param bid
	 *            the bid to set
	 */
	public void setBid(long bid) {
		this.bid = bid;
	}

	/**
	 * @return the btitle
	 */
	public String getBtitle() {
		return btitle;
	}

	/**
	 * @param btitle
	 *            the btitle to set
	 */
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	/**
	 * @return the binfo
	 */
	public String getBinfo() {
		return binfo;
	}

	/**
	 * @param binfo
	 *            the binfo to set
	 */
	public void setBinfo(String binfo) {
		this.binfo = binfo;
	}

	public String getIuri() {
		return iuri;
	}

	public void setIuri(String iuri) {
		this.iuri = iuri;
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

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
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
