<%-- 
    Document   : dummy
    Created on : May 18, 2017, 4:04:20 PM
    Author     : MWESIGYE
    this is just my clipbord for codding

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
<!DOCTYPE html>
 Statement state = null;
    try{ 
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jackemoney","root","");
        state =con.createStatement();
    }catch(ClassNotFoundException err){
        out.print("Failed to Loaed the Database Driver"+err.getMessage());
    }catch(SQLException err){
        out.print("Failed to Connect to database"+err.getMessage());
    }
    
    
    
    
                <%
                    if(request.getParameter("submit").equals("submit")){
                %>
                
                <div class="gadget" align="left">

                    </div>
                <div class="gadget" align="right">
                    
                </div>
                <%} else{%>
                    
                <% } %>
                
                
                <%
                    //passing the java in the jstl var or test tag 
                     //   ${you jsp code here}
                %>
                
                
                
            <c:if test="${param.submit eq Go}">
                <c:out value="here" ></c:out>
            </c:if>
                
                 /*  for(int i = 0; i < transactions.length; i++){
				   String[] transaction = transactions[i].split(" ");
				   
				   if(transaction[0].equals("deposit")){
					   try{
						   if(transaction.length == 4){
								String customerNumber = transaction[1];
								int amount = Integer.parseInt(transaction[2]);
								String kioskPhone = transaction[3];
						   } else{
							   output.writeUTF(" Invalid parameters for deposit");
						   }
						   
					   }catch(NumberFormatException e){
						   output.writeUTF(" Invalid format for Amount ");
					   }catch(ArrayIndexOutOfBoundsException er){
						   output.writeUTF(" Too many parameters not allowed use only the allowed parameters ");
					   }
				   }
				   else if(transaction[0].equals("withdraw")){
					   //do something for witdraw
				   }
				   else{
					   //if not withdraw or deposit
				   }
				   System.out.println(transaction[i]);
			   }*/
               
                           
                           //login
                           
                           String user = null;
			try{
				Statement state = getStatement();
				
				String query = "select userName,password,userId from users";
				ResultSet results = state.executeQuery(query);
				
				while(results.next()){
					if(results.getString("userName").equals(username) && results.getString("password").equals(password)){
						System.out.println(" am inside");
						user = results.getString("userName");
					} else{
						//System.out.print("Wrong User Name and password");
					}
				}
			} catch(SQLException e){
				System.out.println(" Failed login ");
			}
			here:
			while(user != username){
				System.out.print("Enter user name: ");
					username = login.nextLine();
					if(user.equals(username)){
						break;
					}
					else {
						break here;
					}
                        }
            <c:if test="${param.submit eq 'Pay Commission'}" >
                <h2>Paid Commission.</h2>
                
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
                    
                <%-- update the float and commission for the kiosk for mtn only if the commission 
                     being paid is less or equal to the available commission
                --%>
                <c:set var="mtnSuccess" value="${false}"></c:set>
                <c:choose>
                    <c:when test="${availableMtnCommission ge param.mtnCommission}">
                        <sql:update dataSource="${db}" var="mtnUpdate">
                            update kiosk set mtncommission=${newMtnCommission},mtnfloat=${newMtnFloat} where kioskname='${param.kioskname}';
                        </sql:update>
                        <c:set var="mtnSuccess" value="${true}"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="mtnSuccess" value="${false}"></c:set>
                    </c:otherwise>
                </c:choose>
                <!-- update the float and commission for the kiosk for Warid only if the commission being
                 paid is less or equal to the available Warid commission
                -->
                <c:set var="waridSuccess" value="${false}"></c:set>
                <c:choose>
                    <c:when test="${availableWaridCommission ge param.waridCommission}">
                        <sql:update dataSource="${db}" var="waridUpdate">
                            update kiosk set waridcommission=${newWaridCommission},waridfloat=${newWaridFloat} where kioskname='${param.kioskname}';
                        </sql:update>
                        <c:set var="waridSuccess" value="${true}"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="WaridSuccess" value="${false}"></c:set>
                    </c:otherwise>
                </c:choose>
                <div class="gadget">
                    <h3><span>You have payed <font color='blue'><c:out value="${param.kioskname}"></c:out></font> kiosk</span></h3>
                    <table for='paid commission'>
                        <tr>
                            <td>MTN Commission paid:</td>
                            <td>UGx <u><c:out value="${param.mtnCommission}"></c:out></u></td>
                        </tr>
                        <tr>
                            <td>Warid Commission paid:</td>
                            <td>UGx <u> <c:out value="${param.waridCommission}"></c:out></u></td>
                        </tr>
                    </table>
                    <!--
                        display the new commissions and
                        floats for the kiosk after updating
                    -->
                    
                    <sql:query var="kioskresults" dataSource="${db}">
                        select waridline,mtnline,waridfloat,waridcash,mtnfloat,mtncash,waridcommission,mtncommission from kiosk where kioskname='${param.kioskname}';
                    </sql:query>
                    <table border='1'>
                        <thead>
                            <th>Warid Number</th>
                            <th>MTN Number</th>
                            <th>New Warid Float</th>
                            <th>Warid Cash</th>
                            <th>New MTN Float</th>
                            <th>MTN Cash</th>
                            <th>Unpaid Warid Commission</th>
                            <th>Unpaid MTN Commission</th>
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
                </div>
            </c:if>	
            
            
            <form method="POST" action="addfloat.jsp">
                    <table>
                        <tr>
                            <td>Enter MTN commission to pay or 0</td>
                            <td><input type="number" name="mtnCommission" required="required"/></td>
                        </tr>
                        <tr>
                            <td>Enter Warid commission to pay or 0</td>
                            <td><input type="number" name="waridCommission" required="required"/></td>
                        </tr>
                        <tr><td><input type="hidden" name="kioskname" value='<c:out value="${kioskName}"></c:out>'/></td></tr>
                        <tr>
                            <td> </td>
                            <td><input type="submit" name="submit" value="Pay Commission"/></td>
                        </tr>
                    </table>
                </form>
                        
                        
                <h4>Pay MTN and Warid Commission</h4>
                
                
                <sql:setDataSource var="db" scope="session" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/jackemoney" user="root" password=""></sql:setDataSource> 
                <sql:query dataSource="${db}" var="user">
                    select userName from kiosk where kioskname='${param.kiosk}';
                </sql:query>
                <c:forEach var="col" items="${user.rows}">
                    <c:set var="username" value="${col.userName}"></c:set>
                    
                </c:forEach>
                    <p>
                        Here are the Commissions for<b> <c:out value="${username}" ></c:out></b> at Kiosk<b> <c:out value="${param.kiosk}"></c:out> </b>.   
                    </p>
                <sql:query dataSource="${db}" var="warid_result">
                    select commission,amount,type,dateAndTime from transactions where serviceProvider='warid' and userName='${username}';
                </sql:query>
                    
                <div class="gadget">
                <c:choose>
                    <c:when test="${ not empty warid_result.rows}">
                        <p><center><b>WARID COMMISSION</b></center></p>
                        <table border="1" align="center">
                        <tr>
                            <th>Commission (UGX)</th>
                            <th>Type</th>
                            <th>Amount(UGX)</th>
                            <th>Date and Time</th>
                        </tr>
                        <c:set var="totalWaridCommission" value="${0}"></c:set>
                        <c:forEach var="warid" items="${warid_result.rows}">
                        <tr>
                            <td><c:out value="${warid.commission}"/></td>
                            <td><c:out value="${warid.type}"></c:out></td>
                            <td><c:out value="${warid.amount}"></c:out></td>
                            <td><c:out value="${warid.dateAndTime}"></c:out></td>
                        </tr>
                        <c:set var="totalWaridCommission" value="${totalWaridCommission + warid.commission}"></c:set>
                        </c:forEach>
                        </table>
                        <div class="clr"></div>
                        <p>The total Commission for Warid is: <b>UGX <c:out value="${totalWaridCommission}"></c:out> </b></p>
                    </c:when>
                    <c:otherwise>
                        <p><font color='red'><c:out value="${param.kiosk}"/> has no commission yet for Warid.</font></p>
                    </c:otherwise>
                </c:choose>
                    
               
                    <!-- getting the total commission for mtn -->
                    <sql:query dataSource="${db}" var="mtn_results">
                        select commission,amount,type,dateAndTime from transactions where serviceProvider='mtn' and userName='${username}';
                    </sql:query>
                
                    <c:choose>
                    <c:when test="${ not empty mtn_results.rows}">
                        <p><center><b>MTN COMMISSION</b></center></p>
                        <table border="1" align="center" >
                        <tr>
                            <th>Commission (UGX)</th>
                            <th>Type</th>
                            <th>Amount(UGX)</th>
                            <th>Date and Time</th>
                        </tr>
                        <c:set var="totalMtnCommission" value="${0}"></c:set>
                        <c:forEach var="mtn" items="${mtn_results.rows}">
                        <tr>
                            <td><c:out value="${mtn.commission}"/></td>
                            <td><c:out value="${mtn.type}"></c:out></td>
                            <td><c:out value="${mtn.amount}"></c:out></td>
                            <td><c:out value="${mtn.dateAndTime}"></c:out></td>
                        </tr>
                        <c:set var="totalMtnCommission" value="${totalMtnCommission + mtn.commission}"></c:set>
                        </c:forEach>
                        </table>
                        <div class="clr"></div>
                        <p>The total Commission for MTN is: <b>UGX <c:out value="${totalMtnCommission}"></c:out></b></p>
                    </c:when>
                    <c:otherwise>
                        <p><font color='red'><c:out value="${param.kiosk}"/> has no commission yet for MTN.</font></p>
                    </c:otherwise>
                </c:choose>
                </div>