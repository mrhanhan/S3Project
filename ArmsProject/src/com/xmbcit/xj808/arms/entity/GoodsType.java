package com.xmbcit.xj808.arms.entity;

import java.util.Date;

import com.xmbcit.xj808.arms.base.BaseEntity;

/**
 * 商品类型
 * 
 * @author Administrator
 *
 */
public class GoodsType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	private String tnum; // 编号
	private String tcode; // 类型编码
	private String tname; // 类型名称
	private String tremark; // 类型备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTnum() {
		return tnum;
	}

	public void setTnum(String tnum) {
		this.tnum = tnum;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTremark() {
		return tremark;
	}

	public void setTremark(String tremark) {
		this.tremark = tremark;
	}

	public GoodsType(Date createTime, String orgId, String uCreateUnum, String id, String tnum, String tcode,
			String tname, String tremark) {
		super(createTime, orgId, uCreateUnum);
		this.id = id;
		this.tnum = tnum;
		this.tcode = tcode;
		this.tname = tname;
		this.tremark = tremark;
	}

	public GoodsType() {
		super();
	}

}
