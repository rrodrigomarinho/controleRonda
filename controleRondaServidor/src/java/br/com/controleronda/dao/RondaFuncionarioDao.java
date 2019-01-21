package br.com.controleronda.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.RondaFuncionario;

@Repository
public class RondaFuncionarioDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	public void incluir(RondaFuncionario rf) {
		sessionFactory.getCurrentSession().save(rf);
	}
	
	public void excluir(RondaFuncionario rf) {
		sessionFactory.getCurrentSession().delete(rf);
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<RondaFuncionario> listar(int id) {
		return sessionFactory.getCurrentSession().createQuery("from RondaFuncionario where ron_id = " + id).list();
	}
	
	public String funcionarioUtilizado(int funId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select fun_id from ronda_funcionario where fun_id = " + funId + " limit 1");
		query.addScalar("fun_id", new StringType());
		String fun_id = (String) query.uniqueResult();
		return fun_id;
	}
	
	public String vigentePorRonda(int ronId, Date dataInicial, Date dataFinal) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from ronda_funcionario where '" + 
	dataInicial + "' <= fun_dt_fin and '" + dataFinal + "' >= fun_dt_ini and ron_id = " + ronId + " limit 1");
		query.addScalar("ron_id", new StringType());
		String ron_id = (String) query.uniqueResult();
		return ron_id;
	}
	
	public String vigentePorFuncionario(int funId, Date dataInicial, Date dataFinal) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from ronda_funcionario where '" + 
	dataInicial + "' <= fun_dt_fin and '" + dataFinal + "' >= fun_dt_ini and fun_id = " + funId + " limit 1");
		query.addScalar("fun_id", new StringType());
		String fun_id = (String) query.uniqueResult();
		return fun_id;
	}
}
