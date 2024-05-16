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
                    chatList
                }
        );
    };


    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Pane for whole application
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        URL url = getClass().getResource("/TELEKG_background.png");
        root.setStyle("-fx-background-image: url('" + url.toString() + "');");


        messageList = new GridPane();

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

        //Vbox for chatList and ADD
        VBox chatList_ADD = new VBox();
        chatList_ADD.getChildren().addAll(ADD, chatList);

        // Create a new Region
        Region leftRegion = new Region();
        // Set the background color of the Region
        leftRegion.setStyle("-fx-background-color: #15212a;"); // Replace #4CAF50 with the color you want
        // Set the preferred width of the Region
        leftRegion.setPrefWidth(230); // Adjust this value as needed
        // Add the Region to the left side of the BorderPane
        // Create a new StackPane
        StackPane leftStackPane = new StackPane();
        // Add the Region and the VBox to the StackPane
        leftStackPane.getChildren().addAll(leftRegion, chatList_ADD);

        //DESIGN END


        //Hbox for chatSender
//        chatSender = new HBox();
//        chatSender.setSpacing(10);
//        chatSender.setLayoutX(250);
//        chatSender.setLayoutY(730);
//        TextField sender = new TextField();
//        sender.setPrefWidth(500);
//        Button send = new Button("Send");
//        ComboBox<String> author_select = new ComboBox<>();
//        author_select.getItems().addAll("Me", "User");
//        ComboBox<String> message_select = new ComboBox<>();
//        message_select.getItems().addAll("Text", "Image", "Voice");
//        chatSender.getChildren().addAll(author_select, sender, send, message_select);




        //Adding messages to visible chat

        root.setLeft(leftStackPane);
        root.setCenter(centerPane);
        root.setBottom(chatSender);
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application");
        primaryStage.show();

    }

}

