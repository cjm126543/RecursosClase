<?php
class homeController
{
    private $MODEL;
    private $db;

    public function __construct()
    {
        require_once("/var/www/html/final/model/homeModel.php");
        require_once("/var/www/html/final/config/db.php");
        $this->db = new db();
        // require_once('C:\xampp\htdocs\proyecto_siw\final\model\homeModel.php');
        $this->MODEL = new homeModel();
        
    }

    public function obtenerUsuarios()
    {
        $conexion = $this->db->conexion();
        $query = "SELECT * FROM final_usuarios";
        $stmt = $conexion->prepare($query);
        $stmt->execute();
        $usuarios = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $usuarios;
    }

    public function guardarUsuario($correo, $contraseña)
    {
        $valor = $this->MODEL->agregarNuevoUsuario($this->limpiarCorreo($correo), $this->encriptarContraseña($this->limpiarCadena($contraseña)));
        return $valor;
    }

    public function borrarUsuario($usuario_id)
    {
        $conexion = $this->db->conexion();
        $query = "DELETE FROM final_usuarios WHERE id = :id";
        $stmt = $conexion->prepare($query);
        $stmt->bindParam(":id", $usuario_id, PDO::PARAM_INT);
        $resultado = $stmt->execute();
        return $resultado;
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