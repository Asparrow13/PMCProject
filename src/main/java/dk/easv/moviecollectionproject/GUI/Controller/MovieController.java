package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.GUI.Model.MLMovie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class MovieController {

    @FXML
    private TableView<Movie> movieTableView;

    @FXML
    private Button editMovieBtn;
    @FXML
    private TextField movieNameField;
    @FXML
    private TextField movieCategoryField;
    @FXML
    private TextField movieRatingField;
    @FXML
    private TextField movieFilePathField;





    @FXML
    public void onPlayMovieClicked(){
        try {

            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollectionproject/GUI/View/moviePlayer.fxml"));
            Scene scene = new Scene(loader.load());

            // Create a new stage (window)
            Stage newStage = new Stage();
            newStage.setTitle("Playing Movie");
            newStage.setScene(scene);
            newStage.setResizable(false); // Disable resizing
            newStage.show();  // Show the new window

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onAddMovieClicked() {
        try {

            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollectionproject/GUI/View/addMovieWindow.fxml"));
            Scene scene = new Scene(loader.load());

            // Create a new stage (window)
            Stage newStage = new Stage();
            newStage.setTitle("Add Movie");
            newStage.setScene(scene);
            newStage.setResizable(false); // Disable resizing
            newStage.show();  // Show the new window

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEditMovieClicked() {
        try {
            // Load the FXML file for the popup window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollectionproject/GUI/View/editMovieWindow.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the TableView
//            MovieController songController = loader.getController();
//            songController.setMovieTableView(movieTableView);

            // Create and configure the new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false); // Disable resizing
            stage.setTitle("Edit Movie");

            // Set an on-close event handler
//            stage.setOnCloseRequest(event -> {
//                refreshTableView(); // Call a method in the SongController when the window closes
//            });

            // Show the window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void moviaCatalog(){

    }
    @FXML
    public void onAddButtonClicked() {
        Movie movie = new Movie();
        MLMovie mlMovie = new MLMovie();
    if(movieNameField.getText() != null && movieCategoryField.getText() != null && movieRatingField.getText() != null && movieFilePathField.getText() != null){
        movie.setName(movieNameField.getText());
        movie.setCategory(Integer.parseInt(movieCategoryField.getText()));
        movie.setRating(Float.parseFloat(movieRatingField.getText()));
        movie.setFilePath(movieFilePathField.getText());
        movie.setLastView(Date.valueOf("2021-01-17"));
        mlMovie.addMovie(movie);
    }
    }
}

