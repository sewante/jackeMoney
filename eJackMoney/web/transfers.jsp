<%-- 
    Document   : transfers
    Created on : May 29, 2017, 10:58:15 AM
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
<% pageContext.setAttribute("title","transfers"); %>
<% pageContext.setAttribute("page", "");
   pageContext.setAttribute("menu", "transfers");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@include file="/include/loginHeader.jsp" %>

       
<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2>Transfers.</h2>
            <h3><span>Enter the kiosk attendant user name</span></h3>
            <div class="clr"></div>
            <form action="transfers.jsp" method="POST" id="kiosk">
                <table >
                    <tr>
                        <td><label for="attendant">Attendant User Name</label></td>
                        <td><input type="text" name="attendant" required="required"/></td>
                        <td><input type="submit" name="submit" value="Go"/></td>
                        <td></td>
                    </tr>
                </table>
            </form>
            
            
            <!-- getting the requests for the attendant whose user name has been entered-->
            <c:if test="${param.submit eq 'Go'}" >
                <sql:setDataSource var="db" scope="session" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/jackemoney" user="root" password=""></sql:setDataSource>
                
                <sql:query var="attendant" dataSource="${db}">
                    select userId from kiosk where userName='${param.attendant}';
                </sql:query>
                <c:forEach var="user" items="${attendant.rows}">
                    <c:set var="receiverUserId" value="${user.userId}"></c:set>
                </c:forEach>
                <c:choose>
                    <c:when test="${not empty receiverUserId}">
                        <%-- given the correct user name is entered now use the user id to select the requests--%>
                        <sql:query var="request" dataSource="${db}">
                            select requestId,senderUserId,customerNumber,amount,status,dateAndTime from request where receiverUserId='${receiverUserId}';
                        </sql:query>
                        <div class="gadget">
                            <%-- initialise total requests to zero--%>
                            <c:set var="totalRequests" value="${0}"></c:set>
                            <c:if test="${not empty request.rows}">
                                <h4><font color="blue"> Here are the Transfers to <b><c:out value="${param.attendant}"></c:out></b>.</font></h4>
                                <table border='1' align='center'>
                                <tr>
                                    <th>Request Id</th>
                                    <th>From</th>
                                    <th>Customer Number</th>
                                    <th>Amount(UGx)</th>
                                    <th>Status</th>
                                    <th>Date</th>
                                </tr>
                                <c:forEach var="transfer" items="${request.rows}">
                                <%-- first get the userName for the sender of the request--%>
                                <sql:query var="user_from" dataSource="${db}">
                                    select userName from kiosk where userId='${transfer.senderUserId}';
                                </sql:query>
                                    <c:forEach var="from" items="${user_from.rows}">
                                        <c:set var="userFrom" value="${from.userName}"></c:set>
                                    </c:forEach>
                                <tr>
                                    <td><center><c:out value="${transfer.requestId}"></center></c:out></td>
                                    <td><c:out value="${userFrom}"></c:out></td>
                                    <td><c:out value="${transfer.customerNumber}"></c:out></td>
                                    <td><c:out value="${transfer.amount}"></c:out></td>
                                    <td><c:out value="${transfer.status}"></c:out></td>
                                    <td><c:out value="${transfer.dateAndTime}"></c:out></td>
                                </tr>
                                <c:set var="totalRequests" value="${totalRequests + 1}"></c:set>
                                </c:forEach>
                                </table>
                            </c:if>
                            
                                 <p><b><c:out value="${param.attendant}"/> has  <c:out value="${totalRequests}"/></b> Requests</p>
                            
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p><font color='red'><c:out value="${param.attendant}"/>, does not exist.</font></p>
                    </c:otherwise>
                </c:choose>
                <div class="gadget">
                </div>
            </c:if>
                        
                        
                        
                
          </div>
        </div>
        <%@include file="/include/menu.jsp" %>
        
      </div>
        <div class="clr"></div>
      </div>
    </div>
        
  </div>
</div>

<%@include file="/include/footer.jsp" %>