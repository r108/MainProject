package order;

import java.util.ArrayList;

import person.Supplier;
import retailSystem.Product;

/**
 * @author Roland Katona
 *
 */
public class StockDBControl {
	private ArrayList<StockItem> stockList;

	/**
	 * Roland Katona
	 */
	public StockDBControl() {
		stockList = new ArrayList<StockItem>();

	}

	/**
	 * Alters the details for a product in stock
	 * 
	 * @param productId
	 *            The ID of the product
	 * @param productName
	 *            The name of the product
	 * @param category
	 *            The product category
	 * @param description
	 *            Description for the product
	 * @param supplierPrice
	 *            Price the supplier gets for the product
	 * @param profitMargin
	 *            Retailer's profit margin
	 * @param supplier
	 *            Supplier associated with the product
	 */
	public void changeProductDetails(int productId, String productName, String category,
			String description, double supplierPrice, double profitMargin, Supplier supplier) {
		Product product = getStockItem(productId).getProduct();
		product.setSupplier(supplier);
		product.setProductName(productName);
		product.setProductCategory(category);
		product.setProductDescription(description);
		product.setSupplierPrice(supplierPrice);
		product.setProfitMargin(profitMargin); // automatically sets retail price
	}

	/**
	 * @return The list of items in stock
	 */
	public ArrayList<StockItem> getStockList() {
		return stockList;
	}

	/**
	 * @param product
	 *            The product to add
	 * @param quantity
	 *            The quantity to add
	 */
	public void addNewProductToStockList(Product product, int quantity) {
		stockList.add(new StockItem(product, quantity));
	}

	/**
	 * @param productId
	 *            The product ID
	 * @return The product and the amount in stock
	 */
	public StockItem getStockItem(int productId) {
		for (StockItem stockItem : stockList) {
			if (stockItem.getProduct().getProductID() == productId) {
				return stockItem;
			}
		}
		System.out.println("Product ID not found!");
		return null;
	}

	/**
	 * Delete an item from the stock list
	 * 
	 * @param stockItem
	 *            The item to remove
	 */
	public void removeProductFromStockList(StockItem stockItem) {
		if (stockItem != null) {
			stockList.remove(stockItem);
			System.out.println("Product has been removed from stock");
		}
		else {
			System.out.println("Product could not be removed from stock!");
		}
	}
}
