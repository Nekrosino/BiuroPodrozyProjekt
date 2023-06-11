module com.example.biuropodrozyprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.biuropodrozyprojekt to javafx.fxml;
    exports com.example.biuropodrozyprojekt;
}