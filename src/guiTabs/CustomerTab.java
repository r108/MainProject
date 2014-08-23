package guiTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import person.Customer;
import person.Person;
import retailSystem.PersonDB;

@SuppressWarnings("serial")
public class CustomerTab extends PersonTab {

	public CustomerTab(PersonDB personDB) {
		super(personDB);
		
		comboboxLabel.setText("Customer List");
		setTextField(0,personDB.getCustomerList());
		setFieldEditable(false);
		addAllElements();	
	}

	public void setTextField(int index, ArrayList<Person> list){
		
		super.setTextField(index,list);
			
		addItemsToCombobox(personDB.getCustomerList());
		comboBox.setSelectedIndex(index);
		revalidate();
		repaint();
	}
	
	public void personDetailsForm(){	
		super.personDetailsForm();
		if(name!=null && address!=null && email!=null && contactNumber!=null){
		
			if(editMode){
				personDB.changePersonDetails(person, name, email, contactNumber, address, 0, null, null, null);
				setTextField(comboBox.getSelectedIndex(), personDB.getCustomerList());
				valid = true;
			}
			else{
				valid = true;
				personDB.createNewPerson(person,name, email, contactNumber, address,  0, null,null, null);
				setTextField(personDB.getCustomerList().size()-1,personDB.getCustomerList());
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
			clearTextFields(personDB.getCustomerList());
			setFieldEditable(true);			
		}	
		if(e.getSource()==editPersonButton){	
			setFieldEditable(true);	
		}	
		if(e.getSource()==cancelButton){
			setFieldEditable(false);
			setTextField(personDB.getCustomerList().size()-1,personDB.getCustomerList());	
			if(!(personDB.getCustomerList().size()>0))
				clearTextFields(personDB.getCustomerList());
		}
		if(e.getSource()==cancelEditButton){		
			setTextField(comboBox.getSelectedIndex(),personDB.getCustomerList());
			setFieldEditable(false);
			if(!(personDB.getCustomerList().size()>0))
				clearTextFields(personDB.getCustomerList());
		}
		if(e.getSource()==deletePersonButton){
			deletePerson(person, personDB.getCustomerList());
		}
		revalidate();
  		repaint();
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			setTextField(comboBox.getSelectedIndex(),personDB.getCustomerList());
	  		revalidate();
	  		repaint();
       }
		
	}

}
