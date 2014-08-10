package group2;

import java.util.ArrayList;
import java.util.Random;


public class RetailSystem {
	private ArrayList<Person> list;
	private ArrayList<Person> displayList;
	private StockControl stockControl;
	private ArrayList<Person> customerList;
	private ArrayList<Person> staffList;
	private ArrayList<Person> supplierList;
	private ArrayList<PurchaseOrder> purchaseOrderList;
	private ArrayList<SupplyOrder> supplyOrderList;
	private int menuOption;
	private Person person;
	private Random random;
	private boolean valid = false;
	
	
	public RetailSystem() {
		random = new Random();
		list = new ArrayList<Person>();
		stockControl = new StockControl();
		customerList = new ArrayList<Person>();
		staffList = new ArrayList<Person>();
		supplierList = new ArrayList<Person>();
		purchaseOrderList = new ArrayList<PurchaseOrder>();
		supplyOrderList = new ArrayList<SupplyOrder>();
		menuOption = -1;
		
		
		boolean terminateProgram = false;
		do{
			displayMainMenu();
			menuOption = Keyboard.readInt();
			
			switch(menuOption){
			case 0: terminateProgram = true; System.out.println("Terminate Program.."); break;
			case 1: personOperation(1); break;
			case 2: personOperation(2); break;
			case 3: personOperation(3); break;
			case 4: automaticallyCreateProducts(); break;
			case 5: break;
			case 6: break;
			case 7: stockControl.displayStockList(); break;
			case 8: stockControl.displayProductByCategory(); break;
			case 9: break;
			default: break;
			
			}
			
		}while(!terminateProgram);
	}
	
	private void automaticallyCreateProducts(){
		Product product = new Product("Asus EeeePC 1015px",
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
		Person person1 = new Customer("Tom","tom@msn.com","08713455","33 Main Street");
		customerList.add(person1);
		person = new Customer("Bob","bob@msn.com","0859983","1 Main Street");
		customerList.add(person);
		displayList(1);
	}
	
	private void automaticallyCreateStaff(){
		person = new Staff("Jim","jim@msn.com","08712345","123 Main Street","password",1);
		staffList.add(person);
		person = new Staff("John","john@msn.com","08609382","38 Main Street","password",2);
		staffList.add(person);
		displayList(2);
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
		displayList(3);
	}
	
	private void displayList(int type){
		if(type==1){
			displayList = customerList;
		}
		else if(type==2){
			displayList = staffList;
		}
		else if(type==3){
			displayList = supplierList;
		}
		else{
			System.out.println("Something went wrong!!");
		}
		System.out.println();
		for(Person person : displayList){
			person.displayDetails();
		}
		
	}
	
	public void displayMainMenu(){
		
		System.out.println("\n******************Retail System****************");
		System.out.println("0  - Exit The Program");
		System.out.println("1  - Customers Menu");
		System.out.println("2  - Staff Menu");
		System.out.println("3  - Supplier Menu");
		System.out.println("4  - Automatically Create Product and Add to Stock");
		System.out.println("5  - Add New Product to Stock");
		System.out.println("6  - Update Product Quantity On Stock");
		System.out.println("7  - Display Stock Levels");
		System.out.println("8  - Display Products");
		System.out.println("9  - Display Products");
		System.out.println("10  - Display Products");
		System.out.println("11 - Create New Customer\n\n\n");
		
	}
	
	public void personOperation(int type){
		String personType = "";
		String name, email, address, contactNumber, password, contactName, vatNumber;
		int accessLevel = 0;
 MENU:  do{
			valid = true;
			if(1==type){
				//id = "CX";
				personType = "customer";
				person = new Customer();
			}
			else if(2==type){
				personType = "staff";
				person = new Staff();
			}
			else if(3==type){
				//id = "AX";
				person = new Supplier();
				personType = "supplier";
			}
			System.out.println("\n0 - Cancel");
			System.out.println("1 - Create new "+personType);
			System.out.println("2 - Show "+personType+"s list");
			System.out.println("3 - Edit "+personType+" details");
			System.out.println("4 - Automatically Create "+personType);
			menuOption = Keyboard.readInt();
			switch(menuOption){
			case 0: System.out.println("Exiting submenu.."); break;
			case 1: System.out.println("Enter name :");
					name = Keyboard.readString();
					System.out.println("Enter email :");
					email = Keyboard.readString();
					System.out.println("Enter contact number:");
					contactNumber = Keyboard.readString();
					System.out.println("Enter address :");
					address = Keyboard.readString();
					if(type==2){
						
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
									continue MENU;
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
					else if(type==3){
						System.out.println("Enter contact name :");
						contactName = Keyboard.readString();
						System.out.println("Enter VAT number : ");
						vatNumber = Keyboard.readString();
						person = new Supplier(name, email, contactNumber, address, contactName, vatNumber);
						supplierList.add(person);
						System.out.println("Supplier has been created.");
						break;
					}
					person = new Customer(name, email, contactNumber, address);
					customerList.add(person);
					break;
			case 2: displayList(type); break;
			case 3: changePersonDetails(person);  break;
			case 4: if(type==1)
						automaticallyCreateCustomer();
					else if(type==2)
						automaticallyCreateStaff();
					else
						automaticallyCreateSupplier();
					break;
			default: System.out.println("Invalid option! Try again!");
			}
		}while(menuOption!=0);
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
			String userInput = Keyboard.readString();
			boolean isFound = false;
			for(Person person : list){
				if(person.getId()==Integer.parseInt(userInput)){
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
											password = Keyboard.readString();
											if(passwd.equals(password)){
												valid = true;
												((Staff) person).setPassword(password);
											}
											else{
												System.out.println("Error! Password do not match! Try again!");
											}
										}while(!valid);
										
				            		}
				            		break;
				            case 6: if (person instanceof Staff){
						            	System.out.println("Set access level!");
										System.out.println("Enter 1 for staff access or 2 for manager access :");
										try{
											
											do{
												valid = false;
												int accessLevel =Integer.parseInt(Keyboard.readString());
												if(accessLevel==2||1==accessLevel){
													valid = true;
													//person = new Staff(name, email, contactNumber, address, password,accessLevel);
													//staffList.add(person);
													//System.out.println("Account has been created.");
													//System.out.println("Login is : "+person.getId()+" Password is : "+password);
													//continue MENU;
													((Staff) person).setAccessLevel(accessLevel);
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
				            		else if (person instanceof Supplier){
				            			break;
				            		}
				            		break;
				            
				            default: System.out.println("Invalid choice. Try again."); break;
						}			
					}while(menuOption!=0);
					break;	
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
	
	
	public static void main(String args[]){
		new RetailSystem();
	}	
}
