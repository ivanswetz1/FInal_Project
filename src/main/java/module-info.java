module org.example.project2_messanger {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.project2_messanger to javafx.fxml;
    exports org.example.project2_messanger;
}