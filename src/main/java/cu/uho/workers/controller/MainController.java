package cu.uho.workers.controller;

import java.io.IOException;

import cu.uho.workers.i18n.I18n;
import cu.uho.workers.model.Worker;
import cu.uho.workers.service.WorkerService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

public class MainController {
    private final WorkerService service;
    @FXML private TableView<Worker> workerTV;

    private MainController(WorkerService service) {
        this.service = service;
    }

    public static Parent createView(WorkerService service) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/cu/uho/workers/view/main-view.fxml"),
                I18n.getBundle());
        loader.setControllerFactory(_ -> new MainController(service));
        return loader.load();
    }

    @FXML
    private void onEnglish() {
        if (ControllerHelper.switchLanguage(I18n.ENGLISH)) {
            ControllerHelper.restartWindow(service, workerTV);
        }
    }

    @FXML
    private void onSpanish() {
        if (ControllerHelper.switchLanguage(I18n.SPANISH)) {
            ControllerHelper.restartWindow(service, workerTV);
        }
    }

    @FXML
    private void onClose() {
        if (ControllerHelper.Dialogs.confirm(I18n.get("confirm.close"))) {
            Platform.exit();
        }
    }
}
