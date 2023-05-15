package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import database.DBConnection;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Produit;

public class ListProduitController implements Initializable {
    
	//____________________ Database Connection :
	public Connection con ;
	public Statement statement;
	public ResultSet resault;
	
	private void setDatePickerValue(DatePicker datePicker, LocalDate localDate) {
	    if (localDate != null) {
	        datePicker.setValue(localDate);
	    } else {
	        datePicker.setValue(null);
	    }
	}

    
    //____________________ Table Controllers :
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

    
    //____________ DELETE Product from database and table  :
    @FXML private void On_DeleteProduct() {
 	    	/*___ select supplier ___*/
 	        Produit selectedProdcut = Table_Prd.getSelectionModel().getSelectedItem();
 	        if (selectedProdcut != null) {
 	            String ProductID = selectedProdcut.getCode();
 	            /*___ delete supplier ___*/
 	           Table_Prd.getItems().remove(selectedProdcut);
 	            String deleteSQL ="DELETE FROM products WHERE Code = '" + ProductID + "'";
 	            try {
 	                statement = (Statement) con.prepareStatement(deleteSQL);
 	                statement.execute(deleteSQL);
 	                Alert alert = new Alert(AlertType.INFORMATION, "Produit " + ProductID + " supprimer avec succes");
 	                alert.showAndWait();
 	            } catch (SQLException e) {
 	                e.printStackTrace();
 	                Alert alert = new Alert(AlertType.WARNING, "Produit Non Supprimer");
 	                alert.showAndWait();
 	            }
 	        } else {
 	            Alert alert = new Alert(AlertType.ERROR, "Aucun Produit sélectionné");
 	            alert.showAndWait();
 	        }
 	}
    	
    
    
  //____________ UPDATE Product informations  : 
    @FXML private void On_ModifyProduct() {
    	/*___ select supplier ___*/
        Produit selectedProduct = Table_Prd.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
        	/*___ update supplier ___*/
            Produit editedProduct = showEditProductDialog(selectedProduct);
            if (editedProduct != null) {
                String updateSQL = "UPDATE products SET Name = ?, Category = ?, Forme = ?, Price = ?, DateFab = ? , DateExp = ? WHERE Code = ?";
                try {
                	
                	/* solve date problem */
                	LocalDate dateFab = editedProduct.getDateFab();
                	Date sqlDateFab = Date.valueOf(dateFab);
                	LocalDate dateExp = editedProduct.getDateExp();
                	Date sqlDateExp = Date.valueOf(dateExp);
                	
                    PreparedStatement statement = (PreparedStatement) con.prepareStatement(updateSQL);
                    statement.setString(1, editedProduct.getName());
                    statement.setString(2, editedProduct.getCategory());
                    statement.setString(3, editedProduct.getForme());
                    statement.setInt(4, editedProduct.getPrice());
                    statement.setDate(5, sqlDateFab);
                    statement.setDate(6, sqlDateExp);
                    statement.setString(7, editedProduct.getCode());
                    statement.executeUpdate();

                    Alert alert = new Alert(AlertType.INFORMATION, "Produit modifié avec succès");
                    alert.showAndWait();

                    /*___ show new table ___*/
                    showProducts();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.WARNING, "Échec de la modification du Produit");
                    alert.showAndWait();
                }
            }
        } else {
        	Alert alert = new Alert(AlertType.ERROR, "Aucun Produit sélectionné");
            alert.showAndWait();
        }
    }

    //____________ update Product Window  : 
    private Produit showEditProductDialog(Produit Product) {
    	
    	/*___ create stage ___*/
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier les informations du Produit");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        
        /*___ create inputs ___*/
        TextField nameField = new TextField(Product.getName());
        TextField priceField = new TextField(Integer.toString(Product.getPrice()));
        
        
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Spécialité médicament", "Parapharmacerie", "diététique");
        categoryBox.setValue(Product.getCategory());
        
        ComboBox<String> formeBox = new ComboBox<>();
        formeBox.getItems().addAll("Injéctable", "Dérmique", "Inhalées" , "Réctales");
        formeBox.setValue(Product.getForme());
       
        DatePicker dateFabPicker = new DatePicker();
        DatePicker dateExpPicker = new DatePicker();

        setDatePickerValue(dateFabPicker, Product.getDateFab());
        setDatePickerValue(dateExpPicker, Product.getDateExp());

        /*___ add inputs to the stage ___*/
        gridPane.add(new Label("Nom:"), 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(new Label("Prix:"), 0, 1);
        gridPane.add(priceField, 1, 1);
        gridPane.add(new Label("Catégorie:"), 0, 2);
        gridPane.add(categoryBox, 1, 2);
        gridPane.add(new Label("Forme:"), 0, 3);
        gridPane.add(formeBox, 1, 3);
        gridPane.add(new Label("Date de fabrication:"), 0, 4);
        gridPane.add(dateFabPicker, 1, 4);
        gridPane.add(new Label("Date d'expiration:"), 0, 5);
        gridPane.add(dateExpPicker, 1, 5);
        
     	

        /*___ save & cancel ___*/
        Button saveButton = new Button("Enregistrer");
        Button cancelButton = new Button("Annuler");

        /*___ save action ___*/
        saveButton.setOnAction(e -> {
            Product.setName(nameField.getText());
            Product.setPrice(Integer.parseInt(priceField.getText()));
            Product.setCategory(categoryBox.getValue());
            Product.setForme(formeBox.getValue());
            Product.setDateFab(LocalDate.parse(dateFabPicker.getValue().toString()));
            Product.setDateExp(LocalDate.parse(dateExpPicker.getValue().toString()));
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

        return Product;
    }

   
    //________ On_ClearALL
    @FXML private void On_ClearALL() {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Supprimer tous les produits");
        confirmationDialog.setContentText("Voulez-vous vraiment supprimer tous les produits ?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            data.clear(); 

            String deleteSQL = "DELETE FROM products";
            try {
                statement = (Statement) con.prepareStatement(deleteSQL);
                statement.execute(deleteSQL);
                Alert alert = new Alert(AlertType.INFORMATION, "Tous les produits ont été supprimés avec succès");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(AlertType.WARNING, "Échec de suppression des produits");
                alert.showAndWait();
            }
        }
      }
    
   
    //____________ SELECT Suppliers To Table : 
    public ObservableList<Produit> data = FXCollections.observableArrayList();
    private void showProducts() {
    	/*___ clear table to avoid repetition ___*/
    	Table_Prd.getItems().clear();
        try {
        	/*___ select products ___*/
            statement = (Statement) con.createStatement();
            resault = statement.executeQuery("SELECT * FROM `products` WHERE 1");

            while (resault.next()) {
                data.add(new Produit(resault.getString("Code"), resault.getString("Name"), resault.getString("Category"),
                        resault.getString("Forme"), resault.getInt("Price"),resault.getInt("Quantity"),
                        resault.getDate("DateFab").toLocalDate(), resault.getDate("DateExp").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*___ fill table ___*/
        Code_Prd.setCellValueFactory(new PropertyValueFactory<Produit, String>("Code"));
        Nom_Prd.setCellValueFactory(new PropertyValueFactory<Produit, String>("Name"));
        Category_Prd.setCellValueFactory(new PropertyValueFactory<Produit, String>("Category"));
        Forme_Prd.setCellValueFactory(new PropertyValueFactory<Produit, String>("Forme"));
        Prix_Prd.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("Price"));
        DateFab_Prd.setCellValueFactory(new PropertyValueFactory<Produit, java.sql.Date>("DateFab"));
        DateExp_Prd.setCellValueFactory(new PropertyValueFactory<Produit, java.sql.Date>("DateExp"));
        Table_Prd.setItems(data);
    }

	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			   con = DBConnection.connect();
			   showProducts();   
	    }
   
    
	
}
