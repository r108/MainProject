package group2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import person.Person;
import person.Staff;
import personTabGUI.GUI;

/**
 * Launches a GUI for entering a username and password, with password validation
 * taking place within the Staff class.
 * 
 * @author Conor Clarke, modified by Roland
 */
@SuppressWarnings("serial")
public class Login extends JFrame {

	private PersonDB personDB;
	private StockControl stockControl;
	private ArrayList<Person> staffList;
	private boolean login;

	/**
	 * Launches the window for the login screen
	 */
	public Login(PersonDB personDB, StockControl stockControl) {
		this.stockControl = stockControl;
		this.staffList = personDB.getStaffList();
		this.personDB = personDB;
		runLogin();
		login = false;
	}

	public void runLogin() {
		// Setup code for the JFrame and Panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(459, 339);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Setup code for the username text field
		final JTextField username = new JTextField();
		username.setBounds(162, 84, 86, 20);
		contentPane.add(username);
		username.setColumns(10);

		// Label for the username
		JLabel userName = new JLabel("Username");
		userName.setBounds(162, 59, 86, 14);
		contentPane.add(userName);

		// Setup code for the password field
		final JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(162, 140, 86, 20);
		contentPane.add(passwordField);

		// Label for the password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(162, 115, 86, 14);
		contentPane.add(passwordLabel);

		// Login button and event handler
		JButton btnNewButton = new JButton("Login");
		contentPane.getRootPane().setDefaultButton(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usernameText = username.getText();
				Staff user = null;

				// Check if the user exists
				for (Person person : staffList) {
					if (person.getName().equals(usernameText)) {
						user = (Staff) person;
						break;
					}
				}
				if (user != null) {

					// Validate the password
					char[] validate = passwordField.getPassword();
					login = ((Staff) user).passwordValidation(validate);

					/*
					 * If the password is valid, set up the main application the
					 * close the login screen
					 */
					if (login) {

						// If the user is an admin, grant them privileged access
						if (((Staff) user).getAccessLevel() == 2) {
							RetailSystemDriver.setPriviledged(true);
						}

						JOptionPane
								.showMessageDialog(null, "Login successful!");
						new GUI(personDB, stockControl);

						setVisible(false);
						dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Wrong Password!");
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid username!");
			}
		});

		// Add the login button to the panel
		btnNewButton.setBounds(162, 205, 89, 23);
		contentPane.add(btnNewButton);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
