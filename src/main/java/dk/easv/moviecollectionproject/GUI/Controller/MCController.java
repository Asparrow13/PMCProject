package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.BE.Category;
import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BLL.BLCategory;
import dk.easv.moviecollectionproject.BLL.BLMovie;
import dk.easv.moviecollectionproject.GUI.Model.MLCategory;
import dk.easv.moviecollectionproject.GUI.Model.MLMovie;
import dk.easv.moviecollectionproject.GUI.Model.MLMovieInCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Label;

import java.util.ArrayList;

public class MCController {

    // MLMovie
    @FXML
    private TableView<Movie> movieTableView;

    @FXML
    private TableColumn<Movie, String> nameColumn;

    @FXML
    private TableColumn<Movie, Float> ratingColumn;

    @FXML
    private TableColumn<Movie, Integer> categoryColumn;

    @FXML
    private TableColumn<Movie, String> lastviewColumn;

    // MLCategory
    @FXML
    private TableView<MLCategory> categoryTableView;

    @FXML
    private TableColumn<MLCategory, String> categoryNameColumn;

    // MLMovieInCategory
    @FXML
    private TableView<MLMovieInCategory> movieInCategoryTableView;

    @FXML
    private TableColumn<MLMovieInCategory, String> categoryMovieColumn;


    private final BLMovie blMovie = new BLMovie();
    private final ObservableList<Movie> movies = FXCollections.observableArrayList();

    private final BLCategory blCategory = new BLCategory();
    private final ObservableList<MLCategory> categories = FXCollections.observableArrayList();

    private final ObservableList<MLMovieInCategory> movieInCategory = FXCollections.observableArrayList();

    // Initializer
    @FXML
    public void initialize() {

        // Load movies into the TableView
        loadMovies();

        // Set up columns to use the respective properties
        nameColumn.setCellValueFactory(cellData ->  cellData.getValue().getName());
        ratingColumn.setCellValueFactory(cellData -> cellData.getValue().getRating()));
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategory());
        lastviewColumn.setCellValueFactory(cellData -> cellData.getValue().getLastView());


        // Set up the table column to use the name property
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Load categories from the BLL and populate the TableView
        loadCategories();


        // Set up the column to use the movieName property
        categoryMovieColumn.setCellValueFactory(new PropertyValueFactory<>("movieName"));

        // Optionally load movies in the initial category (or handle dynamically based on selection)
        loadMovieInCategory(1); // Example: Load movies in category with ID 1
    }

    // Movie Management
    private void loadMovies() {
        movies.clear();
        movies.addAll(blMovie.getAllMovie());
        movieTableView.setItems(movies);
    }

    // Category Management
    private void loadCategories() {
        categories.clear();
        for (Category category : blCategory.getAllCategories()) {
            categories.add(new MLCategory(category));
        }
        categoryTableView.setItems(categories);
    }

    // MovieInCategory Management
    public void loadMovieInCategory(int categoryId) {
        movieInCategory.clear();

        // Fetch all movies and filter by category
        for (Movie movie : blMovie.getAllMovie()) {
            if (movie.getCategory() == categoryId) {
                    movieInCategory.add(new MLMovieInCategory(movie));
            }
        }
        // Set the data to the TableView
        movieInCategoryTableView.setItems(movieInCategory);
    }
}
