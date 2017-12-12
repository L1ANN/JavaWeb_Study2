<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/11
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户界面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/UserServlet?method=add" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td>
                <input type="text" name="username">
            </td>
        </tr>
        <tr>
            <td>用户密码</td>
            <td>
                <input type="text" name="password">
            </td>
        </tr>
        <tr>
            <td>用户描述</td>
            <td>
                <textarea rows="5" cols="50" name="description"></textarea>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="添加用户">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
