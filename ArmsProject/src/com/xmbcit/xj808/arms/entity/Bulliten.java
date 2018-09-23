package com.xmbcit.xj808.arms.entity;

import java.util.Date;

import com.xmbcit.xj808.arms.base.BaseEntity;

/**
 * ������公告
 * 
 * @author Administrator
 *
 */
public class Bulliten extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;
	private String bnum; // 公告编号
	private String btitle; // 公告标题
	private String bcontent; // 公告内容
	private String unum; // 用户编号
	private Date bdate; // 发布时间
	private Date bUpdateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBnum() {
		return bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getUnum() {
		return unum;
	}

	public void setUnum(String unum) {
		this.unum = unum;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Date getbUpdateDate() {
		return bUpdateDate;
	}

	public void setbUpdateDate(Date bUpdateDate) {
		this.bUpdateDate = bUpdateDate;
	}

	public Bulliten(Date createTime, String orgId, String uCreateUnum, String id, String bnum, String btitle,
			String bcontent, String unum, Date bdate, Date bUpdateDate) {
		super(createTime, orgId, uCreateUnum);
		this.id = id;
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.unum = unum;
		this.bdate = bdate;
		this.bUpdateDate = bUpdateDate;
	}

	public Bulliten(Date create_time, String org_id, String u_create_u_num) {
		super(create_time, org_id, u_create_u_num);
	}


	public Bulliten() {
		super();
	}
}
