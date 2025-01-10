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
        Category category = new Category();
        List<Category> categories = new ArrayList<>();

        String query = "SELECT * FROM Category";

        try(Connection connection =  db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            try(ResultSet resultset = preparedStatement.executeQuery()){
                System.out.println("executing query " + query);

                while(resultset.next()){
                    int id = resultset.getInt("id");
                    String name = resultset.getString("name");
                    category.setId(id);
                    category.setName(name);
                    categories.add(category);

                }

            } catch (SQLException e) {
                System.out.println("error while executing query " + query);
                e.printStackTrace();
            }

            db.closeConnection();
            System.out.println("Connection closed successfully");

        } catch (SQLException e) {
            System.out.println("Error while connecting to the database" + e.getMessage());
            throw new RuntimeException(e);
        }

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


}
