<?php
require_once("/var/www/html/final/controller/homeController.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\controller\homeController.php');
session_start();
$obj = new homeController();
$correo = $obj->limpiarCorreo($_POST['correo']);
$contraseña = $obj->limpiarCadena($_POST['contraseña']);
$bandera = $obj->verificarUsuario($correo, $contraseña);
if ($bandera) {
    $_SESSION['usuario'] = $correo;
    header("Location:panel_control.php");
} else {
    $error = "<li>Las claves son incorrectas</li>";
    header("Location:login.php?error=" . $error);
}
