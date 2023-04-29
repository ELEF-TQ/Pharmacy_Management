package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import database.DBConnection;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import models.Fournisseur;

public class ModFournisseurController implements Initializable{
	
		public Connection con ;
		public Statement statement;
		public ResultSet resault;
		
		// Function to escape special characters in strings
		public String escapeString(String input) {
		    return input.replace("'", "''");
		}
	
		@FXML
	    private StackPane PageContent;
		
	    @FXML
	    private Button AddFournissueur;
	    @FXML
	    private Button AnullerFournisseur;

	    @FXML
	    private TextField Email_Frn;
	    @FXML
	    private TextField Nom_Frn;
	    @FXML
	    private TextField Tel_Frn;
	    @FXML
	    private ComboBox<String> PaymentBox_Frn;
	    @FXML
	    private TextField Rib_Frn;

	    @FXML
	    private TableView<Fournisseur> Table_FRN;
	    @FXML
	    private TableColumn<Fournisseur,String> Email_FRN;
	    @FXML
	    private TableColumn<Fournisseur,String> Tel_FRN;
	    @FXML
	    private TableColumn<Fournisseur,String> Payment_FRN;
	    @FXML
	    private TableColumn<Fournisseur,String> Nom_FRN;

	    
	    
	  //____________ Cancel Adding Supplier : 
	    @FXML
	    void On_AnullerFournisseur() {
	    	// Clear the input fields
	    	Nom_Frn.setText("");
	    	Email_Frn.setText("");
	    	Tel_Frn.setText("");
	    	Rib_Frn.setText("");
	    	PaymentBox_Frn.setValue("Payment Par Bank");
	    }
	    
	  //____________ Add Supplier to data base : 
		@FXML private void On_AddFournissueur() {
			 String name = escapeString(Nom_Frn.getText());
			    String email = escapeString(Email_Frn.getText());
			    String phone = escapeString(Tel_Frn.getText());
			    String payment = PaymentBox_Frn.getValue();
			    String rib = Rib_Frn.getText();

			    if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || payment == null) {
			        Alert alert = new Alert(AlertType.ERROR, "Tous les champs doivent être remplis");
			        alert.showAndWait();
			        return;
			    }

			    if (payment.equals("Payment Par Bank")) {
			        if (rib.isEmpty()) {
			            Alert alert = new Alert(AlertType.ERROR, "Le champ RIB doit être rempli");
			            alert.showAndWait();
			            return;
			        }
			    } else if (payment.equals("Payment Par Cash")) {
			        rib = "Payment Par Cash";
			    }

			    String SQL = "INSERT INTO `suppliers`(`Name`, `Phone`, `Email`, `Payment`, `RIB`) "
			            + "VALUES ('" + name + "', '" + phone + "', '" + email + "', '" + payment + "', '" + rib + "')";

			    try {
			        statement = (Statement) con.prepareStatement(SQL);
			        statement.execute(SQL);
			        Alert alert = new Alert(AlertType.CONFIRMATION, "Fournisseur ajouté avec succès");
			        alert.showAndWait();

			        // Clear the input fields
			        On_AnullerFournisseur();
			        // Show Suppliers
			        showSuppliers();

			    } catch (SQLException e) {
			        e.printStackTrace();
			        Alert alert = new Alert(AlertType.WARNING, "Fournisseur non ajouté");
			        alert.showAndWait();
			    }
			
		}
		
		
		//____________ Show Suppliers in the table : 
		public ObservableList<Fournisseur> data = FXCollections.observableArrayList() ;
	    private void showSuppliers() {
	    	Table_FRN.getItems().clear();
	    	 try {
		        	statement = (Statement) con.createStatement();
		        	
		        	if(statement != null) {
		        		System.out.println("statetment worked");
		        	}else {
		        		System.out.println("statement error");
		        	}
		        	
		            resault = statement.executeQuery("SELECT * FROM `suppliers` WHERE 1");
		           
		            if(resault != null) {
		        		System.out.println("resault worked");
		        	}else {
		        		System.out.println("resalut error");
		        	}
		            
		            while(resault.next()) {
		        	   data.add(new Fournisseur(resault.getInt("Id")   ,resault.getString("Name"),resault.getString("Phone"),resault.getString("Email"),
		        			   resault.getString("Payment"),resault.getString("RIB")));
		        			  
		        	   if(data != null) {
			        		System.out.println("data worked");
			        	}else {
			        		System.out.println("data error");
			        	}
		           }
		        }catch(SQLException e) {
		        	e.printStackTrace();
		        }
		        Nom_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("Name"));
		        Email_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("Email"));
		        Tel_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("Phone"));
		        Payment_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("RIB"));
		        Table_FRN.setItems(data);	
	    }
        
	    
	  //____________ Delete Supplier from database and table  : 
	    public void On_DeleteSupplier() {
	        Fournisseur selectedSupplier = Table_FRN.getSelectionModel().getSelectedItem();
	        if (selectedSupplier != null) {
	            int supplierID = selectedSupplier.getId();
	            Table_FRN.getItems().remove(selectedSupplier);
	            String deleteSQL = "DELETE FROM suppliers WHERE ID = " + supplierID;
	            try {
	                statement = (Statement) con.prepareStatement(deleteSQL);
	                statement.execute(deleteSQL);
	                Alert alert = new Alert(AlertType.CONFIRMATION, "Fournisseur " + supplierID + " supprimer avec succes");
	                alert.showAndWait();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                Alert alert = new Alert(AlertType.WARNING, "Fournisseur Non Supprimer");
	                alert.showAndWait();
	            }
	        } else {
	            Alert alert = new Alert(AlertType.ERROR, "Aucun fournisseur sélectionné");
	            alert.showAndWait();
	        }
	    }
	    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		con = DBConnection.connect();
		showSuppliers();
		
		// Add event listener to PaymentBox_Frn ComboBox
		PaymentBox_Frn.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		    if (newValue != null && newValue.equals("Payment Par Bank")) {
		        Rib_Frn.setDisable(false); // Enable Rib_Frn TextField
		    } else {
		        Rib_Frn.setDisable(true); // Disable Rib_Frn TextField
		    }
		});

		// Set default value to "Payment Par Bank" and enable Rib_Frn TextField
		PaymentBox_Frn.setItems(FXCollections.observableArrayList("Payment Par Bank", "Payment Par Cash"));
		PaymentBox_Frn.setValue("Payment Par Bank");
		Rib_Frn.setDisable(false);

		
		
	}

}
