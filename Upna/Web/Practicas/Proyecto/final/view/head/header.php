<!-- En este codigo simplemente metemos el Navbar de bootstrap-->

<?php
//Aqui simplemente hacemos esto para que se muestre en el login
require_once("head.php");
?>


<!--Ayuda para usuarios-->
<nav class="navbar navbar-expand-md navbar-dark bg-black">
    <div class="container-fluid ">
        <div class="collapse navbar-collapse" id="menu">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <p class="nav-link active" href="#">¿Necesitas ayuda? Contacta con nosotros.</p>
                </li>
                <li class="nav-item">
                    <p class="navbar-brand" href="#">
                        <i class="bi bi-envelope"></i>
                        <a href="#" class="text-warning">hola@transportemadrid.es</a>
                    </p>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!--Inicio del menu-->
<!--Podemos poner en a parte de toda la clase navbar... al final fixed-top para que se quede ahi todo el rato, mucha info en w3-->
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
        <!--Elementos del menú usuario no logueado-->
        <?php if (empty($_SESSION['usuario'])) : ?>
            <div class="collapse navbar-collapse" id="menu">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <select name="lineas" id="lin" onchange="homeControllerJs.setLinea();" class="form-select" style="display: inline-block; width: 200px;">
                            <option selected="selected">Seleccione una linea</option>
                            <?php 
                                require_once("/var/www/html/final/controller/homeController.php");
                                // require_once('C:\xampp\htdocs\proyecto_siw\final\controller\homeController.php');
                                $controller= new homeController();
                                $controller->completarListaLineas();
                            ?>
                        </select>
                        <select name="sentido" id="way" onchange="homeControllerJs.setDireccion();" class="form-select" style="display: inline-block; width: 200px;">
                            <option selected="selected">Seleccione sentido</option>
                            <option value="1" id="ida"></option>
                            <option value="2" id="vuelta"></option>
                        </select>
                        <button id="search_button" onclick="var a = homeControllerJs.getRoute();" class="btn btn-primary" style="color: white;">BUSCAR</button>
                    </li>
                </ul>

                <hr class="text-white-50">
                <!--Enlaces redes-->
                <ul class="navbar-nav flex-row flex-wrap text-light" id="menu">
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
                <!--Boton login y signup-->
                <div class="boton-login">
                    <!-- <a class="btn btn-outline-warning d-none d-md-inline-block" href="/proyecto_siw/var/www/html/final/view/home/login.php" role="button">Login</a> -->
                    <a class="btn btn-outline-warning d-none d-md-inline-block" href="/final/view/home/login.php" role="button">Login</a>
                    
                </div>
                <div class="boton-login" style="margin-left: 10px;">
                    <!-- <a class="btn btn-outline-warning d-none d-md-inline-block" href="view/home/signup.php" role="button">Register</a> -->
                    <a class="btn btn-outline-warning d-none d-md-inline-block" href="/final/view/home/signup.php" role="button">Register</a>
                </div>

            <?php else : ?>
                <!--Elementos del menú colapsable usuario logueado-->
                
                <div class="collapse navbar-collapse" id="menu">
                    <h2 class="nav-item text-white" >Bienvenido
                        <?= $_SESSION['usuario'] ?>
                    </h2>
                    <ul class="navbar-nav me-auto ml-auto">

                        <li class="nav-item" >
                            <div class="dropdown" style="margin-right: 50px; margin-left: 50px;">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Informaci&oacute;n
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <!-- <a class="dropdown-item" href="/proyecto_siw/var/www/html/final/view/head/info-lineas.php">L&iacute;neas</a> -->
                                    <a class="dropdown-item" href="../head/info-lineas.php">L&iacute;neas</a>
                                    <a class="dropdown-item" href="../head/buscarParadas.php">Paradas</a>
                                </div>
                            </div>
                        </li>
                        <li class="nav-item">
                            <div class="dropdown">
                                <select name="lineas" id="lin" onchange="homeControllerJs.setLinea();" class="form-select" style="display: inline-block; width: 200px;">
                                    <option selected="selected">Seleccione una linea</option>
                                    <?php 
                                        require_once("../../controller/homeController.php");
                                        $controller= new homeController();
                                        $controller->completarListaLineas();
                                    ?>
                                </select>
                                <select name="sentido" id="way" onchange="homeControllerJs.setDireccion();" class="form-select" style="display: inline-block; width: 200px;">
                                    <option selected="selected">Seleccione sentido</option>
                                    <option value="1" id="ida"></option>
                                    <option value="2" id="vuelta"></option>
                                </select>
                                <button id="search_button" onclick="var a = homeControllerJs.getRoute();" class="btn btn-primary" style="color: white;">BUSCAR</button>
                            </div>
                        </li>


                    </ul>
                    <hr class="text-white-50">
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
                    <!--Boton login y signup salimos desde panel de control asi que usamos su ruta!!!! Nos paso lo mismo con el home model y controller-->
                    <div class="boton-login">
                        <!-- <a class="btn btn-outline-warning d-none d-md-inline-block" href="/proyecto_siw/var/www/html/final/view/home/logout.php" role="button">Cerrar sesión</a> -->
                        <a class="btn btn-outline-warning d-none d-md-inline-block" href="../home/logout.php" role="button">Cerrar sesión</a>
                    </div>
                    <?php if ($_SESSION['usuario'] === 'admin@admin.com') : ?>
                        <div class="boton-login" style="margin-left: 10px;">
                            <a class="btn btn-outline-warning d-none d-md-inline-block" href="../home/admin.php" role="button">Manejar usuarios</a>
                        </div>
                    <?php endif; ?>
                </div>
            <?php endif ?>
            </div>
</nav>

<nav>
    <div id="custom_map" style="height: 775px; border: 1px solid #AAA;"></div>
</nav>