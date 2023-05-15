package database;

import java.sql.Connection;
import java.sql.DriverManager;

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
				return con;
			} catch (Exception e) {
				System.out.println("erreur de connection a la base de données");
				e.printStackTrace();
				return null ;
			}
    };
    
    

    
}