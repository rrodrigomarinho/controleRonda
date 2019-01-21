package br.com.controleronda.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Cracha;

@Repository
public class CrachaDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	public void incluir(Cracha c) {
		sessionFactory.getCurrentSession().save(c);
	}

	public void alterar(Cracha c) {
		sessionFactory.getCurrentSession().update(c);
	}

	public void excluir(Cracha c) {
		sessionFactory.getCurrentSession().delete(c);
	}

	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Cracha> listar() {
		return sessionFactory.getCurrentSession().createQuery("from Cracha").list();
	}

	public Cracha consultar(int id) {
		return (Cracha) sessionFactory.getCurrentSession().createQuery("from Cracha where id = " + id).uniqueResult();
	}

	public String consultarUltimoCracha() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select num_cra from cracha_ponto limit 1");
		query.addScalar("num_cra", new StringType());
		String numCra = (String) query.uniqueResult();
		sessionFactory.getCurrentSession().createSQLQuery("delete from cracha_ponto").executeUpdate();
		return numCra;
	}
	
	public Cracha consultarPeloNumero(String numeroCracha) {
		return (Cracha) sessionFactory.getCurrentSession().createQuery("from Cracha where numeroCracha = '" + numeroCracha + "'").uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Cracha> pesquisar(String valor) {
		return sessionFactory.getCurrentSession().createQuery("from Cracha where " +
				"upper (cra_num) like upper ('%" + valor + "%') or " +
						"upper (cra_sit) like upper ('%" + valor + "%')").list();
	}
}
