package ServiceImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Model.Item;
import Service.ItemService;

public class ItemServiceImpl implements ItemService {

private DataSource dataSource;
	
	public ItemServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Item> getAllItem() {
		Connection connection = null;
		Statement statement = null;
		List<Item> items = new ArrayList();
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			String sql = "SELECT * FROM item";
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
					Item item = new Item();
				
					item.setId(resultSet.getInt("id"));
					item.setName(resultSet.getString("name"));
					item.setPrice(resultSet.getDouble("price"));
					item.setTotal_number(resultSet.getInt("TOTALNUMBER"));
					
					items.add(item);
					System.out.println("loop");
			}
			System.out.println(items.size());
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return items;
	}

	@Override
	public void saveItem(Item item) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = "INSERT INTO item ( id,NAME,PRICE,total_number)"
						+ " VALUES (?, ?, ?,?)";
			
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, item.getId());
			statement.setString(2, item.getName());
			statement.setDouble(3, item.getPrice());
			statement.setInt(4, item.getTotal_number());
			
			statement.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

	}

	@Override
	public Item findItemById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = "select * from item where id = ?";
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			Item item = new Item();
			if (resultSet.next()) {
					item.setId(resultSet.getInt("id"));
					item.setName(resultSet.getString("name"));
					item.setPrice(resultSet.getDouble("price"));
					item.setTotal_number(resultSet.getInt("TOTALNUMBER"));
			}
			
			return item;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = "DELETE from Item where id = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = "UPDATE ITEM SET NAME =?, PRICE=?, total_number=? WHERE id = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, item.getName());
			statement.setDouble(2, item.getPrice());
			statement.setInt(3, item.getTotal_number());
			statement.setInt(4, item.getId());
			
			statement.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
		
	}
	
	
	



