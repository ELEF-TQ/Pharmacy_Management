package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ModVenteController {
	@FXML
	private StackPane PageContent;
	@FXML
	private Button showVente;

	@FXML private void On_ShowVente() {
     	try {
     		 Parent root= FXMLLoader.load(getClass().getResource("/interfaces/ListVente.fxml"));
              Scene scene = new Scene(root);
              Stage stage = new Stage();
              stage.setScene(scene);
              stage.show();
     	}catch(Exception e) {
 			e.printStackTrace();
     	} 
  }
}
