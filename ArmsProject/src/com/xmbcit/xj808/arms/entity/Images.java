package com.xmbcit.xj808.arms.entity;

import java.util.Date;

import com.xmbcit.xj808.arms.base.BaseEntity;

public class Images extends BaseEntity{

	
	private static final long serialVersionUID = 1L;

	private String id;
	private String ipic;
	private String imessage;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIpic() {
		return ipic;
	}
	public void setIpic(String ipic) {
		this.ipic = ipic;
	}
	public String getImessage() {
		return imessage;
	}
	public void setImessage(String imessage) {
		this.imessage = imessage;
	}


	public Images() {
	}

	public Images(Date createTime, String orgId, String uCreateUnum, String id, String ipic, String imessage) {
		super(createTime, orgId, uCreateUnum);
		this.id = id;
		this.ipic = ipic;
		this.imessage = imessage;
	}
	public Images(Date createTime, String orgId, String uCreateUnum) {
		super(createTime, orgId, uCreateUnum);
	}
	
}
