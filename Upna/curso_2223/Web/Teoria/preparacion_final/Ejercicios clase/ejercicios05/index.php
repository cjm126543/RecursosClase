<?php 

	include "basededatos.php";
	include "modelo.php";
	include "vista.php";

	if (isset($_GET['id'])) {
		$id = $_GET['id'];
	} else {
		if (isset($_POST['id'])) {
			$id = $_POST['id'];
		} else {
			$id = 1;
		}
	}

	if (isset($_GET['accion'])) {
		$accion = $_GET['accion'];
	} else {
		if (isset($_POST['accion'])) {
			$accion = $_POST['accion'];
		} else {
			$accion = "menu";
		}
	}

	if ($accion == "menu") {
		switch ($id) {
			case 1:
				// Mostrar el menú
				vmostrarmenuprincipal();
				break;
		}
	}

	if ($accion == "alta") {
		switch ($id) {
			case 1:
				// Mostrar alta de persona
				vmostraraltapersona(mlistadoprovincias($con));
				break;
			case 2:
				// Validar alta de persona
				vmostrarresultadoalta(mvalidaraltapersona($con));
				break;
		}
	}


	if ($accion == "listado") {
		switch ($id) {
			case 1:
				// Mostrar el listado de personas
				vmostrarlistadopersonas(mlistadopersonas($con), "Listado");
				break;
		}
	}

	if ($accion == "bym") {
		switch ($id) {
			case 1:
				// Mostrar el listado de baja y modificacion
				vmostrarlistadopersonas(mlistadopersonas($con), "Listadobym");
				break;
			case 2:
				// Mostrar el formulario para editar persona
				vmostrarpersonabym(mdatospersona($con), mlistadoprovincias($con), "Modificar");
				break;
			case 3:
				// Validar modificar persona
				vmostrarresultadomodificacion(mvalidarmodificarpersona($con));
				break;
			case 4:
				// Mostrar datos de la persona a eliminar
				vmostrarpersonabym(mdatospersona($con), "Eliminar");
				break;
			case 5:
				// Validar eliminar persona
				vmostrarresultadoeliminacionpersona(mvalidareliminarpersona($con));
				break;
		}
	}

	if ($accion == "altapro") {
		switch ($id) {
			case 1:
				//Mostramos alta de provincia
				vmostraraltaprovincia();
				break;
			case 2:
				//Validar alta de provincia
				vmostrarresultadoaltaprovincia(mvalidaraltaprovincia($con));
				break;
			}
		}

	if ($accion == "listadopro") {
		switch ($id) {
			case 1:
				// Mostrar el listado de provincias
				vmostrarlistadoprovincias(mlistadoprovincias($con), "Listado");
				break;
		}
	}

	if ($accion == "bympro") {
		switch ($id) {
			case 1:
				// Mostrar el listado de baja y modificacion
				vmostrarlistadoprovincias(mlistadoprovincias($con), "Listadobym");
				break;
			case 2:
				// Mostrar el formulario para editar provincia
				vmostrarprovinciabym(mdatosprovincia($con), "Modificar");
				break;
			case 3:
				// Validar modificar provincia
				vmostrarresultadomodificacionprovincia(mvalidarmodificarprovincia($con));
				break;
			case 4:
				// Mostrar datos de la provincia a eliminar
				vmostrarprovinciabym(mdatosprovincia($con), "Eliminar");
				break;
			case 5:
				// Validar eliminar provincia
				vmostrarresultadoeliminacionprovincia(mvalidareliminarprovincia($con));
				break;
		}
	}


?>