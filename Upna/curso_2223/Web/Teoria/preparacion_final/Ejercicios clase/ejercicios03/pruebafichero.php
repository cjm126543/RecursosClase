<?php 

	$fp = fopen("miarchivo.txt", "w");

	fwrite($fp, "Hola que tal \n");
	fwrite($fp, ", ¿Cómo estás? \n");

	fclose($fp);

?>