package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class SignInController {
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button validateButton;
	@FXML
	private Button forgotButton;
	
	public void initialize() {
        validateButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.equals("admin") && password.equals("admin")) {
            	try {
            		 Parent root= FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
                     Scene scene = new Scene(root);
                     Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
                     stage.setScene(scene);
                     stage.show();
            	}catch(Exception e) {
        			e.printStackTrace();
            	}
            	  
            } else {
            	System.out.print("not working");
            }
        });
    }

}
