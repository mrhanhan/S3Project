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
		<div class="title">|-查看商品</div>
		<div class="btn-group" style="text-align: right; display: inline-block;float: right; padding-right: 10px">
        <a class="btn-submit" href="goods/query">管理主页</a>
        <a href="./admin/goods/updateGoods.jsp" class="btn-submit">修改商品</a>
        <a href="goods/querysinglegoods?gnum=${goods.gnum}" class="btn-submit">刷新</a>
   		</div>
   		
		<div style="margin: 0 auto; width: 20%;">
			<div class="userinfo-group">
				<img style="opacity: 1; width: 200px; height: 150px;" title=""
					class="medium"
					src="./images/goodspic/${goods.gimg}">
			</div>

			<div class="userinfo-group">
				<div>
					<strong>商品图片</strong>
				</div>
				<div
					style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
					<div>商品名称：${goods.gname}</div>
				</div>
				<div
					style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
					<div>商品类型：${goods.tnum}</div>
				</div>
				<div
					style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
					<div>商品码：${goods.gnum}</div>
				</div>

				<div style="margin: 10px 0 10px 0;">
					商品价格: <strong style="color: #ef0101;">${goods.gprice}</strong> 
				</div>
				<div style="margin: 10px 0 10px 0;">
					折扣价:<strong style="color: #ef0101;">${goods.gdiscount}</strong>
				</div>

				<div style="margin: 10px 0 10px 0;">
					状态: <a target="_blank" 
						style="background-color: #f07373;">${goods.gstate}</a>
				</div>
				<div style="margin: 10px 0 10px 0;">
					折扣: <a target="_blank" 
						style="background-color: #f07373;">${goods.gisdiscount}</a>
				</div>
				<div style="margin: 10px 0 10px 0;">
					新品: <a target="_blank" 
						style="background-color: #f07373;">${goods.gisnew}</a>
				</div>
				<div style="margin: 10px 0 10px 0;">
					推荐: <a target="_blank" 
						style="background-color: #f07373;">${goods.gisrecom}</a>
				</div>
				<div style="margin: 10px 0 10px 0;">
					备注: <a target="_blank" 
						style="background-color: #f07373;">${goods.gremark}</a>
				</div>
				<div style="margin: 10px 0 10px 0;">
					添加时间: <a target="_blank" 
						style="background-color: #f07373;">${goods.createTime}</a>
				</div>
				
			</div>
		</div>	
	</div>
	<%@ include file="../../tools/msg.jsp"%>
</body>
</html>
