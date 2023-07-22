<?php 

	include "basededatos.php";

	$idprov = $_GET['idprovincia']; 

	$consulta = "select * from poblaciones where IDProvincia = $idprov order by Poblacion";

	$resultado = $con->query($consulta);

	$cadena = file_get_contents("plantillapoblaciones.html");
	$trozos = explode("##fila##", $cadena);

	$cuerpo = "";
	while ($datos = $resultado->fetch_assoc()) {
		$aux = $trozos[1];
		$aux = str_replace("##idpoblacion##", $datos['IDPoblacion'], $aux);
		$aux = str_replace("##poblacion##", $datos['Poblacion'], $aux);
		$cuerpo .= $aux;
	}

	echo $trozos[0] . $cuerpo . $trozos[2];

?>