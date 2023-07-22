<?php 

	include "basededatos.php";

	$consulta = "select * from personas order by nombre, apellido1, apellido2";

	$resultado = $con->query($consulta);

	$cadena = file_get_contents("tabla2.html");
	$trozos = explode("##fila##", $cadena);

	$cuerpo = "";
	while ($datos = $resultado->fetch_assoc()) {
		$aux = $trozos[1];
		$aux = str_replace("##nombre##", $datos["nombre"], $aux);
		$aux = str_replace("##apellido1##", $datos["apellido1"], $aux);
		$aux = str_replace("##apellido2##", $datos["apellido2"], $aux);
		$aux = str_replace("##poblacion##", $datos["poblacion"], $aux);
		$cuerpo .= $aux;
	}

	echo $trozos[0] . $cuerpo . $trozos[2];
?>




