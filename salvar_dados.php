<?php
$conexao = pg_connect("host=localhost port=5432 dbname=controle_ronda user=postgres password=postgres");
date_default_timezone_set("America/Sao_Paulo");
setlocale(LC_ALL, 'pt_BR');

$cracha = $_GET['cracha'];
$cracha = strtoupper($cracha);
$ponto = $_GET['ponto'];
$dataAtual = date('d/m/Y H:i');
$minutoAtual = date('H:i');

// Essa consulta irá verificar se tem o Crachá cadastrado
$sql_cracha = pg_query("select cra_num from cracha where cra_num = '$cracha'");

// Essa consulta irá retornar o ID do Crachá cadastrado
$sql_id_cracha = pg_query("select id from cracha where cra_num = '$cracha'");
$row = pg_fetch_array($sql_id_cracha);
$id_cracha = $row['id'];

// Essa consulta irá retornar o ID do Ponto cadastrado
$sql_id_ponto = pg_query("select id from ponto where pon_ip = '$ponto'");
$row2 = pg_fetch_array($sql_id_ponto);
$id_ponto = $row2['id'];

// Essa consulta irá retornar o Valor do Paramêtro da margem dos pontos cadastrado
$sql_margem = pg_query("select par_val from parametro_sistema where par_nom = 'margemPonto'");
$row3 = pg_fetch_array($sql_margem);
$margemPonto = $row3['par_val'];
$minutos = date('00:s', $margemPonto);

//Essa consulta irá retonar a hora inicial do ponto vinculado a rota, que respectivamente está relacionada ao funcionário
$sqlHorIniPon = pg_query("select pon_hr_ini from ronda_ponto where ron_id = 
(select ron_id from ronda_funcionario where '$dataAtual' between fun_dt_ini and fun_dt_fin and fun_id =
(select fun_id from funcionario_cracha where '$dataAtual' between cra_dt_ini and cra_dt_fin and cra_id = 
(select cra_id from funcionario_cracha where cra_id = $id_cracha)) and pon_id = $id_ponto)");
$row4 = pg_fetch_array($sqlHorIniPon);
$horIniPon = $row4['pon_hr_ini'];

// Essa consulta irá retonar a hora final do ponto vinculado a rota, que respectivamente está relacionada ao funcionário
$sqlHorIniPon = pg_query("select pon_hr_fin from ronda_ponto where ron_id = 
(select ron_id from ronda_funcionario where '$dataAtual' between fun_dt_ini and fun_dt_fin and fun_id =
(select fun_id from funcionario_cracha where '$dataAtual' between cra_dt_ini and cra_dt_fin and cra_id = 
(select cra_id from funcionario_cracha where cra_id = $id_cracha)) and pon_id = $id_ponto)");
$row5 = pg_fetch_array($sqlHorIniPon);
$horFinPon = $row5['pon_hr_fin'];

// Consulta para verificar se crachá está bloqueado
$sqlCraBloq = pg_query("select cra_sit from cracha where cra_num = '$cracha'");
$row6 = pg_fetch_array($sqlCraBloq);
$sitCra = $row6['cra_sit'];

// Consulta para verificar se crachá está bloqueado
$sqlCraInva = pg_query("select fun_id from funcionario_cracha where '$dataAtual' between cra_dt_ini and cra_dt_fin and cra_id = '$id_cracha'");
$row7 = pg_fetch_array($sqlCraInva);
$craInv = $row7['fun_id'];

$sqlPonInva = pg_query("select pon_id from ronda_ponto where ron_id = 
	(select ron_id from ronda_funcionario where '$dataAtual' between fun_dt_ini and fun_dt_fin and fun_id = 
	(select fun_id from funcionario_cracha where '$dataAtual' between cra_dt_ini and cra_dt_fin and cra_id = '$id_cracha'))");
$row8 = pg_fetch_array($sqlPonInva);
$ponInv = $row8['pon_id'];

// Transformar uma string em data
$horaInicialPonto = new DateTime($horIniPon);
$horaFinalPonto = new DateTime($horFinPon);
$minutoAtual = new DateTime($minutoAtual);

// Calculando a diferença da data inicial e final com a data do registro
$difHoraIni = $horaInicialPonto -> diff($minutoAtual) -> format('%H:%I');
$difHoraFin = $horaFinalPonto -> diff($minutoAtual) -> format('%H:%I');

// Condicionais para verificar id da situação
// ID 1 para Pontual
if ($difHoraIni <= $minutos || $difHoraFin <= $minutos) {
	$situacao = 1;
}
// ID 2 para Atrasado
if (($minutoAtual > $horaInicialPonto) && ($difHoraFin > $minutos)) {
	$situacao = 2;
}
// ID 3 para Adiantado
if (($minutoAtual < $horaFinalPonto) && ($difHoraIni > $minutos)) {
	$situacao = 3;
}
// ID 4 para Bloqueado
if ($sitCra == "Bloqueado") {
	$situacao = 4;
}
//ID 5 para Ponto Inválido
if ($ponInv == "" || $ponInv == null) {
	$situacao = 5;
}
// ID 6 para Crachá Inválido
if ($craInv == "" || $craInv == null) {
	$situacao = 6;
}

$id_situacao = $situacao;
 $insertRegistro = "insert into registro (cra_id, pon_id, sit_id, dat_reg) values ($id_cracha, $id_ponto, $id_situacao, '$dataAtual')";
$insertCrachaPonto = "insert into cracha_ponto (num_cra, nom_pon) values ('$cracha', '$ponto')";

if (pg_num_rows($sql_cracha) != 0) {
	pg_query($insertRegistro);
	pg_query($insertCrachaPonto);
} else {
	pg_query($insertCrachaPonto);
}
pg_close();
?>