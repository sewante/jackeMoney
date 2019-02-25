<%-- 
    Document   : test
    Created on : May 18, 2017, 3:22:41 PM
    Author     : MWESIGYE
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test Page</title>
    </head>
    <body>
        
        
        
        <%!
            public Statement getStatement(){
                Statement state = null;
                PrintWriter out = null;
                try{ 
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jackemoney","root","");
                    state =con.createStatement();
                }catch(ClassNotFoundException err){
                    out.print("Failed to Loaed the Database Driver"+err.getMessage());
                }catch(SQLException err){
                    out.print("Failed to Connect to database"+err.getMessage());
                }
                if(state == null){
                    throw new IllegalArgumentException("statement is null");
                }
                return state;
            }
        %>
        
<%      Statement state = getStatement();

        String user = request.getParameter("user");
        String kiosk = request.getParameter("kiosk");
        String password = request.getParameter("pass");
        String category = request.getParameter("category");
        String mtnline = request.getParameter("mtnNo");
        String waridline = request.getParameter("waridNo");
        String waridfloat = request.getParameter("waridfloat");
        String mtnfloat = request.getParameter("mtnfloat");

        String userTable ="insert into users set userName='"+user+"',password='"+password+"',category='"+category+"'";
        String kioskTable = "insert into kiosk set kioskname='"+kiosk+"',"
                + "userName='"+user+"',"
                + "waridline='"+waridline+"',"
                + "mtnline='"+mtnline+"',"
                + "waridfloat='"+waridfloat+"',"
                + "mtnfloat='"+mtnfloat+"'";
            
            
            
            if((state.executeUpdate(userTable)) >= 0){
                state.executeUpdate(kioskTable);
            }
        %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:redirect url="add_attendants.jsp"></c:redirect>
      
    </body>
</html>
