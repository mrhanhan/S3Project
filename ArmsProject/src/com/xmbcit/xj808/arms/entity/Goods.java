package com.xmbcit.xj808.arms.entity;

import java.util.Date;
import java.util.Objects;

import com.xmbcit.xj808.arms.base.BaseEntity;

/**
 * 商品
 * 
 * @author Administrator
 *
 */
public class Goods extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	private String gnum; // 商品编号
	private String tnum; // 类型编号
	private String gname; // 商品名称
	private double gprice; // 商品价格
	private float gdiscount; // 商品折扣
	private String gstate; // 商品状态
	private String gisdiscount; // 是否折扣（是/否）
	private String gimg; // 商品图片
	private String gisnew; // 是否新品（是/否）
	private String gisrecom; // 是否推荐（是/否）
	private String gremark; // 商品备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGnum() {
		return gnum;
	}

	public void setGnum(String gnum) {
		this.gnum = gnum;
	}

	public String getTnum() {
		return tnum;
	}

	public void setTnum(String tnum) {
		this.tnum = tnum;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public double getGprice() {
		return gprice;
	}

	public void setGprice(double gprice) {
		this.gprice = gprice;
	}

	public float getGdiscount() {
		return gdiscount;
	}

	public void setGdiscount(float gdiscount) {
		this.gdiscount = gdiscount;
	}

	public String getGstate() {
		return gstate;
	}

	public void setGstate(String gstate) {
		this.gstate = gstate;
	}

	public String getGisdiscount() {
		return gisdiscount;
	}

	public void setGisdiscount(String gisdiscount) {
		this.gisdiscount = gisdiscount;
	}

	public String getGimg() {
		return gimg;
	}

	public void setGimg(String gimg) {
		this.gimg = gimg;
	}

	public String getGisnew() {
		return gisnew;
	}

	public void setGisnew(String gisnew) {
		this.gisnew = gisnew;
	}

	public String getGisrecom() {
		return gisrecom;
	}

	public void setGisrecom(String gisrecom) {
		this.gisrecom = gisrecom;
	}

	public String getGremark() {
		return gremark;
	}

	public void setGremark(String gremark) {
		this.gremark = gremark;
	}

	public Goods(Date createTime, String orgId, String uCreateUnum, String id, String gnum, String tnum, String gname,
			double gprice, float gdiscount, String gstate, String gisdiscount, String gimg, String gisnew,
			String gisrecom, String gremark) {
		super(createTime, orgId, uCreateUnum);
		this.id = id;
		this.gnum = gnum;
		this.tnum = tnum;
		this.gname = gname;
		this.gprice = gprice;
		this.gdiscount = gdiscount;
		this.gstate = gstate;
		this.gisdiscount = gisdiscount;
		this.gimg = gimg;
		this.gisnew = gisnew;
		this.gisrecom = gisrecom;
		this.gremark = gremark;
	}

	public Goods() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Goods goods = (Goods) o;
		return Objects.equals(id, goods.id) &&
				Objects.equals(gnum, goods.gnum);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, gnum);
	}
}
