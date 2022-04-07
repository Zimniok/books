package com.example.books;

import java.util.Collection;

public interface ILendService {
    public abstract Collection<Lend> getLends();
    public abstract Lend[] getLendsOfClient(int clientId);
    public abstract Lend[] getAllLendsOfBook(int bookId);
    public abstract Client[] getClientsWithBook(int bookId);
    public abstract Lend[] getNotReturned();
    public abstract Book[] getBooksNotReturnedByClient(int clientId);
    public abstract boolean lendBookToClient(int bookId, int clientId);
    public abstract boolean returnBook(int bookId, int clientId);
}
