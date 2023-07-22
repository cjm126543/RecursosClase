<?php 

	$cadena = file_get_contents("plantilla03.html");

	$trozos = explode("##fila##", $cadena);

	$nom = $_GET['nombre'];
	$ape1 = $_GET['apellido'];
	$ape2 = $_GET['apellido2'];
	$color1 = $_GET['color1'];
	$color2 = $_GET['color2'];

	$cuerpo = "";
	for ($i = 0; $i < 200; $i++) {
		$cuerpo .= $trozos[1];
		$cuerpo = str_replace("##numerolinea##", $i, $cuerpo);
		$cuerpo = str_replace("##nombre##", $nom, $cuerpo);
		$cuerpo = str_replace("##apellido1##", $ape1, $cuerpo);
		$cuerpo = str_replace("##apellido2##", $ape2, $cuerpo);

		if ($i % 2 == 0) {
			$cuerpo = str_replace("##color##", $color1, $cuerpo);	
		} else {
			$cuerpo = str_replace("##color##", $color2, $cuerpo);	
		}		
	}
	
	echo $trozos[0] . $cuerpo . $trozos[2];

?>