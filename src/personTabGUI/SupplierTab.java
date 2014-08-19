package personTabGUI;

import group2.PersonDB;

import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Choice;
import java.awt.Insets;
import java.awt.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

import person.Person;
import person.Staff;
import person.Supplier;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class SupplierTab extends PersonTab{
	
	
	private String contactName, vatNumber;
	private JTextField vatNumberField,contactNameField;
	private JLabel vatNumberLabel,contactNameLabel;


	public void setTextField(int index, ArrayList<Person> list){
		
		super.setTextField(index, list);
		
		vatNumberField.setText(((Supplier)person).getVatNumber());
		contactNameField.setText(((Supplier)person).getContactName());
		addItemsToCombobox(personDB.getSupplierList());
		comboBox.setSelectedIndex(index);
		revalidate();
		repaint();
	}
	
	public void clearTextFields(ArrayList<Person> list){

		super.clearTextFields(list);

		vatNumberField.setText("");
		contactNameField.setText("");
		revalidate();
		repaint();
	}
	
	
	
	/**
	 * Create the panel.
	 */
	public SupplierTab(PersonDB personDB) {
		
		super(personDB);
		
		contactNameLabel = new JLabel("Contact Name");
		vatNumberLabel = new JLabel("Vat Number");
		comboboxLabel.setText("Supplier List");
		
		vatNumberField = new JTextField();
		contactNameField = new JTextField();

		//Set the boundaries for each element
		contactNameField.setBounds(150, 160, 265, 20);
		contactNameField.setColumns(10);
		vatNumberField.setBounds(150, 185, 265, 20);
		vatNumberField.setColumns(10);
		contactNameLabel.setBounds(9, 160, 93, 14);
		vatNumberLabel.setBounds(9, 185, 94, 14);
		
		
		setTextField(0,personDB.getSupplierList());
		setFieldEditable(false);
		addAllElements();	
	}
	
	public void addAllElements(){
	
		super.addAllElements();
		add(vatNumberField);
		add(contactNameField);
		add(vatNumberLabel);
		add(contactNameLabel);	
	}
	
	public void personDetailsForm(){	
		super.personDetailsForm();
		
		vatNumber = null;
		contactName = null;
	
		if(!contactNameField.getText().equals(""))
			contactName =contactNameField.getText();
		else {
			errorMessage = errorMessage +"Contact name field cannot be empty!\n";	
		}	
		if(!vatNumberField.getText().equals(""))
			vatNumber =vatNumberField.getText();
		else {
			errorMessage = errorMessage +"VAT number field cannot be empty!\n";	
		}		
		
		if(name!=null && address!=null && email!=null && contactNumber!=null && vatNumber!=null && contactName!=null){
			
			if(editMode){
				personDB.changePersonDetails(person, name, email, contactNumber, address, 0, null, vatNumber, contactName);
				setTextField(comboBox.getSelectedIndex(), personDB.getSupplierList());
				valid = true;
			}
			else{
				
				
				valid = true;
				personDB.createNewPerson(person,name, email, contactNumber, address,  0, null,contactName, vatNumber);
				setTextField(personDB.getSupplierList().size()-1,personDB.getSupplierList());
			}
			deletePersonButton.setEnabled(true);
			
			newPersonButton.setEnabled(true);
			newPersonButton.setVisible(true);
			
			editPersonButton.setEnabled(true);
			editPersonButton.setVisible(true);
			
			submitButton.setVisible(false);
			
			
			cancelButton.setVisible(false);
			cancelEditButton.setVisible(false);
		}	
	
		else{
			JOptionPane.showMessageDialog(null, ""+errorMessage);
		}	
		revalidate();
		repaint();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		super.actionPerformed(e);	
	
		if(e.getSource()==submitButton){
			if(submitButtonMode == 2){
				editMode = true;
				personDetailsForm();
			}
			else if(submitButtonMode == 1){
				editMode = false;
				personDetailsForm();
			}
		}
		
		if(valid){
			setFieldEditable(false);
			valid = false;
		}
		if(e.getSource()==newPersonButton){	
			clearTextFields(personDB.getSupplierList());
			super.actionPerformed(e);
			setFieldEditable(true);	
		}	
		if(e.getSource()==editPersonButton){	
			setFieldEditable(true);	
		}	
		if(e.getSource()==cancelButton){			
			setFieldEditable(false);
			setTextField(personDB.getSupplierList().size()-1,personDB.getSupplierList());	
			if(!(personDB.getSupplierList().size()>0))
				clearTextFields(personDB.getSupplierList());
		}
		if(e.getSource()==cancelEditButton){		
			setTextField(comboBox.getSelectedIndex(),personDB.getSupplierList());
			setFieldEditable(false);
			if(!(personDB.getSupplierList().size()>0))
				clearTextFields(personDB.getSupplierList());
		}
		
		if(e.getSource()==deletePersonButton){
			deletePerson(person, personDB.getSupplierList());
		}
		revalidate();
  		repaint();
	}
	
	public void setFieldEditable(boolean editable){
		super.setFieldEditable(editable);
		vatNumberField.setEditable(editable);
		contactNameField.setEditable(editable);
	}
	
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
	          setTextField(comboBox.getSelectedIndex(),personDB.getSupplierList());
	  		revalidate();
	  		repaint();
	       }
		
	}
}
