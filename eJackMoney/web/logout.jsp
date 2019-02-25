<%-- 
    Document   : logout
    Created on : May 17, 2017, 12:31:28 PM
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
        <title>logout</title>
    </head>
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <%
           session.removeAttribute("user");
       %>
        <c:redirect url="login.jsp"></c:redirect>
    </body>
</html>
