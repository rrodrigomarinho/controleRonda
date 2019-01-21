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
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ronda_ponto")
public class RondaPonto {

	private int id;
	private Ronda ronda;
	private Ponto ponto;
	private String horaInicialPonto;
	private String horaFinalPonto;
	private Date dataCadastro;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ron_id")
	@ForeignKey(name = "ron_ronda_id_fkey")
	@OnDelete(action = OnDeleteAction.CASCADE)	
	public Ronda getRonda() {
		return ronda;
	}

	public void setRonda(Ronda ronda) {
		this.ronda = ronda;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pon_id")
	@ForeignKey(name = "pon_ponto_id_fkey")
	@OnDelete(action = OnDeleteAction.CASCADE)	
	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto nomePonto) {
		this.ponto = nomePonto;
	}

	@Column(name="pon_hr_ini")
	public String getHoraInicialPonto() {
		return horaInicialPonto;
	}
	
	public void setHoraInicialPonto(String horaInicialPonto) {
		this.horaInicialPonto = horaInicialPonto;
	}
	
	@Column(name="pon_hr_fin")
	public String getHoraFinalPonto() {
		return horaFinalPonto;
	}
	
	public void setHoraFinalPonto(String horaFinalPonto) {
		this.horaFinalPonto = horaFinalPonto;
	}
	
	@Column(name="dat_cad")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
