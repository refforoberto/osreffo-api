package com.reffo.osreffo.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reffo.osreffo.domain.model.OrdemServico;
import com.reffo.osreffo.domain.model.enums.SituacaoOrdemServico;
import com.reffo.osreffo.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public OrdemServico criar(OrdemServico os) {
		
		os.setDataAbertura(OffsetDateTime.now());
		os.setSituacao(SituacaoOrdemServico.ABERTA);
		return ordemServicoRepository.save(preencherValoresPadroes(os));
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

}
