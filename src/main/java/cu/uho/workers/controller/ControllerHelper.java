package cu.uho.workers.controller;

import java.io.IOException;
import java.util.Locale;

import cu.uho.workers.i18n.I18n;
import cu.uho.workers.service.WorkerService;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public final class ControllerHelper {
    private ControllerHelper() {
    }

    public static boolean switchLanguage(Locale locale) {
        if (I18n.getLocale() != locale) {
            I18n.setLocale(locale);
            return true;
        }
        return false;
    }

    public static void restartWindow(WorkerService service, TableView<?> tv) {
        try {
            var scene = new Scene(MainController.createView(service));
            var stage = ((Stage) tv.getScene().getWindow());
            stage.setTitle(I18n.get("app.title"));
            stage.setScene(scene);
        } catch (IOException e) {
            Dialogs.show(AlertType.ERROR, e.getMessage());
        }
    }

    public static class Dialogs {
        private static Alert a;
        private static ButtonType yes, no;

        private Dialogs() {
        }

        public static void show(AlertType type, String msg) {
            createAlert(type, msg).showAndWait();
        }

        public static boolean confirm(String prompt) {
            yes = new ButtonType(I18n.get("button.yes"), ButtonData.YES);
            no = new ButtonType(I18n.get("button.no"), ButtonData.NO);
            return createAlert(AlertType.CONFIRMATION, prompt, yes, no).showAndWait().filter(b -> b == yes).isPresent();
        }

        public static Alert createAlert(AlertType type, String msg) {
            a = new Alert(type, msg);
            a.setTitle(null);
            a.setHeaderText(null);
            return a;
        }

        public static Alert createAlert(AlertType type, String msg, ButtonType btn1, ButtonType btn2) {
            createAlert(type, msg);
            a.getButtonTypes().setAll(btn1, btn2);
            return a;
        }
    }
}
