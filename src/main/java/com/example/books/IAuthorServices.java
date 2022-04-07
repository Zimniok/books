package com.example.books;

import java.util.Collection;

public interface IAuthorServices {
    public abstract Collection<Author> getAuthors();
    public abstract Author getAuthor(int id);
    public abstract Boolean addAuthor(Author newAuthor);
    public abstract Boolean updateAuthor(Author updatedAuthor);
    public abstract Boolean deleteAuthor(int id);
}
