package com.xmbcit.xj808.arms.entity;

import java.util.Date;

/**
 * 计数器
 * 
 * @author Administrator
 *
 */
public class SysCount {

	private String id;
	private int scount; // 计数
	private String stable; // 表名
	private Date sdate; // 日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScount() {
		return scount;
	}

	public void setScount(int scount) {
		this.scount = scount;
	}

	public String getStable() {
		return stable;
	}

	public void setStable(String stable) {
		this.stable = stable;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public SysCount(String id, int scount, String stable, Date sdate) {
		super();
		this.id = id;
		this.scount = scount;
		this.stable = stable;
		this.sdate = sdate;
	}

	public SysCount() {
		super();
	}

}
