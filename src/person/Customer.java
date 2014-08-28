package person;

/**
 * @author Roland Katona
 *
 */
public class Customer extends Person {

	/**
	 * Customer constructor
	 */
	public Customer() {
		super();
	}

	/**
	 * @param name
	 *            Customer name
	 * @param email
	 *            Customer email
	 * @param contactNumber
	 *            Customer phone number
	 * @param address
	 *            Customer address
	 */
	public Customer(String name, String email, String contactNumber, String address) {
		super(name, email, contactNumber, address);
	}
}
