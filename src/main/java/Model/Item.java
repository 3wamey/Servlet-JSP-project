package Model;

public class Item {
private int id;
	
	private String name;
	
	private double price;
	
	private int total_number;
	
	
public Item() {
		
	}


	
	
	

	public Item(String name, double price, int total_number) {
	super();
	this.name = name;
	this.price = price;
	this.total_number = total_number;
}






	public Item(int id, String name, double price, int total_number) {
	super();
	this.id = id;
	this.name = name;
	this.price = price;
	this.total_number = total_number;
}






	public int getTotal_number() {
		return total_number;
	}

	public void setTotal_number(int total_number) {
		this.total_number = total_number;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	
	
	
//	public ItemDetails getItemDetails() {
//		return itemDetails;
//	}
//	public void setItemDetails(ItemDetails itemDetails) {
//		this.itemDetails = itemDetails;
//	}
	
}
