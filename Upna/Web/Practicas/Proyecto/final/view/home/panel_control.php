<?php
require_once("../head/head.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\view\head\head.php');
if (empty($_SESSION['usuario'])) {
    header("Location:login.php");
}
?>


<?php 
require_once("../head/header.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\view\head\header.php');
?>


<?php
require_once("../head/footer.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\view\head\footer.php');
?>