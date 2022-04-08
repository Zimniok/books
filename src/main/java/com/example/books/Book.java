package com.example.books;

public class Book {
    private int id;
    private String title;
    private int authorId;
    int pages;
    private int availableCopies;

    public Book(int id, String title, int pages, int availableCopies, int authorId) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.availableCopies = availableCopies;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
