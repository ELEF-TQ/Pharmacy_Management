package models;

public class Fournisseur {
	int Id ;
	String Name ;
	String Phone ;
	String Email ;
	String Payment ;
	String RIB ;
	
	
	public Fournisseur( int id ,String name, String phone, String email, String payment, String rib) {
		super();
		Id = id ;
		Name = name;
		Phone = phone;
		Email = email;
		Payment = payment;
		RIB = rib;
	}
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPayment() {
		return Payment;
	}
	public void setPayment(String payment) {
		Payment = payment;
	}
	public String getRIB() {
		return RIB;
	}
	public void setRIB(String rIB) {
		RIB = rIB;
	}
}
