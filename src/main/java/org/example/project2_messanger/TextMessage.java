package org.example.project2_messanger;

public class TextMessage extends BaseMessage{
    String text;

    public TextMessage(String date, String author, String text) {
        super(date, author);
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
