package com.lssemail.garden.book.model;

import java.io.Serializable;

public class Author implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String bio;

    private String favourite_section;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFavourite_section() {
        return favourite_section;
    }

    public void setFavourite_section(String favourite_section) {
        this.favourite_section = favourite_section;
    }
}

