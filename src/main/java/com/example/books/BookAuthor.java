package com.example.books;

public class BookAuthor {
    private int bookID;
    private int authorID;

    public BookAuthor(int bookID, int authorID){
        this.bookID = bookID;
        this.authorID = authorID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
}
