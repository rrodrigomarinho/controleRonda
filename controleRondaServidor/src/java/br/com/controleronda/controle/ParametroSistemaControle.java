package br.com.controleronda.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.ParametroSistema;
import br.com.controleronda.dao.ParametroSistemaDao;

@Service
@RemotingDestination
public class ParametroSistemaControle {

	ParametroSistemaDao parametroSistemaDao = new ParametroSistemaDao();
	
	@Autowired
	public void setParametroSistemaDao(ParametroSistemaDao parametroSistemaDao) {
		this.parametroSistemaDao = parametroSistemaDao;
	}
	
	@RemotingInclude
	@Transactional
	public void alterar(int id, String nome, String valor) throws Exception  {
		if (nome.equals("") || nome == null) {
			throw new Exception("O valor do parâmetro não pode ser vazio!");
		} else {
			if (nome.equals("view") && !valor.equals("Sim") && !valor.equals("Não")) {
				throw new Exception("Para esse parâmetro só é permitido os valores Sim ou Não!");
			}
			if (nome.equals("margemPonto")) {
				try {
					Integer.parseInt(valor);
					int min = Integer.parseInt(valor);
					if (min < 0 || min >= 60) {
						throw new Exception();
					}
				} catch (Exception e) {
					throw new Exception("Para esse parâmetro só é permitido os valores entre 0 e 59!");
				}
			}
		}
		parametroSistemaDao.alterar(id, valor);
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public List<ParametroSistema> listar() {
		return parametroSistemaDao.listar();
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public ParametroSistema consultar(int id) {
		return parametroSistemaDao.consultar(id);
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public List<ParametroSistema> pesquisar(String valor) {
		return parametroSistemaDao.pesquisar(valor);
	}
}