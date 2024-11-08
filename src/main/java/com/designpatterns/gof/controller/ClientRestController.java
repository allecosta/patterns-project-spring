package com.designpatterns.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designpatterns.gof.model.Client;
import com.designpatterns.gof.service.ClientService;

/**
 * Esse {@link RestController} representa nossa Facade, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 *
 * @author allecosta
 * @author falvojr
 */
@RestController
@RequestMapping("clients")
public class ClientRestController {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<Iterable<Client>> findForAll() {
		return ResponseEntity.ok(clientService.findForAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> findForId(@PathVariable Long id) {
		return ResponseEntity.ok(clientService.findForId(id));
	}

	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody Client client) {
		clientService.insert(client);
		return ResponseEntity.ok(client);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
		clientService.update(id, client);
		return ResponseEntity.ok(client);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.ok().build();
	}
}
