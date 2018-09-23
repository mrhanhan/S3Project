package com.xmbcit.xj808.arms.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmbcit.xj808.arms.base.BaseEntity;

/**
 * 订单详细
 * 
 * @author Administrator
 *
 */
public class OrderDetails extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String onum; // 编号
	private String gnum; // 顾客
	private int dcount; // 总价格
	private BigDecimal dmoney;// 总数量

	public String getOnum() {
		return onum;
	}

	public void setOnum(String onum) {
		this.onum = onum;
	}

	public String getGnum() {
		return gnum;
	}

	public void setGnum(String gnum) {
		this.gnum = gnum;
	}

	public int getDcount() {
		return dcount;
	}

	public void setDcount(int dcount) {
		this.dcount = dcount;
	}

	public BigDecimal getDmoney() {
		return dmoney;
	}

	public void setDmoney(BigDecimal dmoney) {
		this.dmoney = dmoney;
	}

	public OrderDetails(Date createTime, String orgId, String uCreateUnum, String onum, String gnum, int dcount,
						BigDecimal dmoney) {
		super(createTime, orgId, uCreateUnum);
		this.onum = onum;
		this.gnum = gnum;
		this.dcount = dcount;
		this.dmoney = dmoney;
	}

	public OrderDetails() {
		super();
	}

}
