package com.designpatterns.gof.service.impl;

import java.util.Optional;

import com.designpatterns.gof.model.Client;
import com.designpatterns.gof.repository.ClientRepository;
import com.designpatterns.gof.model.Address;
import com.designpatterns.gof.repository.AddressRepository;
import com.designpatterns.gof.service.ClientService;
import com.designpatterns.gof.service.ViaCepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação da Strategy {@link ClientService}, ao qual pode ser
 * injetada pelo Spring (via {@link Autowired}).
 * Com isso, como essa classe é um {@link Service}, ela será tratada como um Singleton.
 *
 * @author allecosta
 * @author falvojr
 */
@Service
public class ClientServiceImplementation implements ClientService {

	// Singleton: Injeta os componentes do Spring com @Autowired.
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ViaCepService viaCepService;
	
	// Strategy: Implementa os métodos definidos na interface.
	// Facade: Abstrai integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Client> findForAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client findForId(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.get();
	}

	@Override
	public void insert(Client client) {
		saveClientWithCep(client);
	}

	@Override
	public void update(Long id, Client client) {
		Optional<Client> clientBd = clientRepository.findById(id);

		if (clientBd.isPresent()) {
			saveClientWithCep(client);
		}
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);
	}

	private void saveClientWithCep(Client client) {
		String cep = client.getAddress().getCep();
		Address address = addressRepository.findById(cep).orElseGet(() -> {
			Address newAddress = viaCepService.consultCep(cep);
			addressRepository.save(newAddress);
			return newAddress;
		});
		client.setAddress(address);
		clientRepository.save(client);
	}
}
