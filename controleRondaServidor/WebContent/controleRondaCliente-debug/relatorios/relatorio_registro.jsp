<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
	<title>Lista de Registros</title>
</head>
<style type="text/css">
	body {
		font-family: verdana;
		font-size: 8pt;
	}
	tr {
		font-family: verdana;
		font-size: 15pt;
	}
	tr {
		font-family: verdana;
		font-size: 6pt;
	}
	border {
		border-bottom: 1px solid;
	}
</style>
<body>
	
	<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
	url="jdbc:postgresql://localhost:5432/controle_ronda"
	user="postgres"  password="postgres"
	/>

	<sql:query dataSource="${snapshot}" var="result">
	select reg.id, dat_reg, cra.cra_num, pon.pon_nom, fun.fun_nom, ron.ron_nom, sit.des_sit from registro reg 
	inner join cracha cra on cra.id = reg.cra_id 
	inner join ponto pon on pon.id = reg.pon_id 
	inner join funcionario_cracha fc on fc.cra_id = cra.id 
	inner join funcionario fun on fun.id = fc.fun_id 
	inner join ronda_funcionario rf on rf.fun_id = fun.id 
	inner join ronda ron on ron.id = rf.ron_id 
	inner join situacao_registro sit on sit.id = reg.sit_id 
	where reg.dat_reg between fc.cra_dt_ini and fc.cra_dt_fin and reg.dat_reg 
	between rf.fun_dt_ini and rf.fun_dt_fin order by dat_reg;
</sql:query>

<table width="100%">
	<tr>
		<th>Nome Funcionário</th>
		<th>Número Crachá</th>
		<th>Ronda</th>
		<th>Ponto</th>
		<th>Data</th>
		<th>Hora</th>
		<th>Situação</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
	<tr>
		<th><c:out value="${row.fun_nom}"/></th>
		<th><c:out value="${row.cra_num}"/></th>
		<th><c:out value="${row.ron_nom}"/></th>
		<th><c:out value="${row.pon_nom}"/></th>
		<th><c:out value="${row.dat_reg}"/></th>
		<th><c:out value="${row.dat_reg}"/></th>
		<th><c:out value="${row.des_sit}"/></th>
	</tr>
</c:forEach>
</table>

</body>
</html>