var paradas = [];
// Realizamos una petición GET al servidor para obtener la lista de paradas
var token;

function getToken() {
  return new Promise(function (resolve, reject) {
    $.ajax({
      url: "https://openapi.emtmadrid.es/v1/mobilitylabs/user/login/",
      method: "GET",
      headers: {
        email: "soladelgadoivan@gmail.com",
        password: "Ivanobb900",
      },
      success: function (response) {
        token = response.data[0].accessToken;
        resolve(); // Resolvemos la promesa una vez que el primer AJAX se completa
      },
      error: function (xhr, state, error) {
        reject(error); // Rechazamos la promesa en caso de error
      },
    });
  });
}

function getParadas() {
  $.ajax({
    url: "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/list/",
    headers: {
      accessToken: token,
    },
    method: "POST",
    dataType: "json",
    success: function (data) {
      // Si la petición es exitosa, guardamos la lista de paradas en la variable 'paradas'
      paradas = data.data;
    },
    error: function (xhr, status, error) {
      console.error("Error al obtener la lista de paradas:", error);
    },
  });
}

// Uso
getToken().then(function () {
  getParadas();
});

function buscarParada(textoBusqueda) {
  $("#resultado-busqueda").empty();

  if (textoBusqueda === "") {
    return;
  }

  var paradasFiltradas = paradas.filter(function (parada) {
    //return parada.node.includes(textoBusqueda);
    return parada.node === textoBusqueda;
  });

  paradasFiltradas.forEach(function (parada) {
    var paradaLink = $("<a>")
      // .attr("href", "/proyecto_siw/var/www/html/final/view/head/info-parada.html?paradaId=" + parada.node)
      .attr("href", "/final/view/head/info-parada.php?paradaId=" + parada.node)
      .addClass("list-group-item list-group-item-action")
      .text(parada.node + " - " + parada.name);
    var paradaItem = $("<div>")
      .addClass("list-group-item list-group-item-action")
      .append(paradaLink);
    $("#resultado-busqueda").append(paradaItem);
  });
}

$("#busqueda-parada").on("input", function () {
  var textoBusqueda = $(this).val();
  buscarParada(textoBusqueda);
});
