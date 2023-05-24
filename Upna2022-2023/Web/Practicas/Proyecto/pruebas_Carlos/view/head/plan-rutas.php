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
        <!-- Cabecera de la web -->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">
            <!--Icono copiado de iconos en bootstrap-->
            <a class="navbar-brand" href="#">
                <i class="bi bi-bus-front-fill"></i>
                <span class="text-warning">Transporte Público Madrid</span>
            </a>
            <!--Boton del menu simplemente copiado de bootstrap (Componentes -> Navbar)-->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="menu">
                <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="../home/panel_control.php">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Planificador de rutas</a>

                </li>
                <li class="nav_item">
                    <a class="nav-link" href="#">Contacto</a>
                </li>
                <li class="nav-item">
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Informaci&oacute;n
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <!-- <a class="dropdown-item" href="/proyecto_siw/pruebas_Carlos/view/head/info-lineas.php">L&iacute;neas</a> -->
                                        <a class="dropdown-item" href="../head/info-lineas.php">L&iacute;neas</a>
                                        <a class="dropdown-item" href="#">Paradas</a>
                                    </div>
                                </div>
                </li>
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
            <div class="boton-login">
                <!-- <a class="btn btn-outline-warning d-none d-md-inline-block" 
                    href="/proyecto_siw/pruebas_Carlos/view/home/logout.php" 
                    role="button">Cerrar sesión</a> -->
                <a class="btn btn-outline-warning d-none d-md-inline-block" 
                    href="../home/logout.php" 
                    role="button">Cerrar sesión</a>
            </div>
        </div>
    </nav>

    <!-- Cuerpo de la web -->
    <nav>
        <div id="cabecera_pagina" class="container-fluid my-4">
            <h1>Planificador de rutas de la EMT: </h1>
            <div class="input-group mb-3">
                <input type="text" class="form-control" id="p_inicio" placeholder="Inicio (Número Calle)" aria-label="Inicio" aria-describedby="btn_ruta">
                <input type="text" class="form-control" id="p_fin" placeholder="Destino (Número Calle)" aria-label="Destino" aria-describedby="btn_ruta">
                <button class="btn btn-outline-warning" type="button" id="btn_ruta" onclick="planificadorControllerJs.sendRouteForm()">Buscar</button>
            </div>

            <div id="resultado-busqueda-ruta"></div>
    </nav>

    <?php require_once("footer.php"); ?>
<?php endif ?>