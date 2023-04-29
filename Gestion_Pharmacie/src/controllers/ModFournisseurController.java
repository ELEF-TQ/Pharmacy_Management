package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import database.DBConnection;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
	    
	  //____________ UPDATE Supplier informations  : 
	    @FXML
	    public void On_ModifySupplier() {
	        Fournisseur selectedSupplier = Table_FRN.getSelectionModel().getSelectedItem();
	        if (selectedSupplier != null) {
	            Fournisseur editedSupplier = showEditSupplierDialog(selectedSupplier);
	            if (editedSupplier != null) {
	                // Update the supplier in the database
	                String updateSQL = "UPDATE suppliers SET Name = ?, Phone = ?, Email = ?, Payment = ?, RIB = ? WHERE ID = ?";
	                try {
	                    PreparedStatement statement = (PreparedStatement) con.prepareStatement(updateSQL);
	                    statement.setString(1, editedSupplier.getName());
	                    statement.setString(2, editedSupplier.getPhone());
	                    statement.setString(3, editedSupplier.getEmail());
	                    statement.setString(4, editedSupplier.getPayment());
	                    statement.setString(5, editedSupplier.getRIB());
	                    statement.setInt(6, editedSupplier.getId());
	                    statement.executeUpdate();

	                    Alert alert = new Alert(AlertType.CONFIRMATION, "Fournisseur modifié avec succès");
	                    alert.showAndWait();

	                    // Refresh the supplier table view
	                    showSuppliers();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    Alert alert = new Alert(AlertType.WARNING, "Échec de la modification du fournisseur");
	                    alert.showAndWait();
	                }
	            }
	        } else {
	            Alert alert = new Alert(AlertType.CONFIRMATION, "Pas de fournisseur sélectionné");
	            alert.showAndWait();
	        }
	    }

	    
	    private Fournisseur showEditSupplierDialog(Fournisseur supplier) {
	        // Create a new Stage for the dialog
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Modifier les informations du fournisseur");

	        // Create the GridPane for the dialog content
	        GridPane gridPane = new GridPane();
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        gridPane.setPadding(new Insets(20));

	        // Create the input fields and labels for each supplier property
	        TextField nameField = new TextField(supplier.getName());
	        TextField phoneField = new TextField(supplier.getPhone());
	        TextField emailField = new TextField(supplier.getEmail());
	        ComboBox<String> paymentBox = new ComboBox<>();
	        paymentBox.getItems().addAll("Payment Par Bank", "Payment Par Cash");
	        paymentBox.setValue(supplier.getPayment());
	        TextField ribField = new TextField(supplier.getRIB());

	        // Add the input fields and labels to the GridPane
	        gridPane.add(new Label("Nom:"), 0, 0);
	        gridPane.add(nameField, 1, 0);
	        gridPane.add(new Label("Téléphone:"), 0, 1);
	        gridPane.add(phoneField, 1, 1);
	        gridPane.add(new Label("Email:"), 0, 2);
	        gridPane.add(emailField, 1, 2);
	        gridPane.add(new Label("Paiement:"), 0, 3);
	        gridPane.add(paymentBox, 1, 3);
	        gridPane.add(new Label("RIB:"), 0, 4);
	        gridPane.add(ribField, 1, 4);

	        // Set the initial value of Rib_Frn based on the selected payment method
	        if (supplier.getPayment().equals("Payment Par Cash")) {
	            ribField.setText("Payment Par Cash");
	        }

	        // Enable/disable the RIB field based on the selected payment method
	        paymentBox.setOnAction(e -> {
	            String selectedPayment = paymentBox.getValue();
	            ribField.setDisable(selectedPayment.equals("Payment Par Cash"));
	            if (selectedPayment.equals("Payment Par Cash")) {
	                ribField.setText("Payment Par Cash");
	            } else {
	                ribField.setText("");
	            }
	        });

	        // Create the Save and Cancel buttons
	        Button saveButton = new Button("Enregistrer");
	        Button cancelButton = new Button("Annuler");

	        // Set the Save button's action handler
	        saveButton.setOnAction(e -> {
	            supplier.setName(nameField.getText());
	            supplier.setPhone(phoneField.getText());
	            supplier.setEmail(emailField.getText());
	            supplier.setPayment(paymentBox.getValue());
	            supplier.setRIB(ribField.getText());
	            dialogStage.close();
	        });

	        // Set the Cancel button's action handler
	        cancelButton.setOnAction(e -> dialogStage.close());

	        // Add the buttons to a HBox
	        HBox buttonBox = new HBox(10);
	        buttonBox.setAlignment(Pos.CENTER_RIGHT);
	        buttonBox.getChildren().addAll(saveButton, cancelButton);

	        // Add the GridPane and buttons to a VBox
	        VBox vbox = new VBox(20);
	        vbox.setPadding(new Insets(20));
	        vbox.getChildren().addAll(gridPane, buttonBox);

	        // Create the Scene and set it on the Stage
	        Scene scene = new Scene(vbox);
	        dialogStage.setScene(scene);

	        // Show the dialog and wait for it to be closed
	        dialogStage.showAndWait();

	        return supplier;
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
