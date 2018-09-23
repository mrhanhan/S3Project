<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    out.println("<base href=\"" + basePath + "shopping/cus/\">");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>厦门军火网</title>
<script type="text/javascript" src="../../script/jquery.min.js"></script>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="../styles/toast.css">
<script type="text/javascript" src="../../script/util.js"></script>
</head>
<body>

	<div class="cotn_principal">
		<div class="cont_centrar">
			<div class="cont_login">
				<div class="cont_info_log_sign_up">
					<div class="col_md_login">
						<div class="cont_ba_opcitiy">
							<h2>登陆</h2>
							<p>Lorem ipsum dolor sit amet, consectetur.</p>
							<button class="btn_login" onClick="cambiar_login()">登录用户</button>
						</div>
					</div>
					<div class="col_md_sign_up">
						<div class="cont_ba_opcitiy">
							<h2>注册</h2>
							<p>Lorem ipsum dolor sit amet, consectetur.</p>
							<button class="btn_sign_up" onClick="cambiar_sign_up()">注册用户</button>
						</div>
					</div>
				</div>
				<div class="cont_back_info">
					<div class="cont_img_back_grey">
						<img src="po.jpeg" alt="" />
					</div>
				</div>
				<div class="cont_forms" style="height: 520px">
					<div class="cont_img_back_">
						<img src="po.jpeg" alt="" />
					</div>
					<div class="cont_form_login">
						<h2>登录用户</h2>
						<form action="../../useroperation/login" method="post">
							<input type="text" placeholder="Account/账号" name="acc" /> <input
								type="password" placeholder="Password/密码" name="pwd" />
							<button class="btn_login">登录用户</button>
						</form>
					</div>
					<div class="cont_form_sign_up" style="height: 520px">
						<form action="../../useroperation/add"
							enctype="multipart/form-data" method="post">
							<h2>注册用户</h2>
							<input type="text" placeholder="Account/账号[6-12位字符]"  pattern="\w{6,12}" required name="u_user" value="${param.u_user}" />
							<input type="text" placeholder="Name/名称[2-6位字符]" name="u_name" pattern=".{2,6}" required value="${param.u_name}"/>
							<input type="password" placeholder="Password/密码[6-16位字符]" pattern="[^<|>\|$|%|&|!]{6,16}" required name="u_pwd" value="${param.u_pwd}"/>
							<hr/>
							<input type="text" placeholder="Telephone/联系电话" name="u_tele" pattern="\d{11,11}"   required value="${param.u_Password}"/>
							<input type="email" placeholder="Email/联系邮箱" name="u_email"    required value="${param.u_Password}"/>
							<textarea rows="3" name="u_add" style="width: 255px;margin: 5px auto; text-align:left;resize: none;" required placeholder="收货地址"></textarea>
							<button class="btn_sign_up">注册用户</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="index.js"></script>
	<%@ include file="../../tools/msg.jsp"%>
</body>
</html>