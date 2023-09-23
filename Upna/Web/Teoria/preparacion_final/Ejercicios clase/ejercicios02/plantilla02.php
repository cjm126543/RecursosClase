<?php 

	$cadena = file_get_contents("plantilla02.html");

	$trozos = explode("##fila##", $cadena);

	$cuerpo = "";
	for ($i = 0; $i < 200; $i++) {
		$cuerpo .= $trozos[1];
		$cuerpo = str_replace("##numerolinea##", $i, $cuerpo);
		$cuerpo = str_replace("##nombre##", "Diego", $cuerpo);
		$cuerpo = str_replace("##apellido##", "Perez", $cuerpo);
	}
	
	echo $trozos[0] . $cuerpo . $trozos[2];

?>