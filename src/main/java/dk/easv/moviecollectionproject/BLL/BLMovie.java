package dk.easv.moviecollectionproject.BLL;
import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.DAL.DBMovie;

import java.sql.Date;
import java.util.List;

public class BLMovie {
    DBMovie dbMovie = new DBMovie();

    public List<Movie> getAllMovie(){
       return dbMovie.getAllMovies();
    }

    public Movie getMovieById(int id){
        return dbMovie.getMovieById(id);
    }
    public void removeMovie(int id){
        dbMovie.removeMovie(id);
    }
    public void updateMovie(int id, Movie movie){
        dbMovie.updateMovie(id, movie);
    }
    public void addMovie(Movie movie){
        dbMovie.addMovie(movie);
    }


    public String getMovieName(int id){
        return dbMovie.getMovieById(id).getName();
    }

    public float getMovieRating(int id){
        return dbMovie.getMovieById(id).getRating();
    }

    public int getMovieCategoryId(int id){
        return dbMovie.getMovieById(id).getCategory();
    }

    public String getMovieFilePath(int id){
        return dbMovie.getMovieById(id).getFilePath();
    }

    public Date getMovieLastview(int id){
        return dbMovie.getMovieById(id).getLastView();
    }

    public List<Movie> getMovieByCategoryId(int id){
        return dbMovie.getMovieInCategory(id);
    }

}
