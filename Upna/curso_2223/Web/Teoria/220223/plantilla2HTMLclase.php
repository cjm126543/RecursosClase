<?php
    $c = file_get_contents("plantilla2HTMLclase.html");

    $trozos = explode("##fila##", $c);

    $cuerpo = "";
    for ($i = 0; $i < 200; $i++) {
        $cuerpo .= $trozos[1];
        $cuerpo = str_replace("##nombre##", "Carlos" . $i, $cuerpo);
        $cuerpo = str_replace("##apellido##", "Jimeno" . $i, $cuerpo);
    }

    print($trozos[0] . $cuerpo . $trozos[2]);
    // print($c);
    // print(count($trozos));
    // print($trozos[1]);
?>