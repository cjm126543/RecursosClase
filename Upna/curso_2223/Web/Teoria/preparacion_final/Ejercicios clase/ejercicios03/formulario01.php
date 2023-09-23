<?php 
	
	$cadena = file_get_contents("plantilla01.html");

	$trozos = explode("##fila##", $cadena);

	$cuerpo = "";
	for ($i=0; $i < $_POST['numero']; $i++) {
		$cuerpo .= $trozos[1]; 
		$cuerpo = str_replace("##nombre##", $_POST['nombre'], $cuerpo);
		$cuerpo = str_replace("##apellido1##", $_POST['apellido1'], $cuerpo);
		$cuerpo = str_replace("##apellido2##", $_POST['apellido2'], $cuerpo);
		if ($i % 2 == 0) {
			$cuerpo = str_replace("##color##", $_POST['color1'], $cuerpo);
		} else {
			$cuerpo = str_replace("##color##", $_POST['color2'], $cuerpo);
		}

		$cuerpo = str_replace("##numero##", ($i % 4) + 1, $cuerpo);
	}

	

	echo $trozos[0] . $cuerpo . $trozos[2];

 ?>