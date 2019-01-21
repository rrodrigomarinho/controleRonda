package br.com.controleronda.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cracha {

	private int id;
	private String numeroCracha;
	private String situacaoCracha;
	private Date dataCadastro;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="cra_num")
	public String getNumeroCracha() {
		return numeroCracha;
	}

	public void setNumeroCracha(String numeroCracha) {
		this.numeroCracha = numeroCracha;
	}

	@Column(name="cra_sit")
	public String getSituacaoCracha() {
		return situacaoCracha;
	}

	public void setSituacaoCracha(String situacaoCracha) {
		this.situacaoCracha = situacaoCracha;
	}

	@Column(name="cra_dt_cad")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
