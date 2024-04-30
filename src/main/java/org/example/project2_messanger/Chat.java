package org.example.project2_messanger;

import java.util.ArrayList;

public class Chat extends User{
    ArrayList<BaseMessage> messages = new ArrayList<BaseMessage>();

    public Chat(String name, String phone) {
        super(name, phone);
    }
    public void AddTMessage(TextMessage message){
        messages.add(message);
    }
    public void AddIMessage(ImageMessage message){
        messages.add(message);
    }
    public void AddVMessage(VoiceMessage message){
        messages.add(message);
    }
}
