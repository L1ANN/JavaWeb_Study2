<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/11
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新角色权限</title>
</head>
<body>
    <table border="1" width="40%">
        <tr>
            <td>角色名称</td>
            <td>${role.name}</td>
        </tr>
        <tr>
            <td>角色描述</td>
            <td>${role.description}</td>
        </tr>
        <tr>
            <td>角色原有权限</td>
            <td>
                <c:forEach items="${role.privileges}" var="p">
                    ${p.name}<br/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>须授予的权限</td>
            <td>
                <form action="${pageContext.request.contextPath}/RoleServlet?method=updatePrivilege" method="post">
                    <input type="hidden" name="rid" value="${role.id}">
                    <c:forEach items="${list}" var="p">
                        <input type="checkbox" name="pid" value="${p.id}">${p.name}<br/>
                    </c:forEach>
                    <input type="submit" value="更新权限">
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
