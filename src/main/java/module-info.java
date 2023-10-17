module edu.cegepvicto.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens edu.cegepvicto.demo to javafx.fxml;
    exports edu.cegepvicto.demo;
}