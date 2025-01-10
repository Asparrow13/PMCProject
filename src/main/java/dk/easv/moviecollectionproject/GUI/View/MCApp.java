package dk.easv.moviecollectionproject.GUI.View;

import dk.easv.moviecollectionproject.GUI.BE.Movie;
import dk.easv.moviecollectionproject.GUI.DAL.DBConnector;
import dk.easv.moviecollectionproject.GUI.DAL.DBMovie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MCApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MCApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Movie Collection Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        runApplicationTest();

        launch();
    }

    public static void runApplicationTest(){
        Movie movie = new Movie("shreed", "Action", 4.0, "path/to/file", "2024-07-14");

        DBMovie dbmovie = new DBMovie();
        dbmovie.getAllMovies();
        System.out.println( dbmovie.getMovieById(1).getCategory());

        dbmovie.addMovie(movie);

    }
}