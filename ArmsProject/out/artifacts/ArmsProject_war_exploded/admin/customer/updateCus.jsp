<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    out.println("<base href=\""+basePath+"\">");
%>
<html>
<head>
<script type="text/javascript" src="./script/jquery.min.js"></script>
<link rel="stylesheet" href="./styles/util.css">
<link rel="stylesheet" href="./styles/color.css">
<link rel="stylesheet" href="./styles/page.css">
<title>首页</title>
<link rel="stylesheet" href="./admin/styles/dialog.css">
<link rel="stylesheet" href="./styles/form.css">
<script type="text/javascript" src="./script/util.js"></script>
<link rel="stylesheet" href="./admin/user/styles/userinfo.css">
</head>
<body>
	<base target="CONTENTBODY">
	<div class="usermanager">
		<!--标题-->
		<div class="title">|-修改顾客</div>
		<div class="btn-group"
			style="text-align: right; display: inline-block; float: right; padding-right: 10px">
			<a class="btn-submit" href="customer/query">管理主页</a>
			<a class="btn-submit" href="./admin/customer/updateCus.jsp">刷新</a>
		</div>

		<hr class="hr" style="margin-top: 45px;" />
		<div>
			<form action="customer/update" enctype="multipart/form-data" method="post">
			 <input type="hidden" name="c_id" value="${customer.id}">
				<table align="center" width="50%" border="0px">
					<tr>
						<td>头像:</td>
						<td width="60%"><img src="./images/customer/${customer.cimg}"
							onclick="$('#id_s_img')[0].click()" id="id_show_img" width="80"
							height="80" style="border-radius: 10px;" /> <input
							style="width: 100%" type="file" hidden required
							onchange="reViewInputFile(this,	'#id_show_img')"
							accept="image/png,image/jpeg,image/gif,image/jpg" name="c_img"
							id="id_s_img"></td>
						<td>点击图片选择头像</td>
					</tr>
					<tr>
						<td>名称:</td>
						<td width="60%"><input style="width: 100%" type="text"
							required pattern=".{2,6}" value="${customer.cname}" name="u_name"
							id="id_c_name"></td>
						<td>请输入2-6为字符</td>
					</tr>
					<tr>
						<td>账号:</td>
						<td><input style="width: 100%" type="text" name="n_caccount"
							value="${customer.caccount}" required pattern="\w{6,12}"
							id="id_c_acc"></td>
						<td>6-12位字符</td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input style="width: 100%" type="password"
							value="${customer.cpwd}" name="n_pwd" equired pattern="\w{6,16}"
							id="id_c_pwd"></td>
						<td>6-16为字符[数字,字母,下划线]</td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input style="width: 100%" type="password" name="n_pwds"
							id="id_c_repwd"></td>
						<td>请输入相同密码</td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td><input style="width: 100%" type="email"
							value="${customer.cemail}" name="n_email" id="id_c_email"></td>
						<td>请输入合法邮箱</td>
					</tr>
					<tr>
						<td>联系方式:</td>
						<td><input style="width: 100%" type="text"
							value="${customer.ctelephone}" name="n_ctelephone" id="id_c_tele"></td>
						<td>请输入联系电话</td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><textarea required style="width: 100%" type="text" rows="3"
								name="n_caddress" id="id_c_address">${customer.caddress}</textarea></td>
						<td>请输入地址</td>
					</tr>

				</table>
				<hr />
				<div class="btn-group" style="width: 30%; padding-right: 10%;">
					<button type="submit" class="btn-submit"
						style="width: 45%; float: left;">提交</button>
					<button type="reset" class="btn" style="width: 45%; float: right">重置</button>
				</div>
			</form>
		</div>
	</div>
	</div>
	<%@ include file="../../tools/msg.jsp"%>
</body>
</html>
