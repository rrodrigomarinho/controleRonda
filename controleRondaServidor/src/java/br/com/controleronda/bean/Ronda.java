package br.com.controleronda.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ronda {

	private int id;
	private String nomeRonda;
	private Date dataCadastro;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ron_nom")
	public String getNomeRonda() {
		return nomeRonda;
	}

	public void setNomeRonda(String nomeRonda) {
		this.nomeRonda = nomeRonda;
	}
	
	@Column(name="ron_dt_cad")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
