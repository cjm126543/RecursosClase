<?php
class homeController
{
    private $MODEL;


    public function __construct()
    {
        require_once("/var/www/html/pruebas_Carlos/model/homeModel.php");
        // require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\model\homeModel.php');
        $this->MODEL = new homeModel();
    }
    public function guardarUsuario($correo, $contraseña)
    {
        $valor = $this->MODEL->agregarNuevoUsuario($this->limpiarCorreo($correo), $this->encriptarContraseña($this->limpiarCadena($contraseña)));
        return $valor;
    }
    public function limpiarCadena($campo)
    {
        $campo = strip_tags($campo);
        $campo = filter_var($campo, FILTER_UNSAFE_RAW);
        $campo = htmlspecialchars($campo);
        return $campo; //Las tres funciones que aplicamos son para limpiar el código que enviamos para evitar posibles errores
    }
    public function limpiarCorreo($campo)
    {
        $campo = strip_tags($campo);
        $campo = filter_var($campo, FILTER_SANITIZE_EMAIL);
        $campo = htmlspecialchars($campo);
        return $campo; //Las tres funciones que aplicamos son para limpiar el código que enviamos para evitar posibles errores
    }
    public function encriptarContraseña($contraseña)
    {
        return password_hash($contraseña, PASSWORD_DEFAULT);
    }
    public function verificarUsuario($correo, $contraseña)
    {
        $keydb = $this->MODEL->obtenerClave($correo);
        return (password_verify($contraseña, $keydb)) ? true : false;
    }

    /**
     * Recoge de la BD la lista completa de lineas y las lista
     */
    function completarListaLineas() {
        $lineas = $this->MODEL->seleccionaAllLineas();
        for ($i = 0; $i < sizeof($lineas); $i++) {
            echo '<option value="' . $lineas[$i]['linea'] . '">' . $lineas[$i]['label'] . ": " . $lineas[$i]['nameA'] . " / " . $lineas[$i]['nameB'] . "</option>\n";
        }
    }
}