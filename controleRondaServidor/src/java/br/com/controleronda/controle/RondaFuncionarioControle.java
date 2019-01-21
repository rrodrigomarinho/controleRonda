package br.com.controleronda.controle;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.RondaFuncionario;
import br.com.controleronda.dao.RondaFuncionarioDao;

@Service
@RemotingDestination
public class RondaFuncionarioControle {

	private RondaFuncionarioDao rondaFuncionarioDao;

	@Autowired
	public void setRondaaFuncionarioDao(RondaFuncionarioDao rondaFuncionarioDao) {
		this.rondaFuncionarioDao = rondaFuncionarioDao;
	}

	@RemotingInclude
	@Transactional
	public void incluir(RondaFuncionario rf) throws Exception {
		rf.setDataCadastro(new Date());
		String rf1 = rondaFuncionarioDao.funcionarioUtilizado(rf.getFuncionario().getId());
		String rf2 = rondaFuncionarioDao.vigentePorRonda(rf.getRonda().getId(), rf.getDataInicialFuncionario(), rf.getDataFinalFuncionario());
		String rf3 = rondaFuncionarioDao.vigentePorFuncionario(rf.getFuncionario().getId(), rf.getDataInicialFuncionario(), rf.getDataFinalFuncionario());

		
		if(rf1 == null) {
			if (rf2 != null) {
				throw new Exception("A ronda já possui um funcionário com as datas informadas!");
			}
			if (rf.getDataInicialFuncionario().before(rf.getDataFinalFuncionario()) || rf.getDataInicialFuncionario().equals(rf.getDataFinalFuncionario())) {
				rondaFuncionarioDao.incluir(rf);
			} else {
				throw new Exception("A data final não pode ser menor que data inicial!");
			}
		} else {
			if (rf2 != null) {
				throw new Exception("A ronda já possui um funcionário com as datas informadas!");
			}
			if (rf3 != null) {
				throw new Exception("O funcionário já está sendo utilizado com as datas informadas!");
			} else {
				if (rf.getDataInicialFuncionario().before(rf.getDataFinalFuncionario()) || rf.getDataInicialFuncionario().equals(rf.getDataFinalFuncionario())) {
					rondaFuncionarioDao.incluir(rf);
				} else {
					throw new Exception("A data final não pode ser menor que data inicial!");
				}
			}
		}
	}

	@RemotingInclude
	@Transactional
	public void excluir(RondaFuncionario rf) {
		rondaFuncionarioDao.excluir(rf);
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public List<RondaFuncionario> listar(int id) {
		return rondaFuncionarioDao.listar(id);
	}
}