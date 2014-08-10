package group2;

import java.util.ArrayList;

public class StockControl {
	private ArrayList<Object[]> stockList;
	private Object[] productAndQuantity;
	
	public StockControl(){
		stockList = new ArrayList<Object[]>();
		
	}
	
	public ArrayList<Object[]> getStockList(){
		
		return stockList;
	}
	
	public void addNewProductToStockList(Product product, int quantity){
		productAndQuantity = new Object[2];
		productAndQuantity[0] = product;
		productAndQuantity[1] = quantity;	
		stockList.add(productAndQuantity);
	}
	
	public void restockProductInStockList(Product product, int quantity){
		productAndQuantity = new Object[2];
		productAndQuantity= findProductInStockList(product.getProductID());
		quantity +=(Integer)productAndQuantity[1];
		productAndQuantity[1] = quantity;
	}
	
	public Object[] findProductInStockList(int productId){
		productAndQuantity = new Object[2];
		for(Object[] productAndQuantityInStock : stockList){
			if(((Product)productAndQuantityInStock[0]).getProductID()==productId){
				productAndQuantity[0] = ((Product)productAndQuantityInStock[0]);
				productAndQuantity[1] = productAndQuantityInStock[1];
			}
			else{
				return null;
			}
		}
		return productAndQuantity;
	}
	
	public void removeProductFromStockList(Product product){
		productAndQuantity = new Object[2];
		productAndQuantity= findProductInStockList(product.getProductID());
		stockList.remove(productAndQuantity);
	}
	
	public void displayProductByCategory(){
		
		System.out.println("Category   | Available |  Price |  Product Name");
		System.out.println("------------------------------------------------\n");
		
		for(Object[] productAndQuantityInStock : stockList){
			if(((Product)productAndQuantityInStock[0]).getProductCategory().equalsIgnoreCase("desktop")){
				System.out.print(((Product)productAndQuantityInStock[0]).getProductCategory());
				if((Integer)productAndQuantityInStock[1]>0)
					System.out.print("       "+"Yes");
				else
					System.out.print("        "+"No");
				System.out.print("         "+((Product)productAndQuantityInStock[0]).getRetailPrice());
				System.out.print("     "+((Product)productAndQuantityInStock[0]).getProductName()+"\n");
			}
			
		}
		System.out.println();
		for(Object[] productAndQuantityInStock : stockList){
			if(((Product)productAndQuantityInStock[0]).getProductCategory().equalsIgnoreCase("laptop")){
				System.out.print(((Product)productAndQuantityInStock[0]).getProductCategory());
				if((Integer)productAndQuantityInStock[1]>0)
					System.out.print("        "+"Yes");
				else
					System.out.print("         "+"No");
				System.out.print("         "+((Product)productAndQuantityInStock[0]).getRetailPrice());
				System.out.print("     "+((Product)productAndQuantityInStock[0]).getProductName()+"\n");
			}
			
		}

		System.out.println();
		for(Object[] productAndQuantityInStock : stockList){
			if(((Product)productAndQuantityInStock[0]).getProductCategory().equalsIgnoreCase("tablet")){
				System.out.print(((Product)productAndQuantityInStock[0]).getProductCategory());
				if((Integer)productAndQuantityInStock[1]>0)
					System.out.print("        "+"Yes");
				else
					System.out.print("         "+"No");
				System.out.print("         "+((Product)productAndQuantityInStock[0]).getRetailPrice());
				System.out.print("     "+((Product)productAndQuantityInStock[0]).getProductName()+"\n");
			}
			
		}

		System.out.println();
		for(Object[] productAndQuantityInStock : stockList){
			if(((Product)productAndQuantityInStock[0]).getProductCategory().equalsIgnoreCase("smartphone")){
				System.out.print(((Product)productAndQuantityInStock[0]).getProductCategory());
				if((Integer)productAndQuantityInStock[1]>0)
					System.out.print("    "+"Yes");
				else
					System.out.print("     "+"No");
				System.out.print("         "+((Product)productAndQuantityInStock[0]).getRetailPrice());
				System.out.print("     "+((Product)productAndQuantityInStock[0]).getProductName()+"\n");
			}
			
		}

		
	}
	
	public void displayStockList(){
		System.out.println("\n******Product Levels On Stock*******");
		System.out.println("Supplier ID  |  Quantity  |  Product Name");
		System.out.println("------------------------------------------");
		for(Object[] productAndQuantityInStock : stockList){
			System.out.print(((Product)productAndQuantityInStock[0]).getSupplier().getId()+"                ");
			System.out.print((Integer)productAndQuantityInStock[1]);
			System.out.print("          "+((Product)productAndQuantityInStock[0]).getProductName()+"\n");
			
		}	
	}
}
