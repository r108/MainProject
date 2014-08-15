package group2;
 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;

import java.awt.SystemColor;
import java.awt.Color;
import java.util.ArrayList;
/**
 * The main application.
 */
public class MainDisplay extends JFrame {

	private JPanel contentPane;
	private StockControl stockControl;
	private ArrayList<Customer> customerList;
	private ArrayList<Staff> staffList;
	private ArrayList<Supplier> supplierList;
	private ArrayList<PurchaseOrder> purchaseOrderList;
	private ArrayList<SupplyOrder> supplyOrderList;
	
	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			MainDisplay frame = new MainDisplay(staffList, customerList, supplierList, purchaseOrderList, supplyOrderList);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public MainDisplay(ArrayList<Staff> staffList, ArrayList<Customer> customerList, ArrayList<Supplier> supplierList, 
		     ArrayList<PurchaseOrder> purchaseOrderList,  ArrayList<SupplyOrder> supplyOrderList) {
		this.staffList = staffList;
		this.customerList = customerList;
		this.supplierList = supplierList;
		this.purchaseOrderList = purchaseOrderList;
		this.supplyOrderList = supplyOrderList;
		runMainDisplay();
	}
	public void runMainDisplay(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.window);
		tabbedPane.setBounds(10, 11, 824, 381);
		contentPane.add(tabbedPane);
		
		CustomerTab tabbedPane_1 = new CustomerTab(customerList);
		SupplierTab supplierPane = new SupplierTab(supplierList);
		tabbedPane.addTab("Customer", null, tabbedPane_1, null);
		tabbedPane.addTab("Supplier", null, supplierPane, null);
	}
}
