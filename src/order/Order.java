package order;

import java.util.ArrayList;

import person.Person;


/**
 * @author Roland Katona
 */
public class Order {
	// 2014.08.26 R&S
		private static int uniqueId = 1;
		private int id;
		private Person person;
		private ArrayList<StockItem> orderEntryList;
		private double grandTotal;
		private boolean status;
		


		public Order(Person person, ArrayList<StockItem> orderEntryList, double grandTotal) {
			this.grandTotal = grandTotal;
			this.id = uniqueId++;
			this.person = person;
			this.orderEntryList = orderEntryList;
			this.status = false;
		}
		
		public double getGrandTotalOfOrder(){
			return grandTotal;
		}
		
		public int getId() {
			return id;
		}

		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}

		public ArrayList<StockItem> getOrderEntryList() {
			return orderEntryList;
		}

		public void setOrderEntryList(ArrayList<StockItem> orderEntryList) {
			this.orderEntryList = orderEntryList;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}	
}
