<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/11
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新资源的权限界面</title>
</head>
<body>
<table border="1" width="40%">
    <tr>
        <td>资源URI</td>
        <td>${resource.uri}</td>
    </tr>
    <tr>
        <td>资源描述</td>
        <td>${resource.description}</td>
    </tr>
    <tr>
        <td>资源原有权限</td>
        <td>${resource.privilege.name}</td>
    </tr>
    <tr>
        <td>须授予的权限</td>
        <td>
            <form action="${pageContext.request.contextPath}/ResourceServlet?method=updatePrivilege" method="post">
                <input type="hidden" name="rid" value="${resource.id}">
                <c:forEach items="${list}" var="p">
                    <input type="radio" name="pid"value="${p.id}">${p.name}<br/>
                </c:forEach>
                <input type="submit" value="更新权限">
            </form>
        </td>
    </tr>

</table>
</body>
</html>
