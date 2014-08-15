package group2;

import java.util.ArrayList;

import javax.swing.JPanel;

public class CustomerTab extends JPanel{
	private ArrayList<Customer> customerList;
	private int accessLevel;
	
	public CustomerTab(ArrayList<Customer> customerList, int accessLevel) {
		this.customerList = customerList;
		this.accessLevel = accessLevel;
	}
}
