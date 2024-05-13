package com.renatobonfim.projectmanagerbackend.rest;

import com.renatobonfim.projectmanagerbackend.domain.Client;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URISyntaxException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name = "Client", description = "Client's operations")
@RequestMapping("/api/client")
public interface ClientRest {

    @PostMapping
    ResponseEntity<Client> createClient(@RequestBody  Client client) throws URISyntaxException;

    @DeleteMapping("/{clientId}")
    ResponseEntity deleteClient(@PathVariable(value = "clientId", required = true) String clientId);

    @PutMapping("/{clientId}")
    ResponseEntity<Client> updateClient(@PathVariable(value = "clientId", required = true) String clientId, @RequestBody(required = true) Client client);

    @GetMapping
    ResponseEntity<Page<Client>> findAll(@RequestParam(value = "page", defaultValue = "0 ") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size);

    @GetMapping("/{clientId}")
    ResponseEntity<Client> findById(@PathVariable(value = "clientId", required = true) String clientId);
}
