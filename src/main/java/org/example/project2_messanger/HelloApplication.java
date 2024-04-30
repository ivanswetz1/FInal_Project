package org.example.project2_messanger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class HelloApplication extends Application {
    private Pane root;
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Pane for whole application
        root = new Pane();
        root.setPrefSize(1000, 800);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        //Button for adding people
        Label people = new Label("Add People");
        people.setFont(Font.font("Times New Roman", 15));
        Button addPeople = new Button("+");
        addPeople.setMaxWidth(100);


        //Vbox for Button
        VBox ADD = new VBox();
        ADD.getChildren().addAll(people, addPeople);

        //Adding dividing line
        Polyline line = new Polyline(200, 0, 200, 1000);
        line.setStroke(Color.LIGHTGOLDENRODYELLOW);
        line.setStrokeWidth(5);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.setStrokeLineJoin(StrokeLineJoin.MITER);
        line.setStrokeDashOffset(10);

        //Adding rectangles
        Rectangle rectangle1 = new Rectangle(0,0,200,1000);
        rectangle1.setFill(Color.LIGHTSKYBLUE);

        Rectangle rectangle2 = new Rectangle(200,0,800,1000);
        rectangle2.setFill(Color.LIGHTBLUE);

        //Adding Vbox for people
        VBox peopleVbox = new VBox();
        peopleVbox.setAlignment(Pos.TOP_LEFT);
        peopleVbox.setLayoutX(60);
        peopleVbox.setLayoutY(50);
        peopleVbox.setSpacing(40);


        //GridPane for messanger
        GridPane gridPane = new GridPane();
        gridPane.setHgap(-150);
        // Setting horizontal gap between cells
        gridPane.setVgap(20);
        // Setting vertical gap between cells
        gridPane.setLayoutX(0);
        // Setting the layout X coordinate
        gridPane.setLayoutY(0);
        // Setting the layout Y coordinate

        for (int i = 0; i < 2; i++) {
            ColumnConstraints column = new ColumnConstraints(400);
            gridPane.getColumnConstraints().add(column);
        }


        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        // Set the ScrollPane layout properties
        scrollPane.setLayoutX(250);
        scrollPane.setLayoutY(50);
        scrollPane.setPrefSize(700, 680);

        // Set the GridPane as the content of the ScrollPane
        scrollPane.setContent(gridPane);



        //Setting Hbox for chat sender
        HBox chatSender = new HBox();
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



        //Setting AddUser Button
        ArrayList<User> users = new ArrayList<>();
        addPeople.setOnAction(actionEvent -> {
            root.getChildren().remove(chatSender);
            VBox infoBox = new VBox();
            Label info = new Label("Add Information");
            info.setFont(Font.font("Times New Roman", 30));
            Label name = new Label("Name");
            name.setFont(Font.font("Times New Roman", 15));
            Label phone = new Label("Phone number");
            phone.setFont(Font.font("Times New Roman", 15));
            infoBox.setSpacing(5);
            TextField username = new TextField();
            TextField phonefield = new TextField("+380");
            Button save = new Button("Save");
            infoBox.setLayoutX(500);
            infoBox.setLayoutY(300);
            infoBox.getChildren().addAll(info,name, username, phone, phonefield, save);
            root.getChildren().add(infoBox);
            save.setOnAction(actionEvent1 -> {
                User user = new Chat(username.getText(), phonefield.getText());
                users.add(user);
                infoBox.setVisible(false);
                Label userLabel = new Label(user.getName());
                userLabel.setFont(Font.font("Times New Roman", 20));
                peopleVbox.getChildren().add(userLabel);
                for (Node node : peopleVbox.getChildren()) {
                    node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            final int[] currentRow = {0};
                            if(root.getChildren().contains(chatSender)){
                                root.getChildren().remove(chatSender);
                            }
                            boolean checker = false;
                            for (User user : users) {
                                if (user.getName().equals(((Label) node).getText())) {
                                    root.getChildren().add(chatSender);
                                    checker = true;
                                    break;
                                }
                            }
                            if(!checker){
                                root.getChildren().remove(chatSender);
                            }

                            //Setting up Chat

                            send.setOnAction(actionEvent -> {
                                //Setting actions for chat sender
                                HBox Message = new HBox();
                                HBox Message1 = new HBox();
                                VBox DateAuthor = new VBox();
                                Label date = new Label("20.04.2024");
                                date.setFont(Font.font("Times New Roman", 8));
                                date.setTextFill(Color.GRAY);
                                Label author = new Label();
                                author.setFont(Font.font("Times New Roman", 8));
                                author.setTextFill(Color.GRAY);
                                Label text = new Label();
                                text.setFont(Font.font("Times New Roman", 20));
                                DateAuthor.getChildren().addAll(date, author);
                                Message1.getChildren().addAll(DateAuthor, text);


                                switch (author_select.getValue()){
                                    case "Me":
                                        switch (message_select.getValue()){
                                            case "Text":
                                                TextMessage message = new TextMessage("20.04.2024", message_select.getValue(), sender.getText());
                                                text.setText(sender.getText());
                                                author.setText("Me");
                                                gridPane.add(Message1, 2, currentRow[0]);
                                                break;
                                            case "Image":
                                                ImageMessage message1 = new ImageMessage("20.04.2024", message_select.getValue(), sender.getText());
                                                text.setText(sender.getText());
                                                author.setText("Me");
                                                gridPane.add(Message, 2, currentRow[0]);
                                                break;
                                            case "Voice":
                                                VoiceMessage message2 = new VoiceMessage("20.04.2024", message_select.getValue(), sender.getText());
                                                text.setText(sender.getText());
                                                author.setText("Me");
                                                gridPane.add(Message, 2, currentRow[0]);
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    case "User":
                                        switch (message_select.getValue()){
                                            case "Text":
                                                TextMessage message = new TextMessage("20.04.2024", message_select.getValue(), sender.getText());
                                                text.setText(sender.getText());
                                                author.setText("User");
                                                gridPane.add(Message1, 0, currentRow[0]);
                                                break;
                                            case "Image":
                                                ImageMessage message1 = new ImageMessage("20.04.2024", message_select.getValue(), sender.getText());
                                                text.setText(sender.getText());
                                                author.setText("User");
                                                gridPane.add(Message1, 0, currentRow[0]);
                                                break;
                                            case "Voice":
                                                VoiceMessage message2 = new VoiceMessage("20.04.2024", message_select.getValue(), sender.getText());
                                                text.setText(sender.getText());
                                                author.setText("User");
                                                gridPane.add(Message1, 0, currentRow[0]);
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    default:
                                        break;
                                }
                                currentRow[0]++;
                            });
                        }
                    });
                }
            });
        });

        peopleVbox.getChildren().add(ADD);



        //Adding messages to visible chat




        root.getChildren().addAll(rectangle1, rectangle2, line, peopleVbox, scrollPane);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application");
        primaryStage.show();
    }
    void chooseUser(){
       //oot.getChildren()
    }

    //Function for node click


}

