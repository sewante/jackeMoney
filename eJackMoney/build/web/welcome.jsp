<%-- 
    Document   : welcome
    Created on : May 16, 2017, 4:01:31 PM
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

<%@page import="java.io.*, java.sql.*,java.util.*" %>
<%@page import="javax.servlet.http.*,javax.servlet.*"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<% pageContext.setAttribute("page", "welcome");%>
<% pageContext.setAttribute("title", "welcome"); %>
<%@include file="/include/loginHeader.jsp" %>

<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2><span>Welcome to </span> JackEMoneytransfer</h2>
            <div class="clr"></div>
            <p><strong>Here is what you have to do with this page</strong></p>
            <ul class="list" style="font-size:15px; text-decoration:none;">
                <li>Click <font id="welp">Add attendants</font> tab to register a new kiosk attendant</li>
                <li>Click the <font id="welp">Float and Cash</font> button to view the Floats , Commissions  and Cash for a particular KiosK</li>
                <li>Click on the <font id="welp">KioskName</font> Link to Add Commission to a particular Kiosk</li>
                <li>Click <font id="welp">Transfers</font> to See the available pending and serviced transactions between attendants </li>
                <li>Click <font id="welp">View commissions</font> to assess the accumulation of commission at a particular kiosk</li>
                <li>Monitor the overall ongoing transactions in all Kiosks  by Clicking All <font id="welp">Transactions button</font></li>
            </ul>
           </div>
          <div class="article">
            <h2><span>Our</span> Mission</h2>
            <div class="clr"></div>
            </div>
        </div>
        <div class="sidebar">
          <div class="gadget">
            <h2 class="star"><span>Management</span> Menu</h2>
            <div class="clr"></div>
            <ul class="sb_menu">
              <li><a href="add_attendants.jsp">Add Attendants</a></li>
              <li><a href="float_and_cash.jsp">Float and Cash</a></li>
              <li><a href="transfers.jsp">Transfers</a></li>
              <li><a href="commissions.jsp">View Commissions</a></li>
              <li><a href="transactions.jsp">All Transactions</a></li>
            </ul>
          </div>
          <div class="gadget">
              
          </div>
        </div>
        <div class="clr"></div>
      </div>
    </div>
  </div>
</div>

<%@include file="/include/footer.jsp" %> 
