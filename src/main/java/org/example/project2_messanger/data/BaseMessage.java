package org.example.project2_messanger.data;

abstract public class BaseMessage {
    private String date;
    private String author;
    public int x;
    public int y;

    public BaseMessage(String date, String author, int x, int y) {
        this.date = date;
        this.author = author;
        this.x = x;
        this.y = y;
    }
    public String getDate() {
        return date;
    }
    public String getAuthor() {
        return author;
    }
}
