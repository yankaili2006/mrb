package com.mrb.bean;

public class PageBean {
	private Integer total;
	private Integer p;
	private Integer maxpage;
	
	private Integer perpage;
	private Integer start;
	private Integer stop;
	private Integer next;
	private Integer pre;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(Integer maxpage) {
		this.maxpage = maxpage;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public Integer getPerpage() {
		return perpage;
	}

	public void setPerpage(Integer perpage) {
		this.perpage = perpage;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getStop() {
		return stop;
	}

	public void setStop(Integer stop) {
		this.stop = stop;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	public Integer getPre() {
		return pre;
	}

	public void setPre(Integer pre) {
		this.pre = pre;
	}

}
