package com.xmbcit.xj808.arms.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmbcit.xj808.arms.base.BaseEntity;

/**
 * 订单
 * 
 * @author Administrator
 *
 */
public class OrderInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	private String onum; // 订单
	private String cnum; // 商品
	private BigDecimal omoney; // 购买数量
	private int ocount; // 总价格
	private Date odate; // 时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnum() {
		return onum;
	}

	public void setOnum(String onum) {
		this.onum = onum;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	public BigDecimal getOmoney() {
		return omoney;
	}

	public void setOmoney(BigDecimal omoney) {
		this.omoney = omoney;
	}

	public int getOcount() {
		return ocount;
	}

	public void setOcount(int ocount) {
		this.ocount = ocount;
	}

	public Date getOdate() {
		return odate;
	}

	public void setOdate(Date odate) {
		this.odate = odate;
	}

	public OrderInfo(Date createTime, String orgId, String uCreateUnum, String id, String onum, String cnum,
					 BigDecimal omoney, int ocount, Date o_date) {
		super(createTime, orgId, uCreateUnum);
		this.id = id;
		this.onum = onum;
		this.cnum = cnum;
		this.omoney = omoney;
		this.ocount = ocount;
		this.odate = o_date;
	}

	public OrderInfo() {
		super();
	}

}
