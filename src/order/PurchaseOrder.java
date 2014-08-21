package order;

import person.Customer;

public class PurchaseOrder extends Order {
	private int orderId;
	private Customer person;

	/**
	 * Default constructor
	 */
	public PurchaseOrder(Customer customer) {
		super();
		orderId = uniqueID++;
		person = customer;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getPerson() {
		return person;
	}

	public void setPerson(Customer person) {
		this.person = person;
	}
}
