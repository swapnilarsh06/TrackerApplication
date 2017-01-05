<%-- 
    Document   : Error
    Created on : 4 Jan, 2017, 5:25:26 PM
    Author     : sarsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String ErrorMessage=(String)request.getAttribute("ErrorMessage");
        %>
        <%= ErrorMessage %>
        <a href="login.jsp">Home</a>
            
    </body>
</html>
