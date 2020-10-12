package com.example.studenthelp;

public class User {
    private String Name;
    private String Email;
    private String Phone;

    public User() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public User(String name, String email, String phone) {
        Name = name;
        Email = email;
        Phone = phone;
    }



}
