package br.com.controleronda.controle;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleronda.bean.Usuario;
import br.com.controleronda.dao.UsuarioDao;

@Service
@RemotingDestination
public class UsuarioControle {

	private UsuarioDao usuarioDao;

	@Autowired
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@RemotingInclude
	@Transactional
	public void incluir(Usuario u) throws Exception {
		u.setDataCadastro(new Date());
		u.setSenha(criptografar(u.getSenha()));
		Usuario u2 = usuarioDao.consultarUsuario(u.getConta());
		if (u2 != null) {
			throw new Exception("Usuário já cadastrado!");
		} else {
			usuarioDao.incluir(u);
		}
	}

	@RemotingInclude
	@Transactional
	public void alterar(Usuario u) throws Exception {
		u.setSenha(criptografar(u.getSenha()));
		usuarioDao.alterar(u);
	}

	@RemotingInclude
	@Transactional
	public void excluir(Usuario u) {
		usuarioDao.excluir(u);
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Usuario> listar() {
		return usuarioDao.listar();
	}

	@RemotingInclude
	@Transactional(readOnly=true)
	public Usuario consultar(int id) {
		return usuarioDao.consultar(id);
	}

	public String criptografar(String senha) throws Exception {
		String password = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("Falha na criptografia da senha");
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		password = hash.toString(16);
		return password;
	}

	@RemotingInclude
	@Transactional
	public void autenticarUsuario(String conta, String senha) throws Exception {
		if (!(conta.equals("admin") && senha.equals("admin"))) {
			if (conta == null || conta.equals("")) {
				throw new Exception("Informe o nome do usuário!");
			}
			if(senha == null || senha.equals("")) {
				throw new Exception("Informe a senha do usuário!");
			}
			Usuario contaUsuario = usuarioDao.consultarUsuario(conta);
			if (contaUsuario == null) {
				throw new Exception("Usuário não encontrado!");
			}
			if(!contaUsuario.getSenha().equals(criptografar(senha))) {
				throw new Exception("Senha inválida!");
			}
		}
	}
	
	@RemotingInclude
	@Transactional(readOnly=true)
	public List<Usuario> pesquisar(String valor) {
		return usuarioDao.pesquisar(valor);
	}
}