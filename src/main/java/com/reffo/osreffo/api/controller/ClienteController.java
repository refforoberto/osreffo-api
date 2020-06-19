package com.reffo.osreffo.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reffo.osreffo.domain.model.Cliente;
import com.reffo.osreffo.domain.repository.ClienteRepository;
import com.reffo.osreffo.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CadastroClienteService cadastroClienteService;

	@PersistenceContext
	private EntityManager em;

//	@GetMapping("/clientes")
//	public List<Cliente> listar() {
//		return Arrays.asList(new Cliente(1L, "Roberto", "roberto@reffo", "47-9999-9988"),
//				new Cliente(2L, "Romario", "romario@silva", "47-2222-3333"));
//	}

//	@GetMapping("/clientes")
//	public List<Cliente> listar() {
//		return em.createQuery("from Cliente", Cliente.class).getResultList();
//	}

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);

		if (clienteOpt.isPresent()) {
			return ResponseEntity.ok(clienteOpt.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroClienteService.adicionar(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
		return cadastroClienteService.alterar(id, cliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		return cadastroClienteService.excluir(id);
	}

}
