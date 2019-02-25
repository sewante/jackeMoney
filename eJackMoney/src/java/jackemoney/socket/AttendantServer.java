/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackemoney.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author MWESIGYE
 */
public class AttendantServer {
    
    public static void main(String[] args){
        
        try{
            ServerSocket port =new ServerSocket(6666);//create Server port
            Socket socket = port.accept();  //establish connection
            DataInputStream input = new DataInputStream(socket.getInputStream());   //reader
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());   //writter
            
            String message = input.readUTF();       //read a string from the client
            //output.writeUTF(transaction);
            
            //interprete the commands
            if(message.startsWith("commit")){
               message = message.substring("commit ".length());    //remove commit from the submitted command
               //store each transaction as a separate element in an array of strings
               String[] transactions = message.split(",");
               for (String t: transactions) {
                    //get the parameters fot the transaction
                    String[] params = t.split(" ");

                    if (params[0].equals("deposit")) {
                        try {
                                if(params.length == 4){
                                    String customerNumber = params[1];
                                    int amount = Integer.parseInt(params[2]);
                                    String agentNumber = params[3];

                                    output.writeUTF("Deposit succesfully recorded.");
                                } else {
                                        output.writeUTF("Invalid parameters supplied for deposit.");
                                    }
                            }
                            catch (NumberFormatException e) {
                                    output.writeUTF("Invalid amount supplied for deposit.");
                            }
                    }
                    else if(params[0].equals("withdraw")) {
                            // Do something for withdraw.
                            try {
                                if(params.length == 4){
                                    String customerNumber = params[1];
                                    int amount = Integer.parseInt(params[2]);
                                    String agentNumber = params[3];

                                    output.writeUTF("Withdraw succesfully recorded.");
                                } else {
                                        output.writeUTF("Invalid parameters supplied for witdraw.");
                                    }
                            }
                            catch (NumberFormatException e) {
                                    output.writeUTF("Invalid amount supplied for witdraw.");
                            }
                    }
                    else {
                            // Show an error message.
                            output.writeUTF(" neither withdraw nor deposit. Invalid values supplied ");
                }

            }
               
            
            }
            
        }catch(Exception err){
            System.out.println("connection problem "+err);
            
        }
    
    }   //end main method
    
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
        }
     public static void insertTransaction(int amount, String customerNumber, String agentNumber, int commission, String type){
         Timestamp time = new Timestamp(System.currentTimeMillis());
         //String username = 
         if(getServiceProvider(customerNumber) != null){
            String serviceProvider = getServiceProvider(customerNumber);
            Statement state = getStatement();  //get statement
            //create sql update
            String insert = "insert into transactions set kioskPhone='"+agentNumber+"',"
                    + "userName='musisi',"
                    + "customerNumber='"+customerNumber+"',"
                    + "type='"+type+"',"
                    + "commission="+commission+","
                    + "amount="+amount+","
                    + "serviceProvider='"+serviceProvider+"',"
                    + "dateAndTime="+time+"";
             
         }else{
             return;
         }  
     }//end insertToDatabase method
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
     
     public static int  getDepositCommission(int amount , String customerNumber){
        int commission = 0;
        //get MTN deposit commission
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
        //get Warid deposit commission
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
    }
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
    }
     //the view float command
     public static void viewFloat(String message){
         if(message.startsWith("view ")){
             String[] params = message.split(" ");
             if(params.length == 4){
                 String serviceProvider = params[3];    //pick the service provider from the command
                 
                 Statement state = getStatement();  //get statement
                 if(serviceProvider.equals("warid")){
                     try{
                         //select the float,userName from the database to compare who has more float
                        String query = "select userName,waridfloat from kiosk";
                        ResultSet floats = state.executeQuery(query);
                        ///output.writeUTF(" Warid Float");
                        //output.writeUTF(" User Name:       Warid Float");
                        while(floats.next()){
                           // output.writeUTF(" "+floats.getString("userName").toString()+"            "+floats.getString("waridfloat").toString());
                        }
                     
                     } catch(SQLException err){
                         //output.wirteUTF(" ERROR: failed to get the floats")
                     }
                     
                 } else if(serviceProvider.equals("mtn")){
                     
                 } else{
                     //output.writeUTF(" ERROR: unsupported service provider "+serviceProvider);
                 }
                 
                 
             } else{
                 //handle the error from here
                 //output.writeUTF(" ERROR: Unsupported Command...so many arguments for view float command ");
             }
         }
     }
     
     public static int getUserId(String user) throws SQLException{
       int userId = 0;
        Statement state = getStatement();
        String query = "select userId from kiosk where userName='"+user+"'";
        ResultSet userIds = state.executeQuery(query);
        while(userIds.next()){
            userId = userIds.getInt("userId");
        }
       
       return userId;
     }
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

                  state.executeUpdate(insert);
               }
    }
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
    }//end updateDepositFloatAndCash method
    
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
    
    //update status and service time
    public static void updateStatus(int requestId) throws SQLException{
        Statement state = getStatement();
        Timestamp newTime = new Timestamp(System.currentTimeMillis());
        String newStatus = "update request set status='serviced',dateAndTime='"+newTime+"' where requestId="+requestId;
        state.executeUpdate(newStatus);
    }//end updateStatus method
    
    //update the Commissions
    public static void updateCommissions(int amount, String type, String customerNumber, String user) throws SQLException {
        Statement state = getStatement();   //get the statement to execute the sql querries
        if(type.equals("deposit")){
            //check for warid deposit
            if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
                int availableWaridCommission = 0;                                                //current warid commission in the database
                int newWaridCommission = 0;                                                      //will hold the new warid commission
                int waridCommissionIncrement = getDepositCommission(amount, customerNumber);    //get the deposit commission
                String waridCommissionQuery = "select waridcommission from kiosk where userName='"+user+"'";
                ResultSet waridCommision = state.executeQuery(waridCommissionQuery);
                if(waridCommision.next()){  //is there a warid commission from our selection
                    availableWaridCommission = waridCommision.getInt("waridcommission");        //get the available warid commission
                    newWaridCommission = availableWaridCommission + waridCommissionIncrement;   //add the increment to the existing warid commission
                    //update the warid commission
                    String updateWaridCommisionQuery = "update kiosk set waridcommission="+newWaridCommission+" where userName='"+user+"'"; 
                    state.executeUpdate(updateWaridCommisionQuery);                            //update the commission for warid 
                }  
            }
            //do samefor mtn deposit
            else if(customerNumber.startsWith("078") || customerNumber.startsWith("077")){
                int availableMtnCommission = 0;                                               //store current mtn commission from the database
                int newMtnCommission = 0;                                                     //will hold the new mtn commission
                int mtnCommissionIncrement = getDepositCommission(amount, customerNumber);    // get the increment in mtn commission 
                String mtnCommissionQuery = "select mtncommission from kiosk where userName='"+user+"'";
                ResultSet mtnCommision = state.executeQuery(mtnCommissionQuery);
                if(mtnCommision.next()){
                    availableMtnCommission = mtnCommision.getInt("mtncommission");           //get the available mtn commission
                    newMtnCommission = availableMtnCommission + mtnCommissionIncrement;      //add the increment to the exsting mtn commission
                    //update the mtn commission
                    String updateMtnCommissionQuery = "update kiosk set mtncommission="+newMtnCommission+" where userName='"+user+"'";
                    state.executeUpdate(updateMtnCommissionQuery);  
                }
            }
            else{
                //leave the commission as it was and return
                //do nothing for now
            }
        } else if(type.equals("withdraw")){
            //do for warid withdraw
            if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
                int availableWaridCommission = 0;                                                //current warid commission in the database
                int newWaridCommission = 0;                                                      //will hold the new warid commission
                int waridCommissionIncrement = getWithdrawCommission(amount, customerNumber);    //get the deposit commission
                String waridCommissionQuery = "select waridcommission from kiosk where userName='"+user+"'";
                ResultSet waridCommision = state.executeQuery(waridCommissionQuery);
                if(waridCommision.next()){  //is there a warid commission from our selection
                    availableWaridCommission = waridCommision.getInt("waridcommission");        //get the available warid commission
                    newWaridCommission = availableWaridCommission + waridCommissionIncrement; //add the increment to the existing warid commission
                    //update the warid commission
                    String updateWaridCommisionQuery = "update kiosk set waridcommission="+newWaridCommission+" where userName='"+user+"'"; 
                    state.executeUpdate(updateWaridCommisionQuery);                                 //update the commission for warid 
                }  
            }
            //do same for mtn withdraw
            else if(customerNumber.startsWith("078") || customerNumber.startsWith("077")){
                int availableMtnCommission = 0;                                             //store current mtn commission from the database
                int newMtnCommission = 0;                                                   //will hold the new mtn commission
                int mtnCommissionIncrement = getWithdrawCommission(amount, customerNumber);  // get the increment in mtn commission 
                String mtnCommissionQuery = "select mtncommission from kiosk where userName='"+user+"'";
                ResultSet mtnCommision = state.executeQuery(mtnCommissionQuery);
                if(mtnCommision.next()){
                    availableMtnCommission = mtnCommision.getInt("mtncommission");          //get the available mtn commission
                    newMtnCommission = availableMtnCommission + mtnCommissionIncrement;     //add the increment to the exsting mtn commission
                    //update the mtn commission
                    String updateMtnCommissionQuery = "update kiosk set mtncommission="+newMtnCommission+" where userName='"+user+"'";
                    state.executeUpdate(updateMtnCommissionQuery);  
                }
            }
            else{
                // do nothing for now...leave the commission as it was and return 
            }
        }
        else{
            //do nothing for now
        }  
    }//end updateCommissions method
     
}//end class
/*
//handle a single deposit
else if (message.startsWith("deposit")) {
    try {
    String[] deposit = message.split(" ");
    if(deposit.length == 4){
            String customerNumber = deposit[1];
            int amount = Integer.parseInt(deposit[2]);
            String agentNumber = deposit[3];
            //insert to database
            // ensure correct number and amount are supplied and insert to the database
            int commission = getDepositCommission(amount,customerNumber);									//get the commission
            if(customerNumber.startsWith("070") || customerNumber.startsWith("075")){
                if(acceptedAmount(amount, customerNumber) == true){
                        insertTransaction(amount, customerNumber, agentNumber,commission,"deposit",userName);	//record the transaction into database
                        updateDepositFloatAndCash(amount, customerNumber, userName);							//update float and cash
                        System.out.println(" Warid Deposit successful..");										//status at the server
                        reply = " Warid Deposit succesfully recorded..";										//inform attendant
                }
                else{
                        reply = "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system";
                }

            } 		
            else if(customerNumber.startsWith("077") || customerNumber.startsWith("078")){
                if(acceptedAmount(amount, customerNumber) == true){											//is amount acceptable
                        insertTransaction(amount, customerNumber, agentNumber,commission,"deposit",userName);	//record the transaction into database
                        updateDepositFloatAndCash(amount, customerNumber, userName);							//update float and cash
                        System.out.println(" MTN Deposit successful..");										//status at the server
                        reply = " MTN Deposit succesfully recorded..";											//inform attendant
                }
                else{
                        reply = "\n ERROR: Amount, "+String.valueOf(amount)+", is too much..Not supported by the system";
                }


            }else {
                    reply = " ERROR: invalid customer Number,"+customerNumber+", enter a valid customer number..";
            }//end ensure
    } else {
            reply = " ERROR: Invalid parameters supplied for deposit.";
    }
    }
    catch (NumberFormatException e) {
            reply = " ERROR: Invalid amount supplied for deposit.";
    }
}

*/