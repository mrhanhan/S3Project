<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/8/19 0019
  Time: 17:16
  To change this template use File | Settings | File Templates.
  信息提示页面，
--%>

<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/core" %>
<msg:if test="${not empty Toast}">
    <script type="text/javascript">
        Toast('${Toast}', 3000, "top");
    </script>

</msg:if>
<msg:if test="${not empty Alert}">
    <script type="text/javascript">
        setTimeout(function () {
            alert('${Alert}')
        }, 30);
    </script>
</msg:if>
<msg:remove var="Toast"></msg:remove>
<msg:remove var="Alert"></msg:remove>