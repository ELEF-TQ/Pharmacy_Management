package models;

import java.sql.Date;

public class Produit {
	String Code ;
	String Name ;
	String Category ;
	String Forme ;
	int Price ;
	Date DateFab;
	Date DateExp ;
	
	public Produit(String code, String name, String category, String forme, int price, Date dateFab, Date dateExp) {
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

	public Date getDateFab() {
		return DateFab;
	}

	public void setDateFab(Date dateFab) {
		DateFab = dateFab;
	}

	public Date getDateExp() {
		return DateExp;
	}

	public void setDateExp(Date dateExp) {
		DateExp = dateExp;
	}
	
	
	
	
}
