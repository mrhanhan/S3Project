package com.xmbcit.xj808.arms.base;

import java.io.Serializable;
import java.util.Date;

/**
 * �־û�����
 * 
 * @author Administrator
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date createTime; // 更新时间
	private String orgId; //
	private String uCreateUnum; // 结束时间

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getuCreateUnum() {
		return uCreateUnum;
	}

	public void setuCreateUnum(String uCreateUnum) {
		this.uCreateUnum = uCreateUnum;
	}

	public BaseEntity(Date createTime, String orgId, String uCreateUnum) {
		super();
		this.createTime = createTime;
		this.orgId = orgId;
		this.uCreateUnum = uCreateUnum;
	}

	public BaseEntity() {
		super();
	}

}
