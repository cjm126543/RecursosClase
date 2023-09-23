<?php 

	$nombre = $_GET['nom'];
	$apellido1 = $_GET['ape1'];
	$apellido2 = $_GET['ape2'];
	$repeticiones = $_GET['repe'];
	$cadena = file_get_contents("tabla.html");

	$cadena = str_replace("##nombre##", $nombre, $cadena);
	$cadena = str_replace("##apellido1##", $apellido1, $cadena);
	$cadena = str_replace("##apellido2##", $apellido2, $cadena);

	$trozos = explode("##fila##", $cadena);

	$cuerpo = "";
	for ($i=0; $i < $repeticiones; $i++) {
		$cuerpo .= $trozos[1];
	}

	echo $trozos[0] . $cuerpo . $trozos[2];

?>