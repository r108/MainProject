package group2;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import person.Person;
import person.Supplier;


public class ProductTab extends JPanel implements ActionListener, ItemListener{

	
	protected boolean editMode;
	protected boolean valid;
	protected int submitButtonMode;
	protected boolean emptiedList;
	protected PersonDB PersonDB;
	private StockControl stockControl;
	protected Vector<String> comboboxItems;
	protected Product product;
	private Supplier supplier;
	protected String name, category, description;
	private double supplierPrice, retailPrice, profitMargin;
	protected JButton newProductButton,editProductButton,deleteProductButton,submitButton,cancelButton,cancelEditButton;
	protected JTextField nameField,categoryField,supplierField,supplierPriceField,retailPriceField,profitMarginField;
	protected JTextArea descriptionField;
	protected JLabel idLabel, idNumberLabel,nameLabel,categoryLabel,descriptionLabel,supplierLabel,supplierPriceLabel,retailPriceLabel,profitMarginLabel,comboboxLabel;
	protected JScrollPane scrollPane;
	protected JComboBox<String> comboBox;
	protected DefaultComboBoxModel<String> comboboxModel;
	private final boolean PRIVILEDGED_ACCESS = RetailSystemDriver.isPriviledged(); 
	
	
	
	
	public ProductTab(StockControl stockControl){
		this.stockControl = stockControl;
		valid = false;
		emptiedList = false;
		submitButtonMode = 0;
		
		comboboxItems = new Vector<String>();
		comboboxModel = new DefaultComboBoxModel<String>(comboboxItems);
		comboBox = new JComboBox<String>(comboboxModel);
		
		
		
		newProductButton = new JButton("Add New");
		editProductButton = new JButton("Edit");
		deleteProductButton = new JButton("Delete");
		submitButton = new JButton("Submit");
		cancelButton = new JButton("Cancel");
		cancelEditButton = new JButton("Cancel");
		
		
		nameField = new JTextField();
		categoryField = new JTextField();
		supplierField = new JTextField();
		supplierPriceField = new JTextField();
		retailPriceField = new JTextField();
		profitMarginField = new JTextField();
		descriptionField = new JTextArea(5,20);
		
		idLabel = new JLabel("Product ID");
		idNumberLabel = new JLabel("0");
		nameLabel = new JLabel("Product Name");
		categoryLabel = new JLabel("Category");
		descriptionLabel = new JLabel("Description");
		supplierLabel = new JLabel("Supplier Price");
		supplierPriceLabel = new JLabel("Supplier");
		retailPriceLabel = new JLabel("Retail Price");
		profitMarginLabel = new JLabel("Profit Margin");
		comboboxLabel = new JLabel("Product List");
		
		if(!PRIVILEDGED_ACCESS){
			newProductButton.setEnabled(false);
			editProductButton.setEnabled(false);
			deleteProductButton.setEnabled(false);
		}
		
		newProductButton.addActionListener(this);
		editProductButton.addActionListener(this);
		deleteProductButton.addActionListener(this);
		submitButton.addActionListener(this);
		submitButton.setVisible(false);
		cancelButton.addActionListener(this);
		cancelButton.setVisible(false);
		cancelEditButton.addActionListener(this);
		cancelEditButton.setVisible(false);
	
		idNumberLabel.setBounds(153, 7, 265, 20);	
		nameField.setBounds(150, 30, 265, 20);
		nameField.setColumns(10);
		categoryField.setBounds(150, 55, 265, 20);
		categoryField.setColumns(10);
		//descriptionField.setBounds(150, 80, 265, 50);
		supplierField.setBounds(150, 135, 265, 20);
		supplierPriceField.setBounds(150, 160, 265, 20);
		retailPriceField.setBounds(150, 185, 265, 20);
		profitMarginField.setBounds(150, 210, 265, 20);
		
		
		idLabel.setBounds(9, 10, 93, 14);	
		nameLabel.setBounds(9, 33, 93, 14);		
		categoryLabel.setBounds(9, 58, 93, 14);
		descriptionLabel.setBounds(9, 83, 93, 14);
		supplierLabel.setBounds(9, 136, 93, 14);
		supplierPriceLabel.setBounds(9, 161, 93, 14);
		retailPriceLabel.setBounds(9, 186, 93, 14);
		profitMarginLabel.setBounds(9, 211, 93, 14);
		
		scrollPane = new JScrollPane(descriptionField);
		scrollPane.setBounds(150, 80, 265, 50);
		
		
		submitButton.setBounds(150, 250, 106, 23);
		newProductButton.setBounds(14, 320, 130, 23);
		cancelButton.setBounds(14, 320, 130, 23);
		cancelEditButton.setBounds(149, 320, 130, 23);
		editProductButton.setBounds(149, 320, 130, 23);
		deleteProductButton.setBounds(285, 320, 130, 23);
		
		comboboxLabel.setBounds(15, 285, 120, 20);
		comboBox.setBounds(150, 285, 265, 20);
		comboBox.addItemListener(this);
		addAllElements();
		setLayout(null);
		setVisible(true);	
			
		setTextField(0,stockControl.getStockList());
		//addItemsToCombobox(stockControl.getStockList());
	}
	
	public void addAllElements(){
		
		add(newProductButton);
		add(deleteProductButton);
		add(submitButton);
		add(editProductButton);
		add(cancelButton);
		add(cancelEditButton);
		add(comboBox);
		add(comboboxLabel);
		
		add(nameField);
		add(descriptionField);
		add(supplierField);
		add(retailPriceField);
		add(supplierPriceField);
		add(profitMarginField);
		add(categoryField);
		add(scrollPane);
		
		add(idLabel);
		add(idNumberLabel);
		add(nameLabel);
		add(descriptionLabel);
		add(supplierLabel);
		add(retailPriceLabel);
		add(supplierPriceLabel);
		add(profitMarginLabel);
		add(categoryLabel);
	}
	
	
	public void addItemsToCombobox(ArrayList<Object []> list){
		comboboxItems.clear();
		String item = "";
		for(Object object[] : list){
			item = ((Product)object[0]).getProductName();
			comboboxItems.add(item);
		}
		
		
		revalidate();
		repaint();
	}
	
	public void setTextField(int index,ArrayList<Object[]> list){

		
		
		
		if(list.size()>0){
		//	product = list.get(index);
			//idNumberLabel.setText(""+product.getProductID());
			emptiedList = false;
		}
		String item = "";
		for(Object object[] : list){
			product = ((Product)object[0]);
			
			nameField.setText(product.getProductName());
			categoryField.setText(product.getProductCategory());
			idNumberLabel.setText(""+product.getProductID());
			supplierField.setText(product.getSupplier().getName());
			descriptionField.setText(product.getProductDescription());
			supplierPriceField.setText(""+product.getSupplierPrice());
			retailPriceField.setText(""+product.getRetailPrice());
			profitMarginField.setText(""+product.getProfitMargin());
			comboboxItems.add(item);
		}
		addItemsToCombobox(list);
		comboBox.setSelectedIndex(index);
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
