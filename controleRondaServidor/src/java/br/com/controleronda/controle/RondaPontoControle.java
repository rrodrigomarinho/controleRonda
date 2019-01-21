package br.com.controleronda.controle;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.RondaPonto;
import br.com.controleronda.dao.RondaPontoDao;

@Service
@RemotingDestination
public class RondaPontoControle {

	private RondaPontoDao rondaPontoDao;

	@Autowired
	public void setRondaPontoDao(RondaPontoDao rondaPontoDao) {
		this.rondaPontoDao = rondaPontoDao;
	}

	@RemotingInclude
	@Transactional
	public void incluir(RondaPonto rp) throws Exception {
		rp.setDataCadastro(new Date());
		
		int hi = Integer.parseInt(rp.getHoraInicialPonto().substring(0,2));
		int hf = Integer.parseInt(rp.getHoraFinalPonto().substring(0,2));
		int mi = Integer.parseInt(rp.getHoraInicialPonto().substring(3,5));
		int mf = Integer.parseInt(rp.getHoraFinalPonto().substring(3,5));
		
		if (hi < hf || hi == hf && mi < mf) {
			rondaPontoDao.incluir(rp);
		} else {
			throw new Exception("A hora final não pode ser menor ou igual a hora inicial!");
		}
	}

	@RemotingInclude
	@Transactional
	public void excluir(RondaPonto rp) {
		rondaPontoDao.excluir(rp);
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public List<RondaPonto> listar(int id) {
		return rondaPontoDao.listar(id);
	}
}