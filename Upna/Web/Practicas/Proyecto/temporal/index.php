<!--Aqui mostramos en la pagina principal el header y el footer -->
<?php
require_once("/var/www/html/pruebas_Carlos/model/homeModel.php");
$model = new homeModel();
$model->inicializarLineas();
require_once("view/head/header.php");
?>
<?php
require_once("view/head/footer.php");
?>