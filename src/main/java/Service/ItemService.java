package Service;

import java.util.List;

import Model.Item;

public interface ItemService {
	List<Item> getAllItem();
	void saveItem(Item item);
	Item findItemById(int id);
	void deleteItem(int id);
	void updateItem(Item item);
	
	
	
}
