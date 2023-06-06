package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController {
	
	//____________________ Database Connection :
	public Connection con ;
	public java.sql.Statement statement;
	public ResultSet resault;
	
	//___________ Interface Controllers :
    @FXML
    private PasswordField confirmpassword;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    //___________ Sign Up :
    @FXML void On_SignUp(ActionEvent event) {
    	 String enteredUsername = username.getText();
    	    String enteredPassword = password.getText();
    	    String enteredEmail = email.getText();
    	    String confirmPassword = confirmpassword.getText();

    	    if (enteredUsername.isEmpty() || enteredPassword.isEmpty() || enteredEmail.isEmpty() || confirmPassword.isEmpty()) {
    	        showAlert(AlertType.ERROR, "champs vides", "Veuillez remplir tous les champs obligatoires.");
    	        return;
    	    }
    	    if (!enteredPassword.equals(confirmPassword)) {
    	        showAlert(AlertType.ERROR, "Mot de passe incorrect", "Les mots de passe saisis ne correspondent pas.");
    	        return;
    	    }
    	    try {
    	        con = DBConnection.connect();
    	        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
    	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
    	        statement.setString(1, enteredUsername);
    	        statement.setString(2, enteredPassword);
    	        statement.setString(3, enteredEmail);

    	        int rowsAffected = statement.executeUpdate();

    	        if (rowsAffected > 0) {
    	            showAlert(AlertType.INFORMATION, "Inscription réussie", "Votre compte a été créé avec succès.");
    	            username.clear();
    	            password.clear();
    	            email.clear();
    	            confirmpassword.clear();
    	        } else {
    	            showAlert(AlertType.ERROR, "échec de l'enregistrement", "Impossible de créer un compte. Veuillez réessayer.");
    	        }

    	        statement.close();
    	    } catch (SQLException e) {
    	    	 Alert alert = new Alert(AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText("erreur de connextion");
                 alert.setContentText("Échec de la connexion à la base de données. Veuillez vérifier vos paramètres de connexion.");
                 alert.showAndWait();
    	    }
    }
    
    //___________ Alert Showing :
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
  

}
