package com.example.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookAuthorController {
    @Autowired
    IBookAuthorService bookAuthorService;

    @RequestMapping(value = "/get/books_authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooksAuthors(){
        return new ResponseEntity<>(bookAuthorService.getBooksAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/authors_of_book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthorsOfBook(@PathVariable("id") int bookId){
        return new ResponseEntity<>(bookAuthorService.getAuthorsOfBook(bookId), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/books_by_author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooksByAuthor(@PathVariable("id") int authorId){
        return new ResponseEntity<>(bookAuthorService.getBooksByAuthor(authorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/book_author/{bookId}/{authorId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBookAuthor(@PathVariable("bookId") int bookId, @PathVariable("authorId") int authorId){
        if(bookAuthorService.deleteBookAuthor(bookId, authorId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/add/book_author", method = RequestMethod.POST)
    public ResponseEntity<Object> addBookAuthor(@RequestBody BookAuthor newBookAuthor){
        if(bookAuthorService.addBookAuthor(newBookAuthor))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
