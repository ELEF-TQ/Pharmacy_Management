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
		

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void On_SignUp(ActionEvent event) {
    	 String enteredUsername = username.getText();
    	    String enteredPassword = password.getText();
    	    String enteredEmail = email.getText();
    	    String confirmPassword = confirmpassword.getText();

    	    if (enteredUsername.isEmpty() || enteredPassword.isEmpty() || enteredEmail.isEmpty() || confirmPassword.isEmpty()) {
    	        showAlert(AlertType.ERROR, "Empty Fields", "Please fill in all the required fields.");
    	        return;
    	    }

    	    if (!enteredPassword.equals(confirmPassword)) {
    	        showAlert(AlertType.ERROR, "Password Mismatch", "The entered passwords do not match.");
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
    	            showAlert(AlertType.INFORMATION, "Registration Successful", "Your account has been created successfully.");
    	            username.clear();
    	            password.clear();
    	            email.clear();
    	            confirmpassword.clear();
    	        } else {
    	            showAlert(AlertType.ERROR, "Registration Failed", "Failed to create an account. Please try again.");
    	        }

    	        statement.close();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        // TODO: Handle database error
    	    }

    }
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
  

}
