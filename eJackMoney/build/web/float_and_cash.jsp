<%-- 
    Document   : float_and_cash
    Created on : May 29, 2017, 7:33:28 PM
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
<% pageContext.setAttribute("title","float-and-cash"); %>
<% pageContext.setAttribute("page", "");
   pageContext.setAttribute("menu", "float_and_cash");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@include file="/include/loginHeader.jsp" %>

       
<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2>Float and Cash Management.</h2>
            <h3><span>Click on the desired Kiosk to add more float</span></h3>
            <div class="clr"></div>
            <!-- getting the  floats and cash for all the kiosks-->
            <sql:setDataSource var="db" scope="session" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/jackemoney" user="root" password=""></sql:setDataSource>
            <div class="gadget">
            <sql:query var="floats" dataSource="${db}">
                select kioskname,userName,waridline,mtnline,waridfloat,waridcash,mtnfloat,mtncash,waridcommission,mtncommission from kiosk;
            </sql:query>
                <table border="3" width="100%">
                    <tr>
                        <th>Kiosk Name</th>
                        <th>Attendant</th>
                        <th>Warid Number</th>
                        <th>MTN Number</th>
                        <th>Warid Float</th>
                        <th>Warid Cash</th>
                        <th>MTN Float</th>
                        <th>MTN Cash</th>
                        <th>Warid Commission</th>
                        <th>MTN Commission</th>
                    </tr>
                <c:forEach var="floats_and_cash" items="${floats.rows}">
                    <tr>
                        <td><a href='addfloat.jsp?kiosk=<c:out value="${floats_and_cash.kioskname}"></c:out>'><c:out value="${floats_and_cash.kioskname}"></c:out></a></td>
                        <td><c:out value="${floats_and_cash.userName}"></c:out></td>
                        <td><c:out value="${floats_and_cash.waridline}"></c:out></td>
                        <td><c:out value="${floats_and_cash.mtnline}"></c:out></td>
                        <td><c:out value="${floats_and_cash.waridfloat}"></c:out></td>
                        <td><c:out value="${floats_and_cash.waridcash}"></c:out></td>
                        <td><c:out value="${floats_and_cash.mtnfloat}"></c:out></td>
                        <td><c:out value="${floats_and_cash.mtncash}"></c:out></td>
                        <td><c:out value="${floats_and_cash.waridcommission}"></c:out></td>
                        <td><c:out value="${floats_and_cash.mtncommission}"></c:out></td>
                    </tr>
                </c:forEach>
                </table>
            </div>
            <c:if test="${param.submit eq 'Go'}" >
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