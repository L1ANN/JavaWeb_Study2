<%--
  Created by IntelliJ IDEA.
  User: L1ANN
  Date: 2017/12/11
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/UserServlet?method=login" method="post">
        用户名：<input type="text" name="username"><br/>
        密码：<input type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
