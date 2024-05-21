package com.example.androidapp;

public class Contact {
    private String name;
    private String phone;
    private String email;
    private int age;

    public Contact(String name, String phone, String email, int age) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
