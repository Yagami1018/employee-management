package cu.uho.mtss.controller;

import java.io.IOException;

import cu.uho.mtss.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}