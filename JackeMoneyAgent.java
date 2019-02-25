import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
/**
 *
 * @author MWESIGYE
 */
  /*
								GROUP MEMBERS
								=============
			______________________________________________________
			* MWESIGYE  ROBERT		    |  15 / U / 771           |
			* NANSUBUGA JOYCE   EUZEBIA |  15 / U / 10807 / EVE   |
			* NAKAALI   PHIONA			| 15 / U / 9286 / PS      |
			* BWAYO     JUDE      		|  15 / U / 4718 / EVE    |
			____________________________|_________________________|
 */
 
public class JackeMoneyAgent {
    public static void main(String[] args) throws IOException{
        //create  Scanner object to get user input
        Scanner input = new Scanner(System.in);
        String transaction = null;
        DataOutputStream messanger = null;
		DataInputStream responder = null;
		Socket clientSocket = null;
            
				try{
					//create connection socket to the server listening on port 6666 and iput and out put streams
					clientSocket = new Socket("localhost",6666);
					 messanger = new DataOutputStream(clientSocket.getOutputStream());	//set up I/O
					 responder = new DataInputStream(clientSocket.getInputStream());
				   
				   //loop back to send more data
				   while(true){							
						//get response from the server
						String response = responder.readUTF();
						System.out.print(response);
						
						transaction = input.nextLine();
						if(transaction != null){
							 if(transaction.equals("logout")){
								 System.out.println(" bye.....");
								 messanger.flush();	//empty the stream before closure of the connection socket
								 messanger.close();   //close the writter
								 clientSocket.close();    //close socket
								 System.exit(0);
							 }
							 else{
								//send the transaction to the server
								messanger.writeUTF(transaction);
							 }
							
					   } else {
						 System.out.println(" No transaction submitted"); 
					   }//end if else
					   
					}//end while
				}catch(Exception err){
					System.out.print(" Failed to connect to the Server Transaction not submitted "+err);
				}
				finally{
					messanger.flush();	//empty the stream before closure of the connection socket
					messanger.close();   //close the writter
					clientSocket.close();    //close socket
				}
            
    }//end main
}//end class JackeMoneyAgent