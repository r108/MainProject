package group2;

import java.util.ArrayList;

import order.Product;
import order.QProduct;

/**
 * @author Roland Katona
 *
 */
public class StockControl {
	private ArrayList<QProduct> stockList;
	private QProduct productAndQuantity;

	/**
	 * Roland Katona
	 */
	public StockControl() {
		stockList = new ArrayList<QProduct>();
	}

	/**
	 * @return stockList.size()
	 */
	public int getStockListSize() {
		return stockList.size();
	}

	/**
	 * @return stockList
	 */
	public ArrayList<QProduct> getStockList() {
		return stockList;
	}

	/**
	 * @param product
	 * @param quantity
	 */
	public void addNewProductToStockList(Product product, int quantity) {
		QProduct q = new QProduct(product, quantity);
		stockList.add(q);
	}

	/**
	 * @param product
	 * @param quantity
	 */
	public void restockProductInStockList(Product product, int quantity) {
		QProduct q = findProductInStockList(product.getProductID());
		q.setQuantity(q.getQuantity() + quantity);
		stockList.set(product.getProductID(), q);
	}

	/**
	 * @param productId
	 * @return productAndQuantityInStock
	 */
	public QProduct findProductInStockList(int productId) {
		for (QProduct productAndQuantityInStock : stockList) {
			if ((productAndQuantityInStock.getProduct()).getProductID() == productId) {
				return productAndQuantityInStock;
			}
		}
		System.out.println("Product ID not found!");
		return null;
	}

	/**
	 * @param q
	 */
	public void removeProductFromStockList(QProduct q) {
		if (q != null) {
			stockList.remove(q);
			System.out.println("Product has been removed from stock");
		}
		else {
			System.out.println("Product could not be removed from stock!");
		}
	}

	/**
	 * @param id
	 */
	public void removeProduct(int id) {
		QProduct q = null;
		System.out.println("enter id");
		int pid = Keyboard.readInt();
		for (QProduct i : stockList) {
			if (i.getProduct().getProductID() == pid) {
				q = i;

				q.getProduct().displayDetail();
				System.out.println("Product has been removed from stock");
			}
		}
		if (q != null) {
			stockList.remove(q);
			System.out.println("Product could not be removed from stock!");
		}
		displayStockList();
	}

	/**
	 * Display details about the items on stock
	 */
	public void displayStockList() {
		if (stockList.size() > 0) {
			System.out.println("\n******Product Levels On Stock*******");
			System.out
					.println("Product ID  |   Supplier   |  Quantity  |  Product Name");
			System.out
					.println("----------------------------------------------------------");
			for (QProduct productAndQuantity : stockList) {
				System.out.print((productAndQuantity.getProduct())
						.getProductID()
						+ "                ");
				System.out.print((productAndQuantity.getProduct())
						.getSupplier().getId()
						+ "                ");
				System.out.print(productAndQuantity.getQuantity());
				System.out.print("          "
						+ (productAndQuantity.getProduct()).getProductName()
						+ "\n");
			}
		}
		else
			System.out.println("Product listis empty!");
	}

	/**
	 * Display product details by category
	 */
	public void displayProductByCategory() {

		System.out.println("Category   | Available |  Price |  Product Name");
		System.out
				.println("------------------------------------------------\n");

		for (QProduct productAndQuantityInStock : stockList) {
			if ((productAndQuantity.getProduct()).getProductCategory()
					.equalsIgnoreCase("desktop")) {
				System.out.print((productAndQuantity.getProduct())
						.getProductCategory());
				if (productAndQuantity.getQuantity() > 0)
					System.out.print("       " + "Yes");
				else
					System.out.print("        " + "No");

				System.out.print("         "
						+ (productAndQuantity.getProduct()).getRetailPrice());
				System.out.print("     "
						+ (productAndQuantity.getProduct()).getProductName()
						+ "\n");
			}

		}
		System.out.println();
		for (QProduct productAndQuantityInStock : stockList) {
			if ((productAndQuantity.getProduct()).getProductCategory()
					.equalsIgnoreCase("laptop")) {
				System.out.print((productAndQuantity.getProduct())
						.getProductCategory());
				if (productAndQuantity.getQuantity() > 0)
					System.out.print("        " + "Yes");
				else
					System.out.print("         " + "No");

				System.out.print("         "
						+ (productAndQuantity.getProduct()).getRetailPrice());
				System.out.print("     "
						+ (productAndQuantity.getProduct()).getProductName()
						+ "\n");
			}

		}

		System.out.println();
		for (QProduct productAndQuantityInStock : stockList) {
			if ((productAndQuantity.getProduct()).getProductCategory()
					.equalsIgnoreCase("tablet")) {
				System.out.print((productAndQuantity.getProduct())
						.getProductCategory());
				if (productAndQuantity.getQuantity() > 0)
					System.out.print("        " + "Yes");
				else
					System.out.print("         " + "No");

				System.out.print("         "
						+ (productAndQuantity.getProduct()).getRetailPrice());
				System.out.print("     "
						+ (productAndQuantity.getProduct()).getProductName()
						+ "\n");
			}

		}

		System.out.println();
		for (QProduct productAndQuantityInStock : stockList) {
			if ((productAndQuantity.getProduct()).getProductCategory()
					.equalsIgnoreCase("smartphone")) {
				System.out.print((productAndQuantity.getProduct())
						.getProductCategory());
				if (productAndQuantity.getQuantity() > 0)
					System.out.print("    " + "Yes");
				else
					System.out.print("     " + "No");

				System.out.print("         "
						+ (productAndQuantity.getProduct()) + "     "
						+ (productAndQuantity.getProduct()).getProductName()
						+ "\n");
			}
		}
	}
}
