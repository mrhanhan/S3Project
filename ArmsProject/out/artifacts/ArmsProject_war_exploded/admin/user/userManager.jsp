<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="./admin/user/script/usermanager.js"></script>
<link rel="stylesheet" href="./admin/user/styles/userinfo.css">
</head>
<body>
	<base target="CONTENTBODY">
	<div class="usermanager">
		<!--标题-->
		<div class="title">|-用户管理</div>
		<!--功能按钮-->
		<div class="btn-group"
			style="text-align: right; display: inline-block; float: right;">
			<a class="btn-submit" href="./admin/user/addUser.jsp">添加员工</a>
			<script type="text/javascript">
				function update(a) {
					console.log($(".selected")[0])
					u = $(".selected")[0]

					if (u) {
						a.href = "user/update?unum=" + u.children[1].innerText;
						return true;
					} else {
						Toast("请选择需要修改的用户")
						return false;
					}
				}
			</script>
			<a class="btn-submit" href="user/queryState?state=经理">经理</a> <a
				class="btn-submit" href="user/queryState?state=员工">员工</a> <a
				class="btn-submit" href="user/queryState?state=">全部</a> | <a
				href="user/query" class="btn-submit">刷新</a>

		</div>
		<!--模糊查询-->
		<div class="input-gropu">
			<form action="user/query">
				<input type="text" placeholder="关键字" value="${key}" name="keyword"
					style="width: 260px" />
				<button class="btn">查询</button>
				<select name="order">
					<option value="u_num">编号</option>
					<option value="create_time" ${order eq 'create_time'?'selected':''}>创建时间</option>
				</select>
				<button class="btn">排序</button>
			</form>
		</div>

		<!--用户信息-->
		<div class="userinfo-group">
			<table cellspacing="0">
				<tr class="top">
					<th>序号</th>
					<th>编号</th>
					<th>头像</th>
					<th>姓名</th>
					<th>权限</th>
					<th>创建时间</th>
					<th>创建者</th>
					<th>操作</th>
				</tr>
				<!--遍历数据-->
				<core:forEach items="${userPage$datas}" var="u" varStatus="i">

					<tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'} userrow"
						style="text-align: center">
						<td>${i.count}</td>
						<td>${u.unum}</td>
						<th><img src="./images/user/${u.uimg}" width="48" height="28"></th>
						<td>${u.uname}</td>
						<td>${u.uauto}</td>
						<td>${u.createTime}</td>
						<td>${u.uCreateUnum}</td>
						<td style="text-align: center">
							<button class="btn1">
								<a
									style="color: inherit; font-size: inherit; text-decoration: none;"
									href="user/update?unum=${u.unum}">修改</a>
							</button>
							<button class="btn2">查看用户</button>
						</td>
					</tr>

				</core:forEach>
				<!--遍历空白-->
				<core:forEach begin="${userPage$dataSize}" end="20" varStatus="i">
					<tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'}">
						<td>&nbsp;</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</core:forEach>
			</table>
			<div class="page-group">
				<a class="btn" href="user/query?pageindex=0">首页</a> <a class="btn"
					href="user/query?pageindex=${userPage$lastIndex}">上一页</a>
				<div class="pages">
					<core:forEach begin="1" end="${userPage$maxPage}" varStatus="i">
						<a class="page ${i.count eq userPage$nowPage?'select':''}"
							href="user/query?pageindex=${i.count}">${i.count}</a>
					</core:forEach>
				</div>
				<a class="btn" href="user/query?pageindex=${userPage$nextIndex}">下一页</a>
				<a class="btn" href="user/query?pageindex=${userPage$maxPage}">尾页</a>
			</div>
		</div>
	</div>
	<%@ include file="../../tools/msg.jsp"%>
</body>
</html>
