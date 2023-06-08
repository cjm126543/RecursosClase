<?php 

	include "basededatos.php";

	$nom = $_POST['nombre'];
	$ape1 = $_POST['apellido1'];
	$ape2 = $_POST['apellido2'];

	$consulta = "insert into personas (nombre, apellido1, apellido2) values ('$nom', '$ape1', '$ape2')";

	if ($con->query($consulta)) {
		echo "Persona insertada";
	} else {
		echo "Error en la inserción";
	}

?>