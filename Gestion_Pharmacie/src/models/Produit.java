package models;

import java.time.LocalDate;

public class Produit {
	String Code ;
	String Name ;
	String Category ;
	String Forme ;
	int Price ;
	LocalDate DateFab;
	LocalDate DateExp ;
	
	public Produit(String code, String name, String category, String forme, int price, LocalDate dateFab, LocalDate dateExp) {
		super();
		this.Code = code;
		this.Name = name;
		this.Category = category;
		this.Forme = forme;
		this.Price = price;
		this.DateFab = dateFab;
		this.DateExp = dateExp;
	}
	

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getForme() {
		return Forme;
	}

	public void setForme(String forme) {
		Forme = forme;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public LocalDate getDateFab() {
		return DateFab;
	}

	public void setDateFab(LocalDate dateFab) {
		DateFab = dateFab;
	}

	public LocalDate getDateExp() {
		return DateExp;
	}

	public void setDateExp(LocalDate dateExp) {
		DateExp = dateExp;
	}
	
	
	
	
}
