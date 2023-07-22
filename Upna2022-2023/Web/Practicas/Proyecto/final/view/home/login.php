<?php
require_once("../head/head.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\view\head\head.php');
if (!empty($_SESSION['usuario'])) {
    header("Location:panel_control.php");
}
?>
<!-- <link rel="stylesheet" href="/proyecto_siw/var/www/html/final/asset/css/estilos.css"> -->
<link rel="stylesheet" href="../../asset/css/estilos.css">

<div class="fondo-login" style="background-color: rgba(0,0,0,0.938);min-height:100vh;padding-top: 50px;">
    <div class="icon">
        <a href="../../index.php">
            <i class="bi bi-bus-front fill text-white" style="font-size: 50px;margin-bottom: 10px;"></i>
        </a>
    </div>
    <div class="titulo">
        Inicia sesión en Transporte Madrid
    </div>
    <!-- Formulario de bootstrap modificado -->
    <form action="verificar.php" method="post" class="col-3 login" autocomplete="off">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" name="correo" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" name="contraseña" class="form-control" id="exampleInputPassword1">

        </div>
        <?php if (!empty($_GET['error'])) : ?>
            <div id="alertError" style="margin: auto;" class="alert alert-danger mb-2" role="alert">
                <?= !empty($_GET['error']) ? $_GET['error'] : "" ?>
            </div>
        <?php endif; ?>
        <div class="d-grid gap-2"><button type="submit" class="btn btn-outline-warning">Login</button></div>

    </form>
    <div class="login col-3 mt-3">
        ¿Nuevo por aquí? <a class="text-warning" href="signup.php">Create una cuenta</a>
    </div>
</div>
<?php
require_once("../head/footer.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\view\head\footer.php');
?>