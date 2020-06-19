package com.reffo.osreffo.domain.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reffo.osreffo.domain.exception.DominioException;
import com.reffo.osreffo.domain.model.Cliente;
import com.reffo.osreffo.domain.repository.ClienteRepository;

@Component
public class CadastroClienteValidator {

	@Autowired
	private ClienteRepository clienteRepository;

	public void validarEmailExistente(Cliente entity) {
		Cliente cliente = clienteRepository.findByEmail(entity.getEmail());
		if (cliente != null && !cliente.equals(entity)) {
			throw new DominioException("Já existe o email " + entity.getEmail() + " cadastrado para o usuário " + cliente.getNome());
		}
	}

}
