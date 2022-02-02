package com.example.testas.dto;

public class IrasoDto {

    private Long id;
    private String title;
    private String text;
    private String uEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return uEmail;
    }

    public void setUsername(String username) {
        this.uEmail = username;
    }
}
