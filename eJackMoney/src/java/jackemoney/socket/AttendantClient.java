/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackemoney.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
/**
 *
 * @author MWESIGYE
 */
public class AttendantClient {
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

                        //send the transaction to the server
                        messanger.writeUTF(transaction);

                   }else{
                         System.out.println(" No transaction submitted"); 
                   }//end if else

                }//end while

            }catch(Exception err){
                    System.out.print(" Failed to connect to the Server Transaction not submitted "+err);
            }
            finally{
                    messanger.flush();  //empty the stream before closure of the connection socket
               messanger.close();   //close the writter
               clientSocket.close();    //close socket
            }
            
    }//end main
}//end class