package com.reffo.osreffo.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reffo.osreffo.domain.exception.DominioException;
import com.reffo.osreffo.domain.model.Cliente;
import com.reffo.osreffo.domain.model.OrdemServico;
import com.reffo.osreffo.domain.repository.ClienteRepository;
import com.reffo.osreffo.domain.repository.OrdemServicoRepository;
import com.reffo.osreffo.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/os")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServico os) {

		Supplier<DominioException> exceptionSupplier = () -> new DominioException("Cliente não encontrado");

		Cliente cliente = clienteRepository.findById(os.getCliente().getId()).orElseThrow(exceptionSupplier);
		os.setCliente(cliente);
		return gestaoOrdemServicoService.criar(os);
	}
	
	@GetMapping
	public List<OrdemServico> listar() {
		return ordemServicoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdemServico> buscar(@PathVariable Long id) {		
		Optional<OrdemServico> osOpt = ordemServicoRepository.findById(id);
		
		if(osOpt.isPresent()) {
			return ResponseEntity.ok(osOpt.get());
		}		
		return ResponseEntity.notFound().build();
	}

}
