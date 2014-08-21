package order;

import group2.StockControl;

import java.util.ArrayList;

/**
 * Class for calculating transactions in and out of the business.
 * 
 * @author Conor
 */
public class Accounting {
	private ArrayList<QProduct> purchaseOrderList;
	private ArrayList<QProduct> supplyOrderList;
	private StockControl stockControl;
	public double totalSales;
	public double totalPurchases;

	/**
	 * Accounting constructor
	 */
	public Accounting() {
		this.totalPurchases = 0;
		this.totalSales = 0;
		this.stockControl = new StockControl();
		this.purchaseOrderList = new ArrayList<QProduct>();
		this.supplyOrderList = new ArrayList<QProduct>();
	}

}
