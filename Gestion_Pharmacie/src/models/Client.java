package models;

public class Client {
	String Client_Name ;
	String Client_CNI ;
	
	
	public Client(String client_Name, String client_CNI) {
		Client_Name = client_Name;
		Client_CNI = client_CNI;
	}
	
	
	public String getClient_Name() {
		return Client_Name;
	}
	public void setClient_Name(String client_Name) {
		Client_Name = client_Name;
	}
	
	public String getClient_CNI() {
		return Client_CNI;
	}
	public void setClient_CNI(String client_CNI) {
		Client_CNI = client_CNI;
	}
	
	

}
