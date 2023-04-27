package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class ModProduitController implements Initializable {
	
	public Connection con ;
	public Statement statement;
	public ResultSet resault;
	
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
    @FXML
    private TextField Code_Prd;
    @FXML
    private DatePicker DateExp_Prd;
    @FXML
    private DatePicker DateFab_Prd;
    @FXML
    private TextField Nom_Prd;
    @FXML
    private TextField Prix_Prd;


   

    @FXML
    void On_AnullerProduct() {

    }

   
	
	//____________ Show the list of products : 
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
	
	//____________ Add Product to data base : 
	@FXML private void On_AddProduct() {
		String Code = Code_Prd.getText();
		String Name = Nom_Prd.getText();
		String Category = CategoryBox.getValue();
		String Forme = FormeBox.getValue();
		int Price = Integer.parseInt(Prix_Prd.getText());
		LocalDate DateFab = DateFab_Prd.getValue();
		LocalDate DateExp = DateExp_Prd.getValue();
		
		String SQL = "INSERT INTO `products`(`Code`, `Name`, `Category`, `Forme`, `Price`, `DateFab`, `DateExp`) "
				+ "VALUES ([Code],[Name],[Category],[Forme],[Price],[DateFab],[DateExp])" ;
		
		try {
			statement = (Statement) con.prepareStatement(SQL);
			statement.execute(SQL);
			Alert alert = new Alert(AlertType.CONFIRMATION , "Produit ajouter avec succes") ;
			alert.showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING , "Produit Non ajouter") ;
			alert.showAndWait();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		con = DBConnection.connect();
		CategoryBox.setItems(FXCollections.observableArrayList("Spécialité médicament","Parapharmacerie","diététique"));
		FormeBox.setItems(FXCollections.observableArrayList("Injéctable","Dérmique","Inhalées","Réctales"));
	} 
	
	
	}
		
	
	 

	






	
	
  

