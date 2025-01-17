package dk.easv.moviecollectionproject.GUI.Model;

import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BLL.BLMovie;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.util.List;

public class MLMovieInCategory {
    private final ObservableList<Movie> movieInCategory = FXCollections.observableArrayList();
    private final BLMovie blMovie = new BLMovie();

    public ObservableList<Movie> getMovieInCategory() {
        return movieInCategory;
    }



    public void loadMovieInCategory(int categoryId) {
        movieInCategory.clear();
        for (Movie movie : blMovie.getAllMovie()) {
            if (movie.getCategory() == categoryId) {
                movieInCategory.add(movie);
            }
        }
    }

    public void configureColumns(TableColumn<Movie, String> categoryMovieColumn) {
        categoryMovieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
    }
}
