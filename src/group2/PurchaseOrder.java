package group2;

import java.util.Date;

public class PurchaseOrder extends Order{
	
	private double shippingPrice;

	public PurchaseOrder() {
		super();
		// TODO Auto-generated constructor stub
		shippingPrice = 10.0;
		this.person = new Customer();
	}
	
	private double calculateTotalPrice(){
		double totalPurchase = 0;
		for(Object[] purchaseAndQuantity : this.getProductList()){
			totalPurchase+=(((Product)purchaseAndQuantity[0]).getRetailPrice()*(Double)purchaseAndQuantity[1]);
		}
		return totalPurchase;
		
	}
	
	public double getShippingPrice(){
		if(calculateTotalPrice()>100){
			shippingPrice = 0;
		}
		else{
			// do nothing
		}
		return shippingPrice;	
	}
	
	public void displayOrderDetails(){
		super.displayOrderDetails();
		System.out.println("Total Purchase : Euro "+calculateTotalPrice());
		System.out.println("Shipping Fee : Euro "+getShippingPrice());
		System.out.println("Grand Total : "+(calculateTotalPrice()+getShippingPrice()));
		Date date = new Date();
		System.out.println("Shipping Date : "+date.toString());
		
		
	}
	

}
