package com.renatobonfim.projectmanagerbackend.service.impl;

import com.renatobonfim.projectmanagerbackend.exception.InvalidFieldException;
import com.renatobonfim.projectmanagerbackend.exception.NotFoundException;
import com.renatobonfim.projectmanagerbackend.domain.Client;
import com.renatobonfim.projectmanagerbackend.repository.ClientRepo;
import com.renatobonfim.projectmanagerbackend.service.ClientService;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepo clientRepo;

    public Client createClient(Client client) {
        try {
            Objects.nonNull(client);
            Objects.requireNonNull(client.getName(), "invalid client name");
            Objects.requireNonNull(client.getDescription(), "invalid client description");
        } catch (NullPointerException ex) {
            throw new InvalidFieldException(ex.getMessage());
        }

        return clientRepo.save(client);
    }

    @Override
    public void deleteClient(String clientId) {
        findById(clientId);
        clientRepo.deleteById(clientId);
    }

    @Override
    public Client updateClient(String clientId, Client client) {
        Client clientFound = findById(clientId);

        try {
            Objects.nonNull(client);
            Objects.requireNonNull(client.getName(), "invalid client name");
            Objects.requireNonNull(client.getDescription(), "invalid client description");
        } catch (NullPointerException ex) {
            throw new InvalidFieldException(ex.getMessage());
        }

        client.setId(clientFound.getId());
        return clientRepo.save(client);
    }

    @Override
    public Page<Client> findAll(int page, int size) {
        return clientRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    @Override
    public Client findById(String clientId) {
        return clientRepo.findById(clientId).orElseThrow(() -> new NotFoundException("client not found for given id: " + clientId));
    }
}
