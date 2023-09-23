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
        background-color: black;
        color: white;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        min-height: 100vh;
        padding-top: 20px;
        box-sizing: border-box;
      }

      .info-container {
        max-width: 800px;
        text-align: center;
        padding: 20px;
        margin-top: 20px;
      }

      .info-table {
        width: 700px;
        background-color: gray;
        color: white;
        border-collapse: separate;
        border-radius: 10px;
        margin-bottom: 20px;
      }

      .info-table th {
        padding: 15px;
      }

      .info-table td {
        padding: 8px;
      }

      .btn-outline-warning {
        font-weight: bold;
        color: #ffc107;
        border-color: #ffc107;
        background-color: #343a40;
        margin-top: 20px;
        margin-bottom: 40px;
        border-radius: 8px;
        padding: 10px 20px;
        display: block;
        margin-left: auto;
        margin-right: auto;
      }

      .btn-outline-warning:hover {
        color: #000;
        background-color: #ffc107;
      }

      .btn-container {
        display: flex;
        justify-content: center;
        margin-top: 10px;
      }

      .btn-container button {
        margin: 0 10px;
      }
    </style>
    </head>
    <body>
    <div class="container info-container">
      <div id="parada-info" class="card">
        <div class="card-body">
          <table class="table info-table">
            <tr>
              <th>Nombre de la parada</th>
              <td id="nombre-parada"></td>
            </tr>
            <tr>
              <th>Número de la parada</th>
              <td id="numero-parada"></td>
            </tr>
            <tr>
              <th>Ubicación</th>
              <td id="postal-address"></td>
            </tr>
            <tr>
              <th>Dirección</th>
              <td id="direction"></td>
            </tr>
            <tr>
              <th>Header</th>
              <td id="header"></td>
            </tr>
            <tr>
              <th>Hora de comienzo</th>
              <td id="start-time"></td>
            </tr>
            <tr>
              <th>Hora final</th>
              <td id="stop-time"></td>
            </tr>
            <tr>
              <th>Coordenada 1</th>
              <td id="coordenada-1"></td>
            </tr>
            <tr>
              <th>Coordenada 2</th>
              <td id="coordenada-2"></td>
            </tr>
          </table>
          <div class="btn-container">
            <button
              class="btn btn-outline-warning d-none d-md-inline-block"
              onclick="generarPDF()"
            >
              Descargar PDF
            </button>
            <button
              class="btn btn-outline-warning d-none d-md-inline-block"
              onclick="generarCSV()"
            >
              Descargar CSV
            </button>
          </div>
        </div>        
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
        crossorigin="anonymous">
      </script>

      <script>
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
              accessToken: homeControllerJs.getToken()
            },
            success: function (data) {
              // Muestra la información de la parada en la página
              var paradaInfoDiv = $("#parada-info .card-body");
              var parada = data.data[0].stops[0];

              $("#nombre-parada").text(parada.name);
              $("#numero-parada").text(parada.stop);
              $("#postal-address").text(parada.postalAddress);
              $("#direction").text(parada.dataLine[0].direction);
              $("#header").text(
                parada.dataLine[0].direction === "B"
                  ? parada.dataLine[0].headerB
                  : parada.dataLine[0].headerA
              );
              $("#start-time").text(parada.dataLine[0].startTime);
              $("#stop-time").text(parada.dataLine[0].stopTime);
              $("#coordenada-1").text(parada.geometry.coordinates[0]);
              $("#coordenada-2").text(parada.geometry.coordinates[1]);
            },
            error: function (err) {
              console.error("Error al obtener la parada:", err);
            },
          });
        }

      // Llama a la función obtenerParada cuando la página esté lista
      $(document).ready(obtenerParada);
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/vfs_fonts.js"></script>
    <script>
      async function generarPDF() {
        var paradaInfoDiv = $("#parada-info .card-body");
        var parada = paradaInfoDiv.html();
        var token = await homeControllerJs.getToken();

        // Obtener los datos de la parada desde la API
        $.ajax({
          url:
            "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/" +
            paradaId +
            "/detail/",
          headers: {
            accessToken: token,
          },
          success: function (data) {
            var parada = data.data[0].stops[0];

            var table = {
              table: {
                widths: ["*", "*"],
                body: [
                  [
                    { text: "Nombre de la parada", style: "tableHeader" },
                    { text: parada.name },
                  ],
                  [
                    { text: "Número de la parada", style: "tableHeader" },
                    { text: parada.stop },
                  ],
                  [
                    { text: "Postal Address", style: "tableHeader" },
                    { text: parada.postalAddress },
                  ],
                  [
                    { text: "Direction", style: "tableHeader" },
                    { text: parada.dataLine[0].direction },
                  ],
                  [
                    { text: "Header", style: "tableHeader" },
                    {
                      text:
                        parada.dataLine[0].direction === "B"
                          ? parada.dataLine[0].headerB
                          : parada.dataLine[0].headerA,
                    },
                  ],
                  [
                    { text: "Start time", style: "tableHeader" },
                    { text: parada.dataLine[0].startTime },
                  ],
                  [
                    { text: "Stop time", style: "tableHeader" },
                    { text: parada.dataLine[0].stopTime },
                  ],
                  [
                    { text: "Coordenada 1", style: "tableHeader" },
                    { text: parada.geometry.coordinates[0] },
                  ],
                  [
                    { text: "Coordenada 2", style: "tableHeader" },
                    { text: parada.geometry.coordinates[1] },
                  ],
                ],
              },
            };

            var docDefinition = {
              content: [
                { text: "Información de la parada", style: "header" },
                table,
              ],
              styles: {
                header: {
                  fontSize: 18,
                  bold: true,
                  margin: [0, 0, 0, 10],
                },
                tableHeader: {
                  bold: true,
                  fillColor: "#f2f2f2",
                },
              },
            };

            pdfMake.createPdf(docDefinition).download("informacion_parada.pdf");
          },
          error: function (err) {
            console.error("Error al obtener la parada:", err);
          },
        });
      }
    </script>
    <script>
      async function generarCSV() {
        var paradaInfoDiv = $("#parada-info .card-body");
        var parada = paradaInfoDiv.html();
        var token = homeControllerJs.getToken();

        // Obtener los datos de la parada desde la API
        $.ajax({
          url:
            "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/" +
            paradaId +
            "/detail/",
          headers: {
            accessToken: token,
          },
          success: function (data) {
            var parada = data.data[0].stops[0];

            var csvContent = "data:text/csv;charset=utf-8,";
            csvContent += "Campo,Valor\n";
            csvContent += "Nombre de la parada," + parada.name + "\n";
            csvContent += "Número de la parada," + parada.stop + "\n";
            csvContent += "Postal Address," + parada.postalAddress + "\n";
            csvContent += "Direction," + parada.dataLine[0].direction + "\n";
            csvContent +=
              "Header," +
              (parada.dataLine[0].direction === "B"
                ? parada.dataLine[0].headerB
                : parada.dataLine[0].headerA) +
              "\n";
            csvContent += "Start time," + parada.dataLine[0].startTime + "\n";
            csvContent += "Stop time," + parada.dataLine[0].stopTime + "\n";
            csvContent +=
              "Coordenada 1," + parada.geometry.coordinates[0] + "\n";
            csvContent +=
              "Coordenada 2," + parada.geometry.coordinates[1] + "\n";

            var encodedUri = encodeURI(csvContent);
            var link = document.createElement("a");
            link.setAttribute("href", encodedUri);
            link.setAttribute("download", "informacion_parada.csv");
            document.body.appendChild(link);
            link.click();
          },
          error: function (err) {
            console.error("Error al obtener la parada:", err);
          },
        });
      }
    </script>
    <script src='/pruebas_Carlos/model/homeModelAjax.js'></script>
    <script src='/pruebas_Carlos/controller/homeControllerAjax.js'></script>
    <script src="/pruebas_Carlos/controller/lineasControllerAjax.js"></script>
    <script src="/pruebas_Carlos/controller/planificadorControllerAjax.js"></script>
    <script>var homeControllerJs = new homeControllerAjax();</script>
    <script>var lineasControllerJs = new lineasControllerAjax();</script>
    <script>var planificadorControllerJs = new planificadorControllerAjax();</script>
    <script>var modelo = new homeModelAjax();</script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- <script src="\proyecto_siw\pruebas_Carlos\asset\js\getParadas.js"></script> -->
    <script src="/pruebas_Carlos/asset/js/getParadas.js"></script>
  </body>
</html>
<?php endif ?>