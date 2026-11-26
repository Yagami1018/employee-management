package cu.uho.workers.controller;

import java.io.IOException;
import java.util.function.Predicate;

import cu.uho.workers.i18n.I18n;
import cu.uho.workers.model.Worker;
import cu.uho.workers.model.enums.Category;
import cu.uho.workers.service.WorkerService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

	/**
	 * CategoryFilter record used at the categoryFilterCB as a wrapper for enumerated
	 * categories, if the wrapped category is null then the value of
	 * categoryFilter is all, using toString for this behavior.
	 *
	 * @param category
	 *                     the category to be wrapped, if its null then the bundle
	 *                     will provide a name.
	 */
	private record CategoryFilter(Category category) {

		@Override
		public String toString() {
			return category == null ? I18n.get("combobox.category.all") : I18n.label(category);
		}

	}

	private final WorkerService service;
	private ObservableList<Worker> items = FXCollections.observableArrayList();
	private FilteredList<Worker> filtered;

	@FXML private TextField searchTF;
	@FXML private TableView<Worker> workerTV;
	@FXML private ComboBox<CategoryFilter> categoryFilterCB;

	private MainController(WorkerService service) {
		this.service = service;
	}

	@FXML
	private void initialize() {
		setupFilter();
	}

	/**
	 * Method to initialize filters used at the Workers TableView,
	 * first add a null category to the Combo Box represent that workers from all
	 * categories matches, and next put all categories in the Combo Box,
	 * wraps the observable ArrayList into the FilteredList, then wraps again into a
	 * SortedList, which uses the Comparator provided with each column at
	 * the table View.
	 */
	private void setupFilter() {
		categoryFilterCB.getItems().add(new CategoryFilter(null));

		for (Category category : Category.values()) {
			categoryFilterCB.getItems().add(new CategoryFilter(category));
		}
		categoryFilterCB.getSelectionModel().selectFirst();
		filtered = new FilteredList<>(items, w -> true);

		SortedList<Worker> sorted = new SortedList<>(filtered);
		sorted.comparatorProperty().bind(workerTV.comparatorProperty());

		categoryFilterCB.valueProperty().addListener((_, _, _) -> applyFilter());
		searchTF.textProperty().addListener((_, _, _) -> applyFilter());
	}

	/**
	 * Method that applies filter to workers against category selection from the
	 * category filter Combo Box and text entered in the search text field.
	 */
	private void applyFilter() {
		CategoryFilter selected = categoryFilterCB.getSelectionModel().getSelectedItem();
		Category category = selected == null ? null : selected.category();
		String text = searchTF.getText() == null ? "" : searchTF.getText().strip().toLowerCase();

		Predicate<Worker> matchesCategory = worker -> worker.getCategory() == category;
		Predicate<Worker> matchesName = worker -> worker.getName().toLowerCase().contains(text);
		Predicate<Worker> matchesId = worker -> worker.getId().contains(text);

		filtered.setPredicate(worker -> (category == null || matchesCategory.test(worker))
				&& (text.isEmpty() || matchesName.or(matchesId).test(worker)));
	}

	/**
	 * This method creates the scene graph from the view and and returns it to be
	 * used for the Stage (Window),
	 *
	 * @param service
	 *                    the worker service that provides the workers list and
	 *                    methods to query data from it, ensuring that business
	 *                    logic stays untangled from the controller.
	 * @return the root node (Parent) containing a hierarchy of children nodes,
	 *         similar to a DOM (Document Object Model) of a HTML.
	 * @throws IOException
	 */
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
