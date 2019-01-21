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

import br.com.controleronda.bean.FuncionarioCracha;

@Repository
public class FuncionarioCrachaDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	public void incluir(FuncionarioCracha fc) {
		sessionFactory.getCurrentSession().save(fc);
	}

	public void excluir(FuncionarioCracha fc) {
		sessionFactory.getCurrentSession().delete(fc);
	}

	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<FuncionarioCracha> listar(int id) {
		return sessionFactory.getCurrentSession().createQuery("from FuncionarioCracha where fun_id = " + id).list();
	}
	
	public String crachaUtilizado(int craId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select cra_id from funcionario_cracha where cra_id = " + craId + " limit 1");
		query.addScalar("cra_id", new StringType());
		String cra_id = (String) query.uniqueResult();
		return cra_id;
	}
	
	public String vigentePorFuncionario(int funId, Date dataInicial, Date dataFinal) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from funcionario_cracha where '" + dataInicial + "' <= cra_dt_fin and '" + dataFinal + "' >= cra_dt_ini and fun_id = " + funId + " limit 1");
		query.addScalar("fun_id", new StringType());
		String fun_id = (String) query.uniqueResult();
		return fun_id;
	}
	
	public String vigentePorCracha(int craId, Date dataInicial, Date dataFinal) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from funcionario_cracha where '" + dataInicial + "' <= cra_dt_fin and '" + dataFinal + "' >= cra_dt_ini and cra_id = " + craId + " limit 1");
		query.addScalar("cra_id", new StringType());
		String cra_id = (String) query.uniqueResult();
		return cra_id;
	}
}
