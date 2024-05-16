package org.example.project2_messanger.data;

import javafx.scene.image.Image;

public class ImageMessage extends BaseMessage {
    private String url;
    private Image image;

    public ImageMessage(String date, String author, int x, int y, String url) {
        super(date, author, x, y);
        this.url = url;
        this.image = new Image(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.image = new Image(url);
    }

    public Image getImage() {
        return image;
    }

}
