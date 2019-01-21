package br.com.controleronda.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ponto {

	private int id;
	private String nomePonto;
	private String ipPonto;
	private String localPonto;
	private Date dataCadastro;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="pon_nom")
	public String getNomePonto() {
		return nomePonto;
	}

	public void setNomePonto(String nomePonto) {
		this.nomePonto = nomePonto;
	}
	
	@Column(name="pon_ip")
	public String getIpPonto() {
		return ipPonto;
	}

	public void setIpPonto(String ipPonto) {
		this.ipPonto = ipPonto;
	}

	@Column(name="pon_loc")
	public String getLocalPonto() {
		return localPonto;
	}

	public void setLocalPonto(String localPonto) {
		this.localPonto = localPonto;
	}
	
	@Column(name="pon_dt_cad")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
