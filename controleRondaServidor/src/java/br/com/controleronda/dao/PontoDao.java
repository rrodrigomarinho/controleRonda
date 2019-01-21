package br.com.controleronda.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Ponto;

@Repository
public class PontoDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}
	
	public void incluir(Ponto p) {
		sessionFactory.getCurrentSession().save(p);
	}
	
	public void alterar(Ponto p) {
		sessionFactory.getCurrentSession().update(p);
	}
	
	public void excluir(Ponto p) {
		sessionFactory.getCurrentSession().delete(p);
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Ponto> listar() {
		return sessionFactory.getCurrentSession().createQuery("from Ponto").list();
	}
	
	public Ponto consultar(int id) {
		return (Ponto) sessionFactory.getCurrentSession().createQuery("from Ponto where id = " + id).uniqueResult();
	}
	
	public String consultarUltimoPonto() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select nom_pon from cracha_ponto limit 1");
		query.addScalar("nom_pon", new StringType());
		String nomPon = (String) query.uniqueResult();
		sessionFactory.getCurrentSession().createSQLQuery("delete from cracha_ponto").executeUpdate();
		return nomPon;
	}
	
	public Ponto consultarPeloIp(String ipPonto) {
		return (Ponto) sessionFactory.getCurrentSession().createQuery("from Ponto where pon_ip = '" + ipPonto + "'").uniqueResult();
	}
	
	public String consultarPontoPeloId(int id) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select pon_ip from Ponto where id = " + id);
		query.addScalar("pon_ip", new StringType());
		String ipPon = (String) query.uniqueResult();
		return ipPon;
	}
	
	@SuppressWarnings("unchecked")
	@RemotingInclude
	@Transactional
	public List<Ponto> pesquisar(String valor) {
		return sessionFactory.getCurrentSession().createQuery("from Ponto where " +
				"upper (pon_ip) like upper ('%" + valor + "%') or " +
						"upper (pon_loc) like upper ('%" + valor + "%') or " +
								"upper (pon_nom) like upper ('%" + valor + "%')").list();
	}
}
