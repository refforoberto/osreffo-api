package com.reffo.osreffo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.reffo.osreffo.domain.ValidationGroups;
import com.reffo.osreffo.domain.model.enums.SituacaoOrdemServico;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of = { "id" })
public class OrdemServico {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne 
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	private Cliente cliente;

	@NotBlank	
	private String descricao;	
	
	@NotNull
	private BigDecimal preco;	

	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private SituacaoOrdemServico situacao;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataAbertura;	

	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public SituacaoOrdemServico getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoOrdemServico situacao) {
		this.situacao = situacao;
	}

	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

}
