package com.reffo.osreffo.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.reffo.osreffo.domain.exception.DominioException;
import com.reffo.osreffo.domain.model.enums.SituacaoOrdemServico;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of = { "id" })
public class OrdemServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Cliente cliente;

	private String descricao;

	private BigDecimal preco;

	@Enumerated(EnumType.STRING)
	private SituacaoOrdemServico situacao;

	private OffsetDateTime dataAbertura;

	private OffsetDateTime dataFinalizacao;
	
	@OneToMany(mappedBy = "ordemServico")
	private List<Comentario> comentarios = new ArrayList<>();

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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	private boolean naoPodeSerFinalizada() {
		return !SituacaoOrdemServico.ABERTA.equals(getSituacao());
	}
	
	public void finalizar() {
		if(naoPodeSerFinalizada()) {
			throw new DominioException("Ordem de serviço não pode ser finalizada");
		}
		setSituacao(SituacaoOrdemServico.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());
	}

}
