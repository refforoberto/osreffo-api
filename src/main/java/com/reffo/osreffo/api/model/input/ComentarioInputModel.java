package com.reffo.osreffo.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ComentarioInputModel {	
	@NotBlank
	private String descricao;
}
