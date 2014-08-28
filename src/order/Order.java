package order;

import java.util.ArrayList;

import person.Person;
import retailSystem.Product;

/**
 * @author Roland Katona
 */
public class Order {
	protected ArrayList<StockItem> orderList; // Includes quantity and product ID
	protected int uniqueID = 1;
	protected Person person;
	private boolean processed;
	private double shippingPrice;

	/**
	 * Order constructor
	 */
	public Order() {
		orderList = new ArrayList<StockItem>();
		processed = false;
	}

	/**
	 * @return The list of products in the order, along with the quantity
	 */
	public ArrayList<StockItem> getProductList() {
		return orderList;
	}

	/**
	 * Adds a product to the order and an associated quantity
	 * 
	 * @param product
	 *            The product to be added
	 * @param quantity
	 *            The amount of this product to be added
	 */
	public void addProductToList(Product product, int quantity) {
		StockItem p = new StockItem(product, quantity);
		orderList.add(p);
	}

	/**
	 * @return The person associated with the order
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Sets the person associated with the order
	 * 
	 * @param person
	 *            The person requesting the order
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Checks if an order has been processed
	 * 
	 * @return The processing status of the order
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * Set the processing status of an order.
	 * 
	 * @param processed
	 *            The status of the order. True if the order has been processed, false otherwise
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	/**
	 * @return Total purchase price
	 */
	public double getOrderPrice() {
		double totalPurchase = 0;
		for (StockItem orderProduct : this.getProductList()) {
			double itemPrice = orderProduct.getProduct().getRetailPrice();
			double quantity = (double) orderProduct.getQuantity();
			totalPurchase += itemPrice * quantity;
		}
		return totalPurchase;
	}

	/**
	 * @return Shipping price
	 */
	public double getShippingPrice() {
		return shippingPrice;
	}

	/**
	 * Sets shipping price based on total order value
	 */
	public void setShippingPrice(double price) {
		if (getOrderPrice() >= 500) {
			shippingPrice = 0;
		}
		else {
			this.shippingPrice = price;
		}
	}
}
