package com.example.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LendController {
    @Autowired
    ILendService lendService;

    @RequestMapping(value = "/get/lends", method = RequestMethod.GET)
    public ResponseEntity<Object> getLends() {
        return new ResponseEntity<>(lendService.getLends(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/lends_of_client/{clientid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getLendsOfClient(@PathVariable("clientid") int id){
        return new ResponseEntity<>(lendService.getLendsOfClient(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/ledns_of_book/{bookid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getLendsOfBook(@PathVariable("bookid") int id){
        return new ResponseEntity<>(lendService.getAllLendsOfBook(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/clients_with_book/{bookid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getClientsWithBook(@PathVariable("bookid") int id){
        return new ResponseEntity<>(lendService.getClientsWithBook(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/not_returned", method = RequestMethod.GET)
    public ResponseEntity<Object> getNotReturned(){
        return new ResponseEntity<>(lendService.getNotReturned(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/books_not_returned_by/{clientid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooksNotReturnedByClient(@PathVariable("clientid") int id){
        return new ResponseEntity<>(lendService.getBooksNotReturnedByClient(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/lend/{bookId}/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> lendBookToClient(@PathVariable("bookId") int bookId, @PathVariable("clientId") int clientId){
        if (lendService.lendBookToClient(bookId, clientId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/return/{bookId}/{clientId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> returnBook(@PathVariable("bookId") int bookId, @PathVariable("clientId") int clientId){
        if(lendService.returnBook(bookId, clientId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
