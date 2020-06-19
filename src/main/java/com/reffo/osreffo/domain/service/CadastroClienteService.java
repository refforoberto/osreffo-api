package com.reffo.osreffo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.reffo.osreffo.api.controller.CrudService;
import com.reffo.osreffo.domain.model.Cliente;
import com.reffo.osreffo.domain.repository.ClienteRepository;
import com.reffo.osreffo.domain.service.validator.CadastroClienteValidator;

@Service
public class CadastroClienteService extends CrudService<Cliente, ClienteRepository> {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired 
	private CadastroClienteValidator cadastroClienteValidator;

	@Override
	public Cliente adicionar(Cliente cliente) {
		cadastroClienteValidator.validarEmailExistente(cliente);		
		return clienteRepository.save(cliente);
	}

	@Override
	public ResponseEntity<Cliente> alterar(Long id, Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(id);
		return ResponseEntity.ok(clienteRepository.save(cliente));
	}

	@Override
	public ResponseEntity<Void> excluir(Long id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
