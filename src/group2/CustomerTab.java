package group2;

import java.util.ArrayList;

import javax.swing.JPanel;

public class CustomerTab extends JPanel{
	public ArrayList<Customer> customerList;
	/**
	 * Create the panel.
	 */
	public CustomerTab(ArrayList<Customer> customerList) {
		this.customerList = customerList; 
		
	}
}
