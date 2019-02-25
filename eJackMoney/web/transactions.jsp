<%-- 
    Document   : transactions
    Created on : May 30, 2017, 5:08:48 PM
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
                <!-- retrieve all the transactions from the kiosks and display them-->
                <sql:query var="transactions" dataSource="${db}">
                    select * from transactions;
                </sql:query>
                    <table border="1">
                        <thead>
                            <th>Transsction ID</th>
                            <th>Kiosk Phone</th>
                            <th>Attendant</th>
                            <th>Customer Number</th>
                            <th>Date</th>
                            <th>Type</th>
                            <th>Service Provider</th>
                            <th>Commission (UGx)</th>
                            <th>Amount(UGx)</th>
                        </thead>
                            <c:forEach var="transactionRows" items="${transactions.rows}">
                            <tr>
                                <td><center><c:out value="${transactionRows.transactionId}"></c:out></center></td>
                                <td><c:out value="${transactionRows.kioskPhone}"></c:out></td>
                                <td><c:out value="${transactionRows.userName}"></c:out></td>
                                <td><c:out value="${transactionRows.customerNumber}"></c:out></td>
                                <td><c:out value="${transactionRows.dateAndTime}"></c:out></td>
                                <td><c:out value="${transactionRows.type}"></c:out></td>
                                <td><c:out value="${transactionRows.serviceProvider}"></c:out></td>
                                <td><c:out value="${transactionRows.commission}"></c:out></td>
                                <td><c:out value="${transactionRows.amount}"></c:out></td>
                            </tr>
                            </c:forEach>
                    </table>
          </div>
        </div>
        <%@include file="/include/menu.jsp" %>
        <div class="sidebar">
            
          <div class="gadget">
            <h2 class="star"><span>Commissions for</span></h2>
            <div class="clr"></div>
            <ul class="ex_menu">
                <li class=""><a href="commissions.jsp">A Kiosk</a></li>
              <li class=""><a href="allCommissions.jsp">All Kiosks</a></li>
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