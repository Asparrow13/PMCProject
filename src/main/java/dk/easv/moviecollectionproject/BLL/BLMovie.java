package dk.easv.moviecollectionproject.BLL;
import dk.easv.moviecollectionproject.BE.Movie;
import dk.easv.moviecollectionproject.DAL.DBMovie;

import java.util.List;

public class BLMovie {
    DBMovie dbMovie = new DBMovie();

    public List<Movie> getAllMovie(){
       return dbMovie.getAllMovies();
    }

    public Movie getMovieById(int id){
        return dbMovie.getMovieById(id);
    }

    public String getMovieTitle(int id){
        return dbMovie.getMovieById(id).getName();
    }
    public float getMovieRating(int id){
        return dbMovie.getMovieById(id).getRating();
    }
    public int getMovieCategoryId(int id){
        return dbMovie.getMovieById(id).getCategory();
    }


    public void addMovie(Movie movie){
        dbMovie.addMovie(movie);
    }

}
