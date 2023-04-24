package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.layout.StackPane;

public class ModFournisseurController implements Initializable{
	@FXML
	private StackPane PageContent;
	@FXML
	private ComboBox<String> PaymentBox;
	@FXML
	private Button AddFournissueur;
	@FXML
	private Button AnullerFournisseur;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		PaymentBox.setItems(FXCollections.observableArrayList("Bank","Cash"));
		
	}

}
