package org.example.project2_messanger.controls;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.project2_messanger.data.TextMessage;

public class TextMessageControl extends VBox {
    private TextMessage textMessage;
    public TextMessageControl(TextMessage textMessage) {
        super();
        this.textMessage = textMessage;
        setStyle("-fx-padding: 20;" +
                "-fx-background-color: #BCB9DF; " +
                "-fx-background-radius: 20; " +
                "-fx-border-radius: 20; ");
        getChildren().add(new Label(textMessage.getText()));
    }

}
