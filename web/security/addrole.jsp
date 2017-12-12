<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/11
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加角色界面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/RoleServlet?method=add" method="post">
    <table>
        <tr>
            <td>角色名称</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>角色描述</td>
            <td>
                <textarea rows="5" cols="50" name="description"></textarea>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="添加角色">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
