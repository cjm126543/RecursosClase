<?php
    $c = file_get_contents("plantilla2HTMLclase.html");

    $trozos = explode("##fila##", $c);

    // GET recibe los parametros a traves de la url de la forma:
    // http://www.url.es/lo_que_sea00?variable=valor&variable2=valor2 (...)
    // ? --> abre el paso de parametros & --> pasa otro parametro mas
    // En una url el espacio en blanco "/s" " " lo interpreta como %20
    $nom = $_GET['variable'];
    $ape = $_GET['variable2'];

    $cuerpo = "";
    for ($i = 0; $i < 200; $i++) {
        $cuerpo .= $trozos[1];
        $cuerpo = str_replace("##nombre##", "Carlos" . $i, $cuerpo);
        $cuerpo = str_replace("##apellido##", "Jimeno" . $i, $cuerpo);
    }

    // print($trozos[0] . $cuerpo . $trozos[2]);
    // print($c);
    // print(count($trozos));
    // print($trozos[1]);

    print($nom); 

    print($ape);
?>