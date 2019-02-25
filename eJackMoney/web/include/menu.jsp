<%-- 
    Document   : menu
    Created on : May 17, 2017, 8:19:56 PM
    Author     : MWESIGYE
--%>
        <div class="sidebar">
          <div class="gadget">
            <h2 class="star"><span>Management</span> Menu</h2>
            <div class="clr"></div>
            <ul class="sb_menu">
                <% if(pageContext.getAttribute("menu").equals("add_attendants")){%>
                        <li class='active'><a href="add_attendants.jsp">Add Attendants</a></li>
                <%} else{%>
                        <li><a href="add_attendants.jsp">Add Attendants</a></li>
                <%}%>
                    
                <% if(pageContext.getAttribute("menu").equals("float_and_cash")){ %>
                        <li class="active"><a href="float_and_cash.jsp">Float and Cash</a></li>
                <%}else{%>
                        <li><a href="float_and_cash.jsp">Float and Cash</a></li>
                <%}%>
                        
                <% if(pageContext.getAttribute("menu").equals("transfers")){ %>       
                        <li class="active"><a href="transfers.jsp">Transfers</a></li>
                <%} else{ %>
                        <li><a href="transfers.jsp">Transfers</a></li>
                <%}%>
                    
                <% if(pageContext.getAttribute("menu").equals("commission")){ %>
                        <li class="active"><a href="commissions.jsp">View Commissions</a></li>
                <%} else{ %>
                        <li><a href="commissions.jsp">View Commissions</a></li>
                <% } %>
                        
                <% if(pageContext.getAttribute("menu").equals("transactions")){ %>
                        <li class="active"><a href="transactions.jsp">All Transactions</a></li>
                <% } else { %>
                        <li><a href="transactions.jsp">All Transactions</a></li>
                <% } %>
            </ul>
          </div>
          <div class="gadget">
              
          </div>
        