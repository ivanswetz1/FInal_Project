package org.example.project2_messanger.data;

public class VoiceMessage extends BaseMessage {
    String url;

    public VoiceMessage(String date, String author, int x, int y, String url) {
        super(date, author, x, y);
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
