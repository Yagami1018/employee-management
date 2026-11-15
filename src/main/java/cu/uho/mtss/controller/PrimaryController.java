package cu.uho.mtss.controller;

import java.io.IOException;

import cu.uho.mtss.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
