package com.reffo.osreffo.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrdemServicoInputModel {	

	@Valid
	@NotNull
	private ClienteOrdemServicoInputModel cliente;
	@NotBlank
	private String descricao;
	@NotNull
	private BigDecimal preco;
}
