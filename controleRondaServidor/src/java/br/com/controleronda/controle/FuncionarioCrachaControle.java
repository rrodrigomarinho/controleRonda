package br.com.controleronda.controle;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.FuncionarioCracha;
import br.com.controleronda.dao.FuncionarioCrachaDao;

@Service
@RemotingDestination
public class FuncionarioCrachaControle {

	private FuncionarioCrachaDao funcionarioCrachaDao;

	@Autowired
	public void setFuncionarioCrachaDao(FuncionarioCrachaDao funcionarioCrachaDao) {
		this.funcionarioCrachaDao = funcionarioCrachaDao;
	}
	
	@RemotingInclude
	@Transactional
	public void incluir(FuncionarioCracha fc) throws Exception {
		fc.setDataCadastro(new Date());
		String fc1 = funcionarioCrachaDao.crachaUtilizado(fc.getCracha().getId());
		String fc2 = funcionarioCrachaDao.vigentePorFuncionario(fc.getFuncionario().getId(), fc.getDataInicialCracha(), fc.getDataFinalCracha());
		String fc3 = funcionarioCrachaDao.vigentePorCracha(fc.getCracha().getId(), fc.getDataInicialCracha(), fc.getDataFinalCracha());

		
		if(fc1 == null) {
			if (fc2 != null) {
				throw new Exception("O funcionário já possui um crachá com as datas informadas!");
			}
			if (fc.getDataInicialCracha().before(fc.getDataFinalCracha()) || fc.getDataInicialCracha().equals(fc.getDataFinalCracha())) {
				funcionarioCrachaDao.incluir(fc);
			} else {
				throw new Exception("A data final não pode ser menor que data inicial!");
			}
		} else {
			if (fc2 != null) {
				throw new Exception("O funcionário já possui um crachá com as datas informadas!");
			}
			if (fc3 != null) {
				throw new Exception("O crachá já está sendo utilizado com as datas informadas!");
			} else {
				if (fc.getDataInicialCracha().before(fc.getDataFinalCracha()) || fc.getDataInicialCracha().equals(fc.getDataFinalCracha())) {
					funcionarioCrachaDao.incluir(fc);
				} else {
					throw new Exception("A data final não pode ser menor que data inicial!");
				}
			}
		}
	}

	@RemotingInclude
	@Transactional
	public void excluir(FuncionarioCracha fc) {
		funcionarioCrachaDao.excluir(fc);
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public List<FuncionarioCracha> listar(int id) {
		return funcionarioCrachaDao.listar(id);
	}
}