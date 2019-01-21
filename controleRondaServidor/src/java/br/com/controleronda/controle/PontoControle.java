package br.com.controleronda.controle;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Ponto;
import br.com.controleronda.dao.PontoDao;

@Service
@RemotingDestination
public class PontoControle {

	private PontoDao pontoDao;
	
	@Autowired
	public void setPontoDao(PontoDao pontoDao) {
		this.pontoDao = pontoDao;
	}
	
	@RemotingInclude
	@Transactional
	public void incluir(Ponto p) throws Exception {
		p.setDataCadastro(new Date());
		Ponto p2 = pontoDao.consultarPeloIp(p.getIpPonto());
		if (p2 != null) {
			throw new Exception("Ponto com o IP informado já existente!");
		} else {
			pontoDao.incluir(p);
		}
	}
	
	@RemotingInclude
	@Transactional
	public void alterar(Ponto p) throws Exception {
		String p2 = pontoDao.consultarPontoPeloId(p.getId());
		if (p2.equals(p.getIpPonto())) {
			pontoDao.alterar(p);
		} else {
			Ponto p3 = pontoDao.consultarPeloIp(p.getIpPonto());
			if (p3 != null) {
				throw new Exception("Ponto com o IP informado já existente!");
			} else {
				pontoDao.alterar(p);
			}
		}
	}

	@RemotingInclude
	@Transactional
	public void excluir(Ponto p) {
		pontoDao.excluir(p);
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Ponto> listar() {
		return pontoDao.listar();
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public Ponto consultar(int id) {
		return pontoDao.consultar(id);
	}

	@RemotingInclude
	@Transactional
	public String consultarUltimoPonto() {
		return pontoDao.consultarUltimoPonto();
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Ponto> pesquisar(String valor) {
		return pontoDao.pesquisar(valor);
	}
}