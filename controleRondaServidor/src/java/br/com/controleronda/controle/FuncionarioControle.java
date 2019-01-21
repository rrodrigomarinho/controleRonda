package br.com.controleronda.controle;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Funcionario;
import br.com.controleronda.dao.FuncionarioDao;

@Service
@RemotingDestination
public class FuncionarioControle {

	private FuncionarioDao funcionarioDao;

	@Autowired
	public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}

	@RemotingInclude
	@Transactional
	public void incluir(Funcionario f) throws Exception {
		f.setDataCadastro(new Date());
		Funcionario f2 = funcionarioDao.consultarPeloCpf(f.getCpf());
		if (f2 != null) {
			throw new Exception("Funcionário já cadastrado com esse CPF!");
		} else {
			funcionarioDao.incluir(f);
		}
	}

	@RemotingInclude
	@Transactional
	public void alterar(Funcionario f) throws Exception {
		String f2 = funcionarioDao.consultarCpfPeloId(f.getId());
		if (f2.equals(f.getCpf())) {
			funcionarioDao.alterar(f);
		} else {
			Funcionario f3 = funcionarioDao.consultarPeloCpf(f.getCpf());
			if (f3 != null) {
				throw new Exception("Funcionário já cadastrado com esse CPF!");
			} else {
				funcionarioDao.alterar(f);
			}
		}
	}

	@RemotingInclude
	@Transactional
	public void excluir(Funcionario f) {
		funcionarioDao.excluir(f);
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Funcionario> listar() {
		return funcionarioDao.listar();
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public Funcionario consultar(int id) {
		return funcionarioDao.consultar(id);
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Funcionario> pesquisar(String valor) {
		return funcionarioDao.pesquisar(valor);
	}
}