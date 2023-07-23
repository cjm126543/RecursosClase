<?php
require_once("../head/head.php");
if (empty($_SESSION['usuario'])) {
    header("Location:login.php");
}
?>
<?php require_once("../head/header.php"); ?>
<h1 class="text-center mt-4">Bienvenido
    <?= $_SESSION['usuario'] ?>
</h1>

<?php
require_once("../head/footer.php");
?>