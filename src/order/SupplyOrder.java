package order;

import person.Supplier;

public class SupplyOrder extends Order {
	private int orderId;
	private double shippingPrice, totalPrice;
	private Supplier person;

	/**
	 * Supply order constructor
	 */
	public SupplyOrder(Supplier customer) {
		super();
		orderId = uniqueID++;
		person = customer;
		setTotalPrice();
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
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

	/**
	 * @return the person
	 */
	public Supplier getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Supplier person) {
		this.person = person;
	}

	/**
	 * @return the totalPrice
	 */
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
