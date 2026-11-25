module cu.uho.workers {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens cu.uho.workers.controller to javafx.fxml;

    exports cu.uho.workers;
}
