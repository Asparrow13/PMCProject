package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.BE.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieController {

    @FXML
    private TableView<Movie> movieTableView;

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
    public void onDeleteMovieClicked() {
        Movie selectedSong = movieTableView.getSelectionModel().getSelectedItem();
//        if (selectedSong != null) {
//            songManager.removeSong(selectedSong);
//            refreshTableView();
    }
}

