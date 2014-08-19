package personTabGUI;

import group2.PersonDB;
import group2.RetailSystemDriver;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import person.Person;

public class PersonTab extends JPanel implements ActionListener, ItemListener{
	
	protected boolean editMode;
	protected boolean valid;
	protected int submitButtonMode;
	protected boolean emptiedList;
	protected PersonDB personDB;
	protected Vector<String> comboboxItems;
	protected Person person;
	protected String name, email, contactNumber, address,errorMessage;
	protected JButton newPersonButton,editPersonButton,deletePersonButton,submitButton,cancelButton,cancelEditButton;
	protected JTextField nameField,emailField,contactNumberField;
	protected JTextArea addressField;
	protected JLabel idLabel,idNumberLabel,nameLabel,emailLabel,contactNumberLabel,addressLabel,comboboxLabel;
	protected JScrollPane scrollPane;
	protected JComboBox<String> comboBox;
	protected DefaultComboBoxModel<String> comboboxModel;
	private final boolean PRIVILEDGED_ACCESS = RetailSystemDriver.isPriviledged(); 
	
	
	
	
	
	public PersonTab(PersonDB personDB) {
		
		this.personDB = personDB;
		valid = false;
		emptiedList = false;
		submitButtonMode = 0;
		
		comboboxItems = new Vector<String>();
		comboboxModel = new DefaultComboBoxModel<String>(comboboxItems);
		comboBox = new JComboBox<String>(comboboxModel);
	
		idLabel = new JLabel("ID");
		idNumberLabel = new JLabel("1");
		nameLabel = new JLabel("Name");
		emailLabel = new JLabel("Email");
		contactNumberLabel = new JLabel("Contact Number");
		addressLabel = new JLabel("Address");
	
		comboboxLabel = new JLabel("");
		
		nameField = new JTextField();
		contactNumberField = new JTextField();
		emailField = new JTextField();
		addressField = new JTextArea(5,20);
		
		newPersonButton = new JButton("Add New");
		editPersonButton = new JButton("Edit");
		deletePersonButton = new JButton("Delete");
		submitButton = new JButton("Submit");
		cancelButton = new JButton("Cancel");
		cancelEditButton = new JButton("Cancel");
		
		if(!PRIVILEDGED_ACCESS){
			newPersonButton.setEnabled(false);
			editPersonButton.setEnabled(false);
			deletePersonButton.setEnabled(false);
		}
		
		newPersonButton.addActionListener(this);
		editPersonButton.addActionListener(this);
		deletePersonButton.addActionListener(this);
		submitButton.addActionListener(this);
		submitButton.setVisible(false);
		cancelButton.addActionListener(this);
		cancelButton.setVisible(false);
		cancelEditButton.addActionListener(this);
		cancelEditButton.setVisible(false);
	
		idNumberLabel.setBounds(153, 7, 265, 20);	
		nameField.setBounds(150, 30, 265, 20);
		nameField.setColumns(10);
		emailField.setBounds(150, 55, 265, 20);
		emailField.setColumns(10);
		contactNumberField.setBounds(150, 80, 265, 20);
		contactNumberField.setColumns(10);
		
		idLabel.setBounds(9, 10, 93, 14);	
		nameLabel.setBounds(9, 33, 93, 14);		
		emailLabel.setBounds(9, 58, 46, 14);
		contactNumberLabel.setBounds(9, 83, 93, 14);
		addressLabel.setBounds(9, 106, 93, 14);
		
		
		scrollPane = new JScrollPane(addressField);
		scrollPane.setBounds(150, 105, 265, 50);
		
		
		submitButton.setBounds(150, 250, 106, 23);
		newPersonButton.setBounds(14, 320, 130, 23);
		cancelButton.setBounds(14, 320, 130, 23);
		cancelEditButton.setBounds(149, 320, 130, 23);
		editPersonButton.setBounds(149, 320, 130, 23);
		deletePersonButton.setBounds(285, 320, 130, 23);
		
		comboboxLabel.setBounds(15, 285, 120, 20);
		comboBox.setBounds(150, 285, 265, 20);
		comboBox.addItemListener(this);
		
		setLayout(null);
		setVisible(true);	
	}


	public void addItemsToCombobox(ArrayList<Person> list){
		comboboxItems.clear();
		String item = "";
		for(Person person : list){
			item = person.getName();
			comboboxItems.add(item);
		}
		revalidate();
		repaint();
	}
	
	public void setTextField(int index,ArrayList<Person> list){

		if(list.size()>0){
			person = list.get(index);
			idNumberLabel.setText(""+person.getId());
			emptiedList = false;
		}
		
		nameField.setText(person.getName());
		addressField.setText(person.getAddress());
		contactNumberField.setText(person.getContactNumber());
		emailField.setText(person.getEmail());
		addItemsToCombobox(list);
		comboBox.setSelectedIndex(index);
	}
	
	public void clearTextFields(ArrayList<Person> list){
		if(list.size()>0){
			idNumberLabel.setText(""+Person.getUniqueId());
		}
		else{
			idNumberLabel.setText("");
			emptiedList = true;
		}	
		nameField.setText("");
		contactNumberField.setText("");
		emailField.setText("");
		addressField.setText("");
	}
	
	
	public void addAllElements(){
		
		add(newPersonButton);
		add(deletePersonButton);
		add(submitButton);
		add(editPersonButton);
		add(cancelButton);
		add(cancelEditButton);
		add(comboBox);
		add(comboboxLabel);
		
		add(nameField);
		add(contactNumberField);
		add(emailField);
		add(scrollPane);
		
		add(idLabel);
		add(idNumberLabel);
		add(nameLabel);
		add(contactNumberLabel);
		add(emailLabel);
		add(addressLabel);
	}

	public void setFieldEditable(boolean editable){
		nameField.setEditable(editable);
		contactNumberField.setEditable(editable);
		emailField.setEditable(editable);
		addressField.setEditable(editable);		
	}
	
	public void deletePerson(Person person, ArrayList<Person> list){
		
		int answer=JOptionPane.showConfirmDialog(null, "Do you really want to delete "+person.getName()+"?", " CONFIRMATION " , JOptionPane.YES_NO_OPTION);//displaying JOptionPane.YES_NO_OPTION Confirmdialog box
		if ( answer==JOptionPane.YES_OPTION ){
			//list.remove(person);
			personDB.deletePersonFromList(person);
			setTextField(list.size()-1, list);
			clearTextFields(list);
			revalidate();
			repaint();
			
		}
		if(list.size()>0){
			setTextField(list.size()-1, list);
			
		}
		else{
			clearTextFields(list);
			deletePersonButton.setEnabled(false);
			editPersonButton.setEnabled(false);
			submitButton.setVisible(false);
		}
		revalidate();
		repaint();
	}
	
	public void personDetailsForm(){	
		name = null;
		email = null;
		contactNumber = null;
		address = null;
		
		errorMessage = "";
		
		if(!nameField.getText().equals("")){
			name = nameField.getText();
		}
		else{ 
			errorMessage = errorMessage +"Name field cannot be empty!\n";	
		}
		if(!emailField.getText().equals("")){
			email = (emailField.getText());
			if(!RetailSystemDriver.validateEmail(email)){
				email = null;
				errorMessage = errorMessage +"\nInvalid email address!!\nValid format is john.dough@example.com";
			}
		}	
		else{
			errorMessage = errorMessage +"Email field cannot be empty!\n";	
		}
		if(!contactNumberField.getText().equals("")){
			contactNumber = (contactNumberField.getText());
			try {
				Integer.parseInt(contactNumber);
			} catch (NumberFormatException e) {
				errorMessage = errorMessage +"\nInvalid phone number!!\nOnly numbers are allowed!";
				e.printStackTrace();
			}
			if(!RetailSystemDriver.validateContactNumber(contactNumber)){
				contactNumber = null;
				errorMessage = errorMessage +"\nInvalid phone number!!\nValid format is 0xxxxxxxxx";
			}
		}
		else{
			errorMessage = errorMessage +"Contact number field cannot be empty!\n";	
		}
		
		if(!addressField.getText().equals(""))
			address = (addressField.getText());
		else{
			errorMessage = errorMessage +"Address field cannot be empty!\n";	
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==newPersonButton){
			submitButton.setVisible(true);
			newPersonButton.setVisible(false);
			deletePersonButton.setEnabled(false);
			editPersonButton.setEnabled(false);
			cancelButton.setVisible(true);
			submitButtonMode = 1;		
			if(emptiedList){
				idNumberLabel.setText(""+Person.getUniqueId());
			}			
		}	
		if(e.getSource()==editPersonButton){
			submitButtonMode = 2;
			editPersonButton.setVisible(false);
			submitButton.setVisible(true);
			cancelEditButton.setVisible(true);
			newPersonButton.setEnabled(false);
			deletePersonButton.setEnabled(false);
		}	
		if(e.getSource()==cancelButton){		
			submitButton.setVisible(false);
			newPersonButton.setVisible(true);
			cancelButton.setVisible(false);
			if(!emptiedList){
				deletePersonButton.setEnabled(true);
				editPersonButton.setEnabled(true);
			}
		}
		if(e.getSource()==cancelEditButton){
			submitButton.setVisible(false);
			cancelEditButton.setVisible(false);
			deletePersonButton.setEnabled(true);
			newPersonButton.setEnabled(true);
			editPersonButton.setEnabled(true);
			editPersonButton.setVisible(true);
		}
		revalidate();
  		repaint();
	}


	@Override
	public void itemStateChanged(ItemEvent e){
		
	}
	
}
