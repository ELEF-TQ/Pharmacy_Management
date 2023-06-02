package controllers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.mysql.jdbc.Statement;

import java.io.IOException;
import database.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class ModVenteController implements Initializable {
	
//____________________ Database Connection :
	public Connection con ;
	public java.sql.Statement statement;
	public ResultSet resault;
	
	 

	
//____________________ Interface Controllers :
	@FXML
	private TextField ClientCNI;
	@FXML
	private TextField ClientName;
    @FXML
    private StackPane PageContent;
    @FXML
    private Button printButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button showVente;
    @FXML
    private ListView<String> selectedProductList;
    @FXML
    private ListView<String> productList;
    
//____________________ Variables // :
    private ObservableList<String> availableProductsList = FXCollections.observableArrayList();
    private ObservableList<String> selectedProductsList = FXCollections.observableArrayList();
    private ObservableList<String> filteredProductsList = FXCollections.observableArrayList();
	
	
//____________________ Show Available Products for sale :
	private void populateAvailableProducts() {
	    try {
        	/*___ select products ___*/
            String selectSQL = "SELECT Name FROM products";
            statement = con.createStatement();
            resault = statement.executeQuery(selectSQL);
            /*___ clear list ___*/
            availableProductsList.clear();
            /*___ add products name to list ___*/
            while (resault.next()) {
                String productName = resault.getString("Name");
                availableProductsList.add(productName);
            }
            /*___ initialize filtered list ___*/
            filteredProductsList.setAll(availableProductsList);
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

//____________________filter products by search :
	private void filterProductList(String searchQuery) {
		/*___ clear list ___*/
        filteredProductsList.clear();
        if (searchQuery.isEmpty()) {
        	/*___ if search is empty ___*/
            filteredProductsList.setAll(availableProductsList);
        } else {
        	/*___ filter products by name ___*/
            for (String product : availableProductsList) {
                if (product.toLowerCase().contains(searchQuery.toLowerCase())) {
                    filteredProductsList.add(product);
                }
            }
        }
    }
  
//____________________ Add Products been sold :
	@FXML private void onAddProduct() {
	    String selectedProduct = productList.getSelectionModel().getSelectedItem();
	    if (selectedProduct != null && !selectedProductsList.contains(selectedProduct)) {
	        TextInputDialog dialog = new TextInputDialog("1");
	        dialog.setTitle("Quantité de Produit");
	        dialog.setHeaderText("Entrer la quantité pour " + selectedProduct);
	        dialog.setContentText("Quantité :");
	        Optional<String> result = dialog.showAndWait();
	        result.ifPresent(quantityStr -> {
	            try {
	                int quantity = Integer.parseInt(quantityStr);
	                if (quantity <= 0) {
	                    Alert alert = new Alert(AlertType.WARNING, "La quantité doit être supérieure à zéro.");
	                    alert.showAndWait();
	                    return;
	                }
	                int currentQuantity = getProductQuantityFromDatabase(selectedProduct);
	                if (quantity > currentQuantity) {
	                    Alert alert = new Alert(AlertType.WARNING, "La quantité choisie dépasse la quantité disponible.");
	                    alert.showAndWait();
	                    return;
	                }
	                int newQuantity = currentQuantity - quantity;
	                updateProductQuantityInDatabase(selectedProduct, newQuantity);
	                String productEntry = selectedProduct + " (Quantité : " + quantity + ")";
	                selectedProductsList.add(productEntry);
	                selectedProductList.setItems(selectedProductsList);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                Alert alert = new Alert(AlertType.WARNING, "Votre choix n'est pas valide.");
	                alert.showAndWait();
	            }
	        });
	    }
	    searchField.clear();
	}

	private int getProductQuantityFromDatabase(String productName) {
	    int quantity = 0;
	    try {
	        String query = "SELECT Quantity FROM products WHERE Name = ?";
	        PreparedStatement statement = con.prepareStatement(query);
	        statement.setString(1, productName);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            quantity = resultSet.getInt("Quantity");
	        }

	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle database error
	    }
	    return quantity;
	}

	private void updateProductQuantityInDatabase(String productName, int newQuantity) {
	    try {
	        String query = "UPDATE products SET Quantity = ? WHERE Name = ?";
	        PreparedStatement statement = con.prepareStatement(query);
	        statement.setInt(1, newQuantity);
	        statement.setString(2, productName);
	        statement.executeUpdate();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle database error
	    }
	}


//____________________ Remove Product From List :
    @FXML
    private void onRemoveProduct() {
        String selectedProduct = selectedProductList.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            /*___ update selectedProducts list ___*/
            selectedProductsList.remove(selectedProduct);
            selectedProductList.setItems(selectedProductsList);
        }
    }

//____________________ Calculate Total Price :
    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (String productEntry : selectedProductsList) {
            /*___ get selected product name & quantity ___*/
            String[] parts = productEntry.split(" \\(Quantité : ");
            String productName = parts[0];
            int quantity = Integer.parseInt(parts[1].replace(")", ""));

            /*___ select product price from database ___*/
            try {
                String selectSQL = "SELECT Price FROM products WHERE Name = ?";
                PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
                preparedStatement.setString(1, productName);
                resault = preparedStatement.executeQuery();
                /*___ calculate total price ___*/
                if (resault.next()) {
                    double price = resault.getDouble("Price");
                    double subtotal = price * quantity;
                    totalPrice += subtotal;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalPrice;
    }

//____________________ Print The Bill :
    @FXML private void On_printButton() {
        // Get client information
        String clientName = (ClientName != null) ? ClientName.getText() : "";
        String clientCNI = (ClientCNI != null) ? ClientCNI.getText() : "";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        if (clientName.isEmpty() || clientCNI.isEmpty()) {
        	Alert alert = new Alert(AlertType.WARNING, "Veuillez saisir à la fois le nom du client et le CNI.");
            alert.showAndWait();
            return;
        }
        // Insert values into the sales table
        String insertSQL = "INSERT INTO `sales` (`Client_Name`, `Client_CNI`, `Sale_Date`, `Total_Price`) "
                + "VALUES (?, ?, ?, ?)";

        try {
            // Execute the insertSQL
            PreparedStatement preparedStatement = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, clientName);
            preparedStatement.setString(2, clientCNI);
            preparedStatement.setString(3, formattedDateTime);
            preparedStatement.setDouble(4, calculateTotalPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet keys = preparedStatement.getGeneratedKeys();
                if (keys.next()) {
                    int saleID = keys.getInt(1);
                    for (String productEntry : selectedProductsList) {
                        String[] parts = productEntry.split(" \\(Quantité : ");
                        String productName = parts[0];
                        int quantity = Integer.parseInt(parts[1].replace(")", ""));

                        String selectProduct = "SELECT `Code` FROM `products` WHERE Name=?";
                        PreparedStatement selectStatement = con.prepareStatement(selectProduct);
                        selectStatement.setString(1, productName);
                        ResultSet result = selectStatement.executeQuery();
                        if (result.next()) {
                            String productCode = result.getString("Code");
                            String insertSaleSQL = "INSERT INTO `sales_products` (`Sale_ID`, `Product_Code`, `Quantity`) "
                                    + "VALUES (?, ?, ?)";
                            PreparedStatement insertStatement = con.prepareStatement(insertSaleSQL);
                            insertStatement.setInt(1, saleID);
                            insertStatement.setString(2, productCode);
                            insertStatement.setInt(3, quantity);
                            insertStatement.executeUpdate();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Produit non trouvé");
                            alert.setContentText("Produit " + productName + " non trouvé dans la base de données.");
                            alert.showAndWait();
                        }
                    }
                    Alert alert = new Alert(AlertType.INFORMATION, "facture créé avec succès");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Vente non créée");
                    alert.setContentText("Erreur lors de la création de la vente. Veuillez réessayer.");
                    alert.showAndWait();
                }
            }

            /*___ create new documentPDF ___*/
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            try {
                /*___ Create a new content stream for the PDF page __*/
                PDPageContentStream contentStream = new PDPageContentStream(document, page);

                /*___ Set font and font size for the text __*/
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);

                /*___ Set the starting position for the text __*/
                float startX = 50;
                float startY = page.getMediaBox().getHeight() - 50;

                /*___ Set line spacing __*/
                float lineHeight = 15;
                contentStream.setLeading(lineHeight);

                /*___ Write the information and total price to the PDF __*/
                contentStream.beginText();
                contentStream.newLineAtOffset(startX, startY);

                /*___ Add client information __*/
                contentStream.showText("Nom Client: " + clientName);
                contentStream.newLine();
                contentStream.newLine();
                /*___ Add selected products ___*/
                contentStream.showText("--Médicaments :");
                contentStream.newLine();
                for (String productEntry : selectedProductsList) {
                    contentStream.showText(productEntry);
                    contentStream.newLine();
                }

                /*___ Add total price ___*/
                contentStream.showText("-- Prix Total : MAD" + calculateTotalPrice());

                contentStream.endText();
                contentStream.close();

                /*___ Save the PDF document ___*/
                document.save("facture.pdf");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    /*___ Close the PDF document ___*/
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database error");
            alert.setContentText("An error occurred while inserting the sale into the database. Please try again.");
            alert.showAndWait();
        }
    }

    

    //____________________ Show Sales List :
    @FXML private void On_ShowVente() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/interfaces/ListVente.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
  //____________________ Cancel Sale :
    @FXML private void On_AnuulerVente() {
    	ClientCNI.setText("");
    	ClientName.setText("");
    	searchField.setText("");
    	ObservableList<String> items = selectedProductList.getItems();
    	items.clear();
    
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        con = DBConnection.connect();

        /*___ set products list ___*/
        productList.setItems(filteredProductsList);
        populateAvailableProducts();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterProductList(newValue);
        });
    }
}
