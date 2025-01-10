package dk.easv.moviecollectionproject.GUI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MCController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Ahhhh it hurts");
    }
}
