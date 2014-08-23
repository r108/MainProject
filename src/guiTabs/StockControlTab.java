
package guiTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import order.StockDBControl;
import order.StockItem;
import person.Person;
import retailSystem.Product;

public class StockControlTab extends JPanel implements ActionListener, ItemListener{

	protected boolean emptiedList;
	private StockDBControl stockDBControl;
	private ArrayList<Object> products;
	protected Vector<String> comboboxItems;
	protected Product product;
	protected String name;
	protected JTextArea productField;
	protected JTextArea quantityField;
	protected JTextArea idField;
	protected JTextArea descriptionField;
	protected JLabel productLabel;
	protected JLabel productIdLabel;
	protected JLabel quantityLabel;
	protected JButton restockButton;
	
	public StockControlTab(StockDBControl stockDBControl){
		this.setStockDBControl(stockDBControl);
		
		productLabel = new JLabel("Product Name");
		quantityLabel = new JLabel("Quantity");
		productIdLabel = new JLabel("Product ID");
		idField = new JTextArea();
		productField = new JTextArea();
		productField.setEditable(false);
		quantityField = new JTextArea();
		quantityField.setEditable(false);
		idField.setEditable(false);
		
		restockButton = new JButton("Restock");	
		
		restockButton.addActionListener(this);
		restockButton.setBounds(209,320,100,23);
		
		
		
		productIdLabel.setBounds(9,10,93,14);	
		idField.setBounds(9,50,93,300);	
		
		productLabel.setBounds(100,10,93,14);	
		productField.setBounds(100,50,200,300);
		
		//productField.setColumns(15);
		
		quantityLabel.setBounds(309,10,93,14);
		quantityField.setBounds(309,50,100,300);
					
		addAllElements();
		setLayout(null);
		setVisible(true);	
		setProductTextField(0,stockDBControl.getStockList());
		setQuantityTextField(0,stockDBControl.getStockList());
	}
	//test comment
	public void addAllElements(){
		add(idField);
		add(productIdLabel);
		add(productLabel);
		add(productField);
		add(quantityLabel);
		add(quantityField);
		//add(restockButton);
	}
	
	public void setProductTextField(int index, ArrayList<StockItem> list){

		for(StockItem stockItem : list){
			Product p = stockItem.getProduct();
			String prod = p.getProductName();
			idField.append("\n" + p.getProductID());
			productField.append("\n " + prod);
		}
	 
		if(list.size()>0){
			emptiedList = false;
		}
	}
	
	public void setQuantityTextField(int index, ArrayList<StockItem> list){
		
		for(StockItem stockItem: list){
			int quantity = stockItem.getQuantity();
			quantityField.append("\n" + quantity);
		}
		
		if(list.size()>0){
			emptiedList = false;
		}
	}
	
	public void Restock(Person person, ArrayList<Person> list){
		boolean valid = false;
		String value = JOptionPane.showInputDialog(null, "Enter how many :", 0);
		do{
			try {
				int quantity = Integer.parseInt(value);
				valid = true;
			} catch (NumberFormatException error) {
				// TODO Auto-generated catch block
				value = JOptionPane.showInputDialog(null, "Wrong input!!Enter how many :", 0);
				error.printStackTrace();
			}
		}while(!valid);
		System.out.println(value);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == restockButton){
			Restock(null, null);
		}
		
	}
	
	
	public StockDBControl getstockDBControl() {
		return stockDBControl;
	}

	public void setStockDBControl(StockDBControl stockDBControl) {
		this.stockDBControl = stockDBControl;
	}
	
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
