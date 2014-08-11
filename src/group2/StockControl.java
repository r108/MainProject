package group2;

import java.util.ArrayList;

public class StockControl {
	private ArrayList<Object[]> stockList;
	private ArrayList<Object[]> list;
	private Object[] productAndQuantity;
	
	public StockControl(){
		stockList = new ArrayList<Object[]>();
		
	}
	
	public int getStockListSize(){
		return stockList.size();
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
		for(Object[] productAndQuantityInStock : stockList){
			if(((Product)productAndQuantityInStock[0]).getProductID()==productId){
				return productAndQuantityInStock;
			}
		}
		System.out.println("Product ID not found!");
		return null;
	}
	
	public void removeProductFromStockList(Object object[]){
		if(object!=null){
			stockList.remove(object);
			System.out.println("Product has been removed from stock");
		}
		else{
			System.out.println("Product could not be removed from stock!");
		}
		
	}
	
	public void removeProduct(int id){
		Object object [] = null;
		System.out.println("enter id");
		int pid = Keyboard.readInt();
		for(Object [] o : stockList){
			if(((Product)o[0]).getProductID()==pid){
				object = o;
				
				((Product)o[0]).displayDetail();
				System.out.println("Product has been removed from stock");
			}
		}
		if(object!=null){
			stockList.remove(object);
			System.out.println("Product could not be removed from stock!");
		}
		displayStockList();
	}
	
	public void displayStockList(){
		if(stockList.size()>0){
			System.out.println("\n******Product Levels On Stock*******");
			System.out.println("Product ID  |   Supplier   |  Quantity  |  Product Name");
			System.out.println("------------------------------------------");
			for(Object[] productAndQuantityInStock : stockList){
				System.out.print(((Product)productAndQuantityInStock[0]).getProductID()+"                ");
				System.out.print(((Product)productAndQuantityInStock[0]).getSupplier().getId()+"                ");
				System.out.print((Integer)productAndQuantityInStock[1]);
				System.out.print("          "+((Product)productAndQuantityInStock[0]).getProductName()+"\n");
				
			}
		}
		else{
			System.out.println("Product listis empty!");
		}
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
}
