package com.reffo.osreffo.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.reffo.osreffo.domain.model.enums.SituacaoOrdemServico;

import lombok.Data;

@Data
public class OrdemServicoModel {
	
	private Long id;
	private ClienteResumoModel cliente;
	private String descricao;	
	private BigDecimal preco;	
	private SituacaoOrdemServico situacao;
	private OffsetDateTime dataAbertura;	
	private OffsetDateTime dataFinalizacao;
	
}
