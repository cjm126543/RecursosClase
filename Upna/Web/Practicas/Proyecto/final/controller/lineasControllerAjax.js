class lineasControllerAjax {
  linea;
  homeModel;

  constructor() {
    this.homeModel = new homeModelAjax();
  }

  async showLinea(identificador) {
    const token = await this.homeModel.getToken();
    const res = await $.ajax({
      url:
        "https://openapi.emtmadrid.es/v1/transport/busemtmad/lines/incidents/" +
        identificador +
        "/",
      method: "GET",
      headers: {
        accessToken: token,
      },

      success: function (response) {
        return response;
      },
      error: function (xhr, state, error) {
        // handle error
        console.log(error);
      },
    });

    // Muestra las incidencias de la línea y un botón de regreso
    var tabla = document.getElementById("table_lineas");
    if (tabla) {
      tabla.parentNode.removeChild(tabla);
    }

    let div_titulo = document.getElementById("cabecera_pagina");
    let boton_volver = document.createElement("a");
    boton_volver.setAttribute(
      "class",
      "btn btn-outline-warning d-none d-md-inline-block"
    );
    // boton_volver.setAttribute('href', '/proyecto_siw/var/www/html/final/view/head/info-lineas.php');
    boton_volver.setAttribute("href", "/final/view/head/info-lineas.php");
    boton_volver.setAttribute("role", "button");
    boton_volver.textContent = "Volver";
    div_titulo.appendChild(boton_volver);

    var new_tabla = document.createElement("table");
    new_tabla.setAttribute("id", "table_incidencias");
    new_tabla.setAttribute("class", "table custom-table");
    new_tabla.style.width = "100%";
    new_tabla.style.tableLayout = "fixed";

    var cabecera_evento = document.createElement("th");
    var cabecera_finicio = document.createElement("th");
    var cabecera_ffin = document.createElement("th");
    var cabecera_lineas_af = document.createElement("th");
    var cabecera_descripcion = document.createElement("th");
    cabecera_evento.textContent = "Incidencia";
    cabecera_finicio.textContent = "Fecha inicio";
    cabecera_ffin.textContent = "Fecha fin (estimada)";
    cabecera_lineas_af.textContent = "Lineas afectadas";
    cabecera_descripcion.textContent = "Descripcion";
    new_tabla.appendChild(cabecera_evento);
    new_tabla.appendChild(cabecera_finicio);
    new_tabla.appendChild(cabecera_ffin);
    new_tabla.appendChild(cabecera_lineas_af);
    new_tabla.appendChild(cabecera_descripcion);

    var endingSentence =
      "Ver más detalle en documento adjunto.<p><img src='http://feeds.emtmadrid.es:8082/images/00-Logo-RSS_Corporativo.png'/></p>";
    for (var i = 0; i < res.data[0].item.length; i++) {
      var fila = document.createElement("tr");
      var c_evento = document.createElement("td");
      var c_finicio = document.createElement("td");
      var c_ffin = document.createElement("td");
      var c_lineas = document.createElement("td");
      var c_descripcion = document.createElement("td");
      c_evento.style.wordWrap = "break-word";
      c_evento.style.maxWidth = "150px";
      c_finicio.style.wordWrap = "break-word";
      c_finicio.style.maxWidth = "150px";
      c_ffin.style.wordWrap = "break-word";
      c_ffin.style.maxWidth = "150px";
      c_lineas.style.wordWrap = "break-word";
      c_lineas.style.maxWidth = "150px";
      c_descripcion.style.wordWrap = "break-word";
      c_descripcion.style.maxWidth = "150px";
      var evento = res.data[0].item[i].title;
      var fecha_ini = res.data[0].item[i].rssAfectaDesde;
      var fecha_fin = res.data[0].item[i].rssAfectaHasta;
      var lineas_afectadas = res.data[0].item[i].category;
      var descripcion = res.data[0].item[i].description;
      if (descripcion.endsWith(endingSentence))
        descripcion = descripcion.slice(0, -endingSentence.length);
      c_evento.textContent = evento;
      c_finicio.textContent = fecha_ini;
      c_ffin.textContent = fecha_fin;
      c_lineas.textContent = lineas_afectadas;
      c_descripcion.textContent = descripcion;
      fila.appendChild(c_evento);
      fila.appendChild(c_finicio);
      fila.appendChild(c_ffin);
      fila.appendChild(c_lineas);
      fila.appendChild(c_descripcion);
      new_tabla.appendChild(fila);
    }

    var contenedor = document.getElementById("paradas_container");
    contenedor.appendChild(new_tabla);
  }
}
