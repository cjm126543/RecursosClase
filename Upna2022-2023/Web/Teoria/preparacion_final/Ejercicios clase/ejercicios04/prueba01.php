<?php 

	include 'basededatos.php';

	$consulta = "insert into personas (nombre, apellido1, apellido2) values ('diego', 'perez', 'randez')";

	if ($con->query($consulta)) {
		echo "Se ha añadido correctamente";
	} else {
		echo "Ha fallado";
	}

	$consulta = "select * from personas";
	if ($resultado = $con->query($consulta)) {
		while ($datos = $resultado->fetch_assoc()) {
			echo $datos["nombre"] . " " . $datos["apellido1"] . " " . $datos["apellido2"] . "<br>";
		}
	}	

	$consulta = "delete from personas where idpersona > 30";
	if ($con->query($consulta)) {
		echo "Hemos borrado";
	} else {
		echo "Ha fallado la consulta";
	}

	$consulta = "update personas set nombre='Pepe', apellido1='Martinez', apellido2 = 'Gonzalez' where idpersona = 5";
	if ($con->query($consulta)) {
		echo "Hemos modificado";
	} else {
		echo "Ha fallado la consulta";
	}

	
?>