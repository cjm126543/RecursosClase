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
<style>
  /* Estilos para el nav */
  .navbar {
            background-color: #333;
        }

        .navbar-brand {
            color: #fff;
        }

        .buscar-paradas {
            color: #fff;
        }

        /* Estilos para la sección de búsqueda */
        .container.my-4 {
            
            padding: 20px;
        }

        .buscarParadas {
            color: #fff;
        }

        .form-control {
            color: #fff;
            border-color: #fff;
        }

        .btn-outline-secondary {
            color: #fff;
            background-color: transparent;
            border-color: #fff;
        }

        .btn-outline-secondary:hover {
            color: #000;
            background-color: #ffc107;
        }

        #resultado-busqueda {
            color: #fff;
        }
</style>

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

<div class="container my-4">
  <h1 class="buscarParadas">Buscar paradas</h1>
  <div class="input-group mb-3">
    <input type="text" class="form-control" id="busqueda-parada" placeholder="Número de la parada" aria-label="Número de la parada" aria-describedby="button-addon2">
  </div>
  <div id="resultado-busqueda"></div>
</div> 
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/final/asset/js/getParadas.js"></script>
<?php endif ?>