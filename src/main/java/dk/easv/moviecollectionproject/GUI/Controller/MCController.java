package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.GUI.Model.MLCategory;
import dk.easv.moviecollectionproject.GUI.Model.MLMovie;
import dk.easv.moviecollectionproject.GUI.Model.MLMovieInCategory;
import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BE.Category;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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


    MovieController movieController = new MovieController();
    CategoryController categoryController = new CategoryController();

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

        checkLastViewAndRating();

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

    // Movie Management
    public void onPlayMovieClicked() {
        movieController.onPlayMovieClicked();
    }

    public void onAddMovieClicked(){
        movieController.onAddMovieClicked();
    }

    public void onEditMovieClicked(){
        movieController.onEditMovieClicked();
    }

    public void onDeleteMovieClicked(){
        movieController.onDeleteMovieClicked();
    }

    // Category Management
    public void onAddCategoryClicked(){
        categoryController.onAddCategoryClicked();
    }

    public void onEditCategoryClicked(){
        categoryController.onEditCategoryClicked();
    }

    public void onDeleteCategoryClicked(){
        categoryController.onDeleteCategoryClicked();
    }



    // Display an alert message
    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setWidth(400);
        alert.setHeight(200);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void checkLastViewAndRating() {

        AtomicBoolean showMsg = new AtomicBoolean(false);
        movieTableView.getItems().forEach(movie -> {
            String lastViewDateStr = "";
            lastViewDateStr =  movie.getLastView().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate lastViewDate = LocalDate.parse(lastViewDateStr, formatter);
            LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
            if(lastViewDate.isBefore(twoYearsAgo) && movie.getRating() < 6){
                showMsg.set(true);
            }
        });
        if(showMsg.get()){
            showAlert("Reminder", "It’s time to clean up your movie collection! Please review and delete any movies with a personal rating below 6 that have not been watched in over 2 years. Keeping your collection up to date ensures better organization and space efficiency.");
            showMsg.set(false);
        }

    }
}
