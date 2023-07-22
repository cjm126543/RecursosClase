

<?php
class lineasController
{
    private $MODEL;


    public function __construct()
    {
        require_once("/var/www/html/final/model/homeModel.php");
        // require_once('C:\xampp\htdocs\proyecto_siw\final\model\homeModel.php');
        $this->MODEL = new homeModel();
    }

    /**
     * Recoge de la BD la lista completa de lineas y las lista
     */
    
    public function displayAllLineas() {
        $lineas = $this->MODEL->seleccionaAllLineas();
        for ($i = 0; $i < 15; $i++) {
            echo "<tr>";
            for ($j = 0; $j < 15; $j++) {
                $lin = $lineas[($i * 15) + $j]['linea'];
                echo '<td id="' . $lin .  '" onclick="lineasControllerJs.showLinea(' . $lin 
                    . ');">' . '<span class="circle circle-color">' . "<i><b>" 
                    . $lineas[($i * 15) + $j]['label'] . "</b></i>" . "</span>" . "</td>";
            }
            echo "</tr>";
        }
    }
}
?>

