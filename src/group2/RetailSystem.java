package group2;

import java.util.ArrayList;
import java.util.Random;

import order.Order;
import order.Product;
import order.QProduct;
import person.Person;
import person.Supplier;

/**
 * @author Roland
 *
 */
public class RetailSystem {
	private StockControl stockControl;
	private ArrayList<Order> purchaseOrderList;
	private ArrayList<Order> supplyOrderList;
	private ArrayList<QProduct> orderList;
	private int menuOption;
	private Person person;
	private Product product;
	private Random random;
	private boolean valid = false;
	private String userInput;
	private Order order;
	private Object orderObject[];
	private PersonDB personDB;

	/**
	 * Default constructor
	 */
	/*
	 * public RetailSystem() {
	 * 
	 * System.out.println("Retail System "); random = new Random(); stockControl
	 * = new StockControl(); purchaseOrderList = new ArrayList<Order>();
	 * supplyOrderList = new ArrayList<Order>(); orderList = new
	 * ArrayList<QProduct>(); menuOption = -1; orderObject = new Object[2];
	 * 
	 * personDB = new PersonDB();
	 * 
	 * 
	 * personDB.automaticallyCreateStaff();
	 * personDB.automaticallyCreateSupplier(); automaticallyCreateProducts();
	 * personDB.automaticallyCreateCustomer(); // personDB.displayPersonList(new
	 * Staff());
	 * 
	 * //new Login(personDB); //new MainDisplay(personDB); //new
	 * MainFrame(personDB); boolean terminateProgram = false; do{
	 * displayMainMenu(); menuOption = Keyboard.readInt();
	 * 
	 * switch(menuOption){ case 0: terminateProgram = true;
	 * System.out.println("Terminate Program.."); break; case 1://
	 * personDB.personOperation(new Customer()); break; case 2://
	 * personDB.personOperation(new Staff()); break; case 3://
	 * personDB.personOperation(new Supplier()); break; case 4:
	 * productOperation(); break; case 5: stockControl.displayStockList();
	 * break; case 6: stockControl.displayProductByCategory(); break; case 7:
	 * orderOperation(new PurchaseOrder()); break; default: break;
	 * 
	 * }
	 * 
	 * }while(!terminateProgram); }
	 */

	/*
	 * public void orderOperation(Order order){ String orderType = "";
	 * 
	 * if(order instanceof SupplyOrder){ orderType = "Supply order"; this.order
	 * = new SupplyOrder(); } else if(order instanceof PurchaseOrder){ orderType
	 * = "Purchase order"; this.order = new PurchaseOrder(); } do{
	 * System.out.println("\n****"+orderType+" menu****");
	 * System.out.println("0 - Cancel");
	 * System.out.println("1 - Create New "+orderType);
	 * System.out.println("2 - Show "+orderType+"s");
	 * System.out.println("3 - Update Active "+orderType);
	 * System.out.println("4 - Automatically Create "+orderType+"s");
	 * System.out.println("5 - Process "+orderType); menuOption =
	 * Keyboard.readInt(); switch(menuOption){ case 0:
	 * System.out.println("Exiting submenu.."); break; case 1:
	 * createNewOrder(this.order);break; case 2: displayOrderList(this.order);
	 * break; case 3: stockControl.displayProductByCategory(); break; case 4:
	 * removeProduct(); break; case 5: break; default:
	 * System.out.println("Invalid option! Try again!"); break; }
	 * }while(menuOption!=0); menuOption = -1; }
	 * 
	 * public void displayOrderList(Order order){ if(order instanceof
	 * SupplyOrder){ orderList = supplyOrderList; } else if(order instanceof
	 * PurchaseOrder){ orderList = purchaseOrderList; }
	 * 
	 * for(Order ord : orderList){
	 * System.out.println("\nOrder id : "+ord.getOrderId()); for(QProduct :
	 * ord.getProductList()){ System.out.println();
	 * ((Product)object[0]).displayDetail(); }
	 * 
	 * System.out.println("The total order is : "+
	 * ((PurchaseOrder)ord).getOrderPrice()+" euro.");
	 * System.out.println("Shipping price : "
	 * +((PurchaseOrder)ord).getShippingPrice()+"euro"); System.out.println(
	 * "===============================================================");
	 * System
	 * .out.println("Grand total is : "+(((PurchaseOrder)ord).getOrderPrice
	 * ()+((PurchaseOrder)ord).getShippingPrice())+" euro\n");
	 * 
	 * } }
	 */

	private Product getProductById(int id) {
		boolean isFound = false;

		for (Object object[] : stockControl
				.getStockList()) {
			product = (Product) object[0];
			if (product.getProductID() == id) {
				isFound = true;
				return product;
			}
			else {
				isFound = false;
			}
		}

		if (!isFound) {
			System.out
					.println("Product ID not found! Try again!");
			System.out
					.println("Enter product ID :");
			menuOption = Keyboard.readInt();
			getProductById(menuOption);
		}
		return null;
	}

	/*
	 * public void createNewOrder(Order order){
	 * 
	 * if(order instanceof SupplyOrder){ orderList = supplyOrderList; } else
	 * if(order instanceof PurchaseOrder){ orderList = purchaseOrderList;
	 * this.order = new PurchaseOrder(); }
	 * 
	 * System.out.println("New Order");
	 * 
	 * 
	 * 
	 * do{ System.out.println("**\nSelect Operation to Perform**");
	 * System.out.println("0 - Cancel");
	 * System.out.println("1 - Add product to order");
	 * System.out.println("2 - Show products in current order");
	 * System.out.println("3 - Show all products "); menuOption =
	 * Keyboard.readInt(); orderObject = new Object[2]; switch(menuOption){ case
	 * 0: System.out.println("Exiting submenu.."); break; case 1:
	 * System.out.println("Enter the ID of the product you want to order :");
	 * valid = false; do{ valid = isUserInputValid(Keyboard.readString());
	 * }while(!valid); do{ product = getProductById(menuOption);
	 * }while(product==null); orderObject[0] = product; product.displayDetail();
	 * System.out.println("\nEnter quantity :"); do{ valid =
	 * isUserInputValid(Keyboard.readString()); }while(!valid); orderObject[1] =
	 * menuOption; if(menuOption>0) //this.order.orderList.add(orderObject);
	 * break; case 2: displayOrderDetails(order); break; case 3:
	 * displayAllProductDetails(); break; default:
	 * System.out.println("Invalid option! Try again!"); break; }
	 * 
	 * }while(menuOption!=0); menuOption = -1;
	 * ((PurchaseOrder)this.order).setShippingPrice(0);
	 * orderList.add(this.order);
	 * //System.out.println("\nNew order with ID: "+this
	 * .order.orderId+" has been created."); }
	 */

	/*
	 * public void displayOrderDetails(Order order){
	 * 
	 * if(order instanceof SupplyOrder){
	 * 
	 * } else if(order instanceof PurchaseOrder){ //
	 * System.out.println("The following products are on order "
	 * +(order.orderId+1)); }
	 * System.out.println("Product ID | Quantity | Product name ");
	 * System.out.println("---------------------------------------------\n");
	 * for(Object object[] : this.order.getProductList()){
	 * System.out.print(((Product)object[0]).getProductID()+"             ");
	 * System.out.print(object[1]+"           ");
	 * System.out.print(((Product)object[0]).getProductName()+"\n"); } }
	 */

	/**
	 * Product operations menu
	 */
	public void productOperation() {
		do {
			System.out
					.println("\n****Product Operation****");
			System.out.println("0 - Cancel");
			System.out
					.println("1 - Add new product to stock");
			System.out
					.println("2 - Show stock levels");
			System.out
					.println("3 - Show products by category");
			System.out
					.println("4 - Remove product from stock ");
			System.out
					.println("5 - Change product detail");
			System.out
					.println("6 - Update product quantity on stock");
			System.out
					.println("7 - Automatically create products and add to stock");
			menuOption = Keyboard.readInt();
			switch (menuOption) {
			case 0:
				System.out
						.println("Exiting submenu..");
				break;
			case 1:
				createNewProduct();
				break;
			case 2:
				stockControl.displayStockList();
				break;
			case 3:
				stockControl
						.displayProductByCategory();
				break;
			case 4:
				removeProduct();
				break;
			case 5:
				changeProductDetails();
				break;
			case 6:
				updateProductQuantity();
				break;
			case 7:// if(personDB.getSupplierListSize()>0)
				automaticallyCreateProducts();
				// else
				System.out
						.println("Suppliers must be created first!");
				break;
			default:
				System.out
						.println("Invalid option! Try again!");
				break;
			}
		}
		while (menuOption != 0);
		menuOption = -1;
	}

	public void updateProductQuantity() {
		do {
			System.out
					.println("\n***Select the operation to perform***");
			System.out.println("0 - Cancel");
			System.out
					.println("1 - Update the quantity of an existing product on stock");
			System.out
					.println("2 - Show current product quantity on stock");
			menuOption = Keyboard.readInt();
			switch (menuOption) {
			case 0:
				System.out
						.println("Exiting submenu..");
				break;
			case 1:
				if (stockControl
						.getStockListSize() > 0) {
					System.out
							.println("Enter product ID to update its quantity :");
					int id = -1;
					try {
						id = Integer
								.parseInt(Keyboard
										.readString());
					}
					catch (NumberFormatException e) {
						System.out
								.println("Incorrect Input! Only digits 1-9 are allowed.");
					}
					boolean isFound = false;
					Object[] productObject = stockControl
							.findProductInStockList(id);
					if (productObject != null) {
						isFound = true;
						System.out
								.println("*****Selected product*******\n");
						((Product) productObject[0])
								.displayDetail();
						System.out
								.println("\nCurrent quantity on stock : "
										+ (Integer) productObject[1]);
						// System.out.println("\nConfirm removing product\nENTER Y or N");
						System.out
								.println("Enter new quantity : ");
						try {
							id = Integer
									.parseInt(Keyboard
											.readString());
						}
						catch (NumberFormatException e) {
							System.out
									.println("Incorrect Input! Only digits 1-9 are allowed.");
						}
						productObject[1] = id;

						stockControl
								.displayStockList();

						break;
					}
					else {
						isFound = false;
					}

					if (!isFound) {
						System.out
								.println("The product ID is not found. Try again!");
					}
				}
				else {
					System.out
							.println("The list is empty!");
				}
				break;
			case 2:
				stockControl.displayStockList();
				break;
			default:
				System.out
						.println("Invalid choice. Try again.");
				break;
			}
		}
		while (menuOption != 0);
		menuOption = -1;
	}

	public void changeProductDetails() {
		do {
			System.out
					.println("\n***Select the operation to perform***");
			System.out.println("0 - Cancel");
			System.out
					.println("1 - Change the details of an existing product on stock");
			System.out
					.println("2 - Show the details of all products on stock");
			menuOption = Keyboard.readInt();
			switch (menuOption) {
			case 1:
				if (stockControl
						.getStockListSize() > 0) {
					System.out
							.println("Enter product ID to change its details :");
					int id = -1;
					try {
						id = Integer
								.parseInt(Keyboard
										.readString());
					}
					catch (NumberFormatException e) {
						System.out
								.println("Incorrect Input! Only digits 1-9 are allowed.");
					}
					boolean isFound = false;
					Object[] productObject = stockControl
							.findProductInStockList(id);
					if (productObject != null) {
						product = (Product) productObject[0];
						isFound = true;
						System.out
								.println("*****Selected product*******\n");
						product.displayDetail();
						do {
							System.out
									.println("\n***Select the detail to change***");
							System.out
									.println("0 - Cancel");
							System.out
									.println("1 - Change product name");
							System.out
									.println("2 - Change product description");
							System.out
									.println("3 - Change product category");
							System.out
									.println("4 - Change supplier price");
							System.out
									.println("5 - Change profit margin");
							System.out
									.println("6 - Change product supplier");
							menuOption = Keyboard
									.readInt();
							switch (menuOption) {
							case 0:
								System.out
										.println("Exiting submenu..");
								break;
							case 1:
								System.out
										.println("Enter new product name:");
								userInput = Keyboard
										.readString();
								product.setProductName(userInput);
								product.displayDetail();
								break;
							case 2:
								System.out
										.println("Enter new product description:");
								userInput = Keyboard
										.readString();
								product.setProductDescription(userInput);
								product.displayDetail();
								break;
							case 3:
								System.out
										.println("Enter new product category:");
								userInput = Keyboard
										.readString();
								product.setProductCategory(userInput);
								product.displayDetail();
								break;
							case 4:
								System.out
										.println("Enter new supplier price:");
								product.setSupplierPrice(Double
										.parseDouble(Keyboard
												.readString()));
								// product.setRetailPrice(product.getSupplierPrice()*(1+product.getProfitMargin()));
								product.displayDetail();
								break;
							case 5:
								System.out
										.println("Enter new profit margin:");
								product.setProfitMargin(Double
										.parseDouble(Keyboard
												.readString()));
								product.displayDetail();
								break;
							case 6: // personDB.addSupplier(2);
								break;

							default:
								System.out
										.println("Invalid choice. Try again.");
								break;
							}
						}
						while (menuOption != 0);
						menuOption = -1;

						break;
					}
					else {
						isFound = false;
					}
					if (!isFound) {
						System.out
								.println("The product ID is not found. Try again!");
					}
				}
				else {
					System.out
							.println("The list is empty!");
				}
				break;
			case 2:
				displayAllProductDetails();
				break;
			default:
				break;
			}
		}
		while (menuOption != 0);
		menuOption = -1;
	}

	public void displayAllProductDetails() {

		for (Object productObject[] : stockControl
				.getStockList()) {
			((Product) productObject[0])
					.displayDetail();
		}
	}

	public void removeProduct() {
		do {
			System.out
					.println("\n***Select the operation to perform***");
			System.out.println("0 - Cancel");
			System.out
					.println("1 - Remove product from stock");
			System.out
					.println("2 - Show products in stock");
			menuOption = Keyboard.readInt();
			switch (menuOption) {
			case 0:
				System.out
						.println("Exiting submenu..");
				break;
			case 1:
				if (stockControl
						.getStockListSize() > 0) {
					System.out
							.println("Enter product ID to remove:");
					int id = -1;
					try {
						id = Integer
								.parseInt(Keyboard
										.readString());
					}
					catch (NumberFormatException e) {
						System.out
								.println("Incorrect Input! Only digits 1-9 are allowed.");
					}
					boolean isFound = false;
					Object[] productObject = stockControl
							.findProductInStockList(id);
					if (productObject != null) {
						isFound = true;
						System.out
								.println("*****Selected product*******\n");
						((Product) productObject[0])
								.displayDetail();
						System.out
								.println("\nConfirm removing product\nENTER Y or N");
						userInput = Keyboard
								.readString();
						if (userInput
								.equalsIgnoreCase("Y")) {
							System.out
									.println("Product has been removed.");
							stockControl
									.removeProductFromStockList(productObject);
							stockControl
									.displayStockList();
						}
						break;
					}
					else {
						isFound = false;
					}

					if (!isFound) {
						System.out
								.println("The product ID is not found. Try again!");
					}
				}
				else {
					System.out
							.println("The list is empty!");
				}
				break;
			case 2:
				stockControl.displayStockList();
				break;
			default:
				System.out
						.println("Invalid choice. Try again.");
				break;
			}
		}
		while (menuOption != 0);
		menuOption = -1;
	}

	public boolean isUserInputValid(String input) {

		try {
			menuOption = Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException e) {
			System.out
					.println("Error!Invalid input. Only digits 0-9 are allowed. Try again!");
		}
		return false;
	}

	public void createNewProduct() {
		String productName, productDescription, productCategory;
		double supplierPrice = -1;
		int quantity = 0;
		System.out
				.println("Enter product category :");
		productCategory = Keyboard.readString();
		System.out
				.println("Enter product name :");
		productName = Keyboard.readString();
		System.out
				.println("Enter product description :");
		productDescription = Keyboard
				.readString();
		valid = false;
		do {
			try {
				System.out
						.println("Enter supplier price :");
				supplierPrice = Double
						.parseDouble(Keyboard
								.readString());
				valid = true;
			}
			catch (NumberFormatException e) {
				System.out
						.println("Error!Invalid input. Only digits 0-9 are allowed! Try again!");
			}
		}
		while (!valid);
		// personDB.addSupplier(1);
		product = new Product(productName,
				productDescription,
				productCategory, supplierPrice,
				(Supplier) person);
		System.out
				.println("\nProduct has been created.");
		valid = false;
		do {
			try {
				System.out
						.println("Enter product quantity to add to stock :");
				quantity = Integer
						.parseInt(Keyboard
								.readString());
				if (quantity < 0) {
					System.out
							.println("Error! Invalid value. Only positive numbers are valid. Try again!");
					continue;
				}
				else {
					valid = true;
				}
			}
			catch (NumberFormatException e) {
				System.out
						.println("Error!Invalid input. Only digits 0-9 are allowed! Try again!");
			}
		}
		while (!valid);
		stockControl.addNewProductToStockList(
				product, quantity);
		System.out
				.println(quantity
						+ " "
						+ productName
						+ " was successfully added to stock.");
	}

	private void automaticallyCreateProducts() {
		product = new Product(
				"Asus EeeePC 1015px",
				"Atom N570 / 1.66 GHz - Windows 7 Starter - 1 GB RAM - 250 GB HDD - 10.1 inches wide 1024 x 600 - Intel GMA 3150 - black Series",
				"Laptop", 200, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 22);
		product = new Product(
				"Apple MacBook Pro",
				"2.0GHz Intel Core i7 - 4 GB RAM - 1,280x800-pixel 15inches display - 500 GB SSD - OSX 10.9.3 Mawericks",
				"Laptop", 1000, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 5);
		product = new Product(
				"HP Chromebook 11",
				"1.7 GHz Samsung Exynos 5250 - 2GB RAM - 11.6 inches 1366 x 768 display - ARM Mali-T604 Graphics - 16GB SSD - Google Chrome OS",
				"Laptop", 350, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 8);

		product = new Product(
				"Apple iPad mini",
				"Battery Life:6 hours 8 minutes Bluetooth Version:4.0 Camera Resolution:1.2MP Front; 5MP Rear Cellular Technology :CDMA 1X EDGE EV-DO EV-DO Rev A GSM HSDPA HSPA HSPA+ LTE UMTS CPU:Apple A7 Dimensions:7.87 x 5.3 x 0.29 (HWD) inches Operating System:Apple iOS Ports:Apple Lightning Screen Pixels Per Inch:326 Screen Resolution:2048 x 1536 pixels Screen Size:7.9 inches Screen Type:Retina Service Provider:AT&T Sprint T-Mobile Verizon Wireless Storage Capacity (as Tested):16 GB Storage Type:SSD Touch Screen:Yes Video Camera Resolution:720p Front; 1080p Rear Weight:11.68 oz Wi-Fi (802.11x) Compatibility:2.4GHz/5GHz",
				"Tablet", 400, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 7);
		product = new Product(
				"Amazon Kindle Fire HDX",
				"Battery Life:44 minutes 7 hours Bluetooth Version:4.0 Camera Resolution:8-megapixel Rear-Facing; 720p Front-Facing Cellular Technology :EDGE GPRS HSDPA HSPA+ LTE CPU:Qualcomm Snapdragon 800 Quad-Core Dimensions:9.1 x 6.2 x 0.31 inches GPS:Yes Graphics Card:ARM Adreno 330 Operating System:Google Android 4.2.2 Ports:micro USB Processor Speed:2.2 GHz RAM:2 GB Screen Pixels Per Inch:339 ppi Screen Resolution:2560 x 1600 pixels Screen Size:8.9 inches Screen Type:IPS LCD Service Provider:AT&T Storage Capacity (as Tested):16 GB Storage Type:SSD Video Camera Resolution:1080p Rear; 720p Front Weight:13.5 oz Wi-Fi (802.11x) Compatibility:2.4GHz/5GHz",
				"Tablet", 330, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 12);
		product = new Product(
				"Samsung Galaxy Note ",
				"Additional Storage:MicroSD Battery Life:31 minutes 7 hours Battery Size:8220 mAh Bluetooth Version:4.0 Camera Resolution:8-megapixel Rear-Facing; 2-megapixel Front CPU:Samsung Exynos 5420 Dimensions:9.57 x 6.75 x 0.31 inches GPS:Yes Operating System:Google Android 4.3 Ports:MHL micro USB Processor Speed:1.9 GHz RAM:3 GB Screen Pixels Per Inch:298 ppi Screen Resolution:2560 x 1600 pixels Screen Size:10.1 inches Screen Type:IPS LCD Storage Capacity (as Tested):16 GB Storage Type:SSD Video Camera Resolution:1080p; 720p Weight:19.75 oz Wi-Fi (802.11x) Compatibility:2.4GHz/5GHz",
				"Tablet", 450, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 10);

		product = new Product(
				"Velocity Micro Vector Z25",
				"Graphics Card:Nvidia GeForce GTX 650 Handbrake Multimedia Tests:3:26 min:sec Heaven - Max quality - Native - AA/AF=4X/4X:18 Heaven - Mid quality - 1,366 x 768 - AA/AF=Off/Off:43 Operating System:Microsoft Windows 8 PCMark7:5554 Photoshop CS6 Multimedia Tests:5.62 min:sec Primary Optical Drive:Dual-Layer DVD+/-RW Processor Name:Intel Core i5-3470 Processor Speed:3.2 GHz RAM:8 GB Storage Capacity (as Tested):1000 GB",
				"Desktop", 700, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 7);
		product = new Product(
				"V3 Gaming Devastator",
				"Operating System:Microsoft Windows 8 PCMark7:7013 Photoshop CS6 Multimedia Tests:2:40 min:sec Primary Optical Drive:Dual-Layer DVD+/-RW Processor Family:Intel Core i5 Processor Name:Intel Core i5-4670K Processor Speed:3.4 GHz RAM:16 GB Storage Capacity (as Tested):1256 GB Storage Type:HDD SSD Tech Support:3 year limited parts/lifetime labor. Type:Gaming",
				"Desktop", 900, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 9);
		product = new Product(
				"Acer Predator AG3-605-UR20",
				"Operating System:Microsoft Windows 8 PCMark7:4088 Photoshop CS6 Multimedia Tests:2:58 min:sec Primary Optical Drive:Blu-Ray Disc Processor Family:Intel Core i7 Processor Name:Intel Core i7-4770 Processor Speed:3.4 GHz RAM:32 GB Storage Capacity (as Tested):3024 GB Storage Type:HDD SSD Tech Support:1-year limited warranty",
				"Desktop", 600, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 6);

		product = new Product(
				"LG G Flex",
				"802.11x/Band(s):802.11 b/g/n/ac Available Integrated Storage:24 GB Bands:1700 1800 1900 700 850 900 Bluetooth Version:4 Camera Resolution:13 MP Rear; 2.1 MP Front-Facing CPU:Qualcomm Snapdragon 800 Quad-Core Dimensions:6.32 x 3.21 x 0.31 inches Form Factor:Candy Bar GPS:Yes High-Speed Data:GPRS HSPA+ 21 LTE UMTS microSD Slot :No NFC:Yes Operating System as Tested:Android 4.2.2 Phone Capability / Network:GPRS GSM LTE UMTS Physical Keyboard:No Processor Speed:2.26 GHz Screen Pixels Per Inch:245 ppi Screen Resolution:1280 x 720 pixels Screen Size:6 inches Screen Type:P-OLED Service Provider:AT&T Total Integrated Storage:32 GB Video Camera Resolution:1080p Weight:6.24 oz",
				"Smartphone", 400, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 5);
		product = new Product(
				"Google Nexus 5",
				"802.11x/Band(s):802.11 a/b/g/n/ac Available Integrated Storage:26.42 GB Bands:1800 1900 850 900 Battery Life (As Tested):14 hours 43 minutes Bluetooth Version:4.0 Camera Resolution:8 MP rear; 1.3 MP front Capacities Available:16GB; 32GB Colors Available:Black; White CPU:Qualcomm Snapdragon 800 Quad-Core Dimensions:5.43 x 2.72 x 0.34 (HWD) inches Form Factor:Candy Bar GPS:Yes High-Speed Data:LTE microSD Slot :No NFC:Yes Operating System as Tested:Android OS Phone Capability / Network:CDMA GSM LTE Physical Keyboard:No Processor Speed:2.26 GHz Screen Pixels Per Inch:445 ppi Screen Resolution:1920 x 1080 pixels Screen Size:4.95 inches Screen Type:IPS LCD Service Provider:AT&T Sprint T-Mobile Unlocked Total Integrated Storage:32 GB Weight:4.59 oz",
				"Smartphone", 300, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 2);
		product = new Product(
				"Apple iPhone 5S",
				"802.11x/Band(s):4.0 Bands:1700 1900 2100 850 900 UMTS Battery Life (As Tested):9 hours Bluetooth Version:Yes Camera Resolution:8 MP Rear; 1.2 MP Front Capacities Available:32GB 64GB Space Gray Colors Available:Gold Silver CPU:Apple A7 Dimensions:4.87 x 2.31 x 0.3 inches Form Factor:Candy bar GPS:No High-Speed Data:16GB EVDO Rev A HSPA+ 42 LTE microSD Slot :GSM NFC:16 GB Operating System as Tested:iOS Phone Capability / Network:800 CDMA LTE UMTS Processor Speed:1.3GHz Screen Pixels Per Inch:326 Screen Resolution:1136 x 640 pixels Screen Size:4 inches Screen Type:LCD Service Provider:Verizon Wireless Total Integrated Storage:No Video Camera Resolution:802.11 a/b/g/n",
				"Smartphone", 220, personDB
						.getRandomSupplier());
		stockControl.addNewProductToStockList(
				product, 6);
	}

	public void displayMainMenu() {

		System.out
				.println("\n******************Retail System****************");
		System.out
				.println("0  - Exit The Program");
		System.out.println("1  - Customers Menu");
		System.out.println("2  - Staff Menu");
		System.out.println("3  - Supplier Menu");
		System.out.println("4  - Product Menu");
		System.out
				.println("5  - Display Stock Levels");
		System.out
				.println("6  - Display Products");
		System.out
				.println("7  - Order Menu\n\n\n");

	}

	public static void main(String args[]) {
		new RetailSystem();
	}
}
