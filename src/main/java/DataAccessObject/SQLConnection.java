/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class SQLConnection {
    public static void main(String[] args) {
        SQLConnection.openConnection();
    }
    public static Connection openConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            String connectionUrl = "jdbc:sqlserver://localhost;database=test;";  
            String userName = "sa";
            String password = "18122001";
            return DriverManager.getConnection(connectionUrl, userName, password);
//            System.out.println("Connection is open");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
