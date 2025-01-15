package dk.easv.moviecollectionproject.GUI.View;

import dk.easv.moviecollectionproject.BE.Category;
import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BLL.BLMovie;
import dk.easv.moviecollectionproject.DAL.DBMovie;
import dk.easv.moviecollectionproject.BLL.BLCategory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class MCApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MCApp.class.getResource("MCApp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 500);
        stage.setTitle("Movie Collection Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        runApplicationTest();

        launch();
    }

    public static void runApplicationTest(){
        //Date date = new Date(2020,4,2);
        //Movie movie = new Movie(1,"Inception", 2, 4.2f , "path/to/file", date);
        Category category = new Category();
        category.setName("Anime");
        BLCategory blCategory = new BLCategory();
        BLMovie blMovie = new BLMovie();
        blCategory.updateCategory(2, category);
        System.out.println(blCategory.getAllCategories());


    }
}