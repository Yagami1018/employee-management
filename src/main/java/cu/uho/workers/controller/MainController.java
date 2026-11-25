package cu.uho.workers.controller;

import java.io.IOException;

import cu.uho.workers.service.WorkerService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainController {
    private final WorkerService service;

    private MainController(WorkerService service) {
        this.service = service;
    }

    public static Parent createView(WorkerService service) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/cu/uho/workers/view/main-view.fxml"));
        loader.setControllerFactory(_ -> new MainController(service));
        return loader.load();
    }
}
