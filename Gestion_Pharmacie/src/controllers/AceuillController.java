package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AceuillController implements Initializable {
	
	//___________ Database Connection :
	public Connection con ;
	public Statement statement;
	public ResultSet resault;

	//___________ Interface Controllers :
	@FXML
	private Label Total_Product;
	@FXML
	private Label Total_Sales;
	@FXML
	private Label Total_Suppliers;

	//___________ Update Statistics :
	private void updateStatistics() {
	    int productCount = countProducts();
	    int salesCount = countSales();
	    int supplierCount = countSuppliers();
	    Total_Product.setText(String.valueOf(productCount));
	    Total_Sales.setText(String.valueOf(salesCount));
	    Total_Suppliers.setText(String.valueOf(supplierCount));
	}

	//___________ countProducts :
	private int countProducts() {
	    int count = 0;
	    try {
	        String countQuery = "SELECT COUNT(*) AS count FROM products";
	        statement = (Statement) con.createStatement();
	        resault = statement.executeQuery(countQuery);
	        if (resault.next()) {
	            count = resault.getInt("count");
	        }
	        resault.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}

	//___________ countSales :
	private int countSales() {
		double totalSum = 0 ;
	    try {
	        String sumQuery = "SELECT SUM(Total_Price) AS totalSum FROM sales";
	        statement = (Statement) con.createStatement();
	        resault = statement.executeQuery(sumQuery);
	        
	        if (resault.next()) {
	            totalSum = resault.getInt("totalSum");
	            Total_Sales.setText(String.valueOf(totalSum));
	        }
	        
	        resault.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return (int) totalSum;
	}

	//___________ countSupplierss :
	private int countSuppliers() {
	    int count = 0;
	    try {
	        String countQuery = "SELECT COUNT(*) AS count FROM suppliers";
	        statement = (Statement) con.createStatement();
	        resault = statement.executeQuery(countQuery);
	        if (resault.next()) {
	            count = resault.getInt("count");
	        }
	        resault.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		con = DBConnection.connect();
		countSales();
	    updateStatistics();
	}
}