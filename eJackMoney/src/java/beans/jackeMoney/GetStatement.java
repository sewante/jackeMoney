/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans.jackeMoney;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MWESIGYE
 */
public class GetStatement {
    PrintWriter out = null;
    public Statement getStatement(){
            Statement statement = null;
        
            try{
                Class.forName("com.myql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/jackemoney","root","");
                statement = (Statement) con.createStatement();
            }catch(SQLException err){
                out.print("Error: Failed to connect to database"+err.getMessage());
            }catch(ClassNotFoundException er){
               out.print("Error: Driver not found");
            }
            if(statement == null){
                throw new IllegalArgumentException("statement is null");
            }
            return statement;
        }
}
