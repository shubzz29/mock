<%-- 
    Document   : process
    Created on : 8 Apr, 2018, 6:40:29 PM
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
            
           
           String uname=request.getParameter("uname");
           String password=request.getParameter("password");
           
           /*Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","root");
           PreparedStatement ps=con.prepareStatement("select * from credentials");
           ResultSet rs=ps.executeQuery();
           boolean flag=false;
           while(rs.next())
           {
               String uid=rs.getString("uname");
               String paswd=rs.getString("password");
               if(uid.equals(uname) && paswd.equals(password))
               {
                   flag=true;
                   
                   break;
               }
           }
*/
           boolean flag=true;
           if(flag==true)
           {
               
               session.setAttribute("uname", uname);
                   response.sendRedirect("home.jsp");
                   
                   
                   
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
               
           }
           else
           {
               out.print("Log in unsuccessful");
               response.sendRedirect("index.html");
           }
           %>
           <br><br><div>
               <a href="logout.jsp">Log Out!</a>
           </div>
           
    </body>
</html>
