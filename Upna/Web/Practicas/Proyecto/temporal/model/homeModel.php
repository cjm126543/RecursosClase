<?php
class homeModel
{
    private $PDO;
    private $accessToken;
    public function __construct()
    {
        require_once("/var/www/html/pruebas_Carlos/config/credentials.php");
        $cred = new credentials();
        $this->accessToken = $cred->getToken();
        require_once("/var/www/html/pruebas_Carlos/config/db.php");
        $pdo = new db();
        $this->PDO = $pdo->conexion();
    }
    public function agregarNuevoUsuario($correo, $password)
    {
        $statement = $this->PDO->prepare("INSERT INTO usuarios values(null,:correo,:password)");
        $statement->bindParam(":correo", $correo);
        $statement->bindParam(":password", $password);
        try {
            $statement->execute();
            return true;
        } catch (PDOException $e) {
            return false;
        }
    }
    public function obtenerClave($correo)
    {
        $statement = $this->PDO->prepare("SELECT password FROM usuarios WHERE correo = :correo");
        $statement->bindParam(":correo", $correo);
        return ($statement->execute()) ? $statement->fetch()['password'] : false;
    }

    public function inicializarLineas()
    {
        $curl = curl_init();
        curl_setopt_array($curl, array(
            CURLOPT_URL => 'https://openapi.emtmadrid.es/v2/transport/busemtmad/lines/info/20230409/?dataref=' . date("YYYYMMDD"),
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_ENCODING => '',
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_TIMEOUT => 0,
            CURLOPT_FOLLOWLOCATION => true,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => 'GET',
            CURLOPT_HTTPHEADER => array(
                'accessToken: ' . $this->accessToken
            ),


        ));

        $response = curl_exec($curl);
        curl_close($curl);
        $obj = json_decode($response);

        $lines = array();

        for ($i = 0; $i < sizeof($obj->data); $i++) {
            $lines[$i]['label'] = $obj->data[$i]->label;
            $lines[$i]['nameA'] = $obj->data[$i]->nameA;
            $lines[$i]['nameB'] = $obj->data[$i]->nameB;
        }

        $statement = $this->PDO->prepare("CREATE TABLE IF NOT EXISTS lineas
            (
                id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                label VARCHAR(6) NOT NULL UNIQUE,
                nameA VARCHAR(50) NOT NULL,
                nameB VARCHAR(50) NOT NULL 
            )");

        try {
            $statement->execute();
        } catch (PDOException $e) {
            echo "No se ha podido crear la tabla 'lineas'";
            return false;
        }

        $statement = $this->PDO->prepare("INSERT INTO lineas VALUES(null, :label, :nameA, :nameB)");
        for ($i = 0; $i < sizeof($obj->data); $i++) {
            $statement->bindParam(":label", $lines[$i]['label']);
            $statement->bindParam(":nameA", $lines[$i]['nameA']);
            $statement->bindParam(":nameB", $lines[$i]['nameB']);
            try {
                $statement->execute();
            } catch (PDOException $e) {
                echo "No se ha podido insertar en la tabla 'lineas'";
                return false;
            }
        }
    }

    public function seleccionaAllLineas() {
        $statement = $this->PDO->prepare("SELECT * FROM lineas");
        try {
            $statement->execute();
        } catch (PDOException $e) {
            echo "No se ha podido seleccionar las lineas";
            return false;
        }

        $lines = array();
        $i = 0;
        while ($row = $statement->fetch()) {
            $lines[$i]['label'] = $row['label']; 
            $lines[$i]['nameA'] = $row['nameA'];
            $lines[$i]['nameB'] = $row['nameB'];
            $i++;
        }

        return $lines;
    }
}