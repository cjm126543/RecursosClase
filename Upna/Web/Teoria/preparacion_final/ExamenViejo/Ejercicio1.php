<?php

    include "llamadas_api.php";

    $db = mysqli_connect("localhost", "root", "", "universidad");
    $pdo = new PDO("mysql:host=localhost;dbname=universidad", "root", "");

    function crear_tabla() {
        global $db;
        $sql = "CREATE TABLE IF NOT EXISTS personas (
                id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(10) NOT NULL,
                personalidad VARCHAR(10) NOT NULL,
                cumpleanos VARCHAR(50) NOT NULL,
                especie VARCHAR(50) NOT NULL,
                sexo VARCHAR(10)
             )";

        if (mysqli_query($db, $sql)) {
            echo "bien creado";
        } else {
            echo "error creacion";
        }
    }

    crear_tabla();
    for ($i = 1; $i < numero_paginas(); $i++) {
        $pag = carga_datos($i);
        $stmnt = $db->prepare("INSERT IGNORE INTO personas (nombre, personalidad, cumpleanos, especie, sexo) VALUES (?, ?, ?, ?, ?);");
        $nom = $pag->name->{'name-EUes'};
        $per = $pag->personality;
        $cum = $pag->{'birthday-string'};
        $spe = $pag->species;
        $gen = $pag->gender;
        $nul = null;
        $stmnt->bind_param("sssss", $nom, $per, $cum, $spe, $gen);
        $stmnt->execute();
    }

?>