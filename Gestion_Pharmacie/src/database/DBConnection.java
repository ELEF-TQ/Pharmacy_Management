package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "pharmacymanagement";
    
    public static Connection con = null ;
    public static Connection connect() {
            try {
				con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USERNAME, PASSWORD);
				System.out.print("connection worked");
				return con;
			} catch (Exception e) {
				System.out.print("connection Failed");
				e.printStackTrace();
				return null ;
			}
			
            
       
    };
    
    
    
   
    // checkLogIn :
    public static int checkLogin(String username, String password) {
        Connection con = DBConnection.connect();
        if(con == null)
            return -1;
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, username);
            prest.setString(2, password);
            
            ResultSet rs = prest.executeQuery();
            
            while(rs.next()) {
                return 0;
            }
        } catch(SQLException se) {
            System.out.println("SQL Error !");
        }
        
        return 1;
    }
    
}