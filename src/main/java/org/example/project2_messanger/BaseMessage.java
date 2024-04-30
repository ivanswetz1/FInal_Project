package org.example.project2_messanger;

abstract public class BaseMessage {
    String date;
    String author;

    public BaseMessage(String date, String author) {
        this.date = date;
        this.author = author;
    }
}
