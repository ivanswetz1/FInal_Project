package org.example.project2_messanger.data;

public class TextMessage extends BaseMessage {
    private String text;

    public TextMessage(String date, String author, int x, int y, String text) {
        super(date, author, x, y);
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
