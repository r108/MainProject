package group2;

import java.util.ArrayList;
public class CustomerTab extends JPanel{
	private ArrayList<Customer> customers;
	private int accessLevel = 0;
	
	/**
	 * Create the panel.
	 */
	public CustomerTab(ArrayList<Customer> customerList, int accessLevel) {
		this.customers = customerList;
		this.accessLevel = accessLevel;
	}
}
