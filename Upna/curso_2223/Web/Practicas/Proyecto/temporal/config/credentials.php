<?php
// Clase usada principalmente para obtener el access-token a la API
class credentials
{
    private $mail = "carlos.jimenom@gmail.com";
    private $pass = "S1Wgrup031";
    private $token = "";

    public function getToken()
    {
        $curl = curl_init();
        curl_setopt_array($curl, array(
            CURLOPT_URL => 'https://openapi.emtmadrid.es/v1/mobilitylabs/user/login/',
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_ENCODING => '',
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_TIMEOUT => 0,
            CURLOPT_FOLLOWLOCATION => true,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => 'GET',
            CURLOPT_HTTPHEADER => array(
              'email: ' . $this->mail,
              'password: ' . $this->pass
            ),
        ));

        $response = curl_exec($curl);
        curl_close($curl);
        $obj = json_decode($response);
        $this->token = $obj->data[0]->accessToken;
        return $this->token;
    }
}