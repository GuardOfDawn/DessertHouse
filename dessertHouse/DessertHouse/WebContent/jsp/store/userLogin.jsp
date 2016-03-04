<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Store User Login Page</title>
</head>
<body>
	<h3 align="center">欢迎来你的甜品屋</h3>
	<h5 align="center">打造世界最好的甜品屋</h5>
	<s:form method='post' action='/Student/login'>
		<table align="center" border="0">
			<tr>
				<td><s:select name="userType" label="用户类型" list="{'系统管理员','经理','总店服务员','分店服务员'}"
						headerKey="-1" headerValue="请选择您的学历"></s:select></td>
			</tr>
			<tr>
				<td><s:textfield name="userId" label="用户名:"/></td>
			</tr>
			<tr>
				<td><s:password name="password" label="密 码 :"/></td>
			</tr>
			<tr>
          		<td colspan="2" align="center">
         		<s:submit value="登录"/>
         		<s:reset value="重置"/>
          		</td>
        	</tr>
		</table>
	</s:form>
</body>
</html>