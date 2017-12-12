<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/10
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理页面（采用分帧技术）</title>
</head>
<body>
<div>
    <ul>
        <li><a href="${pageContext.request.contextPath}/PrivilegeServlet?method=getAll" target="iframe_a">权限管理</a></li>
        <li><a href="${pageContext.request.contextPath}/ResourceServlet?method=getAll" target="iframe_a">资源管理</a></li>
        <li><a href="${pageContext.request.contextPath}/RoleServlet?method=getAll" target="iframe_a">角色管理</a></li>
        <li><a href="${pageContext.request.contextPath}/UserServlet?method=getAll" target="iframe_a">用户管理</a></li>
    </ul>
</div>
<iframe name="iframe_a" frameborder="1" width="600" height="400">

</iframe>
</body>
</html>
