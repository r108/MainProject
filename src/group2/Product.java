package group2;

public class Product {
	
	private int productID;            
	private String productName;               
	private String productDescription;
	private String productCategory;           
	private double retailPrice;
	private double supplierPrice;
	private double profitMargin;
	private Supplier supplier;
	
	
	public Product(String productName,String productDescription, String productCategory,double supplierPrice,Supplier supplier) {
		productID++;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productCategory = productCategory;
		this.supplierPrice = supplierPrice;
		this.profitMargin = 0.2;
		this.retailPrice = supplierPrice+(supplierPrice*profitMargin);
		this.supplier = supplier;
	}
	
	public void displayDetail(){
		
		System.out.println("ID : "+productID);
		System.out.println("Name : "+productName);
		System.out.println("Description : "+productDescription);
		System.out.println("Category : "+productCategory);
		System.out.println("Price : "+retailPrice);
		System.out.println("Supplier details :");
		supplier.displayDetails();
	}
	
	
	public int getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public String getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}


	public double getRetailPrice() {
		return retailPrice;
	}


	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}


	public double getSupplierPrice() {
		return supplierPrice;
	}


	public void setSupplierPrice(double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}


	public double getProfitMargin() {
		return profitMargin;
	}


	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
	
	


}
