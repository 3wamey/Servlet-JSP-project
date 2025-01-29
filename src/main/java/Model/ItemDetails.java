package Model;

public class ItemDetails {
	
	private int item_details_id;
	private String model;
	private String type_model;
	private String description;
	private int itemId;
	
	
	public ItemDetails() {
		
	}
	
	
public ItemDetails(int item_details_id, String model, String type_model, String description, int itemId) {
		
		this.item_details_id = item_details_id;
		this.model = model;
		this.type_model = type_model;
		this.description = description;
		this.itemId = itemId;
	}
	
	
	public int getItemId() {
		return itemId;
	}



	public void setItemId(int itemId) {
		this.itemId = itemId;
	}



	



	public int getItem_details_id() {
		return item_details_id;
	}


	public void setItem_details_id(int item_details_id) {
		this.item_details_id = item_details_id;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getType_model() {
		return type_model;
	}


	public void setType_model(String type_model) {
		this.type_model = type_model;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	

}
