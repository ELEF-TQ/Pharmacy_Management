package database;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DBConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "pharmacymanagement";

    public static Connection con = null;

    public static Connection connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USERNAME, PASSWORD);
            return con;
        } catch (Exception e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de connexion");
                alert.setContentText("Échec de la connexion à la base de données. Veuillez vérifier vos paramètres de connexion.");
                alert.showAndWait();
            });
            return null;
        }
    }
}
