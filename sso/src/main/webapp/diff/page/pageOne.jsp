<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/9
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<%
    request.setAttribute("a","aaa");
%>
<body>
    我是pageOne的jsp！！！${requestScope.a}
<c:forEach var="url" items="${requestScope.list}">
    <iframe src="${url}" style="display: none"></iframe>
</c:forEach>
</body>
</html>
