package org.example.project2_messanger.controls;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.project2_messanger.data.TextMessage;
import org.example.project2_messanger.data.VoiceMessage;

public class VoiceMessageControl extends VBox {
    private VoiceMessage voiceMessage;
    public VoiceMessageControl(VoiceMessage voiceMessage) {
        super();
        this.voiceMessage = voiceMessage;
        setStyle("-fx-padding: 20;" +
                "-fx-background-color: #BCB9DF; " +
                "-fx-background-radius: 20; " +
                "-fx-border-radius: 20; ");
        getChildren().add(new Label(voiceMessage.getUrl()));
    }
}
