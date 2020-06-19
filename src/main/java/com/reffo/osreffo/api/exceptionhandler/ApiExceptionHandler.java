package com.reffo.osreffo.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.reffo.osreffo.api.exceptionhandler.Problema.Campo;
import com.reffo.osreffo.domain.exception.DominioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource ms;
	
	
	@ExceptionHandler(DominioException.class)
	public ResponseEntity<Object> handleDominio( DominioException ex, WebRequest request) {
		
		 HttpStatus status = HttpStatus.BAD_REQUEST;
		 
		 Problema p = new Problema();
		 p.setStatus(status.value());
		 p.setDataHora(LocalDateTime.now());
		 p.setMensagem(ex.getMessage());
		 
		 return handleExceptionInternal(ex, p, new HttpHeaders(), status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Problema p = new Problema(status.value(), LocalDateTime.now(),
				"Um ou mais campos não foram preenchidos corretamente", buscarCampos(ex));
		return super.handleExceptionInternal(ex, p, headers, status, request);
	}

	private List<Campo> buscarCampos(MethodArgumentNotValidException ex) {
		List<Campo> campos = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(e -> {
			String nome = ((FieldError) e).getField();
			String msg = ms.getMessage(e, LocaleContextHolder.getLocale());
			campos.add(new Problema.Campo(nome, msg));
		});
		return campos;
	}

}
