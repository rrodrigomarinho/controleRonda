package br.com.controleronda.dao;

import java.util.List;
import java.util.Date;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegistroDao {
	public SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	@SuppressWarnings("rawtypes")
	@RemotingInclude
	@Transactional
	public List listar() {

		String s = "select reg.id, dat_reg, cra.cra_num, pon.pon_nom, fun.fun_nom, ron.ron_nom, sit.des_sit";
		s += " from registro reg";
		s += " inner join cracha cra on cra.id = reg.cra_id";
		s += " inner join ponto pon on pon.id = reg.pon_id";
		s += " inner join funcionario_cracha fc on fc.cra_id = cra.id";
		s += " inner join funcionario fun on fun.id = fc.fun_id";
		s += " inner join ronda_funcionario rf on rf.fun_id = fun.id";
		s += " inner join ronda ron on ron.id = rf.ron_id";
		s += " inner join situacao_registro sit on sit.id = reg.sit_id";
		s += " where reg.dat_reg between fc.cra_dt_ini and fc.cra_dt_fin and reg.dat_reg between rf.fun_dt_ini and rf.fun_dt_fin";
		s += " order by id";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(s);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.addScalar("dat_reg", new TimestampType());
		query.addScalar("fun_nom", new StringType());
		query.addScalar("ron_nom", new StringType());
		query.addScalar("cra_num", new StringType());
		query.addScalar("pon_nom", new StringType());
		query.addScalar("des_sit", new StringType());
		List result = query.list();
		return result;
	}

	@SuppressWarnings("rawtypes")
	@RemotingInclude
	@Transactional
	public List filtrar(int funId, int craId, int ponId, int ronId, int sitId, Date dtIni, Date dtFin) {

		String s = "select reg.id, dat_reg, cra.cra_num, pon.pon_nom, fun.fun_nom, ron.ron_nom, sit.des_sit";
		s += " from registro reg";
		s += " inner join cracha cra on cra.id = reg.cra_id";
		s += " inner join ponto pon on pon.id = reg.pon_id";
		s += " inner join funcionario_cracha fc on fc.cra_id = cra.id";
		s += " inner join funcionario fun on fun.id = fc.fun_id";
		s += " inner join ronda_funcionario rf on rf.fun_id = fun.id";
		s += " inner join ronda ron on ron.id = rf.ron_id";
		s += " inner join situacao_registro sit on sit.id = reg.sit_id";
		s += " where reg.dat_reg between fc.cra_dt_ini and fc.cra_dt_fin and reg.dat_reg between rf.fun_dt_ini and rf.fun_dt_fin";
		if (funId != 0) {
			s += " and fun.id = " + funId;
		}
		if (craId != 0) {
			s += " and cra.id = " + craId;
		}
		if (ponId != 0) {
			s += " and pon.id = " + ponId;
		}
		if (ronId != 0) {
			s += " and ron.id = " + ronId;
		}
		if (sitId != 0) {
			s += " and sit.id = " + sitId;
		}
		if (dtIni != null && dtFin != null) {
			s += " and reg.dat_reg between '" + dtIni + "' and '" + dtFin + "'";
		}
		s += " order by id";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(s);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.addScalar("dat_reg", new TimestampType());
		query.addScalar("fun_nom", new StringType());
		query.addScalar("ron_nom", new StringType());
		query.addScalar("cra_num", new StringType());
		query.addScalar("pon_nom", new StringType());
		query.addScalar("des_sit", new StringType());
		List result = query.list();
		return result;
	}
}
