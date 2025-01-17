package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.BLL.BLCategory;
import dk.easv.moviecollectionproject.BLL.BLMovie;
import dk.easv.moviecollectionproject.GUI.Model.MLCategory;
import dk.easv.moviecollectionproject.GUI.Model.MLMovie;
import dk.easv.moviecollectionproject.GUI.Model.MLMovieInCategory;
import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BE.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private final BLCategory blCategory = new BLCategory();
    private final BLMovie blMovie = new BLMovie();
    private final MLMovie movies = new MLMovie();

    private CategoryController categoryController;

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

        checkLastViewAndRating();

    }



    // Search
    @FXML
    private void onSearchFieldUpdated() {
        String query = searchField.getText();
        if (query.isEmpty()) {
            movieTableView.setItems(FXCollections.observableArrayList(movies.getMovies()));
            System.out.println("Retrieve all movies to Movies ListView");
        } else {
            movieTableView.setItems(FXCollections.observableArrayList(movies.filterMovies(query)));
            System.out.println("Searching for " + query);
        }
    }

    // Movie Management
    public void onPlayMovieClicked() {
        // Example logic for playing a movie
        System.out.println("Playing selected movie...");
    }

    public void onAddMovieClicked() {
        System.out.println("Adding a new movie...");
    }

    public void onEditMovieClicked() {
        System.out.println("Editing selected movie...");
    }

    public void onAddCategoryClicked() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollectionproject/GUI/View/addCategoryWindow.fxml"));
            Parent root = loader.load();

            // Get the controller for the pop-up window
            CategoryController categoryController = loader.getController();

            // Set the MCController as a reference in the CategoryController
            categoryController.setController(this);

            Stage stage = new Stage();
            stage.setTitle("Add Category");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onEditCategoryClicked(ActionEvent actionEvent) {
    }


    public void onDeleteCategoryClicked() {
        Category selectedCategory = categoryTableView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            blCategory.removeCategory(selectedCategory.getId());
            refreshTableView(); // Refresh the table view in the main controller
        } else {
            System.out.println("No Category Selected");
        }
    }



    // Category Management
    public void refreshTableView() {
        movieTableView.getItems().clear();
        categoryTableView.getItems().clear();

        ObservableList<Movie> moviesObservableList = FXCollections.observableArrayList(movies.getMovies());
        movieTableView.setItems(moviesObservableList);

        ObservableList<Category> categoryObservable = FXCollections.observableArrayList(blCategory.getAllCategories());
        categoryTableView.setItems(categoryObservable);
    }

    public void checkLastViewAndRating() {
        AtomicBoolean showMsg = new AtomicBoolean(false);
        movieTableView.getItems().forEach(movie -> {
            String lastViewDateStr = movie.getLastView().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate lastViewDate = LocalDate.parse(lastViewDateStr, formatter);
            LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
            if (lastViewDate.isBefore(twoYearsAgo) && movie.getRating() < 6) {
                showMsg.set(true);
            }
        });
        if (showMsg.get()) {
            showAlert("Reminder", "It’s time to clean up your movie collection! Please review and delete any movies with a personal rating below 6 that have not been watched in over 2 years.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setWidth(400);
        alert.setHeight(200);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void onDeleteMovieClicked() {
        blMovie.removeMovie(movieTableView.getSelectionModel().getSelectedItem().getId());
        refreshTableView();

    }
}
