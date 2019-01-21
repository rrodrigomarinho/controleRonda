package br.com.controleronda.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.ParametroSistema;

@Repository
public class ParametroSistemaDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	public int alterar(int id, String valor) {
		String s = "update parametro_sistema set par_val = '" + valor + "' where id = " + id;
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(s);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<ParametroSistema> listar() {
		return sessionFactory.getCurrentSession().createQuery("from ParametroSistema").list();
	}
	
	public ParametroSistema consultar(int id) {
		return (ParametroSistema) sessionFactory.getCurrentSession().createQuery("from ParametroSistema where id = " + id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<ParametroSistema> pesquisar(String valor) {
		return sessionFactory.getCurrentSession().createQuery("from ParametroSistema where " +
				"upper (par_des) like upper ('%" + valor + "%') or " +
						"upper (par_nom) like upper ('%" + valor + "%') or " +
								"upper (par_val) like upper ('%" + valor + "%')").list();
	}
}
