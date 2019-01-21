package br.com.controleronda.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Funcionario;

@Repository
public class FuncionarioDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	public void incluir(Funcionario f) {
		sessionFactory.getCurrentSession().save(f);
	}
	
	public void alterar(Funcionario f) {
		sessionFactory.getCurrentSession().update(f);
	}
	
	public void excluir(Funcionario f) {
		sessionFactory.getCurrentSession().delete(f);
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Funcionario> listar() {
		return sessionFactory.getCurrentSession().createQuery("from Funcionario").list();
	}
	
	public Funcionario consultar(int id) {
		return (Funcionario) sessionFactory.getCurrentSession().createQuery("from Funcionario where id = " + id).uniqueResult();
	}
	
	public Funcionario consultarPeloCpf(String cpf) {
		return (Funcionario) sessionFactory.getCurrentSession().createQuery("from Funcionario where fun_cpf = '" + cpf + "'").uniqueResult();
	}
	
	public String consultarCpfPeloId(int id) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select fun_cpf from Funcionario where id = " + id);
		query.addScalar("fun_cpf", new StringType());
		String fun_cpf = (String) query.uniqueResult();
		return fun_cpf;
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Funcionario> pesquisar(String valor) {
		return sessionFactory.getCurrentSession().createQuery("from Funcionario where " +
				"upper (fun_cpf) like upper ('%" + valor + "%') or " +
						"upper (fun_ema) like upper ('%" + valor + "%') or " +
								"upper (fun_nom) like upper ('%" + valor + "%') or " +
										"upper (fun_tel) like upper ('%" + valor + "%')").list();
	}
}
