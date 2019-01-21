package br.com.controleronda.controle;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.dao.RegistroDao;

@Service
@RemotingDestination
public class RegistroControle {

	private RegistroDao registroDao;
	
	@Autowired
	public void setRegistroDao(RegistroDao registroDao) {
		this.registroDao = registroDao;
	}
	
	@SuppressWarnings("rawtypes")
	@RemotingInclude
	@Transactional(readOnly=true)
	public List listar() {
		return registroDao.listar();
	}
	
	@SuppressWarnings("rawtypes")
	@RemotingInclude
	@Transactional(readOnly=true)
	public List filtrar(int funId, int craId, int ponId, int ronId, int sitId, Date dtIni, Date dtFin) {
		return registroDao.filtrar(funId, craId, ponId, ronId, sitId, dtIni, dtFin);
	}
}