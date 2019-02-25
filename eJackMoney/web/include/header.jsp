<%-- 
    Document   : header
    Created on : May 15, 2017, 7:42:45 PM
    Author     : MWESIGYE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title><% out.print(pageContext.getAttribute("title"));%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/arial.js"></script>
        <script type="text/javascript" src="js/cuf_run.js"></script>
    </head>
    <body>
        <div class="main">
          <div class="main_resize">
            <div class="header">
              <div class="logo">
                <h1><a href="#"><span>Jack</span>eMoney<small>easy and convenient</small></a></h1>
              </div>
              <div class="search">

                <!--/searchform -->
                <div class="clr"></div>
              </div>
              <div class="clr"></div>
              <div class="menu_nav">
                <ul>
                    <% if (pageContext.getAttribute("page").equals("index")) { %>
                    <li class="active"><a href="index.jsp">Home</a></li>
                    <% } else { %>
                    <li><a href="index.jsp">Home</a></li>
                    <% } %>
                    
                    
                  
                    <% if (pageContext.getAttribute("page").equals("login")) { %>
                    <li class="active"><a href="login.jsp">Login</a></li>
                    <% } else { %>
                    <li><a href="login.jsp">Login</a></li>
                    <% } %>
                  
                </ul>
                <div class="clr"></div>
              </div>