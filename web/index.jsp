<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/11/27
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="itcast" uri="/itcast" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<c:if test="${user!=null}">
    欢迎您：${user.username}<a href="${pageContext.request.contextPath}/UserServlet?method=logout">注销</a>
    <itcast:permission value="添加分类">
        <a href="${pageContext.request.contextPath}/manager/AddServlet">添加分类</a>
    </itcast:permission>
    <itcast:permission value="删除分类">
        <a href="${pageContext.request.contextPath}/manager/DeleteServlet">删除分类</a>
    </itcast:permission>
    <itcast:permission value="修改分类">
        <a href="${pageContext.request.contextPath}/manager/UpdateServlet">修改分类</a>
    </itcast:permission>
    <itcast:permission value="查找分类">
        <a href="${pageContext.request.contextPath}/manager/FindServlet">查找分类</a>
    </itcast:permission>
</c:if>

<c:if test="${user==null}">
    <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
</c:if>
</body>
</html>
