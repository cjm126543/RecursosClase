<?php
// require_once("../head/head.php");
require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\view\head\head.php');
if (empty($_SESSION['usuario'])) {
    header("Location:login.php");
}
?>
<?php 
// require_once("../head/header.php");
require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\view\head\header.php');
?>
<h1 class="text-center mt-4">Bienvenido
    <?= $_SESSION['usuario'] ?>
</h1>

<?php
// require_once("../head/footer.php");
require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\view\head\footer.php');
?>