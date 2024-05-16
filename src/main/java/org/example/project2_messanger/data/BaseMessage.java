package org.example.project2_messanger.data;

abstract public class BaseMessage {
    private String date;
    private String author;
    private int[] currentRowIndex = {0};
    private int[] currentColumnIndex = {0};

    public BaseMessage(String date, String author, int x, int y) {
        this.date = date;
        this.author = author;
        currentRowIndex[0] = x;
        currentColumnIndex[0] = y;
    }
    public String getDate() {
        return date;
    }
    public String getAuthor() {
        return author;
    }
}
