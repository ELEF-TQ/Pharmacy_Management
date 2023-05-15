package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




public class ModProduitController implements Initializable {
	
	//___________ Database Connection :
	public Connection con ;
	public Statement statement;
	public ResultSet resault;
	
	//___________ Function To Skip Spacing :
	public String escapeString(String input) {
	    return input.replace("'", "''");
	}
	
	//___________ Interface Controllers :
	@FXML
	private StackPane PageContent;
	@FXML 
	private ComboBox<String> CategoryBox ;
	@FXML 
	private ComboBox<String> FormeBox ;
	@FXML
    private ComboBox<String> Fournisseur_Prd;
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
    private TextField Qte_Prd ;


   
    //____________ Cancel Adding Product :
    @FXML
    void On_AnullerProduct() {
	    Code_Prd.setText("");
	    Nom_Prd.setText("");
	    Prix_Prd.setText("");
	    Qte_Prd.setText("");
	    CategoryBox.setPromptText("Catégorie");
	    FormeBox.setPromptText("Forme");
	    Fournisseur_Prd.setPromptText("Fournisseur");
	    DateFab_Prd.setValue(null);
	    DateExp_Prd.setValue(null);
    }


	//____________ Show the list of Products : 
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
	
	//____________ INSERT Product to data base : 
	@FXML private void On_AddProduct() {
		
		/*___ input values ___*/
		String code = escapeString(Code_Prd.getText());
		String name = escapeString(Nom_Prd.getText());
		String category = CategoryBox.getValue();
		String forme = FormeBox.getValue();
		int price = 0;
		int quantity = 0 ;
		LocalDate dateFab = DateFab_Prd.getValue();
		LocalDate dateExp = DateExp_Prd.getValue();

		/*___ check inputs validity ___*/
		if (code.isEmpty() || name.isEmpty() || category == null || forme == null || dateFab == null || dateExp == null) {
		    Alert alert = new Alert(AlertType.WARNING, "Veuillez remplir tous les champs.");
		    alert.showAndWait();
		    return;
		}
		try {
		    price = Integer.parseInt(Prix_Prd.getText());
		    quantity = Integer.parseInt(Qte_Prd.getText());
		} catch (NumberFormatException e) {
		    Alert alert = new Alert(AlertType.WARNING, "Veuillez entrer une valeur numérique pour le prix.");
		    alert.showAndWait();
		    return;
		}
		
        
		/*___ insert values to database  ___*/
		String insertSQL = "INSERT INTO `products` (`Code`, `Name`, `Category`, `Forme`, `Price`, `DateFab`, `DateExp`, `Quantity`) "
		        + "VALUES ('" + code + "', '" + name + "', '" + category + "', '" + forme + "', " + price + ", '"
		        + dateFab.toString() + "', '" + dateExp.toString() + "', '" + quantity  + "')";

		try {
			/*___ execute insertSQL ___*/
	        statement = (Statement) con.prepareStatement(insertSQL);
	        statement.execute(insertSQL);
	        Alert alert = new Alert(AlertType.INFORMATION, "Produit ajouté avec succès");
	        alert.showAndWait();

	        /*___ clear inputs ___*/
		    On_AnullerProduct();
           
		    /*___ error SQL ___*/
		} catch (SQLException e) {
		    e.printStackTrace();
		    Alert alert = new Alert(AlertType.WARNING, "Produit non ajouté.");
		    alert.showAndWait();
		}	
	}
	
	    //____________ Load Suppliers From DataBase : 
		public void loadSuppliers() {
		    ObservableList<String> supplierList = FXCollections.observableArrayList();
		    try {
		    	/*___ execute selectSQL ___*/
		    	statement = (Statement) con.createStatement();
	            resault = statement.executeQuery("SELECT * FROM `suppliers` WHERE 1");
		        while (resault.next()) {
		            String supplierName = resault.getString("Name");
		            supplierList.add(supplierName);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 

		    /*___ add elements to box ___*/ 
		    Fournisseur_Prd.setItems(supplierList);
		}
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		con = DBConnection.connect();
		loadSuppliers();
		CategoryBox.setItems(FXCollections.observableArrayList("Spécialité médicament","Parapharmacerie","diététique"));
		FormeBox.setItems(FXCollections.observableArrayList("Injéctable","Dérmique","Inhalées","Réctales"));
	} 
	
	
}
		
	
	 

	






	
	
  

