package com.reffo.osreffo.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reffo.osreffo.domain.exception.EntidadeNaoEncontradaException;
import com.reffo.osreffo.domain.model.Comentario;
import com.reffo.osreffo.domain.model.OrdemServico;
import com.reffo.osreffo.domain.model.enums.SituacaoOrdemServico;
import com.reffo.osreffo.domain.repository.ComentarioRepository;
import com.reffo.osreffo.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	public OrdemServico criar(OrdemServico os) {
		
		os.setDataAbertura(OffsetDateTime.now());
		os.setSituacao(SituacaoOrdemServico.ABERTA);
		return ordemServicoRepository.save(preencherValoresPadroes(os));
	}
	
	
	
	public void finalizar(Long osId) {
		OrdemServico os = buscar(osId);
		os.finalizar();
		ordemServicoRepository.save(os);
	}

	private OrdemServico preencherValoresPadroes(OrdemServico os) {

		OrdemServico ordemServico = new OrdemServico();

		ordemServico.setCliente(os.getCliente());
		ordemServico.setPreco(os.getPreco());
		ordemServico.setDescricao(os.getDescricao());
		ordemServico.setCliente(os.getCliente());
		ordemServico.setDataAbertura(OffsetDateTime.now());
		ordemServico.setSituacao(SituacaoOrdemServico.ABERTA);
		
		return ordemServico;
	}
	
	
	public Comentario adicionarComentario(Long osId, String descricao) {
		
		OrdemServico os = buscar(osId);
		
	    Comentario comentario = new Comentario();
	    comentario.setOrdemServico(os);
	    comentario.setDescricao(descricao);
	    comentario.setDataEnvio(OffsetDateTime.now());
	    
	    return comentarioRepository.save(comentario);    
		
	}



	private OrdemServico buscar(Long osId) {
		OrdemServico os = ordemServicoRepository.findById(osId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		return os;
	}

}
