var paradas = [];
// Realizamos una petición GET al servidor para obtener la lista de paradas
$.ajax({
  url: "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/list/",
  headers: {
    accessToken: modelo.getToken()
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
      // .attr("href", "/proyecto_siw/var/www/html/pruebas_Carlos/view/head/info-parada.html?paradaId=" + parada.node)
      .attr("href", "/pruebas_Carlos/view/head/info-parada.html?paradaId=" + parada.node)
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
