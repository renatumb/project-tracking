package com.renatobonfim.projectmanagerbackend.rest.impl;

import com.renatobonfim.projectmanagerbackend.domain.Client;
import com.renatobonfim.projectmanagerbackend.rest.ClientRest;
import com.renatobonfim.projectmanagerbackend.service.ClientService;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestImpl implements ClientRest {

    @Autowired
    ClientService clientService;

    @Override
    public ResponseEntity<Client> createClient(Client client) throws URISyntaxException {
        Client clientCreated = clientService.createClient(client);
        return ResponseEntity.created(new URI(clientCreated.getId())).body(clientCreated);
    }

    @Override
    public ResponseEntity deleteClient(String clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Client> updateClient(String clientId, Client client) {
        Client clientUpdated = clientService.updateClient(clientId, client);
        return ResponseEntity.accepted().body(clientUpdated);
    }

    @Override
    public ResponseEntity<Page<Client>> findAll(int page, int size) {
        return ResponseEntity.ok(clientService.findAll(page, size) );
    }

    @Override
    public ResponseEntity<Client> findById(String clientId) {
        return ResponseEntity.ok().body(clientService.findById(clientId) );
    }
}
