package com.example.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ClientsController {
    @Autowired
    IClientService clientService;

    @RequestMapping(value = "/get/clients", method = RequestMethod.GET)
    public ResponseEntity<Object> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getClient(@PathVariable("id") int id){
        return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/client", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateClient(@RequestBody Client updatedClient){
        if(clientService.updateClient(updatedClient))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/add/client", method = RequestMethod.POST)
    public ResponseEntity<Object> addClient(@RequestBody Client newClient) {
        if(clientService.addClient(newClient))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/delete/client/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClient(@PathVariable("id") int id){
        if (clientService.deleteClient(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
