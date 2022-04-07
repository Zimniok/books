package com.example.books;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();

    static {
        booksRepo.add(new Book(1, "Potop", 936));
        booksRepo.add(new Book(2,"Wesele", 150));
        booksRepo.add(new Book(3,"Dziady", 292));
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Boolean addBook(Book newBook) {
        if(getBook(newBook.getId()) == null){
            booksRepo.add(newBook);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateBook(Book updatedBook) {
        Book oldBook = getBook(updatedBook.getId());
        if(oldBook == null){
            return false;
        }
        oldBook.setAuthorId(updatedBook.getAuthorId());
        oldBook.setTitle(updatedBook.getTitle());
        oldBook.setPages(updatedBook.getPages());
        return true;
    }

    @Override
    public Boolean deleteBook(int id) {
        Book bookToDelete = getBook(id);
        if(bookToDelete == null){
            return false;
        }
        booksRepo.remove(bookToDelete);
        return true;
    }
}
