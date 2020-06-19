package com.reffo.osreffo.api.controller;

import org.springframework.http.ResponseEntity;

public abstract class CrudService<T, I> {
	
	public abstract T adicionar(T entity);
	public abstract ResponseEntity<T> alterar(Long id,  T entity);
	public abstract ResponseEntity<Void> excluir(Long id);
}
