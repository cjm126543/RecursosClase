<?php

$curl = curl_init();

curl_setopt_array($curl, array(
  CURLOPT_URL => 'https://openapi.emtmadrid.es/v1/mobilitylabs/discover/categories/',
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => '',
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 0,
  CURLOPT_FOLLOWLOCATION => true,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => 'GET',
  CURLOPT_HTTPHEADER => array(
    'accessToken: f35bea76-c3c7-4b61-aec5-a21876149e8a',
    'Cookie: SERVERIDP=1352a7ab41918bea40d318d84cf6bb844f08c851'
  ),
));

$response = curl_exec($curl);

curl_close($curl);
echo $response;
