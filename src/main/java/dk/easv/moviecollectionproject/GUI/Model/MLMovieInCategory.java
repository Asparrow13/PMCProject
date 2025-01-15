package dk.easv.moviecollectionproject.GUI.Model;

import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.BLL.BLCategory;
import dk.easv.moviecollectionproject.BLL.BLMovie;
import javafx.beans.property.SimpleStringProperty;

public class MLMovieInCategory {
    private final SimpleStringProperty movieName;

    public MLMovieInCategory(Movie movie) {
        this.movieName = new SimpleStringProperty(movie.getName());
    }

    public String getMovieName() {
        return movieName.get();
    }

    public SimpleStringProperty movieNameProperty() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName.set(movieName);
    }
}
