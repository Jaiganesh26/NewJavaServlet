package com.model;

public class companyData {
    private String name;
    private String location;
    private String email;

    public companyData(String name, String location, String email) {
        this.name = name;
        this.location = location;
        this.email = email;
    }

    // Getters and setters for the fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

