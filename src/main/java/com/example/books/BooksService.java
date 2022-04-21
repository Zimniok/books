package com.example.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BooksService implements IBooksService {
    @Autowired
    IAuthorServices authorServices;

    private static List<Book> booksRepo = new ArrayList<>();

    static {
        booksRepo.add(new Book(1, "Potop", 936, 3, 1));
        booksRepo.add(new Book(2,"Wesele", 150, 3, 2));
        booksRepo.add(new Book(3,"Dziady", 292, 3, 3));
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Collection<BookWithAuthor> getBooksWithAuthors() {
        List<BookWithAuthor> booksWithAuthors = new ArrayList<>();
        for(int i = 0; i < booksRepo.size(); i++){
            booksWithAuthors.add(new BookWithAuthor(booksRepo.get(i), authorServices.getAuthor(booksRepo.get(i).getAuthorId())));
        }
        return booksWithAuthors;
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

    @Override
    public Boolean decreaseAvailableCopies(int id) {
        Book b = getBook(id);
        if (b == null)
            return false;
        if (b.getAvailableCopies() == 0)
            return false;
        b.setAvailableCopies(b.getAvailableCopies()-1);
        return true;
    }

    @Override
    public Boolean increaseAvailableCopies(int id) {
        Book b = getBook(id);
        if (b == null)
            return false;
        b.setAvailableCopies(b.getAvailableCopies()+1);
        return true;
    }
}
