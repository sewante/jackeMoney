<%-- 
    Document   : add_attendants
    Created on : May 17, 2017, 8:14:07 PM
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
<% pageContext.setAttribute("title","add_attendants"); %>
<% pageContext.setAttribute("page", "");
   pageContext.setAttribute("menu", "add_attendants");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="/include/loginHeader.jsp" %>

       
<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2><span>Add Attendant</span></h2>
            <div class="clr"></div>
            <form action="add_attendants.jsp" method="post" id="sendemail">
              <ol>
                <li>
                  <label for="kiosk">Kiosk Name</label>
                  <input id="kiosk" name="kiosk" class="text" required="required" />
                </li>
                <li>
                  <label for="user">User Name</label>
                  <input id="user" name="user" class="text" required="required" />
                </li>
                <li>
                  <label for="password">Password</label>
                  <input id="password" name="pass" class="text" required="required" />
                </li>
                <li>
                    <input type="hidden" id="category" name="category" value="Attendant"  />
                </li>
                <li>
                  <label for="warid">Warid Number</label>
                  <input id="warid" name="waridNo" class="text" required="required" />
                </li>
                <li>
                  <label for="mtn">MTN Number</label>
                  <input id="mtn" name="mtnNo" class="text" required="required" />
                </li>
                <li>
                  <label for="waridfloat">Initial Warid Float</label>
                  <input id="waridfloat" name="waridfloat" class="text" required="required" />
                </li>
                <li>
                  <label for="mtnfloat">Initial MTN Float </label>
                  <input id="mtnfloat" name="mtnfloat" class="text" required="required"/>
                </li>
                <li>
                    <input type="submit" name="submit" id="submit" value="Add Attendant"  class="send" />
                  <div class="clr"></div>
                </li>
              </ol>
            </form>
            <c:if test="${param.submit eq 'Add Attendant'}">
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

                <%      Statement state = getStatement();   //get the statement

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
                <script type="text/javascript">
                    alert("Operation successful");
                </script>
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