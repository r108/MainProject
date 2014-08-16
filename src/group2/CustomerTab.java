package group2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CustomerTab extends JPanel{
	private ArrayList<Customer> customers;
	private int selectedIndex = 0, accessLevel = 0;
	protected ArrayList<String>listItems;
	private Customer  c =  new Customer();
	private Customer newCustomer;
	
	private JButton btnNewCustomer = new JButton("New Customer");
	private JButton btnEditCustomer = new JButton("Edit Customer");
	private JButton btnDeleteCustomer = new JButton("Remove Customer");
	private JButton btnDoneEditing = new JButton("Done editing");
	
	private JLabel lblCustomerName = new JLabel("Customer Name");
	private JLabel lblCustomerId = new JLabel("Customer ID");
	private JLabel lblContact = new JLabel("Phone");
	private JLabel lblEmail = new JLabel("Email");
	private JLabel lblAddress = new JLabel("Address");
	
	private JComboBox comboBox;
	private JTextArea textArea = new JTextArea();
	
	private final JTextField vatNumField = new JTextField();
	private final JTextField contactField = new JTextField();
	private final JTextField emailField = new JTextField();
	private final JTextField addressField = new JTextField();
	private final JTextField nameField = new JTextField();
	private final JTextField supplierIdField = new JTextField();
	private final JButton btnCreateCustomer = new JButton("Create new");
	
	/**
	 * Create the panel.
	 */
	public CustomerTab(ArrayList<Customer> customerList, int accessLevel) {
		this.customers = customerList;
		this.accessLevel = accessLevel;

		//Set the access level for the user
		this.accessLevel = accessLevel;
		
		//Initialize components
		initialize();
		
		//Change the text in the text box
		comboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent arg0) {
					selectedIndex = comboBox.getSelectedIndex();
					Customer pers = customers.get(selectedIndex);
					c = pers;
					textArea.setText("Customer name: \t" 	+pers.getName()
							 +"\nCustomer ID: \t\t"			+pers.getId()
							 +"\nPhone: \t\t" 				+pers.getContactNumber()
							 +"\nEmail: \t\t" 				+pers.getEmail()
							 +"\nAddress: \t\t" 			+pers.getAddress());
				
			}
		});
		
		//Add a supplier (brings up text fields)
		btnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startCreationMode();
			}
		});
		
		//Create a new supplier
		btnCreateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newCustomer = new Customer();
				newCustomer.setName(nameField.getText());
				newCustomer.setAddress(addressField.getText());
				newCustomer.setContactNumber(contactField.getText());
				newCustomer.setEmail(emailField.getText());
				customers.add(newCustomer);
				comboBox.addItem(newCustomer.getName());
				endCreationMode();
			}
		});
		
		//Edit a supplier
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startEditMode();
			}
		});
		
		//Turn off edit mode
		btnDoneEditing.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				endEditMode();
			}
		});
		
		//Remove a supplier (BLANK)
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//Add everything to the panel
		addAllElements();
	}
	public void startCreationMode(){
		btnCreateCustomer.setVisible(true);
		
		nameField.setVisible(true);
		vatNumField.setVisible(true);
		addressField.setVisible(true);
		contactField.setVisible(true);
		emailField.setVisible(true);
		
		lblCustomerName.setVisible(true);
		lblContact.setVisible(true);
		lblAddress.setVisible(true);
		lblEmail.setVisible(true);
	}
	public void endCreationMode(){
		btnCreateCustomer.setVisible(false);
		
		nameField.setVisible(false);
		vatNumField.setVisible(false);
		addressField.setVisible(false);
		contactField.setVisible(false);
		emailField.setVisible(false);
		
		lblCustomerName.setVisible(false);
		lblContact.setVisible(false);
		lblAddress.setVisible(false);
		lblEmail.setVisible(false);
	}
	public void startEditMode(){
		btnDoneEditing.setVisible(true);
		
		nameField.setVisible(true);
		vatNumField.setVisible(true);
		addressField.setVisible(true);
		contactField.setVisible(true);
		emailField.setVisible(true);
		
		lblCustomerName.setVisible(true);
		lblContact.setVisible(true);
		lblAddress.setVisible(true);
		lblEmail.setVisible(true);
		
		boolean emailValid = false;
		if (!nameField.getText().equals(""))
			c.setName(nameField.getText());
		else
			c.setName(customers.get(selectedIndex).getName());
		
		if (!addressField.getText().equals(""))
			c.setAddress(addressField.getText());
		else
			c.setAddress(customers.get(selectedIndex).getAddress());
		
		if (!contactField.getText().equals(""))
			c.setContactNumber(contactField.getText());
		else
			c.setContactNumber(customers.get(selectedIndex).getName());
		
		
		if (!emailField.getText().equals("")){
			c.setEmail(emailField.getText());
			emailValid = true;
		}
		else
			c.setEmail(customers.get(selectedIndex).getEmail());
	}
	
	public void endEditMode(){
		if (emailField.getText().contains("@")) {
		customers.set(selectedIndex, c);
		
		btnDoneEditing.setVisible(false);
		
		nameField.setVisible(false);
		vatNumField.setVisible(false);
		addressField.setVisible(false);
		contactField.setVisible(false);
		emailField.setVisible(false);
		
		lblCustomerName.setVisible(false);
		lblContact.setVisible(false);
		lblAddress.setVisible(false);
		lblEmail.setVisible(false);
		}
		else
			JOptionPane.showMessageDialog(null, "Invalid email format!");
	}
	
	public void initialize(){
		
		//Set the initial visiblity of elements
		lblCustomerName.setVisible(false);
		lblCustomerId.setVisible(false);
		lblContact.setVisible(false);
		lblAddress.setVisible(false);
		lblEmail.setVisible(false);
		
		nameField.setVisible(false);
		supplierIdField.setVisible(false);
		vatNumField.setVisible(false);
		addressField.setVisible(false);
		contactField.setVisible(false);
		emailField.setVisible(false);
		
		//Buttons only visble within a mode
		btnDoneEditing.setVisible(false);
		btnCreateCustomer.setVisible(false);
		
		//Buttons visible according to access level
		switch (accessLevel){
			case 1: {
				btnEditCustomer.setVisible(false);
				btnDeleteCustomer.setVisible(false);
				btnNewCustomer.setVisible(false);
				break;
			}
			case 2:{
				btnEditCustomer.setVisible(true);
				btnDeleteCustomer.setVisible(true);
				btnNewCustomer.setVisible(true);
				break;
			}
			default: {
				btnEditCustomer.setVisible(false);
				btnDeleteCustomer.setVisible(false);
				btnNewCustomer.setVisible(false);
				break;
			}
		}
		
		//Set the boundaries for each element
		addressField.setBounds(509, 116, 192, 62);
		addressField.setColumns(10);
		emailField.setBounds(509, 90, 192, 20);
		emailField.setColumns(10);
		contactField.setBounds(509, 65, 192, 20);
		contactField.setColumns(10);
		vatNumField.setBounds(509, 65, 192, 20);
		vatNumField.setColumns(10);
		nameField.setBounds(509, 40, 192, 20);
		nameField.setColumns(10);
		
		textArea.setBounds(60, 43, 298, 175);
		
		btnEditCustomer.setBounds(201, 260, 131, 23);
		btnDeleteCustomer.setBounds(342, 260, 131, 23);
		btnDoneEditing.setBounds(565, 219, 136, 23);
		btnNewCustomer.setBounds(60, 260, 131, 23);
		btnCreateCustomer.setBounds(565, 219, 136, 23);
		
		lblCustomerName.setBounds(409, 43, 93, 14);
		lblContact.setBounds(409, 68, 93, 14);
		lblAddress.setBounds(409, 116, 93, 14);
		lblEmail.setBounds(409, 93, 46, 14);
		
		//Set up the text in the combo box
		updateComboBox();
		comboBox.setBounds(60, 229, 264, 20);
		
		//Set the initial choice for the combo box
		c = customers.get(comboBox.getSelectedIndex());
		setLayout(null);
		
		//Initialize the text in the text box
		textArea.setText("Business name: \t" 	+c.getName()
				 +"\nCustomer ID: \t\t"			+c.getId()
				 +"\nEmail: \t\t" 				+c.getEmail()
				 +"\nAddress: \t\t" 			+c.getAddress());
		textArea.setEditable(false);
		
		addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
			}
		});
		
	}
	public void updateComboBox(){
		listItems = new ArrayList<String>();
		for(Person i:customers)
			listItems.add(i.getName());
		
		String [] items = new String[listItems.size()];
		int index = 0;
		for(String i: listItems)
			items[index++] = i;
		
		comboBox = new JComboBox(items);
	}
	public void addAllElements(){
		add(btnNewCustomer);
		add(btnDeleteCustomer);
		add(btnDoneEditing);
		add(btnEditCustomer);
		
		add(comboBox);
		add(textArea);
		
		add(nameField);
		add(contactField);
		add(emailField);
		add(addressField);
		
		add(lblContact);
		add(lblEmail);
		add(lblAddress);
		add(lblCustomerName);
		add(btnCreateCustomer);
	}
}
