package com.example.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LendService implements ILendService {
    @Autowired
    IBooksService booksService;

    @Autowired
    IClientService clientService;

    private static List<Lend> lendsRepo = new ArrayList<>();

    static {
        lendsRepo.add(new Lend(1, 1, LocalDate.of(2022, 4, 1), null));
        lendsRepo.add(new Lend(2, 1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 5)));
        lendsRepo.add(new Lend(3, 1, LocalDate.of(2022, 3, 1), null));
        lendsRepo.add(new Lend(3, 2, LocalDate.of(2022, 3, 20), null));
    }

    @Override
    public Collection<Lend> getLends() {
        return lendsRepo;
    }

    @Override
    public Lend[] getLendsOfClient(int clientId) {
        return (Lend[]) lendsRepo.stream()
                .filter(l -> l.getClientId() == clientId)
                .toArray();
    }

    @Override
    public Lend[] getAllLendsOfBook(int bookId) {
        return (Lend[]) lendsRepo.stream()
                .filter(l -> l.getBookId() == bookId)
                .toArray();
    }

    @Override
    public Client[] getClientsWithBook(int bookId) {
        Lend[] lends = (Lend[]) lendsRepo.stream()
                .filter(l -> l.getBookId() == bookId && l.getReturnDate() == null)
                .toArray();

        ArrayList<Client> clients = new ArrayList<>();
        for (int i = 0; i < lends.length; i++) {
            clients.add(clientService.getClient(lends[i].getClientId()));
        }
        return (Client[]) clients.toArray();
    }

    @Override
    public Lend[] getNotReturned() {
        return (Lend[]) lendsRepo.stream()
                .filter(l -> l.getReturnDate() == null)
                .toArray();
    }

    @Override
    public Book[] getBooksNotReturnedByClient(int clientId) {
        Lend[] lends = (Lend[]) lendsRepo.stream()
                .filter(l -> l.getClientId() == clientId && l.getReturnDate() == null)
                .toArray();

        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < lends.length; i++) {
            books.add(booksService.getBook(lends[i].getBookId()));
        }
        return (Book[]) books.toArray();
    }

    @Override
    public boolean lendBookToClient(int bookId, int clientId) {
        if (booksService.decreaseAvailableCopies(bookId)){
            lendsRepo.add(new Lend(bookId, clientId, LocalDate.now(), null));
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(int bookId, int clientId) {
        Lend lend = lendsRepo.stream()
                .filter(l -> l.getBookId() == bookId && l.getClientId() == clientId && l.getReturnDate() == null)
                .findAny()
                .orElse(null);
        if(lend == null)
            return false;
        if (booksService.increaseAvailableCopies(bookId)){
            lend.setReturnDate(LocalDate.now());
            return true;
        }
        return false;
    }
}
