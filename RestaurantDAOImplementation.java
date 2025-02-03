package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class  RestaurantDAOImplementation implements RestaurantDAO {
	
	private static final String INSERT_RESTAURANT_QUERY="INSERT into `restaurant`('name`,`address`,`phone`,`rating`,`cuisineType`,`isActive`,`estimateArrivalTime`,`imagePath`) values(?,?,?,?,?,?,?,?)";
	private static final String GET_RESTAURANT_QUERY="SELECT * from `restaurant` WHERE `restaurantId`=?";
	private static final String UPDATE_RESTAURANT_QUERY="UPDATE `restaurant` SET `name`=? `address`=? `phone`=? `rating=?` `cuisineType`=? `isActive`=? `estimateArrivalTime`=? `adminUserId`=? `imagePath`=?";
	private static final String DELETE_RESTAURANT_QUERY="DELETE from `restaurant` WHERE restaurantId=?";
	private static final String GET_ALL_RESTAURANTS="SELECT * from restaurant";
	private static final String SEARCH_RESTAURANTS_QUERY = "SELECT * FROM restaurant WHERE LOWER(name) LIKE ? OR LOWER(cuisineType) LIKE ?";
	
	
	
	public List<Restaurant> searchRestaurants(String query) {
	    List<Restaurant> filteredRestaurants = new ArrayList<>();
	    
	    

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_RESTAURANTS_QUERY)) {

	        preparedStatement.setString(1, "%" + query + "%");
	        preparedStatement.setString(2, "%" + query + "%");

	        ResultSet res = preparedStatement.executeQuery();

	        while (res.next()) {
	            Restaurant restaurant = extractRestaurant(res);
	            filteredRestaurants.add(restaurant);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return filteredRestaurants;
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		try(Connection connection=DBConnection.getConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(INSERT_RESTAURANT_QUERY);) {
			
			prepareStatement.setString(1,restaurant.getName());
			prepareStatement.setString(2,restaurant.getAddress());
			prepareStatement.setString(3,restaurant.getPhone());
			prepareStatement.setDouble(4,restaurant.getRating());
			prepareStatement.setString(5,restaurant.getCuisineType());
			prepareStatement.setBoolean(6,restaurant.getIsActive());
			prepareStatement.setTime(7,restaurant.getEstimateArrivalTime());
			prepareStatement.setString(8,restaurant.getImagePath());
			
			int res=prepareStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		
		Restaurant restaurant=null;
		
		try(Connection connection=DBConnection.getConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(GET_RESTAURANT_QUERY);) {
			prepareStatement.setInt(1, restaurantId);
			ResultSet res=prepareStatement.executeQuery();
			
			restaurant=extractRestaurant(res);
	
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {

		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(UPDATE_RESTAURANT_QUERY);) {
					
			   prepareStatement.setString(1,restaurant.getName() );
			   prepareStatement.setString(2,restaurant.getAddress());
			   prepareStatement.setString(3,restaurant.getPhone());
			   prepareStatement.setDouble(4,restaurant.getRating() );
			   prepareStatement.setString(5,restaurant.getCuisineType());
			   prepareStatement.setBoolean(6,restaurant.getIsActive() );
			   prepareStatement.setTime(7,restaurant.getEstimateArrivalTime() );
			   prepareStatement.setInt(8,restaurant.getAdminUserId() );
			   prepareStatement.setString(9,restaurant.getImagePath() );
			
					
			   prepareStatement.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

	@Override
	public void deleteRestaurant(int restaurantId) {
		
		try(Connection connection=DBConnection.getConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(UPDATE_RESTAURANT_QUERY);) {
			
			prepareStatement.setInt(1, restaurantId);
			prepareStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {

		ArrayList<Restaurant> restaurantsList=new ArrayList<Restaurant>();
		
		try{
			
			Connection connection=DBConnection.getConnection();
			Statement statement=connection.createStatement();
		
			ResultSet res=statement.executeQuery(GET_ALL_RESTAURANTS);
			while(res.next()) {
				
				Restaurant restaurant=extractRestaurant(res);
				restaurantsList.add(restaurant);
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return restaurantsList;
	}
		
	Restaurant extractRestaurant(ResultSet res) throws SQLException{
		
		int restaurantId=res.getInt("restaurantId");
		String name=res.getString("name");
		String address=res.getString("address");
		String phone=res.getString("phone");
		double rating=res.getDouble("rating");
		String cuisineType=res.getString("cuisineType");
		boolean isActive=res.getBoolean("isActive");
		Time eta=res.getTime("estimateArrivalTime");
		int adminUserId=res.getInt("adminUserId");
		String imagePath=res.getString("imagePath");
		
		Restaurant restaurant=new Restaurant(restaurantId, name, address, phone, rating, cuisineType, isActive, eta, adminUserId, imagePath);
		
		return restaurant;
	}

}
