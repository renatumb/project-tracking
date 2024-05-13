package com.renatobonfim.projectmanagerbackend.service;

import com.renatobonfim.projectmanagerbackend.domain.Client;
import org.springframework.data.domain.Page;

public interface ClientService {

    Client createClient(Client client);

    void deleteClient(String clientId);

    Client updateClient(String clientId, Client client);

    Page<Client> findAll(int page, int size);

    Client findById(String clientId);

}
