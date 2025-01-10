package dk.easv.moviecollectionproject.DAL;

import dk.easv.moviecollectionproject.BE.Category;
import dk.easv.moviecollectionproject.BE.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBCategory {
    DBConnector db = new DBConnector();

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";

        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Executing query: " + query);

            while (resultSet.next()) {
                // Create a new Category object for each row
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));

                // Add the object to the list
                categories.add(category);
            }

        } catch (SQLException e) {
            System.out.println("Error occurred while executing query: " + query);
            e.printStackTrace();
            throw new RuntimeException("Error fetching categories", e);
        }

        System.out.println("Connection closed successfully");
        return categories;
    }


    public Category getCategoryById(int id) {
        Category category = new Category();
        String query = "SELECT * FROM Category WHERE id = ?";

         try(Connection connection = db.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)){

             preparedStatement.setInt(1,id);
             try(ResultSet resultSet = preparedStatement.executeQuery()){
                 while (resultSet.next()){
                     category.setId(resultSet.getInt("id"));
                     category.setName(resultSet.getString("name"));

                 }
             }catch (SQLException e){
                 System.out.println("error while executing query ");
                 e.printStackTrace();
             }
         }catch (SQLException e){
             System.out.println("error while connecting to the database");
             e.printStackTrace();
         }
         db.closeConnection();
         System.out.println("Connection closed successfully");
         return category;
    };

    public void removeCategory(int id) {
        String query = "DELETE FROM Category WHERE id = ?";

        try(Connection connection = db.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println("error while executing query ");
            e.printStackTrace();
        }
        db.closeConnection();
        System.out.println("Connection closed successfully");
    }

    public void updateCategory(int id,Category category) {
        String query = "UPDATE Category SET name = ? WHERE id = ?";

        try(Connection connection = db.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated Category" + category.getName() + " with id " + id);
        }catch (SQLException e){
            System.out.println("error while executing query ");
            e.printStackTrace();
        }
        db.closeConnection();
        System.out.println("Connection closed successfully");
    }


}
