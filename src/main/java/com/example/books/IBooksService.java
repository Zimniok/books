package com.example.books;

import java.util.Collection;

public interface IBooksService {
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
    public abstract Boolean addBook(Book newBook);
    public abstract Boolean updateBook(Book updatedBook);
    public abstract Boolean deleteBook(int id);
    public abstract Boolean decreaseAvailableCopies(int id);
    public abstract Boolean increaseAvailableCopies(int id);
    public abstract Collection<BookWithAuthor> getBooksWithAuthors();
}
