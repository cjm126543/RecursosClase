<?php
class homeModel
{
    private $PDO;
    private $accessToken;
    public function __construct()
    {
        // require_once("/var/www/html/pruebas_Carlos/config/credentials.php");
        require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\config\credentials.php');
        $cred = new credentials();
        $this->accessToken = $cred->getToken();
        // require_once("/var/www/html/pruebas_Carlos/config/db.php");
        require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\config\db.php');
        $pdo = new db();
        $this->PDO = $pdo->conexion();
        $this->inicializarParadasLinea();
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

    public function inicializarLineas() {
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
            $lines[$i]['linea'] = $obj->data[$i]->line;
            $lines[$i]['label'] = $obj->data[$i]->label;
            $lines[$i]['nameA'] = $obj->data[$i]->nameA;
            $lines[$i]['nameB'] = $obj->data[$i]->nameB;
        }

        $statement = $this->PDO->prepare("CREATE TABLE IF NOT EXISTS lineas
            (
                id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                linea VARCHAR(6) NOT NULL UNIQUE,
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

        $statement = $this->PDO->prepare("INSERT IGNORE INTO lineas VALUES(null, :linea, :label, :nameA, :nameB)");
        for ($i = 0; $i < sizeof($obj->data); $i++) {
            $statement->bindParam(":linea", $lines[$i]['linea']);
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
            $lines[$i]['linea'] = $row['linea'];
            $lines[$i]['label'] = $row['label']; 
            $lines[$i]['nameA'] = $row['nameA'];
            $lines[$i]['nameB'] = $row['nameB'];
            $i++;
        }

        return $lines;
    }

    public function inicializarParadasLinea() {
        // TODO NO VIABLE HACERLO CON AJAX
        $lineas = $this->seleccionaAllLineas();
        $idLineas = array();
        for ($i = 0; $i < sizeof($lineas); $i++) {
            $idLineas[$i] = $lineas[$i]['linea']; 
        }

        // Hagamos la peticion de las paradas
        $paradasA = array();
        $paradasB = array();
        for ($i = 0; $i < sizeof($idLineas); $i++) {
            $curl = curl_init();
            curl_setopt_array($curl, array(
                CURLOPT_URL => "https://openapi.emtmadrid.es/v1/transport/busemtmad/lines/" . $idLineas[$i] . "/stops/1/",
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

            for ($j = 0; $j < sizeof($obj->data[0]->stops); $j++) {
                $paradasA[$i][$j]['id_parada'] = $obj->data[0]->stops[$j]->stop;
                $paradasA[$i][$j]['nombre'] = $obj->data[0]->stops[$j]->name;
                $paradasA[$i][$j]['direccion'] = $obj->data[0]->stops[$j]->postalAddress;
                $paradasA[$i][$j]['coord_x'] = $obj->data[0]->stops[$j]->geometry->coordinates[0];
                $paradasA[$i][$j]['coord_y'] = $obj->data[0]->stops[$j]->geometry->coordinates[1];
            }

            $curl = curl_init();
            curl_setopt_array($curl, array(
                CURLOPT_URL => "https://openapi.emtmadrid.es/v1/transport/busemtmad/lines/" . $idLineas[$i] . "/stops/2/",
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
    
            for ($j = 0; $j < sizeof($obj->data[0]->stops); $j++) {
                $paradasB[$i][$j]['id_parada'] = $obj->data[0]->stops[$j]->stop;
                $paradasB[$i][$j]['nombre'] = $obj->data[0]->stops[$j]->name;
                $paradasB[$i][$j]['direccion'] = $obj->data[0]->stops[$j]->postalAddress;
                $paradasB[$i][$j]['coord_x'] = $obj->data[0]->stops[$j]->geometry->coordinates[0];
                $paradasB[$i][$j]['coord_y'] = $obj->data[0]->stops[$j]->geometry->coordinates[1];
            }
        }

        // Creamos las tablas de paradas
        $creada = false; // Variable para gestionar que tabla da error en sql
        $statement = $this->PDO->prepare("CREATE TABLE IF NOT EXISTS paradas
        (
            id_parada VARCHAR(10) NOT NULL PRIMARY KEY,
            nombre VARCHAR(50) NOT NULL,
            direccion VARCHAR(100) NOT NULL,
            coord_x FLOAT NOT NULL,
            coord_y FLOAT NOT NULL,
            ruta VARCHAR(6) NOT NULL
        )");
        $statement3 = $this->PDO->prepare("CREATE TABLE IF NOT EXISTS lineas_paradas
        (
            id_linea VARCHAR(6) NOT NULL,
            id_parada VARCHAR(10) NOT NULL,
            direccion VARCHAR(6) NOT NULL,
            CONSTRAINT pk_lineas_paradas PRIMARY KEY (id_linea, id_parada),
            CONSTRAINT fk_linea FOREIGN KEY (id_linea) REFERENCES lineas(linea),
            CONSTRAINT fk_parada FOREIGN KEY (id_parada) REFERENCES paradas(id_parada)
        )");

        try {
            $statement->execute();
            $creada = true;
            $statement3->execute();
        } catch (PDOException $e) {
            if ($creada == false) echo "No se ha podido crear la tabla 'paradas'";
            else echo $e;
            return false;
        }

        $insertada = false;
        $ida = "ida";
        $vuelta = "vuelta";
        $statement = $this->PDO->prepare("INSERT IGNORE INTO paradas VALUES(:id_parada, :nombre, :direccion, :coord_x, :coord_y, :ruta)");
        $statement3 = $this->PDO->prepare("INSERT INTO lineas_paradas VALUES(:id_linea, :id_parada, :direccion)");
        for ($i = 0; $i < sizeof($idLineas); $i++) {
            for ($j = 0; $j < sizeof($paradasA[$i]); $j++) {
                $statement->bindParam(":id_parada", $paradasA[$i][$j]['id_parada']);
                $statement->bindParam(":nombre", $paradasA[$i][$j]['nombre']);
                $statement->bindParam("direccion", $paradasA[$i][$j]['direccion']);
                $statement->bindParam(":coord_x", $paradasA[$i][$j]['coord_x']);
                $statement->bindParam(":coord_y", $paradasA[$i][$j]['coord_y']);
                $statement->bindParam(":ruta", $ida);
                $statement3->bindParam(":id_linea", $idLineas[$i]);
                $statement3->bindParam(":id_parada", $paradasA[$i][$j]['id_parada']);
                $statement3->bindParam(":direccion", $ida);
            }
            try {
                $statement->execute();
                $insertada = true;
                $statement3->execute();
            } catch (PDOException $e) {
                if ($insertada == false) echo "No se ha podido insertar en la tabla 'paradas'";
                else echo "No se ha podido insertar en la tabla 'lineas_paradas'";
                return false;
            }
        }
        for ($i = 0; $i < sizeof($idLineas); $i++) {
            for ($j = 0; $j < sizeof($paradasB[$i]); $j++) {
                $statement->bindParam(":id_parada", $paradasB[$i][$j]['id_parada']);
                $statement->bindParam(":nombre", $paradasB[$i][$j]['nombre']);
                $statement->bindParam("direccion", $paradasB[$i][$j]['direccion']);
                $statement->bindParam(":coord_x", $paradasB[$i][$j]['coord_x']);
                $statement->bindParam(":coord_y", $paradasB[$i][$j]['coord_y']);
                $statement->bindParam(":ruta", $vuelta);
                $statement3->bindParam(":id_linea", $idLineas[$i]);
                $statement3->bindParam(":id_parada", $paradasB[$i][$j]['id_parada']);
                $statement3->bindParam(":direccion", $vuelta);
            }
            try {
                $statement->execute();
                $insertada = true;
                $statement3->execute();
            } catch (PDOException $e) {
                if ($insertada == false) echo "No se ha podido insertar en la tabla 'paradas'";
                else echo "No se ha podido insertar en la tabla 'lineas_paradas'";
                return false;
            }
        }
    }
}
new homeModel();