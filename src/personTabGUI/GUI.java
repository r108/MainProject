package personTabGUI;

import group2.PersonDB;
import group2.StockControl;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


public class GUI extends JFrame{

	private StaffTab staffTab;
	private SupplierTab supplierTab;
	private CustomerTab customerTab;
	private ProductTab productTab;
	private JPanel contentPane;
	
	public GUI(PersonDB personDB, StockControl stockControl){
		this.setVisible(true);
		setSize(460,450);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.window);
		tabbedPane.setBounds(10, 11, 432, 381);
		contentPane.add(tabbedPane);
		
		supplierTab = new SupplierTab(personDB);
		staffTab = new StaffTab(personDB);
		customerTab = new CustomerTab(personDB);
		productTab = new ProductTab(stockControl);
		
		tabbedPane.addTab("Customer", null, customerTab, null);
		tabbedPane.addTab("Supplier", null, supplierTab, null);
		tabbedPane.addTab("Staff", null, staffTab, null);
		tabbedPane.addTab("Product", null, productTab, null);
	}
	
	
}
