package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Vente;

public class ListVenteController implements Initializable {

    // Database Connection
    public Connection con;
    public java.sql.Statement statement;
    public ResultSet result;

    // Table controllers
    @FXML
    private TableView<Vente> Table_Sales;
    @FXML
    private TableColumn<Vente, String> Client_CNI;
    @FXML
    private TableColumn<Vente, String> Client_Name;
    @FXML
    private TableColumn<Vente,Date> Date_Sale;
    @FXML
    private TableColumn<Vente, Integer> Total_Price;

    // Print Total Sales
    @FXML public void On_PrintTotalSales() {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            /*___ Create a new content stream for the PDF page __*/
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            /*___ Set font and font size for the text __*/
            PDFont font = PDType1Font.HELVETICA_BOLD;
            float fontSize = 10;
            contentStream.setFont(font, fontSize);

            /*___ Set the starting position for the text __*/
            float startX = 50;
            float startY = page.getMediaBox().getHeight() - 50;

            /*___ Set line spacing __*/
            float lineHeight = 15;
            contentStream.setLeading(lineHeight);

            /*___ Write the information and total price to the PDF __*/
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);

            String query = "SELECT sales.Client_Name, sales.Client_CNI, sales.Total_Price ,sales_products.Product_Code, sales_products.Quantity " +
                    "FROM sales " +
                    "INNER JOIN sales_products ON sales.id = sales_products.Sale_ID " +
                    "ORDER BY sales.Client_Name, sales.Client_CNI";

            statement = con.createStatement();
            result = statement.executeQuery(query);

            String currentClientName = "";
            String currentClientCNI = "";
            int totalQuantity = 0;
            int totalPrice =0;
            
            while (result.next()) {
                String clientName = result.getString("Client_Name");
                String clientCNI = result.getString("Client_CNI");
                String productCode = result.getString("Product_Code");
                int quantity = result.getInt("Quantity");

                if (!clientName.equals(currentClientName) || !clientCNI.equals(currentClientCNI)) {
                    /*___ New client, print the total price for the previous client (if any) ___*/
                    if (!currentClientName.isEmpty()) {
                        /*___ Print the total price for the previous client ___*/
                        contentStream.showText("---- Total Price: " + totalPrice);
                        contentStream.newLine();
                        contentStream.newLine();
                        /*___ Print the separator between clients ___*/
                        String separator = "_____________________________________________";
                        contentStream.showText(separator);
                        contentStream.newLine();
                        contentStream.newLine();
                    }

                    /*___ Print the client information ___*/
                    contentStream.showText("Client Name: " + clientName);
                    contentStream.newLine();
                    contentStream.showText("Client CNI: " + clientCNI);
                    contentStream.newLine();
                    contentStream.newLine();

                    /*___ Reset the total price for the new client ___*/
                    totalPrice = result.getInt("Total_Price");

                    /*___ Update the current client information ___*/
                    currentClientName = clientName;
                    currentClientCNI = clientCNI;

                    /*___ Print the products header ___*/
                    contentStream.showText("---- Products:");
                    contentStream.newLine();
                }

                /*___ Write the current product information ___*/
                contentStream.showText("Product Code: " + productCode);
                contentStream.newLine();
                contentStream.showText("Quantity: " + quantity);
                contentStream.newLine();

            }

            /*___ Print the total price for the last client ___*/
            if (!currentClientName.isEmpty()) {
                /*___ Print the total price for the last client ___*/
            	contentStream.newLine();
                contentStream.showText("---- Total Price: " + totalPrice);
                contentStream.newLine();
                contentStream.newLine();
                /*___ Print the separator for the last client ___*/
                String separator = "_____________________________________________";
                contentStream.showText(separator);
                contentStream.newLine();
                contentStream.newLine();
            }

           

            

            /*___ End the text content stream ___*/
            contentStream.endText();

            /*___ Close the content stream ___*/
            contentStream.close();

            /*___ Save the PDF document ___*/
            document.save("Vente_Total.pdf");

            /*___ Close the PDF document ___*/
            document.close();

               


            System.out.println("PDF created successfully.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    
    
    private Map<String, Vente> fetchSalesData() {
        Map<String, Vente> salesData = new HashMap<>();
        try {
            String selectVente = "SELECT sales.Client_Name, sales.Client_CNI, sales.Sale_Date, sales_products.Product_Code, sales_products.Quantity, sales.Total_Price " +
                    "FROM sales " +
                    "INNER JOIN sales_products ON sales.id = sales_products.Sale_ID";
            statement = con.createStatement();
            result = statement.executeQuery(selectVente);

            while (result.next()) {
                String clientName = result.getString("Client_Name");
                String clientCNI = result.getString("Client_CNI");
                int totalPrice = result.getInt("Total_Price");
                LocalDate saleDate = result.getDate("Sale_Date").toLocalDate();

                // Check if the client is already in the database
                //if (isClientInDatabase(clientCNI)) {
                    // Add the new total price to the existing total price in the database
                 //   addTotalPriceInDatabase(clientCNI, totalPrice);
               // } else {
                    Vente vente = new Vente(saleDate, totalPrice, clientName, clientCNI);
                    salesData.put(clientCNI, vente);
               // }
            }

            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesData;
    }

    /*
    private boolean isClientInDatabase(String clientCNI) {
        try {
            String selectClient = "SELECT COUNT(*) AS count FROM sales WHERE Client_CNI = ?";
            statement = con.prepareStatement(selectClient);
            ((PreparedStatement) statement).setString(1, clientCNI);
            result = statement.executeQuery(selectClient);
            result.next();
            int count = result.getInt("count");
            result.close();
            statement.close();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void addTotalPriceInDatabase(String clientCNI, int newTotalPrice) {
        try {
            String updateQuery = "UPDATE sales SET Total_Price = Total_Price + ? WHERE Client_CNI = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setInt(1, newTotalPrice);
            preparedStatement.setString(2, clientCNI);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

*/






            // Load sales from database
    private void showSales() {
        Table_Sales.getItems().clear();
        Client_Name.setCellValueFactory(new PropertyValueFactory<Vente, String>("ClientName"));
        Client_CNI.setCellValueFactory(new PropertyValueFactory<Vente, String>("ClientCNI"));
        Date_Sale.setCellValueFactory(new PropertyValueFactory<Vente, Date>("SaleDate"));
        Total_Price.setCellValueFactory(new PropertyValueFactory<Vente, Integer>("TotalPrice"));
        Map<String, Vente> salesData = fetchSalesData();
        Table_Sales.getItems().addAll(salesData.values());
    }


            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {
                con = DBConnection.connect();
                showSales();
            }
        }
