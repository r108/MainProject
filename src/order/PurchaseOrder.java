package order;

import group2.Product;

import java.util.Date;


import person.Customer;

public class PurchaseOrder extends Order{
	
	private double shippingPrice;

	/**
	 * Default constructor
	 */
	public PurchaseOrder() {
		super();
		// TODO Auto-generated constructor stub
		orderId = uniqueId++;
		shippingPrice = 10.0;
		this.person = new Customer();
	}
	
	/**
	 * @return totalPurchase
	 */
	public double calculateTotalOrderValue(){
		double totalPurchase = 0;
		for(Object[] purchaseAndQuantity : this.getProductList()){
			totalPurchase+=(((Product)purchaseAndQuantity[0]).getRetailPrice()*(Integer)purchaseAndQuantity[1]);
		}
		return totalPurchase;
		
	}
	
	/**
	 * @return shippingPrice
	 */
	public double getShippingPrice(){
		
		return shippingPrice;	
	}
	
	/**
	 * Sets shipping price based on total order value
	 */
	public void setShippingPrice(){
		if(calculateTotalOrderValue()>500){
			shippingPrice = 0;
		}
		else{
			// do nothing
		}
	}
	
	/* (non-Javadoc)
	 * @see group2.Order#displayOrderDetails()
	 */
	public void displayOrderDetails(){
		super.displayOrderDetails();
		System.out.println("Total Purchase : Euro "+calculateTotalOrderValue());
		System.out.println("Shipping Fee : Euro "+getShippingPrice());
		System.out.println("Grand Total : "+(calculateTotalOrderValue()+getShippingPrice()));
		Date date = new Date();
		System.out.println("Shipping Date : "+date.toString());
		
		
	}
	

}
