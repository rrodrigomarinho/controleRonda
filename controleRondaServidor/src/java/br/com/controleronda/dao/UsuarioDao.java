package br.com.controleronda.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Usuario;

@Repository
public class UsuarioDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	public void incluir(Usuario u) {
		sessionFactory.getCurrentSession().save(u);
	}
	
	public void alterar(Usuario u) {
		sessionFactory.getCurrentSession().update(u);
	}
	
	public void excluir(Usuario u) {
		sessionFactory.getCurrentSession().delete(u);
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Usuario> listar() {
		return sessionFactory.getCurrentSession().createQuery("from Usuario").list();
	}
	
	public Usuario consultar(int id) {
		return (Usuario) sessionFactory.getCurrentSession().createQuery("from Usuario where id = " + id).uniqueResult();
	}
	
	public Usuario consultarUsuario(String conta) {
		return (Usuario) sessionFactory.getCurrentSession().createQuery("from Usuario where conta = '" + conta + "'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Usuario> pesquisar(String valor) {
		return sessionFactory.getCurrentSession().createQuery("from Usuario where upper (usu_con) like upper ('%" + valor + "%')").list();
	}
	
}
