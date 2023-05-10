package controllers;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Client;
import models.Produit;

public class ListVenteController implements Initializable  {
	
	//____________________ Database Connection :
	public Connection con ;
	public java.sql.Statement statement;
	public ResultSet resault;

	//____________________ Table controllers :
	@FXML
    private TableView<Produit> Table_Sales;
    @FXML
    private TableColumn<Client, String> Client_CNI;
    @FXML
    private TableColumn<Client, String> Client_Name;
    @FXML
    private TableColumn<?, ?> Date_Sale;
    @FXML
    private TableColumn<?, ?> Total_Price;

    
    //____________________ Print Total Sales :
    @FXML
    void On_PrintTotalSales(ActionEvent event) {

    }
    
    
    //____________________ Load sales from data base :
    public ObservableList<Produit> data = FXCollections.observableArrayList();
    private void showSales() {
    	/*___ clear table to avoid repetition ___*/
    	Table_Sales.getItems().clear();
       
        /*___ select sales ___*/
            
        
        /*___ fill table ___*/
        
        
        Table_Sales.setItems(data);
    }
    
    
    
    
    
    
    
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		   con = DBConnection.connect();
		   showSales();   
    }

}