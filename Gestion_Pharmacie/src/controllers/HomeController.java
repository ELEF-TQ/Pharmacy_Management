package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable  {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnProduit;

    @FXML
    private Button btnFournisseur;


    @FXML
    private Button btnVente;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane HomeContent;
    
    @FXML
    private AnchorPane Application;

    
    private Parent Page ;
    private Stage stage ;



    public void handleClicks(ActionEvent actionEvent) {
    	 if (actionEvent.getSource() == btnOverview) {
         	try {
 				Page = FXMLLoader.load(getClass().getResource("/interfaces/Acceuill.fxml"));
 				HomeContent.getChildren().removeAll();
 				HomeContent.getChildren().setAll(Page);
     		} catch (IOException e1) {
 				e1.printStackTrace();
 			}
         }
    	 
         if(actionEvent.getSource()==btnProduit)
         {
         	try {
 				Page = FXMLLoader.load(getClass().getResource("/interfaces/ModProduit.fxml"));
 				HomeContent.getChildren().removeAll();
 				HomeContent.getChildren().setAll(Page);
     		} catch (IOException e1) {
 				e1.printStackTrace();
 			}
           
         }
         
        if (actionEvent.getSource() == btnFournisseur) {
        	try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/ModFournisseur.fxml"));
				HomeContent.getChildren().removeAll();
				HomeContent.getChildren().setAll(Page);
    		} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        if (actionEvent.getSource() == btnVente) {
        	try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/ModVente.fxml"));
				HomeContent.getChildren().removeAll();
				HomeContent.getChildren().setAll(Page);
    		} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
       
        if(actionEvent.getSource()==btnSettings)
        {
        	try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/ModSettings.fxml"));
				HomeContent.getChildren().removeAll();
				HomeContent.getChildren().setAll(Page);
    		} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        if(actionEvent.getSource()==btnSignout)
        {
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("SignOut");
        	alert.setHeaderText("you're about to logout !");
        	//alert.setContentText("Do you want to save before")
           if(alert.showAndWait().get()== ButtonType.OK) {
        	   stage = (Stage) Application.getScene().getWindow();
        	   stage.close();
           }
        }
       
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/Acceuill.fxml"));
				HomeContent.getChildren().removeAll();
				HomeContent.getChildren().setAll(Page);
 		} catch (IOException e1) {
				e1.printStackTrace();
			}
		
	}
}