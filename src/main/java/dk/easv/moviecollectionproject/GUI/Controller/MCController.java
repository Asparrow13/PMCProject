package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.GUI.Model.MLCategory;
import dk.easv.moviecollectionproject.GUI.Model.MLMovie;
import dk.easv.moviecollectionproject.GUI.Model.MLMovieInCategory;
import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BE.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MCController {

    // Movie TableView
    @FXML
    private TableView<Movie> movieTableView;

    @FXML
    private TableColumn<Movie, String> nameColumn;

    @FXML
    private TableColumn<Movie, Float> ratingColumn;

    @FXML
    private TableColumn<Movie, String> categoryColumn;

    @FXML
    private TableColumn<Movie, String> lastviewColumn;

    // Category TableView
    @FXML
    private TableView<Category> categoryTableView;

    @FXML
    private TableColumn<Category, String> categoryNameColumn;

    // Movies in Category TableView
    @FXML
    private TableView<Movie> movieInCategoryTableView;

    @FXML
    private TableColumn<Movie, String> categoryMovieColumn;

    // Search
    @FXML
    private TextField searchField;

    // Model instances
    private final MLMovie movieModel = new MLMovie();
    private final MLCategory categoryModel = new MLCategory();
    private final MLMovieInCategory movieInCategoryModel = new MLMovieInCategory();

    MLMovie movies = new MLMovie();

    @FXML
    public void initialize() {
        // Configure TableView columns
        movieModel.configureColumns(nameColumn, ratingColumn, categoryColumn, lastviewColumn);
        categoryModel.configureColumns(categoryNameColumn);
        movieInCategoryModel.configureColumns(categoryMovieColumn);

        // Load data into models
        movieModel.loadMovies();
        categoryModel.loadCategories();
        movieInCategoryModel.loadMovieInCategory(3);

        // Bind models to TableViews
        movieTableView.setItems(movieModel.getMovies());
        categoryTableView.setItems(categoryModel.getCategories());
        movieInCategoryTableView.setItems(movieInCategoryModel.getMovieInCategory());

        searchField.setOnKeyReleased(event -> onSearchFieldUpdated());
    }

    // Search
    @FXML
    private void onSearchFieldUpdated() {
        String query = searchField.getText();
        if (query.isEmpty()) {
            // Show all movies if the search field is empty
            movieTableView.setItems(FXCollections.observableArrayList(movies.getMovies()));
            System.out.println("Retrieve all movies to Movies ListView");
        } else {
            // Filter movies based on the query
            movieTableView.setItems(FXCollections.observableArrayList(movies.filterMovies(query)));
            System.out.println("Searching for " + query);
        }
    }

}
