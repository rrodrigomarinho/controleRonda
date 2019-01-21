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
@Table(name = "funcionario_cracha")
public class FuncionarioCracha {

	private int id;
	private Funcionario funcionario;
	private Cracha cracha;
	private Date dataInicialCracha;
	private Date dataFinalCracha;
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
	@JoinColumn(name = "fun_id")
	@ForeignKey(name = "fun_funcionario_id_fkey")
	@OnDelete(action = OnDeleteAction.CASCADE)		
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cra_id")
	@ForeignKey(name = "cra_cracha_id_fkey")
	@OnDelete(action = OnDeleteAction.CASCADE)	
	public Cracha getCracha() {
		return cracha;
	}

	public void setCracha(Cracha numeroCracha) {
		this.cracha = numeroCracha;
	}

	@Column(name="cra_dt_ini")
	public Date getDataInicialCracha() {
		return dataInicialCracha;
	}

	public void setDataInicialCracha(Date dataInicialCracha) {
		this.dataInicialCracha = dataInicialCracha;
	}

	@Column(name="cra_dt_fin")
	public Date getDataFinalCracha() {
		return dataFinalCracha;
	}

	public void setDataFinalCracha(Date dataFinalCracha) {
		this.dataFinalCracha = dataFinalCracha;
	}

	@Column(name="dt_cad")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}