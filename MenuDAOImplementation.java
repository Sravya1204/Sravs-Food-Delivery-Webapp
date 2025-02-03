package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class MenuDAOImplementation implements MenuDAO {
	
	 private static final String INSERT_MENU_QUERY="INSERT into `menu`(`restaurantId`,`itemName`,`description`,`price`,`ratings`,`isAvailable`,`imagePath`)(?,?,?,?,?,?,?)";
     private static final String GET_MENU_QUERY="SELECT * from `menu` WHERE `menuId`=?";
     private static final String UPDATE_MENU_QUERY="UPDATE `menu` SET `restaurantId`=? `itemName`=? `description`=? `price`=? `ratings`=? `isAvailable`=? `imagepath`=?";
	 private static final String DELETE_MENU_QUERY="DELETE from `menu` WHERE `menuId`=? ";
	 private static final String GET_ALL_MENU="SELECT * from `menu`";
	 private static final String GET_MENU_BY_RESTAURANT_QUERY = "SELECT * from `menu` WHERE `restaurantId`=?";
     @Override
	public void addMenu(Menu menu) {
		
		try(Connection connection=DBConnection.getConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(INSERT_MENU_QUERY);) {
			
			prepareStatement.setInt(1, menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
			prepareStatement.setString(3, menu.getDescription());
			prepareStatement.setDouble(4, menu.getPrice());
			prepareStatement.setDouble(5, menu.getRatings());
			prepareStatement.setBoolean(6, menu.getIsAvailable());
			prepareStatement.setString(7, menu.getImagePath());
			
			prepareStatement.executeUpdate();
							
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Menu getMenu(int menuId) {
		

		Menu menu=null;
		
		try(Connection connection=DBConnection.getConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(GET_MENU_QUERY);) {
			prepareStatement.setInt(1, menuId);
			ResultSet res=prepareStatement.executeQuery();
			
			menu=extractMenu(res);
	
			
			} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return menu;
	}


	@Override
	public void updateMenu(Menu menu) {
		

		try(Connection connection=DBConnection.getConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(UPDATE_MENU_QUERY);) {
			
			prepareStatement.setInt(1, menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
			prepareStatement.setString(3, menu.getItemName());
			prepareStatement.setDouble(4, menu.getPrice());
			prepareStatement.setDouble(5, menu.getRatings());
			prepareStatement.setBoolean(6, menu.getIsAvailable());
			prepareStatement.setString(7, menu.getImagePath());
			prepareStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMenu(int menuId) {


		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_MENU_QUERY);)
		{
			prepareStatement.setInt(1, menuId);
			prepareStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public List<Menu> getAllMenus() {
		
      ArrayList<Menu> menuList=new ArrayList<Menu>();
		
		try{
			
			Connection connection=DBConnection.getConnection();
			Statement statement=connection.createStatement();
		
			ResultSet res=statement.executeQuery(GET_ALL_MENU);
			while(res.next()) {
				
				Menu menu=extractMenu(res);
				menuList.add(menu);
				
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}

	Menu extractMenu(ResultSet res) throws SQLException{
		
		int menuId=res.getInt("menuId");
		int restaurantId=res.getInt("restaurantId");
		String itemName=res.getString("itemName");
		String description=res.getString("description");
		double price=res.getDouble("price");
		double ratings=res.getDouble("ratings");
		boolean isAvailable=res.getBoolean("isAvailable");
		String imagePath=res.getString("imagePath");
		
		Menu menu=new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);
		
		return menu;
	}

		 @Override
		    public List<Menu> getMenuByRestaurant(int restaurantId) {
		        ArrayList<Menu> menuList = new ArrayList<>();
		        try (Connection connection = DBConnection.getConnection();
		                PreparedStatement prepareStatement = connection.prepareStatement(GET_MENU_BY_RESTAURANT_QUERY)) {
		               
		               prepareStatement.setInt(1, restaurantId);
		               ResultSet res = prepareStatement.executeQuery();
		               
		               while (res.next()) {
		                   Menu menu = extractMenu(res);
		                   menuList.add(menu);
		               }
		               
		           } catch (Exception e) {
		               e.printStackTrace();
		           }
		           return menuList;
		       }
	}
	
	

