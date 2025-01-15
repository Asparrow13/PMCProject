package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.GUI.Model.MLCategory;
import dk.easv.moviecollectionproject.GUI.Model.MLMovie;
import dk.easv.moviecollectionproject.GUI.Model.MLMovieInCategory;
import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BE.Category;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    // Model instances
    private final MLMovie movieModel = new MLMovie();
    private final MLCategory categoryModel = new MLCategory();
    private final MLMovieInCategory movieInCategoryModel = new MLMovieInCategory();

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
    }
}
