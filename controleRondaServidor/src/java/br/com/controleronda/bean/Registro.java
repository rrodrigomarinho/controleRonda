package br.com.controleronda.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Registro {

	private int id;
	private Ponto ponto;
	private Cracha cracha;
	private SituacaoRegistro situacao;
	private Date dataRegistro;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pon_id", nullable = false)
	@ForeignKey(name = "pon_ponto_id_fkey")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto nomePonto) {
		this.ponto = nomePonto;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cra_id", nullable = false)
	@ForeignKey(name = "cra_cracha_id_fkey")
	@OnDelete(action = OnDeleteAction.CASCADE)	
	public Cracha getCracha() {
		return cracha;
	}

	public void setCracha(Cracha numeroCracha) {
		this.cracha = numeroCracha;
	}
	
	@Column(name="dat_reg", nullable = false)
	public Date getDataRegistro() {
		return dataRegistro;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sit_id", nullable = false)
	@ForeignKey(name = "sit_situacao_registro_id_fkey")
	@OnDelete(action = OnDeleteAction.CASCADE)	
	public SituacaoRegistro getSituacaoRegistro() {
		return situacao;
	}

	@Column(name="sit_id")
	public void setSituacaoRegistro(SituacaoRegistro situacao) {
		this.situacao = situacao;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
}
