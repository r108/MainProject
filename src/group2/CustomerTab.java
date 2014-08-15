package group2;

import java.util.ArrayList;

import javax.swing.JPanel;

public class CustomerTab extends JPanel{
	private ArrayList<Customer> customerList;
	public CustomerTab(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
}
