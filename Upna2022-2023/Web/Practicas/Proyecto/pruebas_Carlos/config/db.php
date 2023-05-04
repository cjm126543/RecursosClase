<?php
//En esta clase simplemente hacemos la conexión con la base de datos de mysql
class db
{
    // CREDENCIALES PARA SERVER APACHE
    // private $host = "dbserver";
    // private $dbname = "db_grupo31";
    // private $user = "grupo31";
    // private $password = "key8aeYoov";

    // CREDENCIALES PARA XAMPP LOCAL
    private $host = "127.0.0.1";
    private $dbname = "db_grupo31";
    private $user = "root";
    private $password = "";

    public function conexion()
    {
        try {
            $PDO = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->dbname, $this->user, $this->password); //Conexion a base de datos
            return $PDO;
        } catch (PDOException $e) {
            return $e->getMessage();
        }
        //$conex = mysqli_connect($hostname,$username,password,database)
        //$dblink = mysqli_connect("dbserver", "grupoXX", "password", "db_grupoXX");
    }
}
