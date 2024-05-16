package org.example.project2_messanger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.project2_messanger.controls.*;
import org.example.project2_messanger.data.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private final ArrayList<Chat> chats = new ArrayList<>();
    private VBox chatList = new VBox();
    private GridPane messageList = new GridPane();
    private ChatControl currentChatControl;
    private BorderPane root;



    ChatClickHandler chatClickHandler = sender -> {
        messageList.getChildren().clear();
        // click handler for chat
        sender.getChat().GetMessages().forEach((message) -> {
                    // update message list
                    if (message instanceof TextMessage) {
                        messageList.getChildren().add(new TextMessageControl((TextMessage) message));
                    } else if (message instanceof ImageMessage) {
                        messageList.getChildren().add(new ImageMessageControl((ImageMessage) message));
                    } else if (message instanceof VoiceMessage) {
                        messageList.getChildren().add(new VoiceMessageControl((VoiceMessage) message));
                    }
        }
        );
    };

    private HBox createChatSender(ChatControl chatControl) {
        HBox chatSender = new HBox(10);
        TextField messageField = new TextField();
        messageField.setStyle("-fx-background-color: #15212a; " +
                "-fx-border-color: #4169E1; " +
                "-fx-border-radius: 15px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-size: 14px;");
        messageField.setPromptText("Type your message here...");
        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #4169E1; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-family: 'Arial'; "+
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px; " +
                "-fx-padding: 1px 2px;");

        int[] currentRowIndex = {0};
        // Configure the send button action
        sendButton.setOnAction(event -> {
            String messageText = messageField.getText();
            if (!messageText.isEmpty()) {
                TextMessage message = new TextMessage("Date", "Current User", 0, 0, messageText);
                chatControl.getChat().AddTMessage(message); //AddTMessage adds the message to the chat
                TextMessageControl messageControl = new TextMessageControl(message);
                messageList.add(messageControl, 0, currentRowIndex[0]++);
                messageField.clear();
            }
        });

        chatSender.getChildren().addAll(messageField, sendButton);
        return chatSender;
    }
    private void updateChatList() {
        chatList.getChildren().clear();
        for (Chat chat : chats) {
            ChatControl chatControl = new ChatControl(chat);
            chatControl.setChatClickHandler(chatClickHandler);
            chatList.getChildren().add(chatControl);
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Pane for whole application
        root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        URL url = getClass().getResource("/TELEKG_background.png");
        root.setStyle("-fx-background-image: url('" + url.toString() + "');");


        messageList = new GridPane();
        messageList.setHgap(100);
        messageList.setVgap(40);
        messageList.setPadding(new Insets(10, 10, 10, 10));

        chatList = new VBox(10);
        chatList.setStyle("-fx-padding: 15; -fx-pref-width: 130;");
        chatList.setAlignment(Pos.TOP_CENTER);

        chats.forEach(chat -> {
            ChatControl chatControl = new ChatControl(chat);
            chatControl.setChatClickHandler(chatClickHandler);
            chatList.getChildren().add(chatControl);
        });




        //Vbox for info entering
        VBox infoBox = new VBox();
        Label info = new Label("Add Information");
        info.setFont(Font.font("Times New Roman", 30));
        info.setTextFill(Color.WHITE);
        Label name = new Label("Name");
        name.setFont(Font.font("Times New Roman", 15));
        name.setTextFill(Color.WHITE);
        Label phone = new Label("Phone number");
        phone.setFont(Font.font("Times New Roman", 15));
        phone.setTextFill(Color.WHITE);
        infoBox.setSpacing(5);
        TextField username = new TextField();
        TextField phonefield = new TextField();
        username.setMaxWidth(300);
        username.setStyle("-fx-background-color: #15212a; " + // Background color
                "-fx-border-color: #4169E1; " + // Border color
                "-fx-border-radius: 15px; " + // Border radius
                "-fx-text-fill: #FFFFFF; " + // Text color
                "-fx-font-family: 'Arial'; " + // Font
                "-fx-font-size: 14px;");
        phonefield.setStyle("-fx-background-color: #15212a; " + // Background color
                "-fx-border-color: #4169E1; " + // Border color
                "-fx-border-radius: 10px; " + // Border radius
                "-fx-text-fill: #FFFFFF; " + // Text color
                "-fx-font-family: 'Arial'; " + // Font
                "-fx-font-size: 14px;");
        phonefield.setMaxWidth(300);
        Button save = new Button("Save");
        save.setStyle("-fx-background-color: #4169E1; " + // Background color
                "-fx-text-fill: white; " + // Text color
                "-fx-font-size: 20px; " + //Font size
                "-fx-font-family: 'Arial'; "+
                "-fx-border-radius: 10px; " + // Border radius
                "-fx-background-radius: 10px; " + // Background radius
                "-fx-padding: 1px 2px;");
        infoBox.getChildren().addAll(info, name, username, phone, phonefield, save);
        infoBox.setVisible(false);
        infoBox.setAlignment(Pos.CENTER);


        //ScrollPane for messageList
        ScrollPane scrollPane = new ScrollPane(messageList);


        scrollPane.setStyle("-fx-background: transparent; " + // Makes the ScrollPane's background transparent
                "-fx-background-color: transparent; ");

        Platform.runLater(() -> {
            scrollPane.lookup(".viewport").setStyle("-fx-background-color: transparent;");
        });

        scrollPane.setFitToWidth(true);  // Ensures the scroll pane width fits the content
        scrollPane.setFitToHeight(true);


        //StackPane for INFOBOX and MESSAGE LIST
        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(scrollPane);
        centerPane.getChildren().add(infoBox);
        centerPane.setStyle("-fx-background-image: url('" + url.toString() + "');");



        //Button for adding people
        Button addPeople = new Button("+");
        addPeople.setStyle("-fx-background-color: #4169E1; " + // Background color
                "-fx-text-fill: white; " + // Text color
                "-fx-font-size: 20px; " + //Font size
                "-fx-font-family: 'Arial'; "+
                "-fx-border-radius: 5px; " + // Border radius
                "-fx-background-radius: 5px; " + // Background radius
                "-fx-padding: 1px 2px;");

        addPeople.setOnAction(event -> {
                    infoBox.setVisible(true);
                    save.setOnAction(actionEvent1 -> {
                        if (username.getText().isEmpty() || phonefield.getText().isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setHeaderText("Input Error");
                            alert.setContentText("Please enter both username and phone number!");
                            alert.showAndWait();
                        } else {
                            Chat newChat = new Chat(username.getText(), phonefield.getText());
                            chats.add(newChat);
                            ChatControl newChatControl = new ChatControl(newChat);
                            newChatControl.setChatClickHandler(chatClickHandler);
                            chatList.getChildren().add(newChatControl);
                            infoBox.setVisible(false);
                            username.clear();
                            phonefield.clear();
                        }
                    });
                }
        );
        addPeople.setMaxWidth(100);

        //DESIGN

        //Label for adding people
        Label people = new Label("Add People");
        people.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        people.setTextFill(Color.WHITE);
        VBox peopleLabelVbox = new VBox();
        peopleLabelVbox.setAlignment(Pos.CENTER);
        peopleLabelVbox.getChildren().add(people);

        VBox ButtonVbox = new VBox();
        ButtonVbox.setAlignment(Pos.CENTER);
        ButtonVbox.getChildren().add(addPeople);

        //Vbox for Button
        VBox ADD = new VBox();
        ADD.getChildren().addAll(peopleLabelVbox, ButtonVbox);
        ADD.setStyle("-fx-padding: 50; -fx-pref-width: 190;");




        Region leftRegion = new Region();

        leftRegion.setStyle("-fx-background-color: #15212a;");
        leftRegion.setPrefWidth(250);
        StackPane leftStackPane = new StackPane();


        //DESIGN END

        int[] currentRowIndex = {0};

        chatClickHandler = sender -> {
            messageList.getChildren().clear();
            currentChatControl = sender;
            HBox chatSender = createChatSender(sender);//a chat sender for the selected chat
            chatSender.setVisible(true);
            chatSender.setPadding(new Insets(20));
            chatSender.setAlignment(Pos.CENTER);
            root.setBottom(chatSender); // Set the chat sender to the bottom of the BorderPane
            sender.getChat().GetMessages().forEach((message) -> {
                if (message instanceof TextMessage) {
                    messageList.getChildren().add(new TextMessageControl((TextMessage) message));
                } else if (message instanceof ImageMessage) {
                    messageList.getChildren().add(new ImageMessageControl((ImageMessage) message));
                } else if (message instanceof VoiceMessage) {
                    messageList.getChildren().add(new VoiceMessageControl((VoiceMessage) message));
                }
            });
        };

        // Update chat list function


        Button deletePeople = new Button("-");
        deletePeople.setStyle("-fx-background-color: #4169E1; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px; " +
                "-fx-padding: 1px 2px;");
        deletePeople.setOnAction(event -> {
            if (currentChatControl != null) {
                chats.remove(currentChatControl.getChat());
                chatList.getChildren().remove(currentChatControl);
                messageList.getChildren().clear();
                currentChatControl = null;
            }
        });

        // Button for editing chats
        Button editPeople = new Button("Edit");
        editPeople.setStyle("-fx-background-color: #4169E1; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px; " +
                "-fx-padding: 1px 2px;");
        editPeople.setOnAction(event -> {
            if (currentChatControl != null) {
                Chat chat = currentChatControl.getChat();
                username.setText(chat.getName());
                phonefield.setText(chat.getPhone());
                infoBox.setVisible(true);
                save.setOnAction(saveEvent -> {
                    chat.setName(username.getText());
                    chat.setPhone(phonefield.getText());
                    updateChatList(); // This method will refresh the chat list display
                    infoBox.setVisible(false);
                    username.clear();
                    phonefield.clear();
                });
            }
        });
        //Vbox for Edit and Delete and ADD and ChatList

        VBox chatList_ADD = new VBox();
        chatList_ADD.getChildren().addAll(ADD, editPeople, deletePeople, chatList);


        leftStackPane.getChildren().addAll(leftRegion, chatList_ADD);


        root.setLeft(leftStackPane);
        root.setCenter(centerPane);
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application");
        primaryStage.show();
    }

}

