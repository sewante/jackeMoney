<%-- 
    Document   : verify
    Created on : May 16, 2017, 4:28:12 PM
    Author     : MWESIGYE


                                    GROUP MEMBERS
                                    =============
		______________________________________________________
		* MWESIGYE  ROBERT          |  15 / U / 771           |
		* NANSUBUGA JOYCE   EUZEBIA |  15 / U / 10807 / EVE   |
		* NAKAALI   PHIONA	    | 	15 / U / 9286 / PS    |
		* BWAYO     JUDE      	    |  15 / U / 4718 / EVE    |
		____________________________|_________________________|
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>verify</title>
    </head>
    <body>
        <%@page import="java.io.*, java.sql.*,java.util.*" %>
        <%@page import="javax.servlet.http.*,javax.servlet.*"%>
        <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
        
        <%--
            Verify login
        --%>
        <c:if test="${empty param.username or empty param.password}">
            <c:redirect url="login.jsp" >
                <c:param name="errMsg" value="Please enter user name and Password"/>
            </c:redirect>
        </c:if>
        <c:if test="${not empty param.username and not empty param.password}">
           <sql:setDataSource var="db" scope="session" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/jackemoney" user="root" password=""></sql:setDataSource> 
           
                <sql:query dataSource="${db}" var="result">
                    select userName,password from users where category='Administrator';
                </sql:query>
           <c:forEach var="col" items="${result.rows}" >
               <c:choose>
                   <c:when test="${(col.userName eq param.username) and (col.password eq param.password)}">
                       <c:set scope="session"  var="user" value="${col.userName}"/>
                       <c:redirect url="welcome.jsp"></c:redirect>
                   </c:when>
                   <c:otherwise>
                       <c:redirect url="login.jsp">
                           <c:param name="errMsg" value="Wrong User name or Password "></c:param>
                       </c:redirect>
                   </c:otherwise>
               </c:choose>
           </c:forEach>
        </c:if>
        
        
        
        
       
    </body>
</html>
