package order;

import person.Customer;

public class PurchaseOrder extends Order {
	private int orderId;
	private Customer person;
	private double shippingPrice, totalPrice;

	/**
	 * Default constructor
	 */
	public PurchaseOrder(Customer customer) {
		super();
		orderId = uniqueID++;
		person = customer;
		setTotalPrice();
	}

	/**
	 * @return The order ID
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            The ID for the order
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return The shipping price
	 */
	public double getShippingPrice() {
		return shippingPrice;
	}

	/**
	 * @param shippingPrice
	 *            The shippingPrice to set
	 */
	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	/**
	 * @return A customer object
	 */
	public Customer getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            The customer associated with the order
	 */
	public void setPerson(Customer person) {
		this.person = person;
	}

	/**
	 * @return The total order price
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Set the total price based on the price of the products and the delivery price
	 */
	public void setTotalPrice() {
		this.totalPrice = getOrderPrice() + getShippingPrice();
	}
}
