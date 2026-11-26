package cu.uho.workers;

import java.io.IOException;

import cu.uho.workers.controller.MainController;
import cu.uho.workers.i18n.I18n;
import cu.uho.workers.service.WorkerService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		var scene = new Scene(MainController.createView(new WorkerService()));
		stage.setScene(scene);
		stage.setTitle(I18n.get("app.title"));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
