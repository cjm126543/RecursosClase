<?php session_start(); ?>
<?php if (empty($_SESSION['usuario'])) : ?>

    <!-- Un usuario no registrado no deberia tener acceso, se le prohibe -->
    <?php
        http_response_code(403);
        echo "<h1>403 ERROR Forbidden</h1>";
        echo "<h2>You don't have permission to access this page.</h2>";
        echo "<p>You must <b>log in</b> in order to access this page.</p>";
        exit();
    ?>

<?php else : ?>
  <!DOCTYPE html>
  <html lang="en">
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Información de la parada</title>
      <!-- Incluye Bootstrap CSS -->
      <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl7/6en6XCp+HHAAK5GSLf2xlYtvJ8U2Q4U+9cuEnJoa3"
        crossorigin="anonymous"
      />
      <style>
        body {
          font-family: Arial, sans-serif;
        }
        .info-container {
          max-width: 800px;
          margin: 0 auto;
        }
      </style>
    </head>
    <body>
      <div class="container info-container">
        <h1 class="my-4">Información de la parada</h1>
        <div id="parada-info" class="card">
          <div class="card-body"></div>
        </div>
      </div>

      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      <!-- Incluye Bootstrap JS -->
      <script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-eMNCOe7tC1doHpGoJtKh7z7lGz7fuP4F8nfdFvAOA6Gg/z6Y5J6XqqyGXYM2ntX5"
        crossorigin="anonymous"
      ></script>
      <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"
        integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/"
        crossorigin="anonymous"
      ></script>

      <!--TODO CAMBIAR A DIRECCIONES DE SERVER FILEZILLA -->


      <script>
        var mo = new homeModelAjax();
        // Obtiene el ID de la parada desde la URL
        const urlParams = new URLSearchParams(window.location.search);
        const paradaId = urlParams.get("paradaId");

        function obtenerParada() {
          // Realiza la solicitud a la API de EMT Madrid para obtener los detalles de la parada específica (aquí debes incluir tu propia API key)
          $.ajax({
            url:
              "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/" +
              paradaId +
              "/detail/",
            headers: {
              accessToken: mo.getToken()
            },
            success: function (data) {
              // Muestra la información de la parada en la página
              var paradaInfoDiv = $("#parada-info .card-body");
              var parada = data.data[0].stops[0];

              paradaInfoDiv.append(
                '<p class="card-text">Nombre de la parada: ' +
                  parada.name +
                  "</p>"
              );
              paradaInfoDiv.append(
                '<p class="card-text">Número de la parada: ' +
                  parada.stop +
                  "</p>"
              );
              paradaInfoDiv.append(
                '<p class="card-text">Postal Address: ' +
                  parada.postalAddress +
                  "</p>"
              );
              paradaInfoDiv.append(
                '<p class="card-text">Direction: ' +
                  parada.dataLine[0].direction +
                  "</p>"
              );
              paradaInfoDiv.append(
                '<p class="card-text">Header: ' +
                  (parada.dataLine[0].direction === "B"
                    ? parada.dataLine[0].headerB
                    : parada.dataLine[0].headerA) +
                  "</p>"
              );
              paradaInfoDiv.append(
                '<p class="card-text">Start time: ' +
                  parada.dataLine[0].startTime +
                  "</p>"
              );
              paradaInfoDiv.append(
                '<p class="card-text">Stop time: ' +
                  parada.dataLine[0].stopTime +
                  "</p>"
              );
              paradaInfoDiv.append(
                '<p class="card-text">Coordenada 1: ' +
                  parada.geometry.coordinates[0] +
                  "</p>"
              );
              paradaInfoDiv.append(
                '<p class="card-text">Coordenada 2: ' +
                  parada.geometry.coordinates[1] +
                  "</p>"
              );
            },
            error: function (err) {
              console.error("Error al obtener la parada:", err);
            },
          });
        }

        // Llama a la función obtenerParada cuando la página esté lista
        $(document).ready(obtenerParada);
      </script>
    </body>
  </html>
<?php endif ?>