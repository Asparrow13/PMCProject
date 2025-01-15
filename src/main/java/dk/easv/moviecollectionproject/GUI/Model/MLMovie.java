package dk.easv.moviecollectionproject.GUI.Model;

import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BLL.BLMovie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class MLMovie {
    private final ObservableList<Movie> movies = FXCollections.observableArrayList();
    private final BLMovie blMovie = new BLMovie();

    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public void loadMovies() {
        movies.clear();
        movies.addAll(blMovie.getAllMovie());
    }

    public void configureColumns(TableColumn<Movie, String> nameColumn,
                                 TableColumn<Movie, Float> ratingColumn,
                                 TableColumn<Movie, String> categoryColumn,
                                 TableColumn<Movie, String> lastViewColumn) {
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        ratingColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleFloatProperty(cellData.getValue().getRating()).asObject());
        categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getCategory())));
        lastViewColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getLastView())));
    }
}
