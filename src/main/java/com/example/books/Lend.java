package com.example.books;

import java.time.LocalDate;

public class Lend {
    private int bookId;
    private int clientId;
    private LocalDate lendDate;
    private LocalDate returnDate;

    public Lend(int bookId, int clientId, LocalDate lendDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.clientId = clientId;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public int getClientId() {
        return clientId;
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
