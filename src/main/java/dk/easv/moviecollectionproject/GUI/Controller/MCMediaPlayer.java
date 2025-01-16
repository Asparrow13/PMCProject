package dk.easv.moviecollectionproject.GUI.Controller;

import dk.easv.moviecollectionproject.GUI.Model.MLMoviePlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MCMediaPlayer {

    @FXML
    private MediaView mediaView;
    @FXML
    private ToggleButton playPauseButton;
    @FXML
    private Button skipForwardButton;
    @FXML
    private Button skipBackwardButton;
    @FXML
    private Slider volumeSlider;

    private MLMoviePlayer mlMoviePlayer;
    private boolean isPlaying = false;

    // Initialize the media player
    @FXML
    public void initialize() {
        // Initially, no media file is loaded
        mlMoviePlayer = null;
        mediaView.setPreserveRatio(false); // Allow stretching to fill the MediaView completely

        // Set up button actions
        playPauseButton.setOnAction(e -> togglePlayPause());
        skipForwardButton.setOnAction(e -> skipForward());
        skipBackwardButton.setOnAction(e -> skipBackward());
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> setVolume(newVal.doubleValue()));
        volumeSlider.setValue(100); // by default the value is on maximum
        // Load the media during initialization
        loadMedia();
    }

    // Toggle Play/Pause state
    @FXML
    private void togglePlayPause() {
        if (mlMoviePlayer == null || mlMoviePlayer.getMediaPlayer() == null) {
            showAlert("No Media Selected", "Please select a media file to play.");
            return;
        }

        MediaPlayer mediaPlayer = mlMoviePlayer.getMediaPlayer();

        if (isPlaying) {
            mediaPlayer.pause();
            playPauseButton.setText("▶");  // Change text to play
        } else {
            mediaPlayer.play();
            playPauseButton.setText("⏸");  // Change text to pause
        }
        isPlaying = !isPlaying;
    }

    // Skip forward in the video
    @FXML
    private void skipForward() {
        if (mlMoviePlayer != null) {
            MediaPlayer mediaPlayer = mlMoviePlayer.getMediaPlayer();
            mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(10)));  // Skip forward 10 seconds
        }
    }

    // Skip backward in the video
    @FXML
    private void skipBackward() {
        if (mlMoviePlayer != null) {
            MediaPlayer mediaPlayer = mlMoviePlayer.getMediaPlayer();
            mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(javafx.util.Duration.seconds(10)));  // Skip backward 10 seconds
        }
    }

    // Set the volume from the slider
    @FXML
    private void setVolume(double volume) {
        if (mlMoviePlayer != null) {
            mlMoviePlayer.setVolume(volume / 100.0); // Volume slider in percentage
        }
    }

    // Display an alert message
    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to load media from a file path
    @FXML
    public void loadMedia() {
        // Change this path to your local file or hosted .mp4 file
        String mediaPath =  "movies/new_movie.mp4";

        if (mediaPath != null) {
            System.out.println("Media path: " + mediaPath);  // Debugging line
            mlMoviePlayer = new MLMoviePlayer(mediaPath);    // Initialize MLMoviePlayer with the media path
            mediaView.setMediaPlayer(mlMoviePlayer.getMediaPlayer());  // Attach the MediaPlayer to MediaView

            // Set default volume
            mlMoviePlayer.getMediaPlayer().setVolume(volumeSlider.getValue() / 100.0);

            // Automatically play the video when loaded (optional)
            playPauseButton.setText("⏸");
            togglePlayPause();
            isPlaying = true;
        } else {
            System.out.println("Media path is null!");  // Debugging line
            showAlert("Error", "Media file not found in resources.");
        }
    }
}
