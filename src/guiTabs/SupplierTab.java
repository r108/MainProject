package guiTabs;




import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;



import person.Person;
import person.Supplier;
import retailSystem.PersonDB;

public class SupplierTab extends PersonTab{
	
	private JButton newSupplierButton;
	private JTabbedPane tabbedPane;
	private ProductTab productTab;
	private MainGUI gui;
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
	public SupplierTab(PersonDB personDB,JTabbedPane tabbedPane,MainGUI gui) {
		
		super(personDB);
		
		this.tabbedPane = tabbedPane;
		this.gui = gui;
		newSupplierButton = new JButton();
		newSupplierButton.addActionListener(this);
		contactNameLabel = new JLabel("Contact Name");
		vatNumberLabel = new JLabel("Vat Number");
		comboboxLabel.setText("Supplier List");
		
		vatNumberField = new JTextField();
		contactNameField = new JTextField();

		//Set the boundaries for each element
		contactNameField.setBounds(200, 160, 265, 20);
		contactNameField.setColumns(10);
		vatNumberField.setBounds(200, 185, 265, 20);
		vatNumberField.setColumns(10);
		contactNameLabel.setBounds(59, 160, 93, 14);
		vatNumberLabel.setBounds(59, 185, 94, 14);
		
		
		setTextField(0,personDB.getSupplierList());
		setFieldEditable(false);
		addAllElements();	
	}
	
	public JButton getNewSupplierButton(){
		return newSupplierButton;
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
			if(submitButtonMode == 3){
				editMode = false;
				personDetailsForm();
				if(valid){
					tabbedPane.setSelectedComponent(gui.getProductTab());
					gui.getProductTab().addItemsToSupplierCombobox(personDB.getSupplierList());
				}
			}
			else if(submitButtonMode == 2){
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
		
		if(e.getSource()==newSupplierButton){
			newPersonButton.doClick();
			submitButtonMode = 3;
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
