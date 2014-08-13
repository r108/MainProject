package group2;

/**
 * @author Roland Katona
 *
 */
public class Staff extends Person{
	
	private String password;
	private int accessLevel;
	
	/**
	 * Default constructor
	 */
	public Staff() {
		super();
		password = "";
		accessLevel=0;
	}
	
	
	/**
	 * @param name
	 * @param email
	 * @param contactNumber
	 * @param address
	 * @param password
	 * @param accesslevel
	 */
	public Staff(String name, String email, String contactNumber, String address, String password, int accesslevel) {
		super(name, email, contactNumber, address);
		this.password = password;
		this.accessLevel = accesslevel;
	}
	
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return accessLevel
	 */
	public int getAccessLevel() {
		return accessLevel;
	}
	
	
	/**
	 * @param accessLevel
	 */
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	/* (non-Javadoc)
	 * @see group2.Person#displayDetails()
	 */
	public void displayDetails(){
		super.displayDetails();
		System.out.println("Staff Access Level : "+accessLevel);	
	}
	
}
