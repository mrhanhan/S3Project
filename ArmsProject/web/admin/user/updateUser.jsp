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
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	out.println("<base href=\"" + basePath + "\">");
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
		<div class="title">|-修改用户</div>
		<div class="btn-group"
			style="text-align: right; display: inline-block; float: right; padding-right: 10px">
			<a class="btn-submit" href="user/query">管理主页</a> <a
				class="btn-submit" href="./admin/user/updateUser.jsp">刷新</a>
		</div>

		<hr class="hr" style="margin-top: 45px;" />
		<div>
			<form action="user/update" enctype="multipart/form-data"
				method="post">
				<input type="hidden" name="u_id" value="${user.id}">
				<table align="center" width="60%" border="0px" height="550px">
					<tr>
						<td width="30%" colspan="2"
							style="overflow: hidden; text-align: center;"><img
							src="./images/user/${user.uimg}"
							onclick="$('#id_s_img')[0].click()" id="id_show_img"
							style="border-radius: 10px; width: 250px; height: 150px;" /> <input
							type="file" hidden
							onchange="reViewInputFile(this,'#id_show_img')"
							accept="image/png,image/jpeg,image/gif,image/jpg" name="u_img"
							id="id_s_img">
							<div>点击图图片选择用过头像</div></td>
							<td>头像照片</td>
						<tr>
                    <td width="10">用户编号:</td>
                    <td width="60%"><input style="width: 100%" required readonly value="${user.unum}" type="text"
                                           name="u_num"></td>
                    <td>用户的编号！固定</td>
                </tr>
					<tr>
						<td>姓名:</td>
						<td width="60%"><input style="width: 100%" required
							pattern=".{2,6}" value="${user.uname }" type="text" name="u_name"
							id="id_u_name"></td>
						<td>2-6为字符</td>
					</tr>
					<td>权限:</td>
					<td><select style="width: 100%" name="u_auto" id="id_u_auto">
							<option ${user.uauto eq '员工'?'checked':''} value="员工">员工</option>
							<option ${user.uauto eq '经理'?'checked':''} value="经理">经理</option>
					</select></td>

					<tr>
						<td>账号:</td>
						<td><input style="width: 100%" value="${user.uaccout }"
							type="text" pattern="\d{11}" name="u_account" id="id_u_acc"></td>
						<td>11位数字</td>
					</tr>
					<tr>
						<td>旧密码:</td>
						<td><input style="width: 100%" 
							type="password" pattern="\w{6,16}" name="u_pwd1" id="id_u_pwd"></td>
						<td>6-16为字符[数字,字母,下划线]</td>
					</tr>
					<tr>
						<td>新密码:</td>
						<td><input style="width: 100%" 
							type="password" pattern="\w{6,16}" name="u_pwd2" id="id_u_pwd"></td>
						<td>6-16为字符[数字,字母,下划线]</td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input style="width: 100%" 
							type="password" name="u_pwd3" id="id_u_repwd"></td>
						<td>请输入相同密码</td>
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
	<%@ include file="../../tools/msg.jsp"%>
</body>
</html>
