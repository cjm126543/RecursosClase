<?php require_once("head.php"); ?>
<?php if (empty($_SESSION['usuario'])) : ?>

    <!-- Un usuario no registrado no deberia tener acceso, se le prohibe -->
    <?php
        http_response_code(403);
        echo "<h1>403 ERROR Forbidden</h1>";
        echo "<h2>You don't have permission to access this page.</h2>";
        echo "<p>You must <b>log in</b> in order to access this page.</p>";
        exit();
    ?>

<?php else : ?>
    <!-- MOSTRAMOS ACCESO A LAS PARADAS A INFORMAR -->
    <!-- Cabecera de la web -->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">
            <!--Icono copiado de iconos en bootstrap-->
            <a class="navbar-brand" href="../home/panel_control.php">
                <i class="bi bi-bus-front-fill"></i>
                <span class="text-warning">Transporte Público Madrid</span>
            </a>
            <!--Boton del menu simplemente copiado de bootstrap (Componentes -> Navbar)-->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="menu">
                <ul class="navbar-nav me-auto">
                <!--Enlaces redes-->
                <ul class="navbar-nav flex-row flex-wrap text-light">
                    <li class="nav-item col-6 col-md-auto p-3">
                        <i class="bi bi-twitter"></i>
                        <small class="d-md-none ms-2">Twitter</small>
                    </li>
                    <li class="nav-item col-6 col-md-auto p-3">
                        <i class="bi bi-github"></i>
                        <small class="d-md-none ms-2">GitHub</small>
                    </li>
                    <li class="nav-item col-6 col-md-auto p-3">
                        <i class="bi bi-instagram"></i>
                        <small class="d-md-none ms-2">Instagram</small>
                    </li>
                    <li class="nav-item col-6 col-md-auto p-3">
                        <i class="bi bi-facebook"></i>
                        <small class="d-md-none ms-2">Facebook</small>
                    </li>
                </ul>
        </div>
    </nav>

    <!-- Cuerpo de la web -->
    <nav>
    <style>
        h1{
            color: #fff;
        }
        /* Estilos para el nav */
        nav {
            background-color: #333;
            padding: 20px;
        }

        .titulo {
            color: #333;
            font-size: 24px;
            font-weight: bold;
        }

        /* Estilos para la tabla */
        .custom-table {
            width: 100%;
            border-collapse: collapse;
        }

        .custom-table th,
        .custom-table td {
            padding: 8px;
            text-align: left;
        }

        .custom-table th {
            background-color: #333;
            color: #fff;
            border: 1px solid #fff; /* Agrega un borde a los titulares de la tabla */
        }

        .custom-table td {
            color: #fff;
            background-color: #333;
            border: 1px solid #ccc;
        }
        .custom-table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    
        /* Estilos para las opciones de clicar */
        .circle {
            background-color: transparent;
            border-radius: 50%;
            width: 35px;
            height: 35px;
            display: flex;
            justify-content: center;
            align-items: center;
            transition: background-color 0.3s;
            cursor: pointer;
        }

        .circle:hover {
            background-color: yellow; /* Cambia el color de fondo a amarillo en hover */
        }

        
        </style>
        <div id="cabecera_pagina" class="container-fluid my-4">
            <h1>Incidencias en l&iacute;neas de autob&uacute;s:</h1>
        </div>
        <div id="paradas_container" class="container-fluid my-4">
            <table id="table_lineas" class="table custom-table">
                <?php 
                    require_once("../../controller/lineasController.php");
                    // require_once('C:\xampp\htdocs\proyecto_siw\final\controller\lineasController.php');
                    $controller = new lineasController();
                    $controller->displayAllLineas();
                ?>
            </table>
        </div>
    </nav>
    <?php require_once("footer.php"); ?>
<?php endif ?>