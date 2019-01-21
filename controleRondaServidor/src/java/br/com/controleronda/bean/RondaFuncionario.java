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
@Table(name = "ronda_funcionario")
public class RondaFuncionario {

	private int id;
	private Ronda ronda;
	private Funcionario funcionario;
	private Date dataInicialFuncionario;
	private Date dataFinalFuncionario;
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
	@JoinColumn(name = "fun_id")
	@ForeignKey(name = "fun_funcionario_id_fkey")
	@OnDelete(action = OnDeleteAction.CASCADE)	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario nomefuncionario) {
		this.funcionario = nomefuncionario;
	}

	@Column(name="fun_dt_ini")
	public Date getDataInicialFuncionario() {
		return dataInicialFuncionario;
	}

	public void setDataInicialFuncionario(Date dataInicialFuncionario) {
		this.dataInicialFuncionario = dataInicialFuncionario;
	}

	@Column(name="fun_dt_fin")
	public Date getDataFinalFuncionario() {
		return dataFinalFuncionario;
	}
	
	public void setDataFinalFuncionario(Date dataFinalFuncionario) {
		this.dataFinalFuncionario = dataFinalFuncionario;
	}
	
	@Column(name="dt_cad")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
