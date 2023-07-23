<?php
require_once("../../controller/homeController.php");
$obj = new homeController();
$correo = $_POST['correo'];
$contraseña = $_POST['contraseña'];
$confirmarContraseña = $_POST['confirmarContraseña'];
$error = "";
if (empty($correo) || empty($contraseña) || empty($confirmarContraseña)) {
    $error .= "<li>Completa los campos</li>"; //Error sera igual a lo que contenga la variable error mas la concatenacion que pongamos
    header("Location:signup.php?error=" . $error . "&&correo=" . $correo . "&&contraseña=" . $contraseña . "&&confirmarContraseña=" . $confirmarContraseña);
} else if ($correo && $contraseña && $confirmarContraseña) {
    if ($contraseña == $confirmarContraseña) {
        if ($obj->guardarUsuario($correo, $contraseña) == false) {
            $error .= "<li>Este correo ya está registrado</li>";
            header("Location:signup.php?error=" . $error . "&&correo=" . $correo . "&&contraseña=" . $contraseña . "&&confirmarContraseña=" . $confirmarContraseña);
        } else {
            header("Location:login.php");
        }
    } else {
        $error = "<li>Las contraseñas no coinciden</li>";
        header("Location:signup.php?error=" . $error . "&&correo=" . $correo . "&&contraseña=" . $contraseña . "&&confirmarContraseña=" . $confirmarContraseña);
    }
}
