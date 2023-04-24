package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {

	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button validateButton;
	@FXML
	private Button forgotButton;
	
	@FXML private void onSignIn() {
		 String username = usernameField.getText();
         String password = passwordField.getText();
         if (username.equals("admin") && password.equals("admin")) {
         	try {
         		 Parent root= FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
         	}catch(Exception e) {
     			e.printStackTrace();
         	}
         	  
         } else {
         	System.out.print("not working");
         }
	}

    
}

// in case of test login :

/*
import database.DBConnection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
*/

/* @FXML
  private void onSignIn() {
      if (!usernameField.getText().matches("[a-zA-Z0-9_]{4,}")) {
          return;
      }
      if (passwordField.getText().isEmpty()) {
          return;
      }
      int status = DBConnection.checkLogin(usernameField.getText().trim().toLowerCase(), passwordField.getText());

      switch (status) {
          case 0: {
              Stage stage = (Stage) usernameField.getScene().getWindow();

              Parent root = null;
              try {
                  root = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
              } catch (IOException ex) {
                  Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
              }
              stage.setScene(new Scene(root));
          }
          break;
          case -1:
              JOptionPane.showMessageDialog(null, "Connection Failed");
              break;
          case 1:
              JOptionPane.showMessageDialog(null, "Username or password wrong");
              break;
      }
  }*/