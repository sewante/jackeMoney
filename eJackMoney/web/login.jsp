<%-- 
    Document   : login
    Created on : May 15, 2017, 8:32:17 PM
    Author     : MWESIGYE
--%>
<% pageContext.setAttribute("title", "login");%>
<% pageContext.setAttribute("page", "login"); %>

<%@include file="/include/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2><span>Login</span></h2>
            <div class="clr"></div>
            <form action="login.jsp" method="POST" id="login">
              <ol>
                <li>
                  <label for="name">User Name</label>
                  <input id="name" name="username" class="text" />
                </li>
                <li>
                  <label for="password">Password</label>
                  <input type="password" id="password" name="password" class="text" />
                </li>
                <li>
                  <input type="submit" name="submit" id="submit" value="login" class="send" />
                  <div class="clr"></div>
                </li>
              </ol>
            </form>
            <p>
            <font color='red'>
            <c:if test="${not empty param.errMsg}" >
                <c:out value="${param.errMsg}"/>
            </c:if>
            </font></p>
          </div>
        </div>
        <div>
            <!-- verify the login -->
            <c:if test="${param.submit eq 'login'}">
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
            </c:if>
            
        </div>
        <div class="clr"></div>
      </div>
    </div>
  </div>
</div>
<%@include file="/include/footer.jsp" %>