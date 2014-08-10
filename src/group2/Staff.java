package group2;

public class Staff extends Person{
	
	private String password;
	private int accessLevel;
	public Staff() {
		super();
		password = "";
		accessLevel=0;
	}
	public Staff(String name, String email, String contactNumber, String address, String password, int accesslevel) {
		super(name, email, contactNumber, address);
		this.password = password;
		this.accessLevel = accesslevel;
		// TODO Auto-generated constructor stub
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public void displayDetails(){
		super.displayDetails();
		System.out.println("Staff Access Level : "+accessLevel);	
	}
	
}
