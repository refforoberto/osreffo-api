package com.reffo.osreffo.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reffo.osreffo.api.model.OrdemServicoModel;
import com.reffo.osreffo.api.model.input.OrdemServicoInputModel;
import com.reffo.osreffo.domain.exception.DominioException;
import com.reffo.osreffo.domain.model.Cliente;
import com.reffo.osreffo.domain.model.OrdemServico;
import com.reffo.osreffo.domain.repository.ClienteRepository;
import com.reffo.osreffo.domain.repository.OrdemServicoRepository;
import com.reffo.osreffo.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/os")
public class OrdemServicoController  {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInputModel input) {

		OrdemServico os = toEntity(input);		
		Supplier<DominioException> exceptionSupplier = () -> new DominioException("Cliente não encontrado");

		Cliente cliente = clienteRepository.findById(os.getCliente().getId()).orElseThrow(exceptionSupplier);
		os.setCliente(cliente);
		return toModel(gestaoOrdemServicoService.criar(os));
	}
	
	@PutMapping("/{id}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void finalizacao(@PathVariable Long id) {		
		gestaoOrdemServicoService.finalizar(id);		
	}

	@GetMapping
	public List<OrdemServicoModel> listar() {
		return toCollectionModel(ordemServicoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long id) {
		Optional<OrdemServico> osOpt = ordemServicoRepository.findById(id);

		if (osOpt.isPresent()) {
			OrdemServicoModel osModel = toModel(osOpt.get());
			return ResponseEntity.ok(osModel);
		}
		return ResponseEntity.notFound().build();
	}

	private OrdemServicoModel toModel(OrdemServico os) {
		return modelMapper.map(os, OrdemServicoModel.class);
	}

	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> osList) {
		return osList.stream()
				.map(os -> toModel(os))
				.collect(Collectors.toList());
	}


	public OrdemServico toEntity(OrdemServicoInputModel input) {
		return modelMapper.map(input, OrdemServico.class);
	}	
}
