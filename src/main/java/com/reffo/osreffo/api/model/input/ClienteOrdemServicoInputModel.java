package com.reffo.osreffo.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClienteOrdemServicoInputModel {
	@NotNull
	private Long id;
}
