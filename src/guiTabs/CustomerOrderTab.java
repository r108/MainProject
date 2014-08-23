package guiTabs;







import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import order.StockDBControl;
import order.StockItem;
import retailSystem.Product;

public class CustomerOrderTab extends JPanel implements ActionListener, ItemListener{

	private JScrollPane scrollpane;
	private StockDBControl stockDBControl;
	protected Vector<String> comboBoxItems = new Vector<String>();	
	protected ArrayList<Integer> ItemsQuantity = new ArrayList<Integer>();
	protected ArrayList<Double> ItemsPrice = new ArrayList<Double>();
	protected DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(comboBoxItems);
	protected JComboBox<String> productComboBox = new JComboBox<String>(comboBoxModel);
	
	protected Vector<String> orderingListItems = new Vector<String>();
	protected DefaultListModel<String> listModel = new DefaultListModel<String>();
	protected JList<String> orderingList = new JList<String>(listModel); 
	
	protected ArrayList<Product> currentOrderList = new ArrayList<Product>();
	protected ArrayList<Integer> currentOrderListQuantity = new ArrayList<Integer>();
	
	protected JLabel productComboBoxLabel, quantityLabel, availableQuantityLabel, availableQuantityField,
					priceLabel, priceField, subtotalLabel, subtotalField;
	protected JTextField quantityTextField;
	protected JButton addButton;
	protected double subTotal = 0;
	
	
	public CustomerOrderTab(StockDBControl StockDBControl){
		
		this.stockDBControl = StockDBControl;
		
		
		scrollpane = new JScrollPane(orderingList);
		//product combo box with quantity set option
		setUpProductComboBox();
		setUpQuantityTextField();
		setUpAvailableQuantity();
		setUpPriceFields();
		setAddButton();
		setUpSubtotal();
		orderingList();		
		
		
		setLayout(null);
		setVisible(true);
		
	}
	
	//add product combo box
	private void setUpProductComboBox(){
		productComboBoxLabel = new JLabel("Products");
		productComboBoxLabel.setBounds(60, 10, 100, 20);
		productComboBox.setBounds(60, 30, 200, 20);		
		add(productComboBoxLabel);
		add(productComboBox);
		refreshComboBox(stockDBControl.getStockList());
		productComboBox.addItemListener(this);
	}
	
	//add product quantity combo box
	private void setUpQuantityTextField(){
		quantityLabel = new JLabel("pc(s)");
		quantityLabel.setBounds(270, 10, 50, 20);
		quantityTextField = new JTextField("1");
		quantityTextField.setBounds(270, 30, 30, 20);		
		add(quantityLabel);
		add(quantityTextField);		
	}
	
	//add product available quantity
	private void setUpAvailableQuantity(){
		availableQuantityLabel = new JLabel("Available");
		availableQuantityLabel.setBounds(330, 10, 70, 20);
		
		availableQuantityField = new JLabel(Integer.toString(ItemsQuantity.get(0)));
		availableQuantityField.setBounds(340, 30, 40, 20);
		add(availableQuantityLabel);
		add(availableQuantityField);				
	}
	
	//add product price fields
	private void setUpPriceFields(){
		priceLabel = new JLabel("Price");
		priceLabel.setBounds(400, 10, 40, 20);
		priceField = new JLabel(Double.toString(ItemsPrice.get(0)));
		priceField.setBounds(400, 30, 40, 20);
		add(priceLabel);
		add(priceField);				
	}
	
	//add Subtotal price fields
	private void setUpSubtotal(){
		subtotalLabel = new JLabel("Total:");
		subtotalLabel.setBounds(290, 300, 50, 20);
		subtotalField = new JLabel("0");
		subtotalField.setBounds(350, 300, 80, 20);
		add(subtotalLabel);
		add(subtotalField);
	}
		
	
	//add add button
	private void setAddButton(){
		addButton = new JButton("Add");
		addButton.setBounds(60, 60, 60, 20);
		addButton.addActionListener(this);
		add(addButton);
	}
		
	//add buying list
	private void orderingList(){		
		scrollpane.setBounds(60, 100, 410, 180);
		//add(new JScrollPane(orderingList));
		add(scrollpane);
	}
	
	// refresh combo Box with price and quantity
	private void refreshComboBox(ArrayList<StockItem> oList){
		//ha még egyszer le akarjuk futtatni, akkor itt ki kell kapcsolni a combo listener-jént
		//és a mûvelet után visszakapcsolni!!! Hogy hogyan, nem tudom...
		comboBoxItems.clear();
		ItemsQuantity.clear();
		ItemsPrice.clear();
		for (StockItem stockItem : oList){
			comboBoxItems.add(stockItem.getProduct().getProductName());			
			ItemsQuantity.add(stockItem.getQuantity());
			ItemsPrice.add(stockItem.getProduct().getRetailPrice());			
		}
		productComboBox.setSelectedIndex(0);
	}
	
	// refresh Tab
	private void refreshTab(int index){		
		availableQuantityField.setText(Integer.toString(ItemsQuantity.get(index)));
		priceField.setText(Double.toString(ItemsPrice.get(index)));
		quantityTextField.setText("1");			
	}
	
	//combo box selection
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED){
			refreshTab(productComboBox.getSelectedIndex());
			
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==addButton){
			
			//add product and quantity to the current order list
			Product tempProduct = stockDBControl.getStockList().get(productComboBox.getSelectedIndex()).getProduct();
			int tempQuantity =(Integer.parseInt(quantityTextField.getText()));
			currentOrderList.add(tempProduct);
			currentOrderListQuantity.add(tempQuantity);
			
			
						
			orderingListItems.add(comboBoxItems.get(productComboBox.getSelectedIndex()));
			listModel.addElement(comboBoxItems.get(productComboBox.getSelectedIndex())+
								"\t\t   " + quantityTextField.getText()+
								" \t\t  " + ((Double.parseDouble(priceField.getText())*(Double.parseDouble(quantityTextField.getText())))));
			
			
			subTotal += (tempProduct.getRetailPrice() * tempQuantity);
			
			
			subtotalField.setText(Double.toString(subTotal));
					
			
		}
		
	}
	
	
}
