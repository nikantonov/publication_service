package com.eis.publication;

public class Publication {
    int UserId;
    String titel;
    String author;
    int year;
    String publisher;

    public Publication(){

    }

    public Publication(int UserId, String titel, String author, int year, String publisher){
        this.UserId = UserId;
        this.titel = titel;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
