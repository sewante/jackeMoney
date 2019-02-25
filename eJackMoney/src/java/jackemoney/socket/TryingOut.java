/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackemoney.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Timestamp;
import java.sql.ResultSet;
/**
 *
 * @author MWESIGYE
 *
 */
  /*
							GROUP MEMBERS
							=============
		______________________________________________________
		* MWESIGYE  ROBERT		    |  15 / U / 771           |
		* NANSUBUGA JOYCE   EUZEBIA |  15 / U / 10807 / EVE   |
		* NAKAALI   PHIONA			| 	15 / U / 9286 / PS    |
		* BWAYO     JUDE      		|  15 / U / 4718 / EVE    |
		____________________________|_________________________|
 */
public class TryingOut {
	static boolean isLoggedIn = false;
    static DataInputStream input = null;
	static DataOutputStream output = null;

    public static void main(String[] args) throws IOException{
		
		ServerSocket port =new ServerSocket(6666);//create Server socket at port 6666
		//loop back to the accept call 
		while(true){
			
				try{
					System.out.println(" Listening....");
					Socket socket = port.accept();  //establish connection
					input = new DataInputStream(socket.getInputStream());   //reader
					output = new DataOutputStream(socket.getOutputStream());   //writter
					String userName = null;
		//loging in***************************************************************************************************************************************************************
					while (! isLoggedIn) {
						output.writeUTF(" Enter Username to login: ");
						userName = input.readUTF();
						// output.writeUTF("\n  Login succesfully completed using username: " + userName);
						try{
							Statement state = getStatement();	//get the statement
							String query = "select userName from users WHERE userName = '" + userName+"'";
							ResultSet names = state.executeQuery(query);
							if(names.next()){
								isLoggedIn = true;
							} else{
								output.writeUTF("Invalid username entered, please try again.");
							}
						} catch(SQLException sqle){
							output.writeUTF(sqle.getMessage());
						}
					}//end while
					System.out.println(" Logged in: "+userName);
	  //end loging in***********************************************************************************************************************************************************			
					String reply = "";
					while(true){
						output.writeUTF(reply + "\n" +" Logged in "+ userName+ " @ JackeMoney-transactions>  ");	//writer to client
						String message = input.readUTF();       //read a string from the client
		
				//interprete the commands
	  
		//viewFloat(message)........................................................................................................................................
						 if(message.startsWith("view all floats ")){
								 String[] params = message.split(" ");
								 if(params.length == 4){
									 String serviceProvider = params[3];    //pick the service provider from the command
									 
									 Statement state = getStatement();  //get statement
									 if(serviceProvider.equals("warid")){
										 try{
											 //select the float,userName from the database to compare who has more float for warid
											String query = "select userName,waridfloat from kiosk";
											ResultSet waridFloats = state.executeQuery(query);
											String response = " Warid Float" + "\n" +
															  " User Name:       Warid Float" + "\n" + "\n";
											while(waridFloats.next()){
												response += " " + waridFloats.getString("userName") + "            " + waridFloats.getString("waridfloat") + "\n";
											}
											
											reply = response;
										 
										 } catch(SQLException err){
											 reply = " ERROR: failed to get the warid Floats..";
										 }
										 
									 } else if(serviceProvider.equals("mtn")){
										 //do something for mtn
										 try{
											 //select the float, userName from the database to compare who has more float for mtn
											 String query = "select userName,mtnfloat from kiosk";
											 ResultSet mtnFloats = state.executeQuery(query);
											 String response = " MTN Float" +"\n"+
															   " User Name:       MTN Float" + "\n" + "\n";
										     while(mtnFloats.next()){
												 response += " " + mtnFloats.getString("userName") + "            " + mtnFloats.getString("mtnfloat") + "\n";
											 }
											 reply = response;
											 
										 } catch(SQLException sqle){
											 reply = " ERROR: failed to get MTN Float..";
										 }
									 } else{
										 reply = " ERROR: unsupported service provider "+serviceProvider;
									 }
									 
									 
								 } else{
									 //handle the error from here
									 reply = " ERROR: Unsupported Command...so many arguments for view float command ";
								 }
						}
	  //end view float here.....................................................................................................................
						
	//the request command*********************************************************************************************************************
						 else if(message.startsWith("request ")){
							message = message.substring("request ".length());	//remove request from the submitted command
							String[] request = message.split(" ");	//split the message at space to get individual elements from the command
							try{
								if(request.length == 3){
									String receiver = request[0];
									String customerNumber = request[1];
									int amount = Integer.parseInt(request[2]);
									int receiverId = getUserId(receiver);
									int senderId = getUserId(userName);
									//insert to db
									insertRequest(customerNumber, senderId, receiverId, amount, "pending");
									System.out.println(" request successful...");	//status at server
									reply = " request succesfully sent to "+receiver;	//response to attendant
								} else{
									reply = " ERROR: request failed.. too many parameters supplied for request command";
								}
							} catch(NumberFormatException nfe){
								reply = " ERROR: Invalid amount submitted for request";
							}
						 }
	 //end the request command****************************************************************************************************************
	 
	 //the view requests command****************************************************************************************************************
						 else if(message.startsWith("view requests")){
								int userId = getUserId(userName);
								Statement state = getStatement();
								try{
									String requestQuery = "select requestId,customerNumber,amount,status,serviceProvider from request where receiverUserId="+userId;
									ResultSet requestResults = state.executeQuery(requestQuery);
									String requestResponse = " Requests: "+ "\n"+
											"Request Id  Customer Number      Amount        Status        Service Provider" + "\n" + "\n";
									while(requestResults.next()){
										requestResponse += " "+requestResults.getInt("requestId")+"          "+requestResults.getString("customerNumber")+
															"           "+String.valueOf(requestResults.getInt("amount"))+"      "+
															"   "+requestResults.getString("status")+"        "+requestResults.getString("serviceProvider") + "\n";
										
									}
									reply = requestResponse;
								} catch(SQLException sqle){
									reply = " ERROR: failed to get requests from database";
								}
							 
						 }
	 //end view requests command****************************************************************************************************************
	 
	//for single transaction  commands********************************************************************************************************
						 else if (message.startsWith("service request")) {
								try {
									String[] deposit = message.split(" ");
									if(deposit.length == 6){
										int requestId = Integer.parseInt(deposit[2]);
										String customerNumber = deposit[3];
										int amount = Integer.parseInt(deposit[4]);
										String agentNumber = deposit[5];
										//insert to database
										// ensure correct number and amount are supplied and insert to the database
										int commission = getDepositCommission(amount,customerNumber);									//get the commission
										if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
											if(acceptedAmount(amount, customerNumber) == true){
												insertTransaction(amount, customerNumber, agentNumber,commission,"deposit",userName);	//record the transaction into database
												updateDepositFloatAndCash(amount, customerNumber, userName);							//update float and cash
												updateStatus(requestId);																//update status	
												System.out.println(" Warid Deposit successful..");										//status at the server
												reply = " Warid Deposit succesfully serviced..";										//inform attendant
											}
											else{
												reply = "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system,request not serviced";
											}
											
										} 		
										else if(customerNumber.startsWith("077") || customerNumber.startsWith("078")){
											if(acceptedAmount(amount, customerNumber) == true){											//is amount acceptable
												insertTransaction(amount, customerNumber, agentNumber,commission,"deposit",userName);	//record the transaction into database
												updateDepositFloatAndCash(amount, customerNumber, userName);							//update float and cash
												updateStatus(requestId);																//update status
												System.out.println(" MTN Deposit successful..");										//status at the server
												reply = " MTN Deposit succesfully serviced..";											//inform attendant
											}
											else{
												reply = "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system, request not serviced";
											}
											
											
										}else {
											reply = " ERROR: invalid customer Number,"+customerNumber+", enter a valid customer number..";
										}//end ensure
									} else {
										reply = " ERROR: Invalid parameters supplied for service request.";
									}
								}
								catch (NumberFormatException e) {
									reply = " ERROR: Invalid amount supplied for service request.";
								}
							}
						 else if(message.startsWith("withdraw")){
								//do something for withdraw
								String[] withdraw = message.split(" ");
											try{
												if(withdraw.length == 4){
													String customerNumber = withdraw[1];
													int amount = Integer.parseInt(withdraw[2]);
													String agentNumber = withdraw[3];
													int withdrawCommission = getWithdrawCommission(amount, customerNumber);						//get the commission for 
													//ensure correct number and amount are supplied for withdraw and insert the transaction to database
													if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){					//check number
														if(acceptedAmount(amount,customerNumber) == true){										//check  amount acceptable
															if(verifyWithdraw(amount, customerNumber, userName) == true){						//check if theres enough cash
																insertTransaction(amount, customerNumber, agentNumber,withdrawCommission,"withdraw",userName);	//record the transaction into database
																updateWithdrawFloatAndCash(amount, customerNumber, userName);					//update float and cash
																System.out.println(" Warid Withdraw successful..");								//status at the server
																reply = " Warid Withdraw succesfully recorded..";								//inform attendant	
															} else{
																reply = "\n ERROR: You have insufficient cash withdraw not possible accumulate enough cash and try again..";
															}
														}else{
															reply = "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system";
														}
													}
													//do the same check on the mtn number
													else if(customerNumber.startsWith("077") || customerNumber.startsWith("078")){
														if(acceptedAmount(amount,customerNumber) == true){										//check  amount acceptable
															if(verifyWithdraw(amount, customerNumber, userName) == true){						//check if theres enough cash
																insertTransaction(amount, customerNumber, agentNumber,withdrawCommission,"withdraw",userName);	//record the transaction into database
																updateWithdrawFloatAndCash(amount, customerNumber, userName);					//update float and cash
																System.out.println(" MTN Withdraw successful..");								//status at the server
																reply = " MTN Withdraw succesfully recorded..";									//inform attendant	
															} else{
																reply = "\n ERROR: You have insufficient cash, withdraw not possible accumulate enough cash and try again..";
															}
														}else{
															reply = "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system";
														}
													}
													//when number is not either mtn or warid
													else{
														reply = " ERROR: invalid customer Number,"+customerNumber+", enter a valid customer number..";
													}//end esure
													
												} else{
													reply = " ERROR: Invalid parameters for Withdraw..";
												}
											}catch(NumberFormatException e){
												reply = " ERROR: Invalid amount supplied for Withdraw..";
											}
						 }
	//for more than one transaction*************************************************************************************************************
						 else if(message.startsWith("commit")){
							   message = message.substring("commit ".length());    //remove commit from the submitted command
							   //store each transaction as a separate element in an array of strings
							   String[] transactions = message.split(",");
							   
								if((transactions.length <= 3) && (transactions.length >= 2)){	//for two or three transaction
									
									String[] transaction1 = transactions[1].split(" ");
									String commitReply = "";
									for(int i = 0; i < transactions.length; i++){
										String[] trans = transactions[i].split(" ");	//this contains the particular transaction
										if(trans[0].startsWith("deposit")){
											try{
												if(trans.length == 4){
													String customerNumber = trans[1];
													int amount = Integer.parseInt(trans[2]);
													String agentNumber = trans[3];
													// ensure correct number is supplied for deposit and insert to the database
													int depositCommission = getDepositCommission(amount,customerNumber);			//get the depositCommission
													if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
														if(acceptedAmount(amount, customerNumber) == true){							//is the amount acceptable
															insertTransaction(amount, customerNumber, agentNumber,depositCommission,"deposit",userName);	//record the transaction into database
															updateDepositFloatAndCash(amount, customerNumber, userName);			//update float and cash
															System.out.println(" Warid Deposit successful..");						//status at the server
															commitReply += " Warid Deposit succesfully recorded..";					//inform attendant
															
														}
														else{
															commitReply += "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system, deposit failed";	
														}
														
													} 		
													else if(customerNumber.startsWith("077") || customerNumber.startsWith("078")){
														if(acceptedAmount(amount, customerNumber) == true){												//is the amount acceptable
															insertTransaction(amount, customerNumber, agentNumber,depositCommission,"deposit",userName);	//record the transaction into database
															updateDepositFloatAndCash(amount, customerNumber, userName);							//update float and cash
															System.out.println(" MTN Deposit successful..");										//status at the server
															commitReply += " MTN Deposit succesfully recorded..";									//inform attendant
														}
														else{
															commitReply += "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system, deposit failed";
														}
														
													}
													//when number is neither warid nor mtn
													else {
														commitReply += " ERROR: invalid customer Number,"+customerNumber+", enter a valid customer number.., deposit failed";
													}//end ensure 
												} else{
													commitReply += " ERROR: Invalid parameters for Deposit.., deposit failed";
												}
											}catch(NumberFormatException e){
												commitReply += " ERROR: Invalid amount supplied for Deposit.., deposit failed";
											}
											
												
										}
										else if(trans[0].startsWith("withdraw")){
											try{
												if(trans.length == 4){
													String customerNumber = trans[1];
													int amount = Integer.parseInt(trans[2]);
													String agentNumber = trans[3];
													int withdrawCommission = getWithdrawCommission(amount,customerNumber);
													//insert to the database later
													//ensure correct number is supplied for withdraw
													if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){			//check number
														if(acceptedAmount(amount,customerNumber) == true){								//check  amount acceptable
															if(verifyWithdraw(amount, customerNumber, userName) == true){				//check if theres enough cash
																insertTransaction(amount, customerNumber, agentNumber,withdrawCommission,"withdraw",userName);	//record the transaction into database
																updateWithdrawFloatAndCash(amount, customerNumber, userName);			//update float and cash
																System.out.println(" Warid Withdraw successful..");						//status at the server
																commitReply += "\n Warid Withdraw succesfully recorded..";				//inform attendant	
															} else{
																commitReply += "\n ERROR: You have insufficient cash withdraw not possible accumulate enough cash and try again.., withdraw failed";
															}
														}else{
															commitReply += "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported not supported by the system, withdraw failed";
														}
													}
													//do the same check on the mtn number
													else if(customerNumber.startsWith("077") || customerNumber.startsWith("078")){
														if(acceptedAmount(amount,customerNumber) == true){							//check  amount acceptable
															if(verifyWithdraw(amount, customerNumber, userName) == true){			//check if theres enough cash
																insertTransaction(amount, customerNumber, agentNumber,withdrawCommission,"withdraw",userName);	//record the transaction into database
																updateWithdrawFloatAndCash(amount, customerNumber, userName);		//update float and cash
																System.out.println(" MTN Withdraw successful..");					//status at the server
																commitReply += "\n MTN Withdraw succesfully recorded..";			//inform attendant	
															} else{
																commitReply += "\n ERROR: You have insufficient cash, withdraw not possible accumulate enough cash and try again.., withdraw failed";
															}
														}else{
															commitReply += "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system, withdraw failed";
														}
													}
													//when number is not either mtn or warid
													else{
														commitReply += " ERROR: invalid customer Number,"+customerNumber+", enter a valid customer number..";
													}//end esure
												} else{
													commitReply += "\n ERROR: Invalid parameters for Withdraw.., withdraw failed";
												}
											}catch(NumberFormatException e){
												commitReply += "\n ERROR: Invalid amount supplied for Withdraw.., withdraw failed";
											}		
										}
										else{
										//do something if not deposit nor withdraw
										commitReply += " ERROR: Transactions are Either Deposit or withdraw only..";
										}
									}
									
									reply = commitReply;
								 } else{
									reply = " ERROR: Error in commit... Only a maximum of three transactions are possible at a time..";
								}
						} else{
							reply = " ERROR: Un recogonised command: " + message+", make sure you typed the correct command...";
						}
	//end interpreting transactions*************************************************************************************************************
					}//end inner while
					
				}catch(Exception err){
					System.out.println("connection problem "+err);  
					output.writeUTF(" SERVER FAILURE ");
				}
		}//loop back to the accept call here
    
    }   //end main method
    
/*
	#The helper methods below
	#
	#

*/
	
//get connection and statement
    public static Statement getStatement(){
            Statement state = null;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jackemoney","root","");
                state = con.createStatement();
            } catch(ClassNotFoundException err){
                
            } catch(SQLException err){
                
            }
            if(state == null){
                throw new IllegalArgumentException("statement is null");
            } else {
            }
		return state;
	}//end getStatement method
		
//getServiceProvider method
    public static String getServiceProvider(String customerNumbaer){
         String serviceProvider = null;
         if(customerNumbaer.startsWith("078") || customerNumbaer.startsWith("077")){
             serviceProvider = "mtn";
         }
         else if(customerNumbaer.startsWith("070") || customerNumbaer.startsWith("075")){
             serviceProvider = "warid";
         }
         else{
             return null;
         }
       return serviceProvider;
	}//end getServiceProvider method
	
//get user Id method
    public static int getUserId(String user) throws SQLException{
       int userId = 0;
        Statement state = getStatement();
        String query = "select userId from kiosk where userName='"+user+"'";
        ResultSet userIds = state.executeQuery(query);
        while(userIds.next()){
            userId = userIds.getInt("userId");
        }
       
       return userId;
     }//end getUserId method
	 
 //update status and service time
    public static void updateStatus(int requestId) throws SQLException{
        Statement state = getStatement();
        Timestamp newTime = new Timestamp(System.currentTimeMillis());
        String newStatus = "update request set status='serviced',dateAndTime='"+newTime+"' where requestId="+requestId;
        state.executeUpdate(newStatus);
    }//end updateStatus method
	 
//check the amount supplied for deposit or withdraw
    public static boolean acceptedAmount(int amount, String customerNumber){
        boolean accepted = false;
        //check warid amount
        if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
            if(amount <= 5000000){
                accepted = true;
            } else{
                accepted = false;
            }
        }
        //check Mtn amount
        else if(customerNumber.startsWith("078") || customerNumber.startsWith("077")){
            if(amount <= 4000000)
                accepted = true;
            else
                accepted = false;
        }
        else{
            //if number is neither mtn or Warid return false
            accepted = false;
        }
        return accepted;
    }//end acceptedAmount method
	
//verify withdraw
    public static boolean verifyWithdraw(int amount, String customerNumber, String user) throws SQLException{
        boolean isValid = false;
        Statement state = getStatement();
        //pick cash for warid and compare with the amount to be withdrawn
        if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
            String waridCashQuery = "select waridcash from kiosk where userName='"+user+"'";
            ResultSet waridCash = state.executeQuery(waridCashQuery);
            int wCash = 0;
            while(waridCash.next()){
                wCash = waridCash.getInt("waridcash");
            }
            if(amount <= wCash){
                isValid = true;
            }
            else{
                isValid = false;
            }
        }
        //pick cash for mtn and compare with th amount to be withdrawn
        else if(customerNumber.startsWith("078") || customerNumber.startsWith("077")){
            String mtnCashQuery = "select mtncash from kiosk where userName='"+user+"'";
            ResultSet mtnCash = state.executeQuery(mtnCashQuery);
            int mCash = 0;
            while(mtnCash.next()){
                mCash = mtnCash.getInt("mtncash");
            }
            if(amount <= mCash){
                isValid = true;
            }
            else{
                isValid = false;
            }
            
        }
        else{
            isValid = false;
        }
        return isValid;
    }//end verifyWithdraw
     
//getDepositCommission 
    public static int  getDepositCommission(int amount , String customerNumber){
        int commission = 0;
		//get MTN deposit Commission
        if(customerNumber.startsWith("078") || customerNumber.startsWith("077")){
            if((amount >= 500) &&(amount <= 5000))
                commission = 0;
            else if((amount >= 5001) && (amount <= 60000))
                commission = 285;
            else if((amount >= 60001) && (amount <= 125000))
                commission = 440;
            else if((amount >= 125001) && (amount <= 250000))
                commission = 520;
            else if((amount >= 250001) && (amount <= 500000))
                commission = 850;
            else if((amount >= 500001) && (amount <= 1000000))
                commission = 2500;
            else if((amount >= 1000001) && (amount <= 2000000))
                commission = 4500;
            else if((amount >= 2000001) && (amount <= 4000000))
                commission = 8000;
            else
                commission = 0;
		//get Warid deposit Commission
        } else if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
            if((amount >= 500) &&(amount <= 5000))
                commission = 150;
            else if((amount >= 5001) && (amount <= 60000))
                commission = 285;
            else if((amount >= 60001) && (amount <= 125000))
                commission = 440;
            else if((amount >= 125001) && (amount <= 250000))
                commission = 520;
            else if((amount >= 250001) && (amount <= 500000))
                commission = 850;
            else if((amount >= 500001) && (amount <= 1000000))
                commission = 2500;
            else if((amount >= 1000001) && (amount <= 2000000))
                commission = 4500;
            else if((amount >= 2000001) && (amount <= 3000000))
                commission = 8000;
            else if((amount >= 3000001) && (amount <= 4000000))
                commission = 8000;
            else if((amount >= 4001000) && (amount <= 5000000))
                commission = 9000;
            else
                commission = 0;

        } else {
			//do nothing for now
        }
        return commission;
    }//end getDepositCommission
	
//get withdraw commissions
    public static int getWithdrawCommission(int amount, String customerNumber){
        int commission=0;
        //get MTN withdraw commissions
        if(customerNumber.startsWith("077") ||customerNumber.startsWith("078")){
            if(amount>=500 && amount <=2500){
                commission=100;
            }
            else if(amount>=2501 && amount<=5000){
                commission=125;
            }
            else if(amount>=5001 && amount<=15000){
                commission=450;
            }
            else if(amount>=15001 && amount<=30000){
                commission=500;
            }
            else if(amount>=30001 && amount<=45000){
                commission=525;
            }
            else if(amount>=45001 && amount<=60000){
                commission=575;
            }
            else if(amount>=60001 && amount<=125000){
                commission=700;
            }
            else if(amount>=125001 && amount<=250000){
                commission=1300;
            }
            else if(amount>=250001 && amount<=500000){
                commission=2600;
            }
            else if(amount>=500001 && amount<=1000000){
                commission=5000;
            }
            else if(amount>=1000001 && amount<=2000000){
                commission=7500;
            }
            else if(amount>=2000001 && amount<=4000000){
                commission=12500;
            }
		}
        //get Warid withdraw commissions
        else if(customerNumber.startsWith("075") ||customerNumber.startsWith("070")){
            if(amount>=500 && amount <=2500){
                commission=100;
            }
            else if(amount>=2501 && amount<=5000){
                commission=125;
            }
            else if(amount>=5001 && amount<=15000){
                commission=450;
            }
            else if(amount>=15001 && amount<=30000){
                commission=500;
            }
            else if(amount>=30001 && amount<=45000){
                commission=525;
            }
            else if(amount>=45001 && amount<=60000){
                commission=575;
            }
            else if(amount>=60001 && amount<=125000){
                commission=700;
            }
            else if(amount>=125001 && amount<=250000){
                commission=1300;
            }
            else if(amount>=250001 && amount<=500000){
                commission=2600;
            }
            else if(amount>=500001 && amount<=1000000){
                commission=5000;
            }
            else if(amount>=1000001 && amount<=2000000){
                commission=7500;
            }
            else if(amount>=2000001 && amount<=4000000){
                commission=12500;
            }
            else if(amount>=4000001 && amount<=5000000){
                commission=15000;
            }
        }
        else{
            //do nothing for now
        }
        return commission;
    }// end getWithdrawCommission
	
//insert transaction to database
    public static void insertTransaction(int amount, String customerNumber, String agentNumber, int commission, String type, String user) throws Exception{
         Timestamp time = new Timestamp(System.currentTimeMillis());
         if(getServiceProvider(customerNumber) != null){
            String serviceProvider = getServiceProvider(customerNumber);
            Statement state = getStatement();  //get statement
            //create sql update
            String insert = "insert into transactions set kioskPhone='"+agentNumber+"',"
                    + "userName='"+user+"',"
                    + "customerNumber='"+customerNumber+"',"
                    + "type='"+type+"',"
                    + "commission="+commission+","
                    + "amount="+amount+","
                    + "serviceProvider='"+serviceProvider+"',"
                    + "dateAndTime='"+time+"'";
					
					state.executeUpdate(insert);
             
         }else{
             return;
         }  
     }//end insertTransaction method
	 
 //update the float and cash of a deposit
    public static void updateDepositFloatAndCash(int amount, String customerNumber, String user) throws SQLException {
        Statement state = getStatement();
        //udates for warid
        if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
            int availableWaridFloat = 0;
            int newWaridFloat = 0;
            int newWaridCash = 0;
            int availableWaridCash = 0;
            String waridQuery = "select waridfloat,waridcash from kiosk where userName='"+user+"'";
            ResultSet waridResults = state.executeQuery(waridQuery);
            while(waridResults.next()){
                availableWaridFloat = waridResults.getInt("waridfloat");
                availableWaridCash = waridResults.getInt("waridcash");
            }
            //make sure amount is less than available float before you update
            if(availableWaridFloat >= amount){
                newWaridFloat = availableWaridFloat - amount;
                String waridFloatUpdate = "update kiosk set waridfloat="+newWaridFloat+" where "
                        + "userName='"+user+"'";
                state.executeUpdate(waridFloatUpdate);
                
                //update the warid cash also
                newWaridCash = availableWaridCash + amount;
                String waridCashUpdate = "update kiosk set waridcash="+newWaridCash+" where "
                        + "userName='"+user+"'";
                state.executeUpdate(waridCashUpdate);
            }
            //leave the float and cash as it was
            else{
                return;
            } 
        }
        //update for mtn
        else if(customerNumber.startsWith("077") || customerNumber.startsWith("078")){
            int availableMtnFloat = 0;
            int newMtnFloat = 0;
            int availableMtnCash = 0;
            int newMtnCash = 0;
            String mtnQuery = "select mtnfloat,mtncash from kiosk where userName='"+user+"'";
            ResultSet mtnResults = state.executeQuery(mtnQuery);
            while(mtnResults.next()){
                availableMtnFloat = mtnResults.getInt("mtnfloat");
                availableMtnCash = mtnResults.getInt("mtncash");
            }
            //make sure amount is less than available float mtn float
            if(availableMtnFloat >= amount){
                newMtnFloat = availableMtnFloat - amount;
                String mtnFloatUpdate = "update kiosk set mtnfloat="+newMtnFloat+" where userName='"+user+"'";
                state.executeUpdate(mtnFloatUpdate);
                //update the mtn cash also
                newMtnCash = availableMtnCash + amount;
                String mtnCashUpdate = "update kiosk set mtncash="+newMtnCash+" where userName='"+user+"'";
                state.executeUpdate(mtnCashUpdate);
            } else{
                return;
            }
        } else{
            //do nothing for now
        }
    }//end updateDepositFloatAndCash
	
//update float and cash of a Withdraw
    public static void updateWithdrawFloatAndCash(int amount, String customerNumber, String user) throws SQLException{
        Statement state = getStatement();
        //udates for warid
        if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
            int availableWaridFloat = 0;
            int newWaridFloat = 0;
            int newWaridCash = 0;
            int availableWaridCash = 0;
            String waridQuery = "select waridfloat,waridcash from kiosk where userName='"+user+"'";
            ResultSet waridResults = state.executeQuery(waridQuery);
            while(waridResults.next()){
                availableWaridFloat = waridResults.getInt("waridfloat");
                availableWaridCash = waridResults.getInt("waridcash");
            }
            //make sure amount is less than available cash before you update
            if(availableWaridCash >= amount){
                newWaridFloat = availableWaridFloat + amount;
                String waridFloatUpdate = "update kiosk set waridfloat="+newWaridFloat+" where "
                        + "userName='"+user+"'";
                state.executeUpdate(waridFloatUpdate);
                
                //update the warid cash also
                newWaridCash = availableWaridCash - amount;
                String waridCashUpdate = "update kiosk set waridcash="+newWaridCash+" where "
                        + "userName='"+user+"'";
                state.executeUpdate(waridCashUpdate);
            }
            //leave the float and cash as it was
            else{
            } 
        }
        //update for mtn
        else if(customerNumber.startsWith("077") || customerNumber.startsWith("078")){
            int availableMtnFloat = 0;
            int newMtnFloat = 0;
            int availableMtnCash = 0;
            int newMtnCash = 0;
            String mtnQuery = "select mtnfloat,mtncash from kiosk where userName='"+user+"'";
            ResultSet mtnResults = state.executeQuery(mtnQuery);
            while(mtnResults.next()){
                availableMtnFloat = mtnResults.getInt("mtnfloat");
                availableMtnCash = mtnResults.getInt("mtncash");
            }
            //make sure amount is less than available float mtn float
            if(availableMtnCash >= amount){
                newMtnFloat = availableMtnFloat + amount;
                String mtnFloatUpdate = "update kiosk set mtnfloat="+newMtnFloat+" where userName='"+user+"'";
                state.executeUpdate(mtnFloatUpdate);
                //update the mtn cash also
                newMtnCash = availableMtnCash - amount;
                String mtnCashUpdate = "update kiosk set mtncash="+newMtnCash+" where userName='"+user+"'";
                state.executeUpdate(mtnCashUpdate);
            } else{
            }
        } else{
            //reject the withdraw since number is ivalid
        }  
    }//end updateWithdrawFloatAndCash method
	 
 //insert request to database
    public static void insertRequest(String customerNumber, int senderId, int receiverId, int amount, String status) throws SQLException {
	   Timestamp time = new Timestamp(System.currentTimeMillis());
	   //make sure theres a correct service provider either warid /airtel or mtn
	   if(getServiceProvider(customerNumber) != null){
		  Statement state = getStatement();
		  String serviceProvider = getServiceProvider(customerNumber);
		  String insert = "insert into request set customerNumber='"+customerNumber+"',"
				  + "amount="+amount+","
				  + "receiverUserId="+receiverId+","
				  + "senderUserId="+senderId+","
				  + "status='pending',"
				  + "dateAndTime='"+time+"',"
				  + "serviceProvider='"+serviceProvider+"'";

		  //state.executeUpdate(insert);
	   }
    }
		
}//end class trying out