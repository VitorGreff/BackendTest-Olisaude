package com.greff.challenge.controller;

import com.greff.challenge.domain.Client;
import com.greff.challenge.dto.ClientDTO;
import com.greff.challenge.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <List<Client>> findAll(){ //diz que o id tem que casar com o da url
        List<Client> clients = service.findAll();
        return ResponseEntity.ok().body(clients);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Client> findById(@PathVariable String id){ //diz que o id tem que casar com o da url
        Client client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createClient(@RequestBody ClientDTO obj){
        Client client = service.fromDTO(obj);
        client = service.insertClient(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClient(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateClient(@PathVariable String id, @RequestBody ClientDTO obj){
        Client newClient = service.update(id, obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/ten-greater", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getTenGreater(){
        List<Client> clients = service.getTenGreater();
        return ResponseEntity.ok().body(clients);
    }

}
