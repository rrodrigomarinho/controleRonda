package br.com.controleronda.controle;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Cracha;
import br.com.controleronda.dao.CrachaDao;

@Service
@RemotingDestination
public class CrachaControle {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	private CrachaDao crachaDao;

	@Autowired
	public void setCrachaDao(CrachaDao crachaDao) {
		this.crachaDao = crachaDao;
	}

	@RemotingInclude
	@Transactional
	public void incluir(Cracha c) throws Exception {
		c.setDataCadastro(new Date());
		Cracha c2 = crachaDao.consultarPeloNumero(c.getNumeroCracha());
		if (c2 != null) {
			throw new Exception("Crachá já existente!");
		} else {
			crachaDao.incluir(c);
		}
	}

	@RemotingInclude
	@Transactional
	public void alterar(Cracha c) throws Exception {
		Cracha c2 = crachaDao.consultarPeloNumero(c.getNumeroCracha());
		if (c2 != null) {
			throw new Exception("Crachá já existente!");
		} else {
			crachaDao.alterar(c);
		}
	}

	@RemotingInclude
	@Transactional
	public void excluir(Cracha c) {
		crachaDao.excluir(c);
	}

	@RemotingInclude
	@Transactional(readOnly = true)
	public List<Cracha> listar() {
		return crachaDao.listar();
	}

	@RemotingInclude
	@Transactional(readOnly = true)
	public Cracha consultar(int id) {
		return crachaDao.consultar(id);
	}

	@RemotingInclude
	@Transactional
	public String consultarUltimoCracha() {
		return crachaDao.consultarUltimoCracha();
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Cracha> pesquisar(String valor) {
		return crachaDao.pesquisar(valor);
	}
}
