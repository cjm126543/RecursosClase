<?php 

	include "basededatos.php";

	function mvalidaraltapersona($con) {
		$nom = $_POST['nombre'];
		$ape1 = $_POST['apellido1'];
		$ape2 = $_POST['apellido2'];
		$pob = $_POST['poblacion'];
		$idprov = $_POST['idprovincia'];

		$consulta = "insert into personas (nombre, apellido1, apellido2, poblacion, idprovincia) values ('$nom', '$ape1', '$ape2', '$pob', '$idprov')";

		if ($con->query($consulta)) {
			return 1;
		} else {
			return -1;
		}
	}

	function mlistadopersonas($con) {
		$consulta = "Select *, provincia from personas join provincias on provincias.idprovincia = personas.idprovincia order by nombre, apellido1, apellido2";

		if ($resultado = $con->query($consulta)) {
			return $resultado;
		} else {
			return -1;
		}
	}

	function mdatospersona($con) {
		$idpersona = $_GET['idpersona'];
		$consulta = "select * from personas where idpersona = $idpersona";

		if ($resultado = $con->query($consulta)) {
			return $resultado;
		} else {
			return -1;
		}
	}

	function mvalidarmodificarpersona($con) {
		$nom = $_POST['nombre'];
		$ape1 = $_POST['apellido1'];
		$ape2 = $_POST['apellido2'];
		$pob = $_POST['poblacion'];
		$idprov = $_POST['idprovincia'];
		$id = $_POST['idpersona'];

		$consulta = "update personas set nombre='$nom', apellido1='$ape1', apellido2='$ape2', poblacion='$pob', idprovincia='$idprov' where idpersona = $id";

		if ($con->query($consulta)) {
			return 1;
		} else {
			return -1;
		}
	}

	function mvalidaraltaprovincia($con) {
		$pro = $_POST['provincia'];

		$consulta = "insert into provincias (provincia) values ('$pro')";

		if ($con->query($consulta)) {
			return 1;
		} else {
			return -1;
		}
	}

	function mlistadoprovincias($con) {
		$consulta = "Select * from provincias order by provincia";

		if ($resultado = $con->query($consulta)) {
			return $resultado;
		} else {
			return -1;
		}
	}

	function mdatosprovincia($con) {
		$idprovincia = $_GET['idprovincia'];
		$consulta = "select * from provincias where idprovincia = $idprovincia";

		if ($resultado = $con->query($consulta)) {
			return $resultado;
		} else {
			return -1;
		}
	}

	function mvalidarmodificarprovincia($con) {
		$provincia = $_POST['provincia'];
		$idprovincia = $_POST['idprovincia'];

		$consulta = "update provincias set provincia='$provincia' where idprovincia = $idprovincia";

		if ($con->query($consulta)) {
			return 1;
		} else {
			return -1;
		}
	}

	function mvalidareliminarprovincia($con) {
		$idprovincia = $_POST['idprovincia'];

		$consulta = "delete from provincias where idprovincia = $idprovincia";

		if ($con->query($consulta)) {
			return 1;
		} else {
			return -1;
		}
	}

	function mvalidareliminarpersona($con) {
		$idpersona = $_POST['idpersona'];

		$consulta = "delete from personas where idpersona = $idpersona";

		if ($con->query($consulta)) {
			return 1;
		} else {
			return -1;
		}
	}

?>









