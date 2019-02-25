<%-- 
    Document   : allCommissions
    Created on : May 30, 2017, 3:32:11 PM
    Author     : MWESIGYE
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("title","all-commissions"); %>
<% pageContext.setAttribute("page", "");
   pageContext.setAttribute("menu", "");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource var="db" scope="session" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/jackemoney" user="root" password=""></sql:setDataSource>
<%@include file="/include/loginHeader.jsp" %>

       
<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2><span>Commissions for all kiosks</span></h2>
            <div class="clr"></div>
            <div class="">
                <!-- retrieve all the commissions from the kiosk table and display it-->
                <sql:query var="commissions" dataSource="${db}">
                    select kioskname,userName,waridline,mtnline,waridfloat,mtnfloat,waridcommission,mtncommission from kiosk;
                </sql:query>
                    <table border="1">
                        <thead>
                            <th>Kiosk Name</th>
                            <th>Attendant</th>
                            <th>Warid Number</th>
                            <th>MTN Number</th>
                            <th>Warid Float(UGx)</th>
                            <th>Unpaid Warid Commission(UGx)</th>
                            <th>MTN Float(UGx)</th>
                            <th>Unpaid MTN Commission(UGx)</th>
                        </thead>
                            <c:forEach var="commissionRows" items="${commissions.rows}">
                            <tr>
                                <td><c:out value="${commissionRows.kioskname}"></c:out></td>
                                <td><c:out value="${commissionRows.userName}"></c:out></td>
                                <td><c:out value="${commissionRows.waridline}"></c:out></td>
                                <td><c:out value="${commissionRows.mtnline}"></c:out></td>
                                <td><c:out value="${commissionRows.waridfloat}"></c:out></td>
                                <td><c:out value="${commissionRows.waridcommission}"></c:out></td>
                                <td><c:out value="${commissionRows.mtnfloat}"></c:out></td>
                                <td><c:out value="${CommissionRows.mtncommission}"></c:out></td>
                            </tr>
                            </c:forEach>
                    </table>
            </div>
             
          </div>
        </div>
        <%@include file="/include/menu.jsp" %>
        <div class="sidebar">
            
          <div class="gadget">
            <h2 class="star"><span>Commissions for</span></h2>
            <div class="clr"></div>
            <ul class="ex_menu">
                <li class=""><a href="commissions.jsp">A Kiosk</a></li>
              <li class="active"><a href="allCommissions.jsp">All Kiosks</a></li>
              <li> </li>
            </ul>
          </div>
        </div>
      </div>
        <div class="clr"></div>
      </div>
    </div>
        
  </div>
</div>

<%@include file="/include/footer.jsp" %>