package controllers;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.Statement;
import database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignInController implements Initializable {
	
	//___________ Database Connection :
		public Connection con ;
		public Statement statement;
		public ResultSet resault;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
	void On_SignIn() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		if (username.isEmpty() || password.isEmpty()) {
			showAlert(AlertType.ERROR, "Empty Fields", "Please enter both username and password.");
			return;
		}
		try {
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			resault = statement.executeQuery();

			if (resault.next()) {
				Stage stage = (Stage) usernameField.getScene().getWindow();
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				stage.setScene(new Scene(root));
				stage.centerOnScreen();
				
				
			} else {
				showAlert(AlertType.ERROR, "Invalid Credentials", "Please enter valid username and password.");
			}

			resault.close();
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


	@FXML
	void On_forgotPass() {
	    TextInputDialog dialog = new TextInputDialog();
	    dialog.setTitle("Forgot Password");
	    dialog.setHeaderText("Enter your email address");
	    dialog.setContentText("Email:");

	    Optional<String> result = dialog.showAndWait();
	    if (result.isPresent()) {
	        String emailAddress = result.get();

	        try {
	            String query = "SELECT * FROM users WHERE email = ?";
	            PreparedStatement statement = con.prepareStatement(query);
	            statement.setString(1, emailAddress);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                String username = resultSet.getString("username");
	                String password = resultSet.getString("password");

	              //  sendEmail(emailAddress, username, password);

	                showAlert(AlertType.INFORMATION, "Email Sent", "Username and password have been sent to your email address.");
	            } else {
	                showAlert(AlertType.ERROR, "Incorrect Email", "The email address you entered is not registered.");
	            }

	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // TODO: Handle database error
	        }
	    }
	}
	
/*
	// Method to send an email
	private void sendEmail(String emailAddress, String username, String password) {
	    // Sender's email address and password
	    String senderEmail = "your_email@example.com";
	    String senderPassword = "your_email_password";
	    // SMTP server properties
	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.example.com");
	    properties.put("mail.smtp.port", "587");

	    // Create a session with authentication
	    Session session = Session.getInstance(properties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(senderEmail, senderPassword);
	        }
	    });

	    try {
	        // Create a new email message
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(senderEmail));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
	        message.setSubject("Forgot Password");
	        message.setText("Dear user,\n\nYour username: " + username + "\nYour password: " + password);

	        // Send the email
	        Transport.send(message);

	        System.out.println("Email sent successfully");
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        // TODO: Handle email sending failure
	    }
	}

*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		con = DBConnection.connect();
		
	}

}