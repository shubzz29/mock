<%-- 
    Document   : home
    Created on : 8 Apr, 2018, 8:03:40 PM
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
            response.setHeader("Pragma", "no-cache");
             response.setDateHeader("Expires", 0);
            
        if(session.getAttribute("uname")==null)
        {
            response.sendRedirect("index.html");
        }
        else
        {
            out.println("Welcome Mr"+session.getAttribute("uname"));
        }
        
        %>
        <a href="logout.jsp">Log out!</a>
    </body>
</html>
