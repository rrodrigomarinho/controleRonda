package br.com.controleronda.controle;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.dao.ConfiguracaoDao;

@Service
@RemotingDestination
public class ConfiguracaoControle {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}
	
	private ConfiguracaoDao configuracaoDao;
	
	@Autowired
	public void setConfiguracaoDao(ConfiguracaoDao configuracaoDao) {
		this.configuracaoDao = configuracaoDao;
	}
	
	@RemotingInclude
	@Transactional
	public void carregarSistema() {
		configuracaoDao.incluirSituacaoRegistro();
		configuracaoDao.incluirParametroSistema();
	}
}
