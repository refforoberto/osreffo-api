package com.reffo.osreffo.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Problema {	
	private Integer status;
	private LocalDateTime dataHora;
	private String  mensagem;
	private List<Campo> campos;
	
	@AllArgsConstructor
    @NoArgsConstructor
	@Data	
	public static class Campo {
		private String nome;
		private String mensagem;		
	}
	
}
