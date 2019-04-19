<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Process</title>
</head>
<body>
<%
if(session.getAttribute("user")==null){
	response.sendRedirect("index.jsp");
}

%>
<div>
<b>Welcome <%= session.getAttribute("user") %> </b>
</div>
<a href="logout">LogOut</a>
</body>
</html>