package group2;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerTab extends JPanel{
	public ArrayList<Customer> customerList;
	/**
	 * Create the panel.
	 */
	public CustomerTab(ArrayList<Customer> customerList) {
		this.customerList = customerList;
		setLayout(null);
		
		JButton buttonCu = new JButton("New button");
		buttonCu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		buttonCu.setBounds(28, 81, 117, 29);
		add(buttonCu);
	}
}
