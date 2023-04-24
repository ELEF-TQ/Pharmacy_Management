package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class ModProduitController implements Initializable {
	@FXML
	private Button AddProduct;
	@FXML
	private Button showProduct;
	@FXML
	private Button AnullerProduct;
	@FXML
	private StackPane PageContent;
	@FXML 
	private ComboBox<String> CategoryBox ;
	@FXML 
	private ComboBox<String> FormeBox ;
	
	
	
	@FXML private void On_ShowProduct() {
         	try {
         		 Parent root= FXMLLoader.load(getClass().getResource("/interfaces/ListProduit.fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
         	}catch(Exception e) {
     			e.printStackTrace();
         	} 
      }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CategoryBox.setItems(FXCollections.observableArrayList("Spécialité médicament","Parapharmacerie","diététique"));
		FormeBox.setItems(FXCollections.observableArrayList("Injéctable","Dérmique","Inhalées","Réctales"));
	} 
	
	
	}
		
	
	 

	






	
	
  

