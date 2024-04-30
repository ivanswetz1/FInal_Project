package org.example.project2_messanger;

public class ImageMessage extends BaseMessage{
    String url;

    public ImageMessage(String date, String author, String url) {
        super(date, author);
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
