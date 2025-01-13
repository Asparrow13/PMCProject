package dk.easv.moviecollectionproject.GUI.Model;

import dk.easv.moviecollectionproject.BE.Movie;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class MLMovie {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleFloatProperty rating;
    private final SimpleIntegerProperty category;
    private final SimpleStringProperty filePath;
    private final SimpleObjectProperty<Date> lastView;

    public MLMovie(Movie movie) {
        this.id = new SimpleIntegerProperty(movie.getId());
        this.name = new SimpleStringProperty(movie.getName());
        this.rating = new SimpleFloatProperty(movie.getRating());
        this.category = new SimpleIntegerProperty(movie.getCategory());
        this.filePath = new SimpleStringProperty(movie.getFilePath());
        this.lastView = new SimpleObjectProperty<>(movie.getLastView());
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public float getRating() {
        return rating.get();
    }

    public SimpleFloatProperty ratingProperty() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating.set(rating);
    }

    public int getCategory() {
        return category.get();
    }

    public SimpleIntegerProperty categoryProperty() {
        return category;
    }

    public void setCategory(int category) {
        this.category.set(category);
    }

    public String getFilePath() {
        return filePath.get();
    }

    public SimpleStringProperty filePathProperty() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath.set(filePath);
    }

    public Date getLastView() {
        return lastView.get();
    }

    public SimpleObjectProperty<Date> lastViewProperty() {
        return lastView;
    }

    public void setLastView(Date lastView) {
        this.lastView.set(lastView);
    }
}
