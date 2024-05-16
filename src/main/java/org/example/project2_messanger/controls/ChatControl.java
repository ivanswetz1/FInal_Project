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
    public HBox chatSender;

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
    public HBox getChatSender() {
        chatSender = new HBox();
        chatSender.setSpacing(10);
        chatSender.setLayoutX(250);
        chatSender.setLayoutY(730);
        TextField sender = new TextField();
        sender.setPrefWidth(500);
        Button send = new Button("Send");
        ComboBox<String> author_select = new ComboBox<>();
        author_select.getItems().addAll("Me", "User");
        ComboBox<String> message_select = new ComboBox<>();
        message_select.getItems().addAll("Text", "Image", "Voice");
        chatSender.getChildren().addAll(author_select, sender, send, message_select);
        return chatSender;
    }
    public Chat getChat() {
        return chat;
    }
    public void setChatClickHandler(ChatClickHandler chatClickHandler) {
        this.chatClickHandler = chatClickHandler;
    }
}