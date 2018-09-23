package com.xmbcit.xj808.arms.entity;

import com.xmbcit.xj808.arms.base.BaseEntity;

import java.util.Date;

/**
 * 客户
 * 
 * @author Administrator
 *
 */
public class Customer extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id; //
	private String cnum; // 客户编号
	private String cimg;// 头像
	private String cname; // 客户名称
	private String caddress; // 客户地址ַ
	private String ctelephone; // 客户联系方式
	private String caccount; // 客户账号
	private String cpwd; // 客户密码
	private String cemail; // 客户邮箱

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public String getCtelephone() {
		return ctelephone;
	}

	public void setCtelephone(String ctelephone) {
		this.ctelephone = ctelephone;
	}

	public String getCaccount() {
		return caccount;
	}

	public void setCaccount(String caccount) {
		this.caccount = caccount;
	}

	public String getCpwd() {
		return cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getCimg() {
		return cimg;
	}

	public void setCimg(String cimg) {
		this.cimg = cimg;
	}

	public Customer(Date createTime, String orgId, String uCreateUnum, String id, String cnum, String cimg,
                    String cname, String caddress, String ctelephone, String caccount, String cpwd, String cemail) {
		super(createTime, orgId, uCreateUnum);
		this.id = id;
		this.cnum = cnum;
		this.cimg = cimg;
		this.cname = cname;
		this.caddress = caddress;
		this.ctelephone = ctelephone;
		this.caccount = caccount;
		this.cpwd = cpwd;
		this.cemail = cemail;
	}

	public Customer() {
		super();
	}

}
