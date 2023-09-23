<?php
//En esta clase simplemente hacemos la conexión con la base de datos de mysql
class db
{
    private $host = "dbserver";
    private $dbname = "db_grupo31";
    private $user = "grupo31";
    private $password = "key8aeYoov";
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
