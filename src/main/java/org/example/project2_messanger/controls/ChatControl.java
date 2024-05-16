package org.example.project2_messanger.controls;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.project2_messanger.data.Chat;

public class ChatControl extends VBox {
    private Chat chat;
    private ChatClickHandler chatClickHandler;

    public ChatControl(Chat chat) {
        super();
        this.chat = chat;
        setStyle("-fx-padding: 2 5;");
        Label chatLabel = new Label(chat.getName());
        chatLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        chatLabel.setTextFill(Color.WHITE);
        getChildren().add(chatLabel);
        setOnMouseClicked(event -> {
            if (chatClickHandler != null) {
                chatClickHandler.onClick(this);
            }
        });
    }
    public Chat getChat() {
        return chat;
    }
    public void setChatClickHandler(ChatClickHandler chatClickHandler) {
        this.chatClickHandler = chatClickHandler;
    }


}
