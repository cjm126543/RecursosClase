<?php 

	include "basededatos.php";

	$idcom = $_GET['idcomunidad']; 

	$consulta = "select * from provincias where IDComunidad = $idcom order by Provincia";

	$resultado = $con->query($consulta);

	$cadena = file_get_contents("plantillaprovincias.html");
	$trozos = explode("##fila##", $cadena);

	$cuerpo = "";
	while ($datos = $resultado->fetch_assoc()) {
		$aux = $trozos[1];
		$aux = str_replace("##idprovincia##", $datos['IDProvincia'], $aux);
		$aux = str_replace("##provincia##", $datos['Provincia'], $aux);
		$cuerpo .= $aux;
	}

	echo $trozos[0] . $cuerpo . $trozos[2];

?>