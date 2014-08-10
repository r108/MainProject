package group2;

public class Supplier extends Person {
	private  String contactName;
	private  String vatNumber;
	
	public Supplier() {
		super();
		vatNumber = "";
	}

	public Supplier(String name, String email, String contactNumber,
			String address,String contactName, String vatNumber) {
		super(name, email, contactNumber, address);
		this.vatNumber = vatNumber;
		this.contactName = contactName;
	}

	public String getVatNumber() {
		return vatNumber;
	}
	

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getContactName() {
		return contactName;
	}
	

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public void displayDetails(){
		super.displayDetails();
		System.out.println("Contact Name : "+contactName);	
		System.out.println("Supplier VAT Number : "+vatNumber);	
	}
	
	

}
