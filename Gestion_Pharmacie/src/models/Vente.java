package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vente {
	LocalDate SaleDate ;
	int TotalPrice ;
	String ClientName ;
	String ClientCNI ;
	
	ArrayList<Produit> products = new ArrayList<Produit>();
	
	public Vente(LocalDate sale_Date, int total_Price, String ClientName ,String ClientCNI  ) {
		SaleDate = sale_Date;
		TotalPrice = total_Price;
		this.ClientName = ClientName ;
		this.ClientCNI = ClientCNI ;
	
	}
	
	

	public String getClientName() {
		return ClientName;
	}
	public LocalDate getSaleDate() {
		return SaleDate;
	}



	public void setSaleDate(LocalDate saleDate) {
		SaleDate = saleDate;
	}



	public int getTotalPrice() {
		return TotalPrice;
	}



	public void setTotalPrice(int totalPrice) {
		TotalPrice = totalPrice;
	}



	public void setClientName(String clientName) {
		ClientName = clientName;
	}
	public String getClientCNI() {
		return ClientCNI;
	}
	public void setClientCNI(String clientCNI) {
		ClientCNI = clientCNI;
	}



	public LocalDate getSale_Date() {
		return SaleDate;
	}

	public void setSale_Date(LocalDate sale_Date) {
		SaleDate = sale_Date;
	}

	public int getTotal_Price() {
		return TotalPrice;
	}

	public void setTotal_Price(int total_Price) {
		TotalPrice = total_Price;
	}


	public ArrayList<Produit> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Produit> products) {
		this.products = products;
	}
	
	
	

}
