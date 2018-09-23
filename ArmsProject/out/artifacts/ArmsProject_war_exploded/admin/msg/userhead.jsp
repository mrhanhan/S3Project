<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/8/20 0020
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 用户头部信息 --%>
<base target="CONTENTBODY">
<div>
    <div class="top">
        <form method="post">
            &nbsp;&nbsp;<span>军火交易管理系统</span>
            <input type="text" placeholder="搜索" name="top_n_search">&nbsp;&nbsp;&nbsp;
        </form>
        <div class="user-group">
            <div class="user">
                <img src="./images/user/${LOGIN_USER.uimg}"/>
                <span>欢迎用户 ${LOGIN_USER.uname}</span>
            </div>
            <!--按钮功能区-->
            <div class="user-btn">
                <div><a href="./admin/user/messageUser.jsp">信息</a></div>
                <div><a href="./admin/user/updatePass.jsp">修改密码</a></div>
                <div style="background-color: #208ed3;" class="dialog-btn" bind="#userout">退出</div>
            </div>
        </div>
    </div>
</div>
<div class="dialog userloginout" width="30%" height="200" top="30%" id="userout" >
    <div style="background-color: #4b4f51;color: #FFF;border-radius: 5px;border: 1px solid white; padding: 10px 20px;">
        <form action="user/logout" target="_top">
            <h4>是否退出系统！</h4>
            <input name="a" type="hidden" value="none">
            <hr/>
            <div style="text-align: right;">
                 <button type="reset" class="dialog-btn btn-ok"  bind="#userout">取消</button>
                <button type="submit" class="dialog-btn btn-cancl " bind="#userout">退出</button>
            </div>
        </form>
    </div>
</div>