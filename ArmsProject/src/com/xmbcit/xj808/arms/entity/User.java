package com.xmbcit.xj808.arms.entity;

import java.util.Date;

import com.xmbcit.xj808.arms.base.BaseEntity;

/**
 * 管理员
 * 
 * @author Administrator
 *
 */
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	private String unum; // 管理员编号
	private String uname; // 管理员名称
	private String uimg;// 管理员账号
	private String uaccout;// 管理员账号
	private String upwd; // 管理员密码
	private String uauto; // 管理员等级（1，2，3，4）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnum() {
		return unum;
	}

	public void setUnum(String unum) {
		this.unum = unum;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUaccout() {
		return uaccout;
	}

	public void setUaccout(String uaccout) {
		this.uaccout = uaccout;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUauto() {
		return uauto;
	}

	public void setUauto(String uauto) {
		this.uauto = uauto;
	}

	public User(Date createTime, String orgId, String uCreateUnum, String id, String unum, String uname, String uaccout,
			String upwd, String uauto) {
		super(createTime, orgId, uCreateUnum);
		this.id = id;
		this.unum = unum;
		this.uname = uname;
		this.uaccout = uaccout;
		this.upwd = upwd;
		this.uauto = uauto;
	}

	public User() {
		super();
	}

    public void setUimg(String u_img) {
		uimg = u_img;
    }

	public String getUimg() {
		return uimg;
	}
}
