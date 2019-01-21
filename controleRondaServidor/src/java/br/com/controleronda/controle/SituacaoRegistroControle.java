package br.com.controleronda.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.SituacaoRegistro;
import br.com.controleronda.dao.SituacaoRegistroDao;

@Service
@RemotingDestination
public class SituacaoRegistroControle {

	SituacaoRegistroDao situacaoRegistroDao = new SituacaoRegistroDao();
	
	@Autowired
	public void setSitucaoRegistroDao(SituacaoRegistroDao situacaoRegistroDao) {
		this.situacaoRegistroDao = situacaoRegistroDao;
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public List<SituacaoRegistro> listar() {
		return situacaoRegistroDao.listar();
	}
}