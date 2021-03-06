package com.example.books;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookAuthorService implements IBookAuthorService {
    private static List<BookAuthor> bookAuthorRepo = new ArrayList<>();

    static {
        bookAuthorRepo.add(new BookAuthor(1, 1));
        bookAuthorRepo.add(new BookAuthor(2, 2));
        bookAuthorRepo.add(new BookAuthor(3, 3));
    }

    @Override
    public Collection<BookAuthor> getBooksAuthors() {
        return bookAuthorRepo;
    }

    @Override
    public BookAuthor[] getAuthorsOfBook(int bookID) {
        Object result[] = bookAuthorRepo.stream()
                .filter(ba -> ba.getBookID() == bookID)
                .toArray();
        BookAuthor bookAuthor[] = new BookAuthor[result.length];
        for (int i = 0; i < result.length; i++) {
            bookAuthor[i] = (BookAuthor) result[i];
        }
        return bookAuthor;
    }

    @Override
    public BookAuthor[] getBooksByAuthor(int authorID) {
        Object result[] = bookAuthorRepo.stream()
                .filter(ba -> ba.getAuthorID() == authorID)
                .toArray();
        BookAuthor bookAuthors[] = new BookAuthor[result.length];
        for (int i = 0; i < result.length; i++) {
            bookAuthors[i] = (BookAuthor) result[i];
        }
        return bookAuthors;
    }

    @Override
    public boolean deleteBookAuthor(int bookID, int authorID) {
        BookAuthor toDelete = bookAuthorRepo.stream()
                .filter(ba -> ba.getBookID() == bookID && ba.getAuthorID() == authorID)
                .findAny()
                .orElse(null);
        if(toDelete == null)
            return false;
        bookAuthorRepo.remove(toDelete);
        return true;
    }

    @Override
    public boolean addBookAuthor(BookAuthor newBookAuthor) {
        BookAuthor exists = bookAuthorRepo.stream()
                .filter(ba -> ba.getBookID() == newBookAuthor.getBookID() && ba.getAuthorID() == newBookAuthor.getAuthorID())
                .findAny()
                .orElse(null);
        if(exists != null)
            return false;
        bookAuthorRepo.add(newBookAuthor);
        return true;
    }
}
