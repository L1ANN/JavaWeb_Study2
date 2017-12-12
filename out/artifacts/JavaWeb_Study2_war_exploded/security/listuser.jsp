<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/11
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
    <br/><br/>
    <table width="80%" align="center">
        <tr>
            <td></td>
            <td></td>
            <td align="right">
                <a href="${pageContext.request.contextPath}/UserServlet?method=addUI">添加用户</a>
            </td>
        </tr>
    </table>
    <br/>
    <table width="80%" border="1" align="center">
        <tr>
            <td>用户名称</td>
            <td>用户密码</td>
            <td>用户描述</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${list}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.description}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/UserServlet?method=forUpdateUserRoleUI&id=${user.id}">为用户授予角色</a>
                    <a href="#">删除</a>
                    <a href="#">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
