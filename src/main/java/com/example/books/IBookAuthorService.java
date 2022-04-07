package com.example.books;

import java.util.Collection;

public interface IBookAuthorService {
    public abstract Collection<BookAuthor> getBooksAuthors();
    public abstract BookAuthor getAuthorsOfBook(int bookID);
    public abstract BookAuthor getBooksByAuthor(int authorID);
    public abstract boolean deleteBookAuthor(int bookID, int authorID);
    public abstract boolean addBookAuthor(BookAuthor newBookAuthor);
}
