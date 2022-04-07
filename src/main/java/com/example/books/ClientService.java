package com.example.books;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ClientService implements IClientService{
    private static List<Client> clientsRepo = new ArrayList<>();

    static {
        clientsRepo.add(new Client(1, "Joe", "Smith", LocalDate.of(1970, 1, 1)));
        clientsRepo.add(new Client(2, "Jan", "Nowak", LocalDate.of(1980, 2, 2)));
        clientsRepo.add(new Client(3, "Maria", "Kowalska", LocalDate.of(1990, 3, 3)));
    }

    @Override
    public Collection<Client> getClients() {
        return clientsRepo;
    }

    @Override
    public Client getClient(int id) {
        return clientsRepo.stream()
                .filter(c -> c.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public boolean updateClient(Client updatedClient) {
        Client clientToUpdate = getClient(updatedClient.getId());
        if(clientToUpdate == null)
            return false;
        clientToUpdate.setFirstName(updatedClient.getFirstName());
        clientToUpdate.setLastName(updatedClient.getLastName());
        clientToUpdate.setBirthDate(updatedClient.getBirthDate());
        return true;
    }

    @Override
    public boolean addClient(Client newClient) {
        if(getClient(newClient.getId()) != null)
            return false;
        clientsRepo.add(newClient);
        return true;
    }

    @Override
    public boolean deleteClient(int id) {
        Client clientToDelete = getClient(id);
        if(clientToDelete == null)
            return false;
        clientsRepo.remove(clientToDelete);
        return true;
    }
}
