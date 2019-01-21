package br.com.controleronda.controle;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Ronda;
import br.com.controleronda.dao.RondaDao;

@Service
@RemotingDestination
public class RondaControle {

	private RondaDao rondaDao;
	
	@Autowired
	public void setRondaDao(RondaDao rondaDao) {
		this.rondaDao = rondaDao;
	}
	
	@RemotingInclude
	@Transactional
	public void incluir(Ronda r) throws Exception {
		r.setDataCadastro(new Date());
		rondaDao.incluir(r);
	}
	
	@RemotingInclude
	@Transactional
	public void alterar(Ronda r) throws Exception {
		rondaDao.alterar(r);
	}

	@RemotingInclude
	@Transactional
	public void excluir(Ronda r) {
		rondaDao.excluir(r);
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Ronda> listar() {
		return rondaDao.listar();
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public Ronda consultar(int id) {
		return rondaDao.consultar(id);
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Ronda> pesquisar(String valor) {
		return rondaDao.pesquisar(valor);
	}
}