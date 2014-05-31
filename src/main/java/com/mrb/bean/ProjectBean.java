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
public class ProjectBean {

	private Long pid;
	private String name;
	private long pcid;
	private long cid;
	private String iuri;
	private String level;
	private String area;
	private String store;
	private String build;
	private String pack;
	private String sale;
	private int chain;
	private int fee;
	private long date;
	private long operid;

	public long getCid() {
		return cid;
	}

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

	public long getPcid() {
		return pcid;
	}

	public void setPcid(long pcid) {
		this.pcid = pcid;
	}

	public String getIuri() {
		return iuri;
	}

	public void setIuri(String iuri) {
		this.iuri = iuri;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the store
	 */
	public String getStore() {
		return store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(String store) {
		this.store = store;
	}

	/**
	 * @return the build
	 */
	public String getBuild() {
		return build;
	}

	/**
	 * @param build
	 *            the build to set
	 */
	public void setBuild(String build) {
		this.build = build;
	}

	/**
	 * @return the pack
	 */
	public String getPack() {
		return pack;
	}

	/**
	 * @param pack
	 *            the pack to set
	 */
	public void setPack(String pack) {
		this.pack = pack;
	}

	/**
	 * @return the sale
	 */
	public String getSale() {
		return sale;
	}

	/**
	 * @param sale
	 *            the sale to set
	 */
	public void setSale(String sale) {
		this.sale = sale;
	}

	/**
	 * @return the chain
	 */
	public int getChain() {
		return chain;
	}

	/**
	 * @param chain
	 *            the chain to set
	 */
	public void setChain(int chain) {
		this.chain = chain;
	}

	/**
	 * @return the fee
	 */
	public int getFee() {
		return fee;
	}

	/**
	 * @param fee
	 *            the fee to set
	 */
	public void setFee(int fee) {
		this.fee = fee;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return DateUtil.format(date);
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(long date) {
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

	/**
	 * @return the pid
	 */
	public Long getPid() {
		return pid;
	}

}
