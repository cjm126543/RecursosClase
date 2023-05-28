<!--Aqui mostramos en la pagina principal el header y el footer -->
<?php
require_once("/var/www/html/final/model/homeModel.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\model\homeModel.php');
$model = new homeModel();
$model->inicializarLineas();
require_once("/var/www/html/final/view/head/header.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\view\head\header.php');
?>
<?php
require_once("/var/www/html/final/view/head/footer.php");
// require_once('C:\xampp\htdocs\proyecto_siw\final\view\head\footer.php');
?>