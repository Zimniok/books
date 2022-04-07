package com.example.books;

import java.util.Collection;

public interface IClientService {
    public abstract Collection<Client> getClients();
    public abstract Client getClient(int id);
    public abstract boolean updateClient(Client updatedClient);
    public abstract boolean addClient(Client newClient);
    public abstract boolean deleteClient(int id);
}
