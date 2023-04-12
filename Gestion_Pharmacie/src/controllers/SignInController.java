package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

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
                System.out.print("working");
            } else {
            	System.out.print("not working");
            }
        });
    }

}
