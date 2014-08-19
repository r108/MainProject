package person;


/**
 * @author Roland Katona
 *
 */
public class Customer extends Person {

	/**
	 * Default constructor
	 */
	public Customer() {
		super();
	}

	/**
	 * @param name
	 * @param email
	 * @param contactNumber
	 * @param address
	 */
	public Customer(String name, String email, String contactNumber, String address) {
		super(name, email, contactNumber, address);
	}
}
