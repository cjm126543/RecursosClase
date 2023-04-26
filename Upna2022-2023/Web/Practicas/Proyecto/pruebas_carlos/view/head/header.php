<!-- En este codigo simplemente metemos el Navbar de bootstrap-->

<?php
//Aqui simplemente hacemos esto para que se muestre en el login
require_once("head.php");
?>


<!--Copia de Valortic rancia-->
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
        <!--Elementos del menú colapsable usuario no logueado-->
        <?php if (empty($_SESSION['usuario'])) : ?>
            <div class="collapse navbar-collapse" id="menu">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contacto</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown">Servicios</a>
                        <ul class="dropdown-menu bg-secondary">
                            <li>
                                <a class="dropdown-item" href="">Servicio 1</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="">Servicio 2</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="">Servicio 3</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <form>
                            <label>Lineas</label>
                            <select name="lineas" id="lin">
                                <option selected="selected">Seleccione una linea</option>
                                <?php 
                                    require_once("/var/www/html/pruebas_Carlos/controller/homeController.php");
                                    $controller= new homeController();
                                    $controller->completarListaLineas();
                                ?>
                            </select>
                        </form>
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
                <!--Boton login y signup-->
                <div class="boton-login">
                    <a class="btn btn-outline-warning d-none d-md-inline-block" href="../../../prueba_servidor_completa/view/home/login.php" role="button">Login</a>

                </div>
                <div class="boton-login" style="margin-left: 10px;">
                    <a class="btn btn-outline-warning d-none d-md-inline-block" href="view/home/signup.php" role="button">Register</a>
                </div>

            <?php else : ?>
                <!--Elementos del menú colapsable usuario logueado-->
                <div class="collapse navbar-collapse" id="menu">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contacto</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown">Servicios + Servicios usuario logueado</a>
                            <ul class="dropdown-menu bg-secondary">
                                <li>
                                    <a class="dropdown-item" href="">Servicio 1</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="">Servicio 2</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="">Servicio 3</a>
                                </li>
                            </ul>
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
                        <a class="btn btn-outline-warning d-none d-md-inline-block" href="logout.php" role="button">Cerrar sesión</a>

                    </div>

                </div>
            <?php endif ?>
            </div>
</nav>

<nav>
    <div>
        <iframe class="container-fluid" 
            src="https://www.openstreetmap.org/export/embed.html?bbox=-3.71188759803772%2C40.413201972925%2C-3.6977255344390874%2C40.420259460855284&amp;layer=mapnik" 
            width="800" height="770" frameborder="0" style="border:0"  allowfullscreen></iframe>
    </div>
</nav>