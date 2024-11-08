package com.designpatterns.gof.service;

import com.designpatterns.gof.model.Address;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Client HTTP, criado via OpenFeign, para o consumo da API do ViaCEP.
 * 
 * @see https://spring.io/projects/spring-cloud-openfeign">
 * @see https://viacep.com.br">ViaCEP
 *
 * @author allecosta
 * @author falvojr
 */
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

	@GetMapping("/{cep}/json/")
    Address consultCep(@PathVariable("cep") String cep);
}
