package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    
    private Parent Page ;

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
        	try {
				Page = FXMLLoader.load(getClass().getResource("/interfaces/inter6.fxml"));
				HomeContent.getChildren().removeAll();
				HomeContent.getChildren().setAll(Page);
    		} catch (IOException e1) {
				e1.printStackTrace();
			}
          
        }
    }
}