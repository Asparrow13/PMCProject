package dk.easv.moviecollectionproject.GUI.Model;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;


public class MLMoviePlayer {

    private MediaPlayer mediaPlayer;

    public MLMoviePlayer(String mediaFilePath) {
        try {
            // Try to locate the resource
            URL resourceURL = getClass().getClassLoader().getResource(mediaFilePath);
            if (resourceURL == null) {
                throw new IllegalArgumentException("Resource not found: " + mediaFilePath);
            }
            // Convert the resource URL to a URI and create the media object
            Media media = new Media(resourceURL.toURI().toString());
            mediaPlayer = new MediaPlayer(media);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error loading media file: " + mediaFilePath, e);
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void play() {
        mediaPlayer.play();
    }



    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public void seek(double seconds) {
        mediaPlayer.seek(javafx.util.Duration.seconds(seconds));
    }
}
