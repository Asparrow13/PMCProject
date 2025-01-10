package dk.easv.moviecollectionproject.DAL;

import dk.easv.moviecollectionproject.BE.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBMovie {
    DBConnector db = new DBConnector();
    Movie movie = new Movie();

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movie";

        try(Connection connection =  db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            try(ResultSet resultset = preparedStatement.executeQuery()){
                System.out.println("executing query " + query);

                while(resultset.next()){
                    int id = resultset.getInt("id");
                    String name = resultset.getString("name");
                    int category_id = resultset.getInt("category_id");
                    float rating = resultset.getFloat("rating");
                    String filelink = resultset.getString("filelink");
                    Date lastview = resultset.getDate("lastview");
                    movie.setId(id);
                    movie.setName(name);
                    movie.setRating(rating);
                    movie.setCategory(category_id);
                    movie.setFilePath(filelink);
                    movie.setLastView((java.sql.Date) lastview);
                    movies.add(movie);
                    System.out.println(movie.toArray());
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

        return movies;
    }

    public Movie getMovieById(int ID) {
        String query = "SELECT * FROM Movie where id = ?";

        try(Connection connection =  db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, ID);

            try(ResultSet resultset = preparedStatement.executeQuery()){
                String IDToString = " " + ID;
                String newQuery =  query.replace(" ?", IDToString);
                System.out.println("executing query " + newQuery);

                while(resultset.next()){
                    int id = resultset.getInt("id");
                    String name = resultset.getString("name");
                    int category_id = resultset.getInt("category_id");
                    float rating = resultset.getFloat("rating");
                    String filelink = resultset.getString("filelink");
                    Date lastview = resultset.getDate("lastview");
                    movie.setId(id);
                    movie.setName(name);
                    movie.setRating(rating);
                    movie.setCategory(category_id);
                    movie.setFilePath(filelink);
                    movie.setLastView((java.sql.Date) lastview);

                }
                db.closeConnection();
                System.out.println("Connection closed successfully");
            } catch (SQLException e) {
                System.out.println("error while executing query " + query);
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("Error while connecting to the database" + e.getMessage());
            throw new RuntimeException(e);
        }

        return movie;
    }

    public void addMovie(Movie movie) {
        String query = "INSERT INTO Movie (name,rating,category_id,filelink,lastview) VALUES (?,?,?,?,?)";

        try(Connection connection = db.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, movie.getName());
                preparedStatement.setFloat(2, movie.getRating());
                preparedStatement.setInt(3, movie.getCategory());
                preparedStatement.setString(4, movie.getFilePath());
                preparedStatement.setDate(5,movie.getLastView());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        db.closeConnection();
        System.out.println("Connection closed successfully");
        System.out.println("Movie added successfully" + movie.toArray());

    }



}

