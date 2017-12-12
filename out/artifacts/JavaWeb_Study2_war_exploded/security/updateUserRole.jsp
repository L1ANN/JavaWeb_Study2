<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/11
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新用户的角色</title>
</head>
<body>
    <table border="1" width="40%">
        <tr>
            <td>用户名</td>
            <td>${user.username}</td>
        </tr>
        <tr>
            <td>用户描述</td>
            <td>${user.description}</td>
        </tr>
        <tr>
            <td>用户原有角色</td>
            <td>
                <c:forEach items="${user.roles}" var="role">
                    ${role.name}<br/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>须授予的角色</td>
            <td>
                <form action="${pageContext.request.contextPath}/UserServlet?method=updateRole" method="post">
                    <input type="hidden" name="uid" value="${user.id}">
                    <c:forEach items="${list}" var="r">
                        <input type="checkbox" name="rid" value="${r.id}">${r.name}<br/>
                    </c:forEach>
                    <input type="submit" value="更新角色">
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
