<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
	<title>Lista de Crachás</title>
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
	select * from cracha;
</sql:query>

<table width="100%">
	<tr>
		<th>Número Crachá</th>
		<th>Situação</th>
		<th>Data de Cadastro</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
	<tr>
		<th><c:out value="${row.cra_num}"/></th>
		<th><c:out value="${row.cra_sit}"/></th>
		<th><c:out value="${row.cra_dt_cad}"/></th>
	</tr>
</c:forEach>
</table>

</body>
</html>