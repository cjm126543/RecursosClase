<!--Aqui mostramos en la pagina principal el header y el footer -->
<?php
require_once("/var/www/html/pruebas_Carlos/model/homeModel.php");
// require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\model\homeModel.php');
$model = new homeModel();
$model->inicializarLineas();
require_once("/var/www/html/pruebas_Carlos/view/head/header.php");
// require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\view\head\header.php');
?>
<?php
require_once("/var/www/html/pruebas_Carlos/view/head/footer.php");
// require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\view\head\footer.php');
?>