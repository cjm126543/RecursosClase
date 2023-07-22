<?php 

	function vmostrarmenuprincipal() {
		echo file_get_contents("menu.html");
	}

	function vmostraraltapersona($resultado) {
		$pagina = file_get_contents("alta.html");

		if (!is_object($resultado)) {
			vmostrarmensaje("Alta de persona", "Se ha producido un error en el sistema. Vuelva a intentarlo. Y si el problema persiste póngase en contacto con el administrador.");		
		} else {
			$trozos = explode("##fila##", $pagina);
			$centro = "";
			while ($datos = $resultado->fetch_assoc()) {		
				$aux = $trozos[1];
				$aux = str_replace("##provincia##", $datos['provincia'], $aux);
				$aux = str_replace("##idprovincia##", $datos['idprovincia'], $aux);
				$centro .= $aux;
			}
			echo $trozos[0] . $centro . $trozos[2];
		}


	}

	function vmostrarmensaje($titulo, $texto) {
		$pagina = file_get_contents("mensaje.html");
		$pagina = str_replace("##titulo##", $titulo, $pagina);
		$pagina = str_replace("##texto##", $texto, $pagina);
		echo $pagina;		
	}

	function vmostrarresultadoalta($resultado) {
		$mensaje = "";
		if ($resultado == 1) {
			$mensaje = "La persona se ha dado de alta correctamente.";
		} else {
			$mensaje = "No se ha podido añadir la persona. Vuelva a intentarlo. Si el problema persiste póngase en contacto con el administrador";
		}
		vmostrarmensaje("Alta de persona", $mensaje);
	}

	function vmostrarlistadopersonas($resultado, $tipo) {
		if ($tipo == "Listado") {
			$pagina = file_get_contents("listado.html");	
		} else {
			$pagina = file_get_contents("listadobym.html");
		}

		if (!is_object($resultado)) {
			vmostrarmensaje("Listado de personas", "Se ha producido un error en el sistema. Vuelva a intentarlo. Y si el problema persiste póngase en contacto con el administrador.");		
		} else {
			$trozos = explode("##fila##", $pagina);
			$centro = "";
			while ($datos = $resultado->fetch_assoc()) {
				$aux = $trozos[1];
				$aux = str_replace("##nombre##", $datos['nombre'], $aux);
				$aux = str_replace("##apellido1##", $datos['apellido1'], $aux);
				$aux = str_replace("##apellido2##", $datos['apellido2'], $aux);
				$aux = str_replace("##poblacion##", $datos['poblacion'], $aux);
				$aux = str_replace("##provincia##", $datos['provincia'], $aux);
				$aux = str_replace("##idpersona##", $datos['idpersona'], $aux);
				$centro .= $aux;
			}
			echo $trozos[0] . $centro . $trozos[2];
		}
	}

	function vmostrarpersonabym($resultado, $resultadoprovincias, $tipo) {
		if (!is_object($resultado) || !is_object($resultadoprovincias)) {
			if ($tipo == "Modificar") {
				vmostrarmensaje("Modificación de personas", "Se ha producido un error en el sistema. Vuelva a intentarlo. Y si el problema persiste póngase en contacto con el administrador.");				
			} else {
				vmostrarmensaje("Eliminación de personas", "Se ha producido un error en el sistema. Vuelva a intentarlo. Y si el problema persiste póngase en contacto con el administrador.");
			}
		
		} else {
			$datos = $resultado->fetch_assoc();
			if ($tipo == "Modificar") {
				$aux = file_get_contents("modificar.html");	
			} else {
				$aux = file_get_contents("eliminar.html");
			}

			//Colocamos los datos de la persona
			$aux = str_replace("##nombre##", $datos['nombre'], $aux);
			$aux = str_replace("##apellido1##", $datos['apellido1'], $aux);
			$aux = str_replace("##apellido2##", $datos['apellido2'], $aux);
			$aux = str_replace("##poblacion##", $datos['poblacion'], $aux);
			$aux = str_replace("##idpersona##", $datos['idpersona'], $aux);

			//Montamos el desplegable de provincias
			$trozos = explode("##fila##", $aux);
			$centro = "";
			while ($datos2 = $resultadoprovincias->fetch_assoc()) {		
				$aux = $trozos[1];
				$aux = str_replace("##provincia##", $datos2['provincia'], $aux);
				$aux = str_replace("##idprovincia##", $datos2['idprovincia'], $aux);
				if ($datos['idprovincia'] == $datos2['idprovincia']) {
					$aux = str_replace("##seleccionado##", "selected", $aux);	
				} else {
					$aux = str_replace("##seleccionado##", "", $aux);
				}
								
				$centro .= $aux;
			}
			echo $trozos[0] . $centro . $trozos[2];
		}
	}

	function vmostrarresultadomodificacion($resultado) {
		$mensaje = "";
		if ($resultado == 1) {
			$mensaje = "La persona se ha modificado correctamente.";
		} else {
			$mensaje = "No se ha podido modificar la persona. Vuelva a intentarlo. Si el problema persiste póngase en contacto con el administrador";
		}
		vmostrarmensaje("Modificación de persona", $mensaje);		
	}

	function vmostraraltaprovincia() {
		echo file_get_contents("altaprovincia.html");
	}

	function vmostrarresultadoaltaprovincia($resultado) {
		$mensaje = "";
		if ($resultado == 1) {
			$mensaje = "La provincia se ha dado de alta correctamente.";
		} else {
			$mensaje = "No se ha podido añadir la provincia. Vuelva a intentarlo. Si el problema persiste póngase en contacto con el administrador";
		}
		vmostrarmensaje("Alta de provincia", $mensaje);
	}

	function vmostrarlistadoprovincias($resultado, $tipo) {
		if ($tipo == "Listado") {
			$pagina = file_get_contents("listadoprovincias.html");	
		} else {
			$pagina = file_get_contents("listadobymprovincias.html");
		}

		if (!is_object($resultado)) {
			vmostrarmensaje("Listado de provincias", "Se ha producido un error en el sistema. Vuelva a intentarlo. Y si el problema persiste póngase en contacto con el administrador.");		
		} else {
			$trozos = explode("##fila##", $pagina);
			$centro = "";
			while ($datos = $resultado->fetch_assoc()) {		
				$aux = $trozos[1];
				$aux = str_replace("##provincia##", $datos['provincia'], $aux);
				$aux = str_replace("##idprovincia##", $datos['idprovincia'], $aux);
				$centro .= $aux;
			}
			echo $trozos[0] . $centro . $trozos[2];
		}
	}

	function vmostrarprovinciabym($resultado, $tipo) {
		if (!is_object($resultado)) {
			if ($tipo == "Modificar") {
				vmostrarmensaje("Modificación de provincias", "Se ha producido un error en el sistema. Vuelva a intentarlo. Y si el problema persiste póngase en contacto con el administrador.");				
			} else {
				vmostrarmensaje("Eliminación de provincias", "Se ha producido un error en el sistema. Vuelva a intentarlo. Y si el problema persiste póngase en contacto con el administrador.");
			}
		
		} else {
			$datos = $resultado->fetch_assoc();
			if ($tipo == "Modificar") {
				$aux = file_get_contents("modificarprovincia.html");	
			} else {
				$aux = file_get_contents("eliminarprovincia.html");
			}
			
			$aux = str_replace("##provincia##", $datos['provincia'], $aux);
			$aux = str_replace("##idprovincia##", $datos['idprovincia'], $aux);
			echo $aux;
		}
	}

	function vmostrarresultadomodificacionprovincia($resultado) {
		$mensaje = "";
		if ($resultado == 1) {
			$mensaje = "La provincia se ha modificado correctamente.";
		} else {
			$mensaje = "No se ha podido modificar la provincia. Vuelva a intentarlo. Si el problema persiste póngase en contacto con el administrador";
		}
		vmostrarmensaje("Modificación de persona", $mensaje);		
	}

	function vmostrarresultadoeliminacionprovincia($resultado) {
		$mensaje = "";
		if ($resultado == 1) {
			$mensaje = "La provincia se ha eliminado correctamente.";
		} else {
			$mensaje = "No se ha podido eliminar la provincia. Vuelva a intentarlo. Si el problema persiste póngase en contacto con el administrador";
		}
		vmostrarmensaje("Eliminación de provincia", $mensaje);		
	}

	function vmostrarresultadoeliminacionpersona($resultado) {
		$mensaje = "";
		if ($resultado == 1) {
			$mensaje = "La persona se ha eliminado correctamente.";
		} else {
			$mensaje = "No se ha podido eliminar la persona. Vuelva a intentarlo. Si el problema persiste póngase en contacto con el administrador";
		}
		vmostrarmensaje("Eliminación de persona", $mensaje);		
	}

?>








