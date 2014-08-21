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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the shippingPrice
	 */
	public double getShippingPrice() {
		return shippingPrice;
	}

	/**
	 * @param shippingPrice
	 *            the shippingPrice to set
	 */
	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Customer getPerson() {
		return person;
	}

	public void setPerson(Customer person) {
		this.person = person;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Set the total price based on the price of the products and the delivery
	 * price
	 */
	public void setTotalPrice() {
		this.totalPrice = getOrderPrice() + getShippingPrice();
	}
}
