package com.reffo.osreffo.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reffo.osreffo.api.model.ComentarioModel;
import com.reffo.osreffo.api.model.input.ComentarioInputModel;
import com.reffo.osreffo.domain.exception.EntidadeNaoEncontradaException;
import com.reffo.osreffo.domain.model.Comentario;
import com.reffo.osreffo.domain.model.OrdemServico;
import com.reffo.osreffo.domain.repository.OrdemServicoRepository;
import com.reffo.osreffo.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/os/{osId}/comentario")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long osId, @Valid @RequestBody ComentarioInputModel input) {
		Comentario comentario = gestaoOrdemServicoService.adicionarComentario(osId, input.getDescricao());
		return toModel(comentario);

	}

	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long osId) {

		OrdemServico os = ordemServicoRepository.findById(osId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		
		return toCollectionModel(os.getComentarios());

	}

	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}

	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
		return comentarios.stream().map(c -> modelMapper.map(c, ComentarioModel.class)).collect(Collectors.toList());
	}

}
