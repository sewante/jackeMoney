<%-- 
    Document   : addfloat
    Created on : May 29, 2017, 8:42:19 PM
    Author     : MWESIGYE
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("title","add-float"); %>
<% pageContext.setAttribute("page", "");
   pageContext.setAttribute("menu", "");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@include file="/include/loginHeader.jsp" %>
<sql:setDataSource var="db" scope="session" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/jackemoney" user="root" password=""></sql:setDataSource>
       
<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <c:set var="kioskName" value="${param.kiosk}"></c:set>
            <c:if test="${not empty kioskName }">
            <h2>Pay Commission.</h2>
            <h3><span>Add MTN or Warid Float to <font color='blue'><c:out value="${kioskName}"></c:out></font> kiosk</span></h3>
            <div class="clr"></div>
            <!-- getting the  floats and cash for all the kiosk selected-->
            
            <div class="gadget">
            <sql:query var="kioskresults" dataSource="${db}">
                select waridline,mtnline,waridfloat,waridcash,mtnfloat,mtncash,waridcommission,mtncommission from kiosk where kioskname='${kioskName}';
            </sql:query>
                <table border='1'>
                    <thead>
                        <th>Warid Number</th>
                        <th>MTN Number</th>
                        <th>Warid Float</th>
                        <th>Warid Cash</th>
                        <th>MTN Float</th>
                        <th>MTN Cash</th>
                        <th>Warid Commission</th>
                        <th>MTN Commission</th>
                    </thead>
                <c:forEach var="kiosk" items="${kioskresults.rows}">
                    <tr>
                        <td><c:out value="${kiosk.waridline}"></c:out></td>
                        <td><c:out value="${kiosk.mtnline}"></c:out></td>
                        <td><c:out value="${kiosk.waridfloat}"></c:out></td>
                        <td><c:out value="${kiosk.waridcash}"></c:out></td>
                        <td><c:out value="${kiosk.mtnfloat}"></c:out></td>
                        <td><c:out value="${kiosk.mtncash}"></c:out></td>
                        <td><c:out value="${kiosk.waridcommission}"></c:out></td>
                        <td><c:out value="${kiosk.mtncommission}"></c:out></td>
                    </tr>
                </c:forEach>
                </table>
                <div class="clr"></div><br>
                <center><h4>Pay MTN Commission:</h4></center>
                <form for="mtnCommission" method="POST" action="addfloat.jsp">
                    <table>
                        <tr>
                            <td>Enter MTN commission to pay </td>
                            <td><input type="number" name="mtnCommission" required="required"/></td>
                            <td><input type="hidden" name="kioskname" value='<c:out value="${kioskName}"></c:out>'/>
                            <td><input type="submit" name="submit" value="Pay MTN Commission"/></td>
                        </tr>
                    </table>
                </form>
            <center><h4>Pay Warid Commission:</h4></center>
                <form for="waridCommission" method="POST" action="addfloat.jsp">
                    <table>
                        <tr>
                            <td>Enter Warid commission to pay </td>
                            <td><input type="number" name="waridCommission" required="required"/></td>
                            <td><input type="hidden" name="kioskname" value='<c:out value="${kioskName}"></c:out>'/>
                            <td><input type="submit" name="submit" value="Pay Warid Commission"/></td>
                        </tr>
                    </table>
                </form>
                
            </div>
            </c:if>
            <!-- handle the submission-->
            <!-- handle Warid Submission -->
            <c:if test="${param.submit eq 'Pay Warid Commission'}">
                
                <!-- update the warid float and commission for the selected kiosk -->
                <c:set var="newWaridCommission" value="${0}"></c:set>
                <c:set var="newWaridFloat" value="${0}"></c:set>
                <c:set var="availableWaridCommission" value="${0}"></c:set>
                <sql:query dataSource="${db}" var="waridResults">
                    select waridcommission,waridfloat from kiosk where kioskname='${param.kioskname}';
                </sql:query>
                <c:forEach var="waridCol" items="${waridResults.rows}">
                    <c:set var="availableWaridCommission" value="${waridCol.waridcommission}"></c:set>
                    <c:set var="newWaridCommission" value="${waridCol.waridcommission - param.waridCommission}"></c:set>
                    <c:set var="newWaridFloat" value="${waridCol.waridfloat + param.waridCommission}"></c:set>
                </c:forEach>
                <div class="gadget">
                    <h2>Paid Commission.</h2> 
                    <!-- update the float and commission for the kiosk for Warid only if the commission being
                     paid is less or equal to the available Warid commission
                    -->
                    
                    <c:choose>
                        <c:when test="${availableWaridCommission ge param.waridCommission}">
                            <sql:update dataSource="${db}" var="waridUpdate">
                                update kiosk set waridcommission=${newWaridCommission},waridfloat=${newWaridFloat} where kioskname='${param.kioskname}';
                            </sql:update>
                            <!-- display the warid changes-->
                            <h3><span>You have payed Warid Commission to <font color='blue'><c:out value="${param.kioskname}"></c:out></font> kiosk</span></h3>
                            <table for='paid commission'>
                                <tr>
                                    <td>Warid Commission paid:</td>
                                    <td>UGx<b> <u> <c:out value="${param.waridCommission}"></c:out></u></b></td>
                                </tr>
                            </table>
                            <p>Updated Kiosk <b><c:out value="${param.kioskname}"></c:out></b></p>
                            <sql:query var="kioskresults" dataSource="${db}">
                                select waridline,mtnline,waridfloat,waridcash,mtnfloat,mtncash,waridcommission,mtncommission from kiosk where kioskname='${param.kioskname}';
                            </sql:query>
                            <table border='1'>
                                <thead>
                                    <th>Warid Number</th>
                                    <th>MTN Number</th>
                                    <th>New Warid Float(UGx)</th>
                                    <th>Warid Cash(UGx)</th>
                                    <th>MTN Float(UGx)</th>
                                    <th>MTN Cash(UGx)</th>
                                    <th>Unpaid Warid Commission(UGx)</th>
                                    <th>Unpaid MTN Commission(UGx)</th>
                                </thead>
                                <c:forEach var="kiosk" items="${kioskresults.rows}">
                                <tr>
                                    <td><c:out value="${kiosk.waridline}"></c:out></td>
                                    <td><c:out value="${kiosk.mtnline}"></c:out></td>
                                    <td><c:out value="${kiosk.waridfloat}"></c:out></td>
                                    <td><c:out value="${kiosk.waridcash}"></c:out></td>
                                    <td><c:out value="${kiosk.mtnfloat}"></c:out></td>
                                    <td><c:out value="${kiosk.mtncash}"></c:out></td>
                                    <td><c:out value="${kiosk.waridcommission}"></c:out></td>
                                    <td><c:out value="${kiosk.mtncommission}"></c:out></td>
                                </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                                UGx: <b><c:out value="${param.waridCommission}"/></b><font color='red'>.  is greater than available Warid Commission.</font>
                                <br>Commission not paid.
                        </c:otherwise>
                    </c:choose>
                    
                </div>
                
            </c:if>
            <!-- Handle mtn Submmission-->
            <c:if test="${param.submit eq 'Pay MTN Commission'}">
                <!-- update the mtn float and 
                     commission for the selected kiosk
                -->
                <c:set var="newMtnCommission" value="${0}"></c:set>
                <c:set var="newMtnFloat" value="${0}"></c:set>
                <c:set var="availableMtnCommission" value="${0}"></c:set>
                <sql:query var="mtnResults" dataSource="${db}">
                    select mtncommission,mtnfloat from kiosk where kioskname='${param.kioskname}';
                </sql:query>
                <c:forEach var="mtnCol" items="${mtnResults.rows}">
                    <c:set var="availableMtnCommission" value="${mtnCol.mtncommission}"></c:set>
                    <c:set var="newMtnCommission" value="${mtnCol.mtncommission - param.mtnCommission}"></c:set>
                    <c:set var="newMtnFloat" value="${mtnCol.mtnfloat + param.mtnCommission}"></c:set>
                </c:forEach>
                <h2>Paid Commission.</h2> 
                    <!-- update the float and commission for the kiosk for Mtn only if the commission being
                     paid is less or equal to the available Warid commission
                    -->
                    
                    <c:choose>
                        <c:when test="${availableMtnCommission ge param.mtnCommission}">
                            <sql:update dataSource="${db}" var="mtnUpdate">
                                update kiosk set mtncommission=${newMtnCommission},mtnfloat=${newMtnFloat} where kioskname='${param.kioskname}';
                            </sql:update>
                            <!-- display the Mtn changes-->
                            <h3><span>You have payed MTN Commission to <font color='blue'><c:out value="${param.kioskname}"></c:out></font> kiosk</span></h3>
                            <table for='paid commission'>
                                <tr>
                                    <td>MTN Commission paid:</td>
                                    <td>UGx<b> <u> <c:out value="${param.mtnCommission}"></c:out></u></b></td>
                                </tr>
                            </table>
                            <p>Updated Kiosk <b><c:out value="${param.kioskname}"></c:out></b></p> 
                            <sql:query var="kioskresults" dataSource="${db}">
                                select waridline,mtnline,waridfloat,waridcash,mtnfloat,mtncash,waridcommission,mtncommission from kiosk where kioskname='${param.kioskname}';
                            </sql:query>
                            <table border='1'>
                                <thead>
                                    <th>Warid Number</th>
                                    <th>MTN Number</th>
                                    <th>Warid Float(UGx)</th>
                                    <th>Warid Cash(UGx)</th>
                                    <th>New MTN Float(UGx)</th>
                                    <th>MTN Cash(UGx)</th>
                                    <th>Unpaid Warid Commission(UGx)</th>
                                    <th>Unpaid MTN Commission(UGx)</th>
                                </thead>
                                <c:forEach var="kiosk" items="${kioskresults.rows}">
                                <tr>
                                    <td><c:out value="${kiosk.waridline}"></c:out></td>
                                    <td><c:out value="${kiosk.mtnline}"></c:out></td>
                                    <td><c:out value="${kiosk.waridfloat}"></c:out></td>
                                    <td><c:out value="${kiosk.waridcash}"></c:out></td>
                                    <td><c:out value="${kiosk.mtnfloat}"></c:out></td>
                                    <td><c:out value="${kiosk.mtncash}"></c:out></td>
                                    <td><c:out value="${kiosk.waridcommission}"></c:out></td>
                                    <td><c:out value="${kiosk.mtncommission}"></c:out></td>
                                </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            UGx: <b><c:out value="${param.mtnCommission}"/></b><font color='red'>.  is greater than available MTN Commission.</font>
                            <br>Commission not paid.
                        </c:otherwise>
                    </c:choose>
                    
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