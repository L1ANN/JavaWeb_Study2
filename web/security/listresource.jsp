<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/10
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>资源列表</title>
</head>
<body>
<br/><br/>
<table width="80%" align="center">
    <tr>
        <td></td>
        <td></td>
        <td align="right">
            <a href="${pageContext.request.contextPath}/ResourceServlet?method=addUI">添加资源</a>
        </td>
    </tr>
</table>
<br/>
<table width="80%" border="1" align="center">
    <tr>
        <td>资源uri</td>
        <td>控制资源的权限</td>
        <td>资源描述</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="r">
        <tr>
            <td>${r.uri}</td>
            <td>${r.privilege.name}</td>
            <td>${r.description}</td>
            <td>
                <a href="${pageContext.request.contextPath}/ResourceServlet?method=forUpdatePrivilegeUI&id=${r.id}">修改资源的权限</a>
                <a href="#">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
