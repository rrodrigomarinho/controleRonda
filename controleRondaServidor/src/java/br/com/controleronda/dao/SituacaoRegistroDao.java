package br.com.controleronda.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.SituacaoRegistro;

@Repository
public class SituacaoRegistroDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<SituacaoRegistro> listar() {
		return sessionFactory.getCurrentSession().createQuery("from SituacaoRegistro").list();
	}
}
