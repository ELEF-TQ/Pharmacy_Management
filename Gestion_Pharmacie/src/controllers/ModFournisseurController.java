package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
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
	    private TextField Email_Frn;
	    @FXML
	    private TextField Nom_Frn;
	    @FXML
	    private TextField Tel_Frn;
	    @FXML
	    private ComboBox<String> PaymentBox_Frn;
	    @FXML
	    private TextField Rib_Frn;
        
	    //___________ Array Controllers :
	    @FXML
	    private TableView<Fournisseur> Table_FRN;
	    @FXML
	    private TableColumn<Fournisseur, String> ID_FRN;
	    @FXML
	    private TableColumn<Fournisseur,String> Email_FRN;
	    @FXML
	    private TableColumn<Fournisseur,String> Tel_FRN;
	    @FXML
	    private TableColumn<Fournisseur,String> Payment_FRN;
	    @FXML
	    private TableColumn<Fournisseur,String> Nom_FRN;

	    
	    
	   //____________ Cancel Adding Supplier (Clear Inputs) : 
	    @FXML
	    void On_AnullerFournisseur() {
	    	Nom_Frn.setText("");
	    	Email_Frn.setText("");
	    	Tel_Frn.setText("");
	    	Rib_Frn.setText("");
	    	PaymentBox_Frn.setValue("Paiement par banque");
	    }
	    
	   //____________ INSERT Supplier To Database : 
		@FXML private void On_AddFournissueur() {
			   
			    /*___ input values ___*/
			    String name = escapeString(Nom_Frn.getText());
			    String email = escapeString(Email_Frn.getText());
			    String phone = escapeString(Tel_Frn.getText());
			    String payment = PaymentBox_Frn.getValue();
			    String rib = Rib_Frn.getText();
			    
			    /*___ check inputs validity ___*/
			    if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || payment == null) {
			        Alert alert = new Alert(AlertType.ERROR, "Tous les champs doivent être remplis");
			        alert.showAndWait();
			        return;
			    }
			    if (payment.equals("Paiement par banque")) {
			        if (rib.isEmpty()) {
			            Alert alert = new Alert(AlertType.ERROR, "Le champ RIB doit être rempli");
			            alert.showAndWait();
			            return;
			        }
			    } else if (payment.equals("Paiement par cash")) {
			        rib = "Paiement par cash";
			    }
                
			    /*___ insert values to database  ___*/
			    String insertSQL = "INSERT INTO `suppliers`(`Name`, `Phone`, `Email`, `Payment`, `RIB`) "
			            + "VALUES ('" + name + "', '" + phone + "', '" + email + "', '" + payment + "', '" + rib + "')";

			    try {
			    	/*___ execute insertSQL ___*/
			        statement = (Statement) con.prepareStatement(insertSQL);
			        statement.execute(insertSQL);
			        Alert alert = new Alert(AlertType.INFORMATION, "Fournisseur ajouté avec succès");
			        alert.showAndWait();

			        /*___ clear inputs ___*/
			        On_AnullerFournisseur();
			        /*___ fill table ___*/
			        showSuppliers();

			        /*___ error SQL ___*/
			    } catch (SQLException e) {
			        e.printStackTrace();
			        Alert alert = new Alert(AlertType.WARNING, "Fournisseur non ajouté");
			        alert.showAndWait();
			    }
		}
		
		
		//____________ SELECT Suppliers To Table : 
		public ObservableList<Fournisseur> data = FXCollections.observableArrayList() ;
	    private void showSuppliers() {
	    	/*___ clear table to avoid repetition ___*/
	    	Table_FRN.getItems().clear();
	    	 try {
	    		    /*___ select suppliers ___*/
		        	statement = (Statement) con.createStatement();
		            resault = statement.executeQuery("SELECT * FROM `suppliers` WHERE 1");
		            while(resault.next()) {
		        	   data.add(new Fournisseur(resault.getInt("Id")   ,resault.getString("Name"),resault.getString("Phone"),resault.getString("Email"),
		        			   resault.getString("Payment"),resault.getString("RIB")));
		           }
		        }catch(SQLException e) {
		        	e.printStackTrace();
		        }
	    	    /*___ fill table ___*/
	    	    ID_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("Id"));
		        Nom_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("Name"));
		        Email_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("Email"));
		        Tel_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("Phone"));
		        Payment_FRN.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("RIB"));
		        Table_FRN.setItems(data);	
	    }
        
	    
	    //____________ DELETE Supplier from database and table  : 
	    @FXML private void On_DeleteSupplier() {
	    	/*___ select supplier ___*/
	        Fournisseur selectedSupplier = Table_FRN.getSelectionModel().getSelectedItem();
	        if (selectedSupplier != null) {
	            int supplierID = selectedSupplier.getId();
	            /*___ delete supplier ___*/
	            Table_FRN.getItems().remove(selectedSupplier);
	            String deleteSQL = "DELETE FROM suppliers WHERE ID = " + supplierID;
	            try {
	                statement = (Statement) con.prepareStatement(deleteSQL);
	                statement.execute(deleteSQL);
	                Alert alert = new Alert(AlertType.INFORMATION, "Fournisseur " + supplierID + " supprimer avec succes");
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
	    @FXML private void On_ModifySupplier() {
	    	/*___ select supplier ___*/
	        Fournisseur selectedSupplier = Table_FRN.getSelectionModel().getSelectedItem();
	        if (selectedSupplier != null) {
	        	/*___ update supplier ___*/
	            Fournisseur editedSupplier = showEditSupplierDialog(selectedSupplier);
	            if (editedSupplier != null) {
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

	                    Alert alert = new Alert(AlertType.INFORMATION, "Fournisseur modifié avec succès");
	                    alert.showAndWait();

	                    /*___ show new table ___*/
	                    showSuppliers();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    Alert alert = new Alert(AlertType.WARNING, "Échec de la modification du fournisseur");
	                    alert.showAndWait();
	                }
	            }
	        } else {
	        	Alert alert = new Alert(AlertType.ERROR, "Aucun fournisseur sélectionné");
	            alert.showAndWait();
	        }
	    }

	    //____________ update Supplier Window  : 
	    private Fournisseur showEditSupplierDialog(Fournisseur supplier) {
	    	
	    	/*___ create stage ___*/
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Modifier les informations du fournisseur");
	        GridPane gridPane = new GridPane();
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        gridPane.setPadding(new Insets(20));
	        
	        /*___ create inputs ___*/
	        TextField nameField = new TextField(supplier.getName());
	        TextField phoneField = new TextField(supplier.getPhone());
	        TextField emailField = new TextField(supplier.getEmail());
	        ComboBox<String> paymentBox = new ComboBox<>();
	        paymentBox.getItems().addAll("Paiement par banque", "Paiement par cash");
	        paymentBox.setValue(supplier.getPayment());
	        TextField ribField = new TextField(supplier.getRIB());
	        
	        /*___ add inputs to the stage ___*/
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
	        
	     	/*___control payment ___*/
	        if (supplier.getPayment().equals("Paiement par cash")) {
	            ribField.setText("Paiement par cash");
	        }
	        paymentBox.setOnAction(e -> {
	            String selectedPayment = paymentBox.getValue();
	            ribField.setDisable(selectedPayment.equals("Paiement par cash"));
	            if (selectedPayment.equals("Paiement par cash")) {
	                ribField.setText("Paiement par cash");
	            } else {
	                ribField.setText("");
	            }
	        });

	        /*___ save & cancel ___*/
	        Button saveButton = new Button("Enregistrer");
	        Button cancelButton = new Button("Annuler");

	        /*___ save action ___*/
	        saveButton.setOnAction(e -> {
	            supplier.setName(nameField.getText());
	            supplier.setPhone(phoneField.getText());
	            supplier.setEmail(emailField.getText());
	            supplier.setPayment(paymentBox.getValue());
	            supplier.setRIB(ribField.getText());
	            dialogStage.close();
	        });

	        /*___ cancel action ___*/
	        cancelButton.setOnAction(e -> dialogStage.close());

	        /*___ show dialog stage ___*/
	        HBox buttonBox = new HBox(10);
	        buttonBox.setAlignment(Pos.CENTER_RIGHT);
	        buttonBox.getChildren().addAll(saveButton, cancelButton);
	        VBox vbox = new VBox(20);
	        vbox.setPadding(new Insets(20));
	        vbox.getChildren().addAll(gridPane, buttonBox);
	        Scene scene = new Scene(vbox);
	        dialogStage.setScene(scene);
	        dialogStage.showAndWait();

	        return supplier;
	    }

       //________ On_ClearALL
	    @FXML private void On_ClearALL() {
	    	 // Create a confirmation dialog
	        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
	        confirmationDialog.setTitle("Confirmation");
	        confirmationDialog.setHeaderText("Effacer tous les fournisseurs");
	        confirmationDialog.setContentText("Voulez-vous vraiment supprimer tous les fournisseurs?");

	        Optional<ButtonType> result = confirmationDialog.showAndWait();
	        if (result.isPresent() && result.get() == ButtonType.OK) {
	            // User confirmed, proceed with deletion
	            data.clear(); // Clear the supplierData list

	            String deleteSQL = "DELETE FROM suppliers";
	            try {
	                statement = (Statement) con.prepareStatement(deleteSQL);
	                statement.execute(deleteSQL);
	                Alert alert = new Alert(AlertType.INFORMATION, "Tous les fournisseurs ont été supprimés avec succès");
	                alert.showAndWait();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                Alert alert = new Alert(AlertType.WARNING, "Échec de suppression des fournisseurs");
	                alert.showAndWait();
	            }
	        }
	      }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		con = DBConnection.connect();
		showSuppliers();
		
		/*____ payment box control ___*/
		PaymentBox_Frn.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		    if (newValue != null && newValue.equals("Paiement par banque")) {
		        Rib_Frn.setDisable(false); 
		    } else {
		        Rib_Frn.setDisable(true); 
		    }
		});
		PaymentBox_Frn.setItems(FXCollections.observableArrayList("Paiement par banque", "Paiement par cash"));
		PaymentBox_Frn.setValue("Paiement par banque");
		Rib_Frn.setDisable(false);
	
	}

}
