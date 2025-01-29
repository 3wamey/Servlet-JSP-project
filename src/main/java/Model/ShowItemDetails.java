package Model;

public class ShowItemDetails extends ItemDetails {
   private int id;
	
	private String name;
	
	private double price;
	
	private int TOTALNUMBER;
	
	
	 

	
	
	public  ShowItemDetails() {
		
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

	public int getTotalNumber() {
		return TOTALNUMBER;
	}

	public void setTotalNumber(int TOTALNUMBER) {
		this.TOTALNUMBER = TOTALNUMBER;
	}


	
	

}
