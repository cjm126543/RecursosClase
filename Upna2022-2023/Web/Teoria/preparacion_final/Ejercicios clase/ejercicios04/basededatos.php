<?php 

	$servidor = "localhost";
	$usuario = "root";
	$password = "";
	$basededatos = "pruebas2";

	$con = mysqli_connect($servidor, $usuario, $password, $basededatos);

	if (!$con) {
		die ("Conexión fallida: " . mysqli_connect_error());
	}

?>