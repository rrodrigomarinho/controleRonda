package br.com.controleronda.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cracha_ponto")
public class CrachaPonto {

	private int id;
	private String numeroCracha;
	private String nomePonto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="num_cra")
	public String getNumeroCracha() {
		return numeroCracha;
	}

	public void setNumeroCracha(String numeroCracha) {
		this.numeroCracha = numeroCracha;
	}

	@Column(name="nom_pon")
	public String getNomePonto() {
		return nomePonto;
	}

	public void setNomePonto(String nomePonto) {
		this.nomePonto = nomePonto;
	}
}
