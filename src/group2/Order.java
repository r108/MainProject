package group2;

import java.util.ArrayList;

/**
 * @author Roland Katona
 *
 */
public class Order {

	protected ArrayList<Object[]> purchaseList;  //Includes quantity and product ID
	protected static int uniqueId = 1;
	private int orderId = 1;
	protected Person person;
	private boolean processed;
	
	/**
	 * Default constructor
	 */
	public Order() {
		purchaseList = new ArrayList<Object[]>();
		//orderId++;
		processed = false;
	}
	
	/**
	 * Display order details
	 */
	public void displayOrderDetails(){
		System.out.println("\n*******"+this.getClass().getName()+" Details*******");
		System.out.println("Quantity       Unit Price      Product Name");
		for(Object[] purchaseAndQuantity : purchaseList){
			System.out.println((Integer)purchaseAndQuantity[1]+"          "+((Product)purchaseAndQuantity[0]).getRetailPrice()+"           "+((Product)purchaseAndQuantity[0]).getProductName());		
		}
		
	}
	
	/**
	 * @return purchaseList
	 */
	public ArrayList<Object[]> getProductList() {
		return purchaseList;
	}

	/**
	 * @param productAndQuantity
	 */
	public void addProductToList(Object[] productAndQuantity) {
		purchaseList.add(productAndQuantity);
	}

	/**
	 * @return orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @return person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return processed
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * @param processed
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
		
}
