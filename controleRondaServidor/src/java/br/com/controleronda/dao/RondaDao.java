package br.com.controleronda.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Ronda;

@Repository
public class RondaDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}
	
	public void incluir(Ronda r) {
		sessionFactory.getCurrentSession().save(r);
	}
	
	public void alterar(Ronda r) {
		sessionFactory.getCurrentSession().update(r);
	}
	
	public void excluir(Ronda r) {
		sessionFactory.getCurrentSession().delete(r);
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Ronda> listar() {
		return sessionFactory.getCurrentSession().createQuery("from Ronda").list();
	}
	
	public Ronda consultar(int id) {
		return (Ronda) sessionFactory.getCurrentSession().createQuery("from Ronda where id = " + id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Ronda> pesquisar(String valor) {
		return sessionFactory.getCurrentSession().createQuery("from Ronda where upper (ron_nom) like upper ('%" + valor + "%')").list();
	}
}
