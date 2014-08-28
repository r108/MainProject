package order;

import retailSystem.Product;

public class StockItem {
	private Product product;
	private int quantity;

	/**
	 * StockItem constructor
	 * 
	 * @param product
	 *            The product
	 * @param quantity
	 *            Amount of the product in stock
	 */
	public StockItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * StockItem constructor
	 */
	public StockItem() {

	}

	/**
	 * @return The product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            The product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return The amount of the product in stock
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            Amount of the product in stock
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
