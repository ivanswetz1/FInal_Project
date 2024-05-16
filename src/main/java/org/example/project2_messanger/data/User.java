package org.example.project2_messanger.data;

public class User {
    private String name;
    private String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
