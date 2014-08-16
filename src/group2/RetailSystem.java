package group2;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author TIMI
 *
 */
public class RetailSystem {
	private ArrayList<Person> list;
	private ArrayList<Person> displayList;
	private StockControl stockControl;
	private ArrayList<Person> customerList;
	private ArrayList<Person> staffList;
	private ArrayList<Person> supplierList;
	private ArrayList<Order> purchaseOrderList;
	private ArrayList<Order> supplyOrderList;
	private ArrayList<Order> orderList;
	private int menuOption;
	private Person person;
	private Product product;
	private Random random;
	private boolean valid = false;
	private String userInput;
	private Order order;
	private Object orderObject[];
	
	/**
	 * Default constructor
	 */
	public RetailSystem() {
		random = new Random();
		list = new ArrayList<Person>();
		stockControl = new StockControl();
		customerList = new ArrayList<Person>();
		staffList = new ArrayList<Person>();
		supplierList = new ArrayList<Person>();
		purchaseOrderList = new ArrayList<Order>();
		supplyOrderList = new ArrayList<Order>();
		orderList = new ArrayList<Order>();
		menuOption = -1;
		orderObject = new Object[2];
		
		
		boolean terminateProgram = false;
		do{
			displayMainMenu();
			menuOption = Keyboard.readInt();
			
			//
			switch(menuOption){
			case 0: terminateProgram = true; System.out.println("Terminate Program.."); break;
			case 1: personOperation(new Customer()); break;
			case 2: personOperation(new Staff()); break;
			case 3: personOperation(new Supplier()); break;
			case 4: productOperation(); break;
			case 5: stockControl.displayStockList(); break;
			case 6: stockControl.displayProductByCategory(); break;
			case 7: orderOperation(new PurchaseOrder()); break;
			default: break;
			
			}
			
		}while(!terminateProgram);
	}
	
	public void orderOperation(Order order){
		String orderType = "";
		
		if(order instanceof SupplyOrder){
			orderType = "supply order";
			this.order = new SupplyOrder();
		}
		else if(order instanceof PurchaseOrder){
			orderType = "purchase order";
			this.order = new PurchaseOrder();
		}
		do{
			System.out.println("\n0 - Cancel");
			System.out.println("1 - Create new "+orderType);
			System.out.println("2 - Show "+orderType+"s");
			System.out.println("3 - Update active "+orderType);
			System.out.println("4 - Automatically create "+orderType+"s");
			System.out.println("5 - Process "+orderType);
			menuOption = Keyboard.readInt();
			switch(menuOption){
			case 0: System.out.println("Exiting submenu.."); break;
			case 1: createNewOrder(this.order);break;
			case 2: displayOrderList(this.order); break;
			case 3: stockControl.displayProductByCategory();  break;
			case 4: removeProduct(); break;
			case 5: break;
			default: System.out.println("Invalid option! Try again!"); break;
			}
		}while(menuOption!=0);
		menuOption = -1;	
	}
	
	public void displayOrderList(Order order){
		if(order instanceof SupplyOrder){
			orderList = supplyOrderList;
		}
		else if(order instanceof PurchaseOrder){
			orderList = purchaseOrderList;
		}
		
		for(Order ord : orderList){
			System.out.println("\nOrder id : "+ord.getOrderId());
			for(Object object[] : ord.getProductList()){
				System.out.println();
				((Product)object[0]).displayDetail();
			}

			System.out.println("The total order is : "+ ((PurchaseOrder)ord).calculateTotalOrderValue()+" euro.");
			System.out.println("Shipping price : "+((PurchaseOrder)ord).getShippingPrice()+"euro");
			System.out.println("===============================================================");
			System.out.println("Grand total is : "+(((PurchaseOrder)ord).calculateTotalOrderValue()+((PurchaseOrder)ord).getShippingPrice())+" euro\n");
			
		}
	}
	
	private Product getProductById(int id){
		boolean isFound = false;
		
		for(Object object[] : stockControl.getStockList()){
			product = (Product)object[0];
			if(product.getProductID()==id){
				isFound = true;
				return product;
			}
			else{
				isFound = false;
			}
		}
		
		if(!isFound){
			System.out.println("Product ID not found! Try again!");
			System.out.println("Enter product ID :");
			menuOption = Keyboard.readInt();
			getProductById(menuOption);
		}
		return null;
	}
	
	public void createNewOrder(Order order){
		
		if(order instanceof SupplyOrder){
			orderList = supplyOrderList;
		}
		else if(order instanceof PurchaseOrder){
			orderList = purchaseOrderList;
			this.order = new PurchaseOrder();
		}
		
		System.out.println("New Order");
		
		do{
			orderObject = new Object[2];
			System.out.println("\n0 - Cancel");
			System.out.println("1 - Add product to order");
			System.out.println("2 - Show products in current order");
			System.out.println("3 - Show all products ");
			menuOption = Keyboard.readInt();
			switch(menuOption){
			case 0: System.out.println("Exiting submenu.."); break;
			case 1: System.out.println("Enter the ID of the product you want to order :");
					valid = false;
					do{
						valid = isUserInputValid(Keyboard.readString());
					}while(!valid);
					do{
						product = getProductById(menuOption);
					}while(product==null);
					orderObject[0] = product;
					product.displayDetail();
					System.out.println("\nEnter quantity :");
					do{
						valid = isUserInputValid(Keyboard.readString());
					}while(!valid);
					orderObject[1] = menuOption;
					if(menuOption>0)
						this.order.orderList.add(orderObject);
					break;
			case 2: displayOrderDetails(order); break;
			case 3: displayAllProductDetails(); break;
			default: System.out.println("Invalid option! Try again!"); break;
			}
			
		}while(menuOption!=0);
		menuOption = -1;
		((PurchaseOrder)this.order).setShippingPrice();
		orderList.add(this.order);
		System.out.println("\nNew order with ID: "+this.order.orderId+" has been created.");
	}
	
	public void displayOrderDetails(Order order){
		if(order instanceof SupplyOrder){
			
		}
		else if(order instanceof PurchaseOrder){
			System.out.println("The following products are on order "+(order.orderId+1));
		}
		System.out.println("Product ID | Quantity | Product name ");
		System.out.println("---------------------------------------------\n");
		for(Object object[] : this.order.getProductList()){
			System.out.print(((Product)object[0]).getProductID()+"             ");
			System.out.print(object[1]+"           ");
			System.out.print(((Product)object[0]).getProductName()+"\n");
		}
	}
	
	/**
	 * Product operations menu
	 */
	public void productOperation(){
		do{
			System.out.println("\n0 - Cancel");
			System.out.println("1 - Add new product to stock");
			System.out.println("2 - Show stock levels");
			System.out.println("3 - Show products by category");
			System.out.println("4 - Remove product from stock ");
			System.out.println("5 - Change product detail");
			System.out.println("6 - Update product quantity on stock");
			System.out.println("7 - Automatically create products and add to stock");
			menuOption = Keyboard.readInt();
			switch(menuOption){
			case 0: System.out.println("Exiting submenu.."); break;
			case 1: createNewProduct();
					break;
			case 2: stockControl.displayStockList(); break;
			case 3: stockControl.displayProductByCategory();  break;
			case 4: removeProduct(); break;
			case 5: changeProductDetails(); break;
			case 6: updateProductQuantity(); break;
			case 7: if(supplierList.size()>0)
						automaticallyCreateProducts();
					else
						System.out.println("Suppliers must be created first!");
					break;
			default: System.out.println("Invalid option! Try again!"); break;
			}
		}while(menuOption!=0);
		menuOption = -1;
	}
	
	public void updateProductQuantity(){		
		do{
			System.out.println("\n***Select the operation to perform***");
			System.out.println("0 - Cancel");
			System.out.println("1 - Update the quantity of an existing product on stock");
			System.out.println("2 - Show current product quantity on stock");
			menuOption = Keyboard.readInt();
			switch (menuOption) {
				case 0: System.out.println("Exiting submenu..");break;
	            case 1: if(stockControl.getStockListSize()>0){
    						System.out.println("Enter product ID to update its quantity :");
    						int id=-1;
    						try{
    								id = Integer.parseInt(Keyboard.readString());
			    			}
			    			catch(NumberFormatException e){
			    				System.out.println("Incorrect Input! Only digits 1-9 are allowed.");
			    			}
			    			boolean isFound = false;
			    			Object [] productObject = stockControl.findProductInStockList(id);
		    				if(productObject!=null){
		    					isFound = true;
		    					System.out.println("*****Selected product*******\n");
		    					((Product)productObject[0]).displayDetail();
		    					System.out.println("\nCurrent quantity on stock : "+(Integer)productObject[1]);
		    					//System.out.println("\nConfirm removing product\nENTER Y or N");
		    					System.out.println("Enter new quantity : ");
		    					try{
    								id = Integer.parseInt(Keyboard.readString());
				    			}
				    			catch(NumberFormatException e){
				    				System.out.println("Incorrect Input! Only digits 1-9 are allowed.");
				    			}
		    					productObject[1] = id;
		    					
		    					stockControl.displayStockList();	
		    					
		    					break;
		    				}
		    				else{
		    					isFound = false;
		    				}
			    			
			    			if(!isFound){
			    				System.out.println("The product ID is not found. Try again!");
			    			}
	            		}
	            		else{
	            			System.out.println("The list is empty!");
	            		}
	            		break;
	            case 2:	stockControl.displayStockList();
	            		break;
	          default: System.out.println("Invalid choice. Try again."); break;
			}			
		}while(menuOption!=0);
		menuOption=-1;	
	}
	
	public void changeProductDetails(){
		do{
			System.out.println("\n***Select the operation to perform***");
			System.out.println("0 - Cancel");
			System.out.println("1 - Change the details of an existing product on stock");
			System.out.println("2 - Show the details of all products on stock");
			menuOption = Keyboard.readInt();
			switch (menuOption) {	
			case 1: if(stockControl.getStockListSize()>0){
				System.out.println("Enter product ID to change its details :");
				int id=-1;
				try{
						id = Integer.parseInt(Keyboard.readString());
    			}
    			catch(NumberFormatException e){
    				System.out.println("Incorrect Input! Only digits 1-9 are allowed.");
    			}
    			boolean isFound = false;
    			Object [] productObject = stockControl.findProductInStockList(id);
				if(productObject!=null){
					product = (Product)productObject[0];
					isFound = true;
					System.out.println("*****Selected product*******\n");
					product.displayDetail();	
					do{
						System.out.println("\n***Select the detail to change***");
						System.out.println("0 - Cancel");
						System.out.println("1 - Change product name");
						System.out.println("2 - Change product description");
						System.out.println("3 - Change product category");
						System.out.println("4 - Change supplier price");
						System.out.println("5 - Change profit margin");
						System.out.println("6 - Change product supplier");
						menuOption = Keyboard.readInt();
						switch (menuOption) {
							case 0: System.out.println("Exiting submenu..");break;
				            case 1: System.out.println("Enter new product name:");
				            		userInput = Keyboard.readString();
				            		product.setProductName(userInput);
				            		product.displayDetail();
				            		break;
				            case 2:	System.out.println("Enter new product description:");
				            		userInput = Keyboard.readString();
				            		product.setProductDescription(userInput);
				            		product.displayDetail();
				            		break;
				            case 3: System.out.println("Enter new product category:");
				            		userInput = Keyboard.readString();
				            		product.setProductCategory(userInput);
				            		product.displayDetail();
				            		break;
				            case 4:	System.out.println("Enter new supplier price:");
		            				product.setSupplierPrice(Double.parseDouble(Keyboard.readString()));
		            				//product.setRetailPrice(product.getSupplierPrice()*(1+product.getProfitMargin()));
		            				product.displayDetail();
		            				break;
				            case 5:	System.out.println("Enter new profit margin:");
		            				product.setProfitMargin(Double.parseDouble(Keyboard.readString()));
		            				product.displayDetail();
		            				break;
				            case 6: addSupplier(2);
				            		break;
				            
				            default: System.out.println("Invalid choice. Try again."); break;
						}			
					}while(menuOption!=0);
					menuOption=-1;	
					
					break;
				}
				else{
					isFound = false;
				}
    			if(!isFound){
    				System.out.println("The product ID is not found. Try again!");
    			}
    		}
    		else{
    			System.out.println("The list is empty!");
    		}
    		break;
			case 2: displayAllProductDetails(); break;
				default : break;
			}
		}while(menuOption!=0);
		menuOption = -1;
	}
	
	public void displayAllProductDetails(){
		
		for(Object productObject [] : stockControl.getStockList()){
			((Product)productObject[0]).displayDetail();
		}
	}
	
	public void removeProduct(){
		do{
			System.out.println("\n***Select the operation to perform***");
			System.out.println("0 - Cancel");
			System.out.println("1 - Remove product from stock");
			System.out.println("2 - Show products in stock");
			menuOption = Keyboard.readInt();
			switch (menuOption) {
				case 0: System.out.println("Exiting submenu..");break;
	            case 1: if(stockControl.getStockListSize()>0){
    						System.out.println("Enter product ID to remove:");
    						int id=-1;
    						try{
    								id = Integer.parseInt(Keyboard.readString());
			    			}
			    			catch(NumberFormatException e){
			    				System.out.println("Incorrect Input! Only digits 1-9 are allowed.");
			    			}
			    			boolean isFound = false;
			    			Object [] productObject = stockControl.findProductInStockList(id);
		    				if(productObject!=null){
		    					isFound = true;
		    					System.out.println("*****Selected product*******\n");
		    					((Product)productObject[0]).displayDetail();
		    					System.out.println("\nConfirm removing product\nENTER Y or N");
		    					userInput = Keyboard.readString();
		    					if(userInput.equalsIgnoreCase("Y")){
		    						System.out.println("Product has been removed.");   
		    						stockControl.removeProductFromStockList(productObject);
		    						stockControl.displayStockList();	
		    					}
		    					break;
		    				}
		    				else{
		    					isFound = false;
		    				}
			    			
			    			if(!isFound){
			    				System.out.println("The product ID is not found. Try again!");
			    			}
	            		}
	            		else{
	            			System.out.println("The list is empty!");
	            		}
	            		break;
	            case 2:	stockControl.displayStockList();
	            		break;
	          default: System.out.println("Invalid choice. Try again."); break;
			}			
		}while(menuOption!=0);
		menuOption=-1;	
	}
	
	public boolean isUserInputValid(String input){
		
		try{
			menuOption = Integer.parseInt(input);
			return true;
		}
		catch(NumberFormatException e){
			System.out.println("Error!Invalid input. Only digits 0-9 are allowed. Try again!");
		}
		return false;
	}
	
	public void createNewProduct(){
		String productName, productDescription, productCategory;
		double supplierPrice = -1;
		int quantity = 0;
		System.out.println("Enter product category :");
		productCategory = Keyboard.readString();
		System.out.println("Enter product name :");
		productName = Keyboard.readString();
		System.out.println("Enter product description :");
		productDescription = Keyboard.readString();
		valid = false;
		do{
			try{
				System.out.println("Enter supplier price :");
				supplierPrice = Double.parseDouble(Keyboard.readString());
				valid = true;
			}
			catch(NumberFormatException e){
				System.out.println("Error!Invalid input. Only digits 0-9 are allowed! Try again!");
			}
		}while(!valid);
		addSupplier(1);
		product = new Product(productName, productDescription, productCategory, supplierPrice, (Supplier)person);
		System.out.println("\nProduct has been created.");
		valid = false;
		do{
			try{
				System.out.println("Enter product quantity to add to stock :");
				quantity = Integer.parseInt(Keyboard.readString());
				if(quantity<0){
					System.out.println("Error! Invalid value. Only positive numbers are valid. Try again!");
					continue;
				}
				else{
					valid = true;
				}
			}
			catch(NumberFormatException e){
				System.out.println("Error!Invalid input. Only digits 0-9 are allowed! Try again!");
			}
		}while(!valid);
		stockControl.addNewProductToStockList(product, quantity);
		System.out.println(quantity+" "+productName+" was successfully added to stock.");
	}
	
	private void addSupplier(int option){
		
		System.out.println("Please select one of the following options :");
		OUTER : do{
					//System.out.println("0 - Cancel ");
					System.out.println("\n1 - Find supplier by ID ");
					System.out.println("2 - Show suppliers list");
					System.out.println("3 - Create new supplier ");			
					menuOption = Keyboard.readInt();
					switch(menuOption){
					case 1: valid = false;
							if(supplierList.size()>0){
								do{
									System.out.println("Enter supplier ID or 0 to cancel:"); 
									try{
										menuOption = Integer.parseInt(Keyboard.readString());
										
										if(menuOption==0){
											menuOption = -1;
											continue OUTER;
										}
										else{
											do{
												person = getSupplierById(menuOption);
											//System.out.println("null again");
										}while(person==null);
											if(option==2){
												product.setSupplier((Supplier)person);
												System.out.println("Supplier of the product has been changed.");
											}
											//person.displayDetails();
											valid = true;
											menuOption = 0;
										}
									}
									catch(NumberFormatException e){
										System.out.println("Error!Invalid ");
									}
								}while(!valid);
							}
							else{
								System.out.println("Supplier list is empty. Add supplier to the list first!");
								break;
							}
							break;
					case 2: displayPersonList(new Supplier()); break;
					case 3: createNewPerson(new Supplier()); 
							menuOption = 0;
							if(option==2){
								product.setSupplier((Supplier)person);
								System.out.println("Supplier of the product has been changed.");
							}
							break;
					default: System.out.println("Error! Invalid option! Only numbers 1-3 are valid.");break;
					}		
				}while(menuOption!=0);
		menuOption=-1;
	}
	
	private Supplier getSupplierById(int id){
		boolean isFound = false;
		
		for(Person person : supplierList){
			if(person.getId()==id){
				isFound = true;
				return (Supplier)person;
			}
			else{
				isFound = false;
			}
		}
		
		if(!isFound){
			System.out.println("Supplier ID not found! Try again!");
			System.out.println("Enter supplier ID :");
			menuOption = Keyboard.readInt();
			getSupplierById(menuOption);
		}
		return null;
	}
	
	private void automaticallyCreateProducts(){
		product = new Product("Asus EeeePC 1015px",
				"Atom N570 / 1.66 GHz - Windows 7 Starter - 1 GB RAM - 250 GB HDD - 10.1 inches wide 1024 x 600 - Intel GMA 3150 - black Series",
				"Laptop", 200, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 22);
		product = new Product("Apple MacBook Pro",
				"2.0GHz Intel Core i7 - 4 GB RAM - 1,280x800-pixel 15inches display - 500 GB SSD - OSX 10.9.3 Mawericks",
				"Laptop", 1000, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 5);
		product = new Product("HP Chromebook 11",
				"1.7 GHz Samsung Exynos 5250 - 2GB RAM - 11.6 inches 1366 x 768 display - ARM Mali-T604 Graphics - 16GB SSD - Google Chrome OS",
				"Laptop", 350, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 8);
		
		product = new Product("Apple iPad mini",
				"Battery Life:6 hours 8 minutes Bluetooth Version:4.0 Camera Resolution:1.2MP Front; 5MP Rear Cellular Technology :CDMA 1X EDGE EV-DO EV-DO Rev A GSM HSDPA HSPA HSPA+ LTE UMTS CPU:Apple A7 Dimensions:7.87 x 5.3 x 0.29 (HWD) inches Operating System:Apple iOS Ports:Apple Lightning Screen Pixels Per Inch:326 Screen Resolution:2048 x 1536 pixels Screen Size:7.9 inches Screen Type:Retina Service Provider:AT&T Sprint T-Mobile Verizon Wireless Storage Capacity (as Tested):16 GB Storage Type:SSD Touch Screen:Yes Video Camera Resolution:720p Front; 1080p Rear Weight:11.68 oz Wi-Fi (802.11x) Compatibility:2.4GHz/5GHz",
				"Tablet", 400, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 7);
		product = new Product("Amazon Kindle Fire HDX",
				"Battery Life:44 minutes 7 hours Bluetooth Version:4.0 Camera Resolution:8-megapixel Rear-Facing; 720p Front-Facing Cellular Technology :EDGE GPRS HSDPA HSPA+ LTE CPU:Qualcomm Snapdragon 800 Quad-Core Dimensions:9.1 x 6.2 x 0.31 inches GPS:Yes Graphics Card:ARM Adreno 330 Operating System:Google Android 4.2.2 Ports:micro USB Processor Speed:2.2 GHz RAM:2 GB Screen Pixels Per Inch:339 ppi Screen Resolution:2560 x 1600 pixels Screen Size:8.9 inches Screen Type:IPS LCD Service Provider:AT&T Storage Capacity (as Tested):16 GB Storage Type:SSD Video Camera Resolution:1080p Rear; 720p Front Weight:13.5 oz Wi-Fi (802.11x) Compatibility:2.4GHz/5GHz",
				"Tablet", 330, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 12);
		product = new Product("Samsung Galaxy Note ",
				"Additional Storage:MicroSD Battery Life:31 minutes 7 hours Battery Size:8220 mAh Bluetooth Version:4.0 Camera Resolution:8-megapixel Rear-Facing; 2-megapixel Front CPU:Samsung Exynos 5420 Dimensions:9.57 x 6.75 x 0.31 inches GPS:Yes Operating System:Google Android 4.3 Ports:MHL micro USB Processor Speed:1.9 GHz RAM:3 GB Screen Pixels Per Inch:298 ppi Screen Resolution:2560 x 1600 pixels Screen Size:10.1 inches Screen Type:IPS LCD Storage Capacity (as Tested):16 GB Storage Type:SSD Video Camera Resolution:1080p; 720p Weight:19.75 oz Wi-Fi (802.11x) Compatibility:2.4GHz/5GHz",
				"Tablet", 450, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 10);
		
		product = new Product("Velocity Micro Vector Z25",
				"Graphics Card:Nvidia GeForce GTX 650 Handbrake Multimedia Tests:3:26 min:sec Heaven - Max quality - Native - AA/AF=4X/4X:18 Heaven - Mid quality - 1,366 x 768 - AA/AF=Off/Off:43 Operating System:Microsoft Windows 8 PCMark7:5554 Photoshop CS6 Multimedia Tests:5.62 min:sec Primary Optical Drive:Dual-Layer DVD+/-RW Processor Name:Intel Core i5-3470 Processor Speed:3.2 GHz RAM:8 GB Storage Capacity (as Tested):1000 GB",
				"Desktop", 700, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 7);
		product = new Product("V3 Gaming Devastator",
				"Operating System:Microsoft Windows 8 PCMark7:7013 Photoshop CS6 Multimedia Tests:2:40 min:sec Primary Optical Drive:Dual-Layer DVD+/-RW Processor Family:Intel Core i5 Processor Name:Intel Core i5-4670K Processor Speed:3.4 GHz RAM:16 GB Storage Capacity (as Tested):1256 GB Storage Type:HDD SSD Tech Support:3 year limited parts/lifetime labor. Type:Gaming",
				"Desktop", 900, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 9);
		product = new Product("Acer Predator AG3-605-UR20",
				"Operating System:Microsoft Windows 8 PCMark7:4088 Photoshop CS6 Multimedia Tests:2:58 min:sec Primary Optical Drive:Blu-Ray Disc Processor Family:Intel Core i7 Processor Name:Intel Core i7-4770 Processor Speed:3.4 GHz RAM:32 GB Storage Capacity (as Tested):3024 GB Storage Type:HDD SSD Tech Support:1-year limited warranty",
				"Desktop", 600, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 6);
		
		product = new Product("LG G Flex",
				"802.11x/Band(s):802.11 b/g/n/ac Available Integrated Storage:24 GB Bands:1700 1800 1900 700 850 900 Bluetooth Version:4 Camera Resolution:13 MP Rear; 2.1 MP Front-Facing CPU:Qualcomm Snapdragon 800 Quad-Core Dimensions:6.32 x 3.21 x 0.31 inches Form Factor:Candy Bar GPS:Yes High-Speed Data:GPRS HSPA+ 21 LTE UMTS microSD Slot :No NFC:Yes Operating System as Tested:Android 4.2.2 Phone Capability / Network:GPRS GSM LTE UMTS Physical Keyboard:No Processor Speed:2.26 GHz Screen Pixels Per Inch:245 ppi Screen Resolution:1280 x 720 pixels Screen Size:6 inches Screen Type:P-OLED Service Provider:AT&T Total Integrated Storage:32 GB Video Camera Resolution:1080p Weight:6.24 oz",
				"Smartphone", 400, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 5);
		product = new Product("Google Nexus 5",
				"802.11x/Band(s):802.11 a/b/g/n/ac Available Integrated Storage:26.42 GB Bands:1800 1900 850 900 Battery Life (As Tested):14 hours 43 minutes Bluetooth Version:4.0 Camera Resolution:8 MP rear; 1.3 MP front Capacities Available:16GB; 32GB Colors Available:Black; White CPU:Qualcomm Snapdragon 800 Quad-Core Dimensions:5.43 x 2.72 x 0.34 (HWD) inches Form Factor:Candy Bar GPS:Yes High-Speed Data:LTE microSD Slot :No NFC:Yes Operating System as Tested:Android OS Phone Capability / Network:CDMA GSM LTE Physical Keyboard:No Processor Speed:2.26 GHz Screen Pixels Per Inch:445 ppi Screen Resolution:1920 x 1080 pixels Screen Size:4.95 inches Screen Type:IPS LCD Service Provider:AT&T Sprint T-Mobile Unlocked Total Integrated Storage:32 GB Weight:4.59 oz",
				"Smartphone", 300, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 2);
		product = new Product("Apple iPhone 5S",
				"802.11x/Band(s):4.0 Bands:1700 1900 2100 850 900 UMTS Battery Life (As Tested):9 hours Bluetooth Version:Yes Camera Resolution:8 MP Rear; 1.2 MP Front Capacities Available:32GB 64GB Space Gray Colors Available:Gold Silver CPU:Apple A7 Dimensions:4.87 x 2.31 x 0.3 inches Form Factor:Candy bar GPS:No High-Speed Data:16GB EVDO Rev A HSPA+ 42 LTE microSD Slot :GSM NFC:16 GB Operating System as Tested:iOS Phone Capability / Network:800 CDMA LTE UMTS Processor Speed:1.3GHz Screen Pixels Per Inch:326 Screen Resolution:1136 x 640 pixels Screen Size:4 inches Screen Type:LCD Service Provider:Verizon Wireless Total Integrated Storage:No Video Camera Resolution:802.11 a/b/g/n",
				"Smartphone", 220, getRandomSupplier());
		stockControl.addNewProductToStockList(product, 6);
	}
	
	private Supplier getRandomSupplier(){
		int randomSupplier = random.nextInt((supplierList.size() - 1) + 1);
		return (Supplier)supplierList.get(randomSupplier);		
	}
	
	private void automaticallyCreateCustomer(){
		person = new Customer("Roland","roland@msn.com","08712345","12 Main Street");
		customerList.add(person);
		person = new Customer("Tom","tom@msn.com","08713455","33 Main Street");
		customerList.add(person);
		person = new Customer("Bob","bob@msn.com","0859983","1 Main Street");
		customerList.add(person);
		displayPersonList(person);
	}
	
	private void automaticallyCreateStaff(){
		person = new Staff("Jim","jim@msn.com","08712345","123 Main Street","password",1);
		staffList.add(person);
		person = new Staff("John","john@msn.com","08609382","38 Main Street","password",2);
		staffList.add(person);
		displayPersonList(person);
	}
	
	private void automaticallyCreateSupplier(){
		person = new Supplier("Poor Buy","pb@msn.com","08502274","111 Main Street","Pat","x0123");
		supplierList.add(person);
		person = new Supplier("Good Buy","gb@msn.com","087512345","200 Main Street","Kate","x1234");
		supplierList.add(person);
		person = new Supplier("Better Buy","bb@msn.com","087713455","400 Main Street","Sam","x2345");
		supplierList.add(person);
		person = new Supplier("Best Buy","bbb@msn.com","08599983","600 Main Street","Fred","x3456");
		supplierList.add(person);
		displayPersonList(person);
	}
	
	private void displayPersonList(Person person){
		if(person instanceof Customer){
			displayList = customerList;
		}
		else if(person instanceof Staff){
			displayList = staffList;
		}
		else if(person instanceof Supplier){
			displayList = supplierList;
		}
		else{
			System.out.println("Something went wrong!!");
		}
		System.out.println();
		for(Person pers : displayList){
			pers.displayDetails();
		}
		
	}
	
	public void displayMainMenu(){
		
		System.out.println("\n******************Retail System****************");
		System.out.println("0  - Exit The Program");
		System.out.println("1  - Customers Menu");
		System.out.println("2  - Staff Menu");
		System.out.println("3  - Supplier Menu");
		System.out.println("4  - Product Menu");
		System.out.println("5  - Display Stock Levels");
		System.out.println("6  - Display Products");
		System.out.println("7  - Order Menu\n\n\n");
		
	}
	
	public void personOperation(Person person){
		String personType = "";
	
		//valid = true;
		if(person instanceof Customer){
			//id = "CX";
			personType = "customer";
			person = new Customer();
		}
		else if(person instanceof Staff){
			personType = "staff";
			person = new Staff();
		}
		else if(person instanceof Supplier){
			//id = "AX";
			person = new Supplier();
			personType = "supplier";
		}
		do{
			System.out.println("\n0 - Cancel");
			System.out.println("1 - Create new "+personType);
			System.out.println("2 - Show "+personType+"s list");
			System.out.println("3 - Edit "+personType+" details");
			System.out.println("4 - Automatically Create "+personType);
			System.out.println("5 - Remove "+personType);
			menuOption = Keyboard.readInt();
			switch(menuOption){
			case 0: System.out.println("Exiting submenu.."); break;
			case 1: createNewPerson(person);
					break;
			case 2: displayPersonList(person); break;
			case 3: changePersonDetails(person);  break;
			case 4: if(person instanceof Customer)
						automaticallyCreateCustomer();
					else if(person instanceof Staff)
						automaticallyCreateStaff();
					else
						automaticallyCreateSupplier();
					break;
			case 5: removePerson(person); break;
			default: System.out.println("Invalid option! Try again!");
			}
		}while(menuOption!=0);
		menuOption = -1;
	}
	
	public void createNewPerson(Person pers){
		person = pers;
		String name, email, address, contactNumber, password, contactName, vatNumber;
		int accessLevel = 0;
		System.out.println("Enter name :");
		name = Keyboard.readString();
		System.out.println("Enter email :");
		email = Keyboard.readString();
		System.out.println("Enter contact number:");
		contactNumber = Keyboard.readString();
		System.out.println("Enter address :");
		address = Keyboard.readString();
		if(person instanceof Customer){
			person = new Customer(name, email, contactNumber, address);
			customerList.add(person);
		}
		else if(person instanceof Staff){
			
			do{
				valid = false;
				System.out.println("Enter password :");
				String passwd = Keyboard.readString();
				System.out.println("Enter password again :");
				password = Keyboard.readString();
				if(passwd.equals(password)){
					valid = true;
				}
				else{
					System.out.println("Error! Password do not match! Try again!");
				}
			}while(!valid);
			System.out.println("Set access level!");
			System.out.println("Enter 1 for staff access or 2 for manager access :");
			try{
				
				do{
					valid = false;
					accessLevel =Integer.parseInt(Keyboard.readString());
					if(accessLevel==2||1==accessLevel){
						valid = true;
						person = new Staff(name, email, contactNumber, address, password,accessLevel);
						staffList.add(person);
						System.out.println("Account has been created.");
						System.out.println("Login is : "+person.getId()+" Password is : "+password);
					
					}
					else{
						System.out.println("Error! Only access level 1 or 2 are valid!\nEnter access level again :");
					}
				}while(!valid);
			}
			catch(NumberFormatException e){
				System.out.println("Incorrect Input! Only digits 1-2 are allowed.");
			}
		}
		else if(person instanceof Supplier){
			System.out.println("Enter contact name :");
			contactName = Keyboard.readString();
			System.out.println("Enter VAT number : ");
			vatNumber = Keyboard.readString();
			person = new Supplier(name, email, contactNumber, address, contactName, vatNumber);
			supplierList.add(person);
			System.out.println("Supplier has been created.");
		}
	}
	
	public void changePersonDetails(Person pers){
		String personType = "";
		if (pers instanceof Customer){
			list = customerList;
			personType = "customer";
		}
		else if(pers instanceof Staff){
			list = staffList;
			personType = "staff";
		}
		else{
			list = supplierList;
			personType = "supplier";
		}
		if(list.size()>0){
			System.out.println("Enter "+personType+" ID to edit details:");
			int id=-1;
			try{
				id = Integer.parseInt(Keyboard.readString());
			}
			catch(NumberFormatException e){
				System.out.println("Incorrect Input! Only digits 1-2 are allowed.");
			}
			boolean isFound = false;
			for(Person person : list){
				if(person.getId()==id){
					isFound = true;
					person.displayDetails();
					do{
						System.out.println("\n***Select the detail to change***");
						System.out.println("0 - Cancel");
						System.out.println("1 - Change name");
						System.out.println("2 - Change email");
						System.out.println("3 - Change contact number");
						System.out.println("4 - Change address");
						if (person instanceof Staff) {
							System.out.println("5 - Change access level ");
							System.out.println("6 - Change password");	
						}
						else if(person instanceof Supplier){
							System.out.println("5 - Change contact name");
							System.out.println("6 - Change VAT number");
						}
						menuOption = Keyboard.readInt();
						switch (menuOption) {
							case 0: System.out.println("Exiting submenu..");break;
				            case 1: System.out.println("Enter new name:");
				            		userInput = Keyboard.readString();
				            		if(!userInput.equalsIgnoreCase(" "))
				            			person.setName(userInput);
				            		person.displayDetails();
				            		break;
				            case 2:	System.out.println("Enter new email:");
				            		userInput = Keyboard.readString();
				            		if(!userInput.equalsIgnoreCase(" "))
				            			person.setEmail(userInput);
				            		person.displayDetails();
				            		break;
				            case 3: System.out.println("Enter new contract number:");
				            		userInput = Keyboard.readString();
				            		if(!userInput.equalsIgnoreCase(" "))
				            			person.setContactNumber(userInput);
				            		person.displayDetails();
				            		break;
				            case 4:	System.out.println("Enter new address:");
		            				userInput = Keyboard.readString();
		            				if(!userInput.equalsIgnoreCase(" "))
		            					person.setAddress(userInput);
		            				person.displayDetails();
		            				break;
				            case 5:	if (person instanceof Staff){
				            		String password = "";
						            	do{
											valid = false;
											System.out.println("Enter new password :");
											String passwd = Keyboard.readString();
											System.out.println("Enter new password again :");
											userInput = Keyboard.readString();
											if(passwd.equals(userInput)){
												valid = true;
												((Staff) person).setPassword(userInput);
												break;
											}
											else{
												System.out.println("Error! Password do not match! Try again!");
											}
										}while(!valid);
				            		}
				            		else if (person instanceof Supplier){
				            			System.out.println("Enter new contract name:");
					            		userInput = Keyboard.readString();
					            		if(!userInput.equalsIgnoreCase(" "))
					            			((Supplier) person).setContactName(userInput);
					            		person.displayDetails();
					            		break;
				            		}
				            		break;
				            case 6: if (person instanceof Staff){
						            	System.out.println("Set access level!");
										System.out.println("Enter 1 for staff access or 2 for manager access :");
										do{
											try{
												valid = false;
												int accessLevel =Integer.parseInt(Keyboard.readString());
												if(accessLevel==2||1==accessLevel){
													valid = true;
													((Staff) person).setAccessLevel(accessLevel);
													break;
												}
												else{
													System.out.println("Error! Only access level 1 or 2 are valid!\nEnter access level again :");
												}
											}
											catch(NumberFormatException e){
												System.out.println("Incorrect Input! Only digits 1-2 are allowed.\nEnter access level again :");
											}
										}while(!valid);
				            		}
				            		else if (person instanceof Supplier){
				            			System.out.println("Enter new VAT number:");
					            		userInput = Keyboard.readString();
					            		if(!userInput.equalsIgnoreCase(" "))
					            			((Supplier) person).setVatNumber(userInput);
					            		person.displayDetails();
					            		break;
				            		}
				            		break;
				            
				            default: System.out.println("Invalid choice. Try again."); break;
						}			
					}while(menuOption!=0);
					menuOption=-1;	
				}
				else{
					isFound = false;
				}					
			}
			if(!isFound){
				System.out.println("The "+personType+" ID not found\n");
			}
		}
		else
			System.out.println("The "+personType+"s list is empty.\n");	
	}
	
	public void removePerson(Person pers){
		String personType = "";
		if (pers instanceof Customer){
			list = customerList;
			personType = "customer";
		}
		else if(pers instanceof Staff){
			list = staffList;
			personType = "staff";
		}
		else{
			list = supplierList;
			personType = "supplier";
		}	
		do{
			System.out.println("\n***Select the operation to perform***");
			System.out.println("0 - Cancel");
			System.out.println("1 - Remove "+personType);
			System.out.println("2 - List "+personType+"s");
			menuOption = Keyboard.readInt();
			switch (menuOption) {
				case 0: System.out.println("Exiting submenu..");break;
	            case 1: if(list.size()>0){
    						System.out.println("Enter "+personType+" ID to remove:");
    						int id=-1;
    						try{
    								id = Integer.parseInt(Keyboard.readString());
			    			}
			    			catch(NumberFormatException e){
			    				System.out.println("Incorrect Input! Only digits 1-2 are allowed.");
			    			}
			    			boolean isFound = false;
			    			for(Person person : list){
			    				if(person.getId()==id){
			    					isFound = true;
			    					System.out.println("*****Selected "+personType+"*******\n");
			    					person.displayDetails();
			    					System.out.println("\nConfirm removing "+personType+"?\nENTER Y or N");
			    					userInput = Keyboard.readString();
			    					if(userInput.equalsIgnoreCase("Y")){
			    						System.out.println(person.getName()+" has been removed.");
			    						list.remove(person);	    						
			    					}
			    					break;
			    				}
			    				else{
			    					isFound = false;
			    				}
			    			}
			    			if(!isFound){
			    				System.out.println("The ID is not found. Try again!");
			    			}
	            		}
	            		else{
	            			System.out.println("The list is empty!");
	            		}
	            		break;
	            case 2:	if(list.size()>0){
		    				for(Person person : list){
		    					person.displayDetails();
		    				}
	    				}			            		
			            else{
	            			System.out.println("The list is empty!");
			            }
	            		break;
	          default: System.out.println("Invalid choice. Try again."); break;
			}			
		}while(menuOption!=0);
		menuOption=-1;	
	}
	
	public static void main(String args[]){
		new RetailSystem();
	}	
}
