package controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      

    }


    public void handleClicks(ActionEvent actionEvent) {
    	 if (actionEvent.getSource() == btnOverview) {
         	try {
 				Page = FXMLLoader.load(getClass().getResource("/interfaces/inter1.fxml"));
 				HomeContent.getChildren().removeAll();
 				HomeContent.getChildren().setAll(Page);
     		} catch (IOException e1) {
 				e1.printStackTrace();
 			}
         }
    	 
         if(actionEvent.getSource()==btnOrders)
         {
         	try {
 				Page = FXMLLoader.load(getClass().getResource("/interfaces/inter2.fxml"));
 				HomeContent.getChildren().removeAll();
 				HomeContent.getChildren().setAll(Page);
     		} catch (IOException e1) {
 				e1.printStackTrace();
 			}
           
         }
         
        if (actionEvent.getSource() == btnCustomers) {
        	try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/inter3.fxml"));
				HomeContent.getChildren().removeAll();
				HomeContent.getChildren().setAll(Page);
    		} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        if (actionEvent.getSource() == btnMenus) {
        	try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/inter4.fxml"));
				HomeContent.getChildren().removeAll();
				HomeContent.getChildren().setAll(Page);
    		} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
       
        if(actionEvent.getSource()==btnSettings)
        {
        	try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/inter5.fxml"));
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
}