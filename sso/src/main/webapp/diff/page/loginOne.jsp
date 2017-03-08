<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML>
<html>
<head>用户登录
</head>
<body>
<s:form action="/diff/pageOne/login.action" >
    <s:textfield name="userName" label="用户名" value="abc"></s:textfield>
    <s:password name="password" label="密码" value="123"></s:password>
    <s:hidden name="redirectUrl"></s:hidden>
    <s:submit></s:submit>
</s:form>

</body>
</html>