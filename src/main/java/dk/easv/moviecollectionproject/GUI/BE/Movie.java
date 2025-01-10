package dk.easv.moviecollectionproject.GUI.BE;

import java.util.ArrayList;
import java.sql.Date;

public class Movie {
    private int id;
    private String name;
    private String category;
    private float rating;
    private String filePath;
    private Date lastView;

    public Movie(){

    }
    public Movie(String name, String category, float rating, String filePath, Date lastView) {

        this.name = name;
        this.category = category;
        this.rating = rating;
        this.filePath = filePath;
        this.lastView = lastView;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public java.sql.Date getLastView() {
        return lastView;
    }
    public void setLastView(Date lastView) {
        this.lastView = lastView;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                ", filePath='" + filePath + '\'' +
                ", lastView=" + lastView +
                '}';
    }


    public ArrayList<Object> toArray() {
        ArrayList<Object> movies = new ArrayList<>();
        movies.add(id);
        movies.add(name);
        movies.add(category);
        movies.add(rating);
        movies.add(filePath);
        movies.add(lastView);
        return movies;
    }
}
