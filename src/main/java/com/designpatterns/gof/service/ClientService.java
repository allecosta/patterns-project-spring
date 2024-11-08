package com.designpatterns.gof.service;

import com.designpatterns.gof.model.Client;

/**
 * Interface que define o padrão Strategy no domínio de cliente.
 * Com isso, se necessário, podemos ter multiplas implementações dessa mesma interface.
 *
 * @author allecosta
 * @author falvojr
 */
public interface ClientService {

	Iterable<Client> findForAll();

	Client findForId(Long id);

	void insert(Client client);

	void update(Long id, Client client);

	void delete(Long id);

}
