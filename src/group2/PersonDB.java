package group2;

import java.util.ArrayList;
import java.util.Random;

import person.Customer;
import person.Person;
import person.Staff;
import person.Supplier;

public class PersonDB {

	private ArrayList<Person> customerList;
	private ArrayList<Person> staffList;
	private ArrayList<Person> supplierList;
	private Person person;
	private Random random;
	private int menuOption;

	public PersonDB(){
		customerList = new ArrayList<Person>();
		staffList = new ArrayList<Person>();
		supplierList = new ArrayList<Person>();
		random = new Random();
		
	}
	/**
	 * @return A list of customers
	 */
	public ArrayList<Person> getCustomerList() {
		return customerList;
	}

	/**
	 * @return A list of staff
	 */
	public ArrayList<Person> getStaffList() {
		return staffList;
	}

	/**
	 * @return A list of suppliers
	 */
	public ArrayList<Person> getSupplierList() {
		return supplierList;
	}
	
	/**
	 * Find a supplier using their ID
	 * @param id The Suppliers' ID
	 * @return A Supplier
	 */
	public Supplier getSupplierById(int id){
		boolean isFound = false;
		
		for(Person person : supplierList){
			if(person.getId()==id){
				isFound = true;
				return (Supplier)person;
			}
			else{
				isFound = false;
			}
		}
		
		if(!isFound){
			System.out.println("Supplier ID not found! Try again!");
			System.out.println("Enter supplier ID :");
			menuOption = Keyboard.readInt();
			getSupplierById(menuOption);
		}
		return null;
	}

	/**
	 * Randomly select a supplier from the list
	 * @return A random supplier
	 */
	public Supplier getRandomSupplier(){
		int randomSupplier = random.nextInt((supplierList.size() - 1) + 1);
		return (Supplier)supplierList.get(randomSupplier);		
	}
	
	/**
	 * Add Customers automatically generated
	 */
	public void automaticallyCreateCustomer(){
		person = new Customer("Roland","roland@msn.com","08712345","12 Main Street");
		customerList.add(person);
		person = new Customer("Tom","tom@msn.com","08713455","33 Main Street");
		customerList.add(person);
		person = new Customer("Bob","bob@msn.com","0859983","1 Main Street");
		customerList.add(person);
	}
	
	/**
	 * Add Staff automatically generated
	 */
	public void automaticallyCreateStaff(){
		person = new Staff("Jim","jim@msn.com","08712345","123 Main Street","pass",1);
		staffList.add(person);
		person = new Staff("John","john@msn.com","08609382","38 Main Street","password",2);
		staffList.add(person);
	}
	
	/**
	 * Add Suppliers automatically generated
	 */
	public void automaticallyCreateSupplier(){
		person = new Supplier("Poor Buy","pb@msn.com","08502274","111 Main Street","Pat","x0123");
		supplierList.add(person);
		person = new Supplier("Good Buy","gb@msn.com","087512345","200 Main Street","Kate","x1234");
		supplierList.add(person);
		person = new Supplier("Better Buy","bb@msn.com","087713455","400 Main Street","Sam","x2345");
		supplierList.add(person);
		person = new Supplier("Best Buy","bbb@msn.com","08599983","600 Main Street","Fred","x3456");
		supplierList.add(person);
	}
	
	/**
	 * Creates a new person and adds them to the relevant list
	 * @param person The person object
	 * @param name Name of the person
	 * @param email Email of the person
	 * @param contactNumber Contact number of the person
	 * @param address Address of the person
	 * @param accessLevel Determines elements visible to staff
	 * @param password Password for staff login
	 * @param vatNumber VAT number for a supplier
	 * @param contactName COntact person for a suppliers business
	 */
	public void createNewPerson(Person person, String name, String email, String contactNumber, String address, int accessLevel, String password, String vatNumber, String contactName ){
		if (person instanceof Customer){
			customerList.add( new Customer(name, email, contactNumber, address));
		}
		else if(person instanceof Staff){
			staffList.add(new Staff(name, email, contactNumber, address, password, accessLevel));
		}
		else{
			supplierList.add(new Supplier(name, email, contactNumber, address, contactName, vatNumber));
		}
	}

	public void changePersonDetails(Person person, String name, String email, String contactNumber, String address, int accessLevel, String password, String vatNumber, String contactName ){
		this.person = person;
		this.person.setName(name);
		this.person.setEmail(email);
		this.person.setAddress(address);
		this.person.setContactNumber(contactNumber);
		if((accessLevel==0 && password==null)&&(vatNumber!=null && contactName!=null)){
			((Supplier)this.person).setVatNumber(vatNumber);
			((Supplier)this.person).setContactName(contactName);
		}
		else if((vatNumber==null && contactName==null)&&(accessLevel!=0 && password!=null)){
			((Staff)this.person).setPassword(password);
			((Staff)this.person).setAccessLevel(accessLevel);
		}	
	}
	
	
	
	public void deletePersonFromList(Person person){
		if(person instanceof Customer){
			customerList.remove(person);
		}
		else if(person instanceof Staff){
			staffList.remove(person);
		}
		else if(person instanceof Supplier){
			supplierList.remove(person);
		}
	}
}