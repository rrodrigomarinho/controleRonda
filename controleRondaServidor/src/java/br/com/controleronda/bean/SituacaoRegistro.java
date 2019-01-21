package br.com.controleronda.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "situacao_registro")
public class SituacaoRegistro {

	private int id;
	private String descricaoSituacao;
	private String observacaoSituacao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="des_sit")
	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}

	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}

	@Column(name="obs_sit")
	public String getObservacaoSituacao() {
		return observacaoSituacao;
	}

	public void setObservacaoSituacao(String observacaoSituacao) {
		this.observacaoSituacao = observacaoSituacao;
	}
}