<%-- 
    Document   : logout
    Created on : 8 Apr, 2018, 7:23:57 PM
    Author     : ameya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("uname", "no-cache");
            response.setDateHeader("Expires", 0);
           session.removeAttribute("uname");
           session.setAttribute("uname", null);
            request.getSession(false).invalidate();
            
            response.sendRedirect("index.html");
            
            
            %>
    </body>
</html>
