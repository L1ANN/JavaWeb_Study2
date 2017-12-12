<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/11
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>角色列表</title>
</head>
<body>
<br/><br/>
<table width="80%" align="center">
    <tr>
        <td></td>
        <td></td>
        <td align="right">
            <a href="${pageContext.request.contextPath}/RoleServlet?method=addUI">添加角色</a>
        </td>
    </tr>
</table>
<table width="80%" border="1" align="center">
    <tr>
        <td>角色名称</td>
        <td>角色描述</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="role">
        <tr>
            <td>${role.name}</td>
            <td>${role.description}</td>
            <td>
                <a href="${pageContext.request.contextPath}/RoleServlet?method=forUpdateRolePrivilegeUI&id=${role.id}">为角色授予权限</a>
                <a href="#">删除</a>
                <a href="#">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
