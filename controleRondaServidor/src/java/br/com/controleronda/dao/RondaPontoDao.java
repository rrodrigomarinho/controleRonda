package br.com.controleronda.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.RondaPonto;

@Repository
public class RondaPontoDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	public void incluir(RondaPonto rp) {
		sessionFactory.getCurrentSession().save(rp);
	}
	
	public void excluir(RondaPonto rp) {
		sessionFactory.getCurrentSession().delete(rp);
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<RondaPonto> listar(int id) {
		return sessionFactory.getCurrentSession().createQuery("from RondaPonto where ron_id = " + id).list();
	}
}
