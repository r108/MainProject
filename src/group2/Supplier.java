package group2;

/**
 * @author Roland Katona
 *
 */
public class Supplier extends Person {
	private  String contactName;
	private  String vatNumber;
	private  int supplierId, defaultId = 1;
	
	/**
	 * Default constructor
	 */
	public Supplier() {
		super();
		vatNumber = "";
		supplierId = defaultId++;
	}

	/**
	 * @param name
	 * @param email
	 * @param contactNumber
	 * @param address
	 * @param contactName
	 * @param vatNumber
	 */
	public Supplier(String name, String email, String contactNumber,
			String address,String contactName, String vatNumber) {
		super(name, email, contactNumber, address);
		this.vatNumber = vatNumber;
		this.contactName = contactName;
	}

	/**
	 * @return vatNumber
	 */
	public String getVatNumber() {
		return vatNumber;
	}
	

	/**
	 * @param vatNumber
	 */
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	/**
	 * @return contactName
	 */
	public String getContactName() {
		return contactName;
	}
	
	public int getSupplierId() {
		return supplierId;
	}

	/**
	 * @param contactName
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	/* (non-Javadoc)
	 * @see group2.Person#displayDetails()
	 */
	public void displayDetails(){
		super.displayDetails();
		System.out.println("Contact Name : "+contactName);	
		System.out.println("Supplier VAT Number : "+vatNumber);	
	}
	
	

}
