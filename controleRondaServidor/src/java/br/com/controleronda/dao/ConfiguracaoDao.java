package br.com.controleronda.dao;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConfiguracaoDao {
	public SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}
	
	public int incluirSituacaoRegistro() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select id from situacao_registro limit 1");
		query.addScalar("id", new StringType());
		String qtd = (String) query.uniqueResult();
		String s = "insert into situacao_registro (id, des_sit, obs_sit) values " +
				"(1, 'Pontual', 'Ponto Pontual'), " +
				"(2, 'Atrasado', 'Ponto Atrasado'), " +
				"(3, 'Adiantado', 'Ponto Adiantado'), " +
				"(4, 'Bloqueado', 'Ponto Bloqueado'), " +
				"(5, 'Ponto Inválido', 'Ponto Inválido'), " +
				"(6, 'Crachá Inválido', 'Crachá Inválido')";
		
		if (qtd == null || qtd.equals("")) {
			SQLQuery inserir = sessionFactory.getCurrentSession().createSQLQuery(s);
			return inserir.executeUpdate();
		} else {
			return 0;
		}
	}
	
	public int incluirParametroSistema() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select id from parametro_sistema limit 1");
		query.addScalar("id", new StringType());
		String qtd = (String) query.uniqueResult();
		String s = "insert into parametro_sistema (id, par_des, par_nom, par_val) values " +
				"(1, 'Define qual a margem de erro do horário dos pontos (em minutos)', 'margemPonto', '2')";
		
		if (qtd == null || qtd.equals("")) {
			SQLQuery inserir = sessionFactory.getCurrentSession().createSQLQuery(s);
			return inserir.executeUpdate();
		} else {
			return 0;
		}
	}
}
