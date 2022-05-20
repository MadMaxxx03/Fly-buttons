module com.example.classwork {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.classwork to javafx.fxml;
    exports com.example.classwork;
}