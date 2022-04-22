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
        Object result[] = lendsRepo.stream()
                .filter(l -> l.getClientId() == clientId)
                .toArray();
        Lend lends[] = new Lend[result.length];
        for (int i = 0; i < result.length; i++) {
            lends[i] = (Lend) result[i];
        }
        return lends;
    }

    @Override
    public Lend[] getAllLendsOfBook(int bookId) {
        Object lends[] = lendsRepo.stream()
                .filter(l -> l.getBookId() == bookId)
                .toArray();
        Lend result[] = new Lend[lends.length];
        for (int i = 0; i < lends.length; i++) {
            result[i] = (Lend) lends[i];
        }
        return result;
    }

    @Override
    public Client[] getClientsWithBook(int bookId) {
        Object[] lends =lendsRepo.stream()
                .filter(l -> l.getBookId() == bookId && l.getReturnDate() == null)
                .toArray();

        Client[] clients = new Client[lends.length];
        for (int i = 0; i < lends.length; i++) {
            clients[i] = (clientService.getClient(((Lend) lends[i]).getClientId()));
        }
        return clients;
    }

    @Override
    public Lend[] getNotReturned() {
        Object result[] = lendsRepo.stream()
                .filter(l -> l.getReturnDate() == null)
                .toArray();
        Lend lends[] = new Lend[result.length];
        for (int i = 0; i < result.length; i++) {
            lends[i] = (Lend) result[i];
        }
        return lends;
    }

    @Override
    public Book[] getBooksNotReturnedByClient(int clientId) {
        Object[] lends = lendsRepo.stream()
                .filter(l -> l.getClientId() == clientId && l.getReturnDate() == null)
                .toArray();

        Book books[] = new Book[lends.length];
        for (int i = 0; i < lends.length; i++) {
            books[i] = (booksService.getBook(((Lend) lends[i]).getBookId()));
        }
        return books;
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

    @Override
    public Collection<LendData> getLendData() {
        Collection<LendData> lendData = new ArrayList<>();
        for(int i =0; i < lendsRepo.size(); i++) {
            lendData.add(new LendData(booksService.getBook(lendsRepo.get(i).getBookId()), clientService.getClient(lendsRepo.get(i).getClientId()), lendsRepo.get(i).getLendDate(), lendsRepo.get(i).getReturnDate()));
        }
        return lendData;
    }
}
