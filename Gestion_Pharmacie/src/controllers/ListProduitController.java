package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mysql.jdbc.Statement;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Produit;

public class ListProduitController implements Initializable {
  
	public Connection con ;
	public Statement statement;
	public ResultSet resault;
	
	

    @FXML
    private Button DeleteProduct;
    @FXML
    private Button ModifProduct;
    
    
    @FXML
    private TableView<Produit> Table_Prd;
    @FXML
    private TableColumn<Produit,String> Code_Prd;
    @FXML
    private TableColumn<Produit,String> Nom_Prd;
    @FXML  
    private TableColumn<Produit,String> Category_Prd;
    @FXML
    private TableColumn<Produit,String> Forme_Prd;
    @FXML
    private TableColumn<Produit, Integer> Prix_Prd;
    @FXML
    private TableColumn<Produit,String> Fournisseur_Prd;
    @FXML
    private TableColumn<Produit,Date> DateFab_Prd ;
    @FXML
    private TableColumn<Produit,Date> DateExp_Prd ;

    @FXML private void On_ModifyProduct() {
    	
    }
@FXML private void On_DeleteProduct() {
    	
    }

   
    
    
    public ObservableList<Produit> data = FXCollections.observableArrayList() ;
    private void showProducts() {
    	 try {
	        	statement = (Statement) con.createStatement();
	            resault = statement.executeQuery("SELECT * FROM `products` WHERE 1");

	            while(resault.next()) {
	        	   data.add(new Produit(resault.getString("Code"),resault.getString("Name"),resault.getString("Category"),
	        			   resault.getString("Forme"),resault.getInt("Price"),
	        			   resault.getDate("DateFab"),resault.getDate("DateExp")));
	           }
	        }catch(SQLException e) {
	        	e.printStackTrace();
	        }
	        Code_Prd.setCellValueFactory(new PropertyValueFactory<Produit,String>("Code"));
	        Nom_Prd.setCellValueFactory(new PropertyValueFactory<Produit,String>("Name"));
	        Category_Prd.setCellValueFactory(new PropertyValueFactory<Produit,String>("Category"));
	        Forme_Prd.setCellValueFactory(new PropertyValueFactory<Produit,String>("Forme"));
	        Prix_Prd.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("Price"));
	        DateFab_Prd.setCellValueFactory(new PropertyValueFactory<Produit,Date>("DateFab"));
	        DateExp_Prd.setCellValueFactory(new PropertyValueFactory<Produit,Date>("DateExp"));
	        Table_Prd.setItems(data);	
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		   con = DBConnection.connect();
		
       	
		   showProducts();
		   
		   
    }
   
    
	
}
