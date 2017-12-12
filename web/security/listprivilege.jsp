<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/10
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>权限列表</title>
</head>
<body>

<br/><br/>

<table width="60%" align="center">
    <tr>
        <td></td>
        <td></td>
        <td align="right">
            <a href="${pageContext.request.contextPath}/PrivilegeServlet?method=addUI">添加权限</a>
        </td>
    </tr>
</table>

<br/>

<table width="60%" border="1" align="center">
    <tr>
        <td>权限名称</td>
        <td>权限描述</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="p">
        <tr>
            <td>${p.name}</td>
            <td>${p.description}</td>
            <td>
                <a href="#">删除</a>
                <a href="#">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
