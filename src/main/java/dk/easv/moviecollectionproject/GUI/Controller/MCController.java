package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BLL.BLMovie;
import dk.easv.moviecollectionproject.GUI.Model.MLMovie;
import dk.easv.moviecollectionproject.BE.Category;
import dk.easv.moviecollectionproject.BLL.BLCategory;
import dk.easv.moviecollectionproject.GUI.Model.MLCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Label;

public class MCController {

    @FXML
    private TableView<MLMovie> movieTableView;

    @FXML
    private TableColumn<MLMovie, String> nameColumn;

    @FXML
    private TableColumn<MLMovie, Float> ratingColumn;

    @FXML
    private TableColumn<MLMovie, Integer> categoryColumn;

    @FXML
    private TableColumn<MLMovie, String> lastviewColumn;


    @FXML
    private TableView<MLCategory> categoryTableView;

    @FXML
    private TableColumn<MLCategory, String> categoryNameColumn;

    private final BLMovie blMovie = new BLMovie();
    private final ObservableList<MLMovie> movies = FXCollections.observableArrayList();

    private final BLCategory blCategory = new BLCategory();
    private final ObservableList<MLCategory> categories = FXCollections.observableArrayList();


    // Initializer
    @FXML
    public void initialize() {
        // Set up columns to use the respective properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        lastviewColumn.setCellValueFactory(new PropertyValueFactory<>("lastView"));

        // Load movies into the TableView
        loadMovies();


        // Set up the table column to use the name property
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Load categories from the BLL and populate the TableView
        loadCategories();
    }

    // Movie Management
    private void loadMovies() {
        movies.clear();
        for (Movie movie : blMovie.getAllMovie()) {
            movies.add(new MLMovie(movie));
        }
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
}
