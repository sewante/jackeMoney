<%-- 
    Document   : commissions
    Created on : May 18, 2017, 9:16:03 PM
    Author     : MWESIGYE
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("title","view-commission"); %>
<% pageContext.setAttribute("page", "");
   pageContext.setAttribute("menu", "commission");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource var="db" scope="session" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/jackemoney" user="root" password=""></sql:setDataSource>
<%@include file="/include/loginHeader.jsp" %>

       
<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2><span>Commissions</span></h2>
            <div class="clr"></div>
            <h3>Enter the kiosk name to view the available Warid and MTN Commission it has generated for the period.</h3>
            <form action="commissions.jsp" method="POST" id="kiosk">
                <table >
                    <tr>
                        <td><label for="kiosk">Kiosk Name</label></td>
                        <td><input type="text" name="kiosk" required="required"/></td>
                        <td><input type="submit" name="submit" value="Go"/></td>
                    </tr>
                </table>
            </form>
            
            
            <!-- getting the total commision for Warid-->
            <c:if test="${param.submit eq 'Go'}" >
                <sql:query var="kioskResult" dataSource="${db}">
                    select userName,waridline,mtnline,waridfloat,mtnfloat,waridcommission,
                    mtncommission from kiosk where kioskname='${param.kiosk}';
                </sql:query>
                <c:choose>
                    <c:when test="${not empty kioskResult.rows}">
                        <p>Commissions for  <font id="welp"><c:out value="${param.kiosk}"></c:out></font></p>
                        <p><a href='addfloat.jsp?kiosk=<c:out value="${param.kiosk}"></c:out>'>Pay in Commission</a> to <c:out value="${param.kiosk}"></c:out></p>
                        <table border="1">
                            <thead>
                                <th>Warid Number</th>
                                <th>MTN Number</th>
                                <th>Warid Float(UGx)</th>
                                <th>Unpaid Warid Commission(UGx)</th>
                                <th>MTN Float(UGx)</th>
                                <th>Unpaid MTN Commission(UGx)</th>
                            </thead>
                                <c:forEach var="kioskRow" items="${kioskResult.rows}">
                                <tr>
                                    <td><c:out value="${kioskRow.waridline}"></c:out></td>
                                    <td><c:out value="${kioskRow.mtnline}"></c:out></td>
                                    <td><c:out value="${kioskRow.waridfloat}"></c:out></td>
                                    <td><c:out value="${kioskRow.waridcommission}"></c:out></td>
                                    <td><c:out value="${kioskRow.mtnfloat}"></c:out></td>
                                    <td><c:out value="${kioskRow.mtncommission}"></c:out></td>
                                </tr>
                                </c:forEach>
                        </table> 
                    </c:when>
                    <c:otherwise>
                        <p><font color='red'><b><c:out value="${param.kiosk}"/></b> does not exist.</font></p>
                    </c:otherwise>
                </c:choose>
                
                
            </c:if>
                
          </div>
        </div>
        <%@include file="/include/menu.jsp" %>
        <div class="sidebar">
            
          <div class="gadget">
            <h2 class="star"><span>Commissions for</span></h2>
            <div class="clr"></div>
            <ul class="ex_menu">
                <li class=""><a href="commissions.jsp">A Kiosk</a></li>
              <li><a href="allCommissions.jsp">All Kiosks</a></li>
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