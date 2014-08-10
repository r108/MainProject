package group2;

import java.util.ArrayList;

public class Order {

	protected ArrayList<Object[]> purchaseList;  //Includes quantity and product ID
	private int orderId = 1;
	protected Person person;
	private boolean processed;
	
	public Order() {
		purchaseList = new ArrayList<Object[]>();
		orderId++;
		processed = false;
	}
	
	public void displayOrderDetails(){
		System.out.println("\n*******"+this.getClass().getName()+" Details*******");
		System.out.println("Quantity       Unit Price      Product Name");
		for(Object[] purchaseAndQuantity : purchaseList){
			System.out.println((Integer)purchaseAndQuantity[1]+"          "+((Product)purchaseAndQuantity[0]).getRetailPrice()+"           "+((Product)purchaseAndQuantity[0]).getProductName());		
		}
		
	}
	
	public ArrayList<Object[]> getProductList() {
		return purchaseList;
	}

	public void addProductToList(Object[] productAndQuantity) {
		purchaseList.add(productAndQuantity);
	}

	public int getOrderId() {
		return orderId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
		
}
