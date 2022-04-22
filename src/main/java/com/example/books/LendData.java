package com.example.books;

import java.time.LocalDate;

public class LendData {
    private Book book;
    private Client client;
    private LocalDate lendDate;
    private LocalDate returnDate;

    public LendData(Book book, Client client, LocalDate lendDate, LocalDate returnDate) {
        this.book = book;
        this.client = client;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getLendDate() {
        return lendDate;
    }

    public void setLendDate(LocalDate lendDate) {
        this.lendDate = lendDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
