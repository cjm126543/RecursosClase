<?php
    function api_call($num) {

        $curl = curl_init();
        curl_setopt_array($curl, array(
            CURLOPT_URL => 'http://acnhapi.com/v1/villagers/' . $num,
            CURLOPT_CUSTOMREQUEST => 'GET',
            CURLOPT_RETURNTRANSFER => true
            // CURLOPT_HTTPHEADER => array(
            //     'token: ' . $mi_token 
            // )
        ));

        $response = curl_exec($curl);
        curl_close($curl);
        $obj = json_decode($response);
        return $obj;
    }

    function numero_paginas() {
        return 20;
    }

    function carga_datos($numero_pagina) {
        return api_call($numero_pagina);
    }
?>