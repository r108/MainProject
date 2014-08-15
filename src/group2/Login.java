package group2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
/**
 * Launches a GUI for entering a username and password, with
 * password validation taking place within the Staff class.
 * @author Conor Clarke
 */
public class Login extends JFrame {
	@SuppressWarnings("javadoc")
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JButton btnNewButton = new JButton();
	
	private ArrayList<Staff> staffList;
	private ArrayList<Customer>customerList;
	private ArrayList<Supplier> supplierList;
	private ArrayList<PurchaseOrder> purchaseOrderList;
	private ArrayList<SupplyOrder> supplyOrderList;
	
	private String usernameText = new String();
	private final Action action = new SwingAction();
	private boolean login;

	/**
	 * Launches the window for the login screen
	 */
	public void run() {
		try {
			Login frame = new Login(staffList, customerList, supplierList, purchaseOrderList, supplyOrderList);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor for setting up the login screen with text fields 
	 * for the username and password and a button. The staff list is 
	 * passed into the constructor
	 */
	public Login(ArrayList<Staff> staffList, ArrayList<Customer> customerList, ArrayList<Supplier> supplierList, 
			     ArrayList<PurchaseOrder> purchaseOrderList,  ArrayList<SupplyOrder> supplyOrderList) {
		this.staffList = staffList;
		this.customerList = customerList;
		this.supplierList = supplierList;
		this.purchaseOrderList = purchaseOrderList;
		this.supplyOrderList = supplyOrderList;
		runLogin();
	}
	public void runLogin(){
		//Setup code for the JFrame and Panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Setup code for the username text field
		username = new JTextField();
		username.setBounds(162, 84, 86, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		//Label for the username
		JLabel Username = new JLabel("Username");
		Username.setBounds(162, 59, 86, 14);
		contentPane.add(Username);
		
		//Setup code for the password field
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					btnNewButton.doClick();
				}
			}
		});
		passwordField.setBounds(162, 140, 86, 20);
		contentPane.add(passwordField);
		
		//Label for the password
		lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(162, 115, 86, 14);
		contentPane.add(lblNewLabel);
		
		//Login button and event handler
		
		btnNewButton.setAction(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usernameText = username.getText();
				Staff user = null;
				for(Staff i:staffList){
					if(i.getName().equals(usernameText)){
						user = i;
						break;
					}	
				}
				if(user != null){
					char [] validate = passwordField.getPassword();
					login = ((Staff)user).passwordValidation(validate);
				}
				else{
					JOptionPane.showMessageDialog(null,"Invalid username!");
				}
					
			}
		});
		
		//Add the login button to the panel
		btnNewButton.setBounds(162, 205, 89, 23);
		contentPane.add(btnNewButton);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Login");
			putValue(SHORT_DESCRIPTION, "Log in to the system");
		}
		public void actionPerformed(ActionEvent e) {
			if(login){
				JOptionPane.showMessageDialog(null,"Login successful");
				setVisible(false);
				dispose();
				MainDisplay display = new MainDisplay(staffList, customerList, supplierList, purchaseOrderList, supplyOrderList);
				//Access level
				display.run();
			}
			else
				JOptionPane.showMessageDialog(null,"Invalid password!");
		}
	}
}
