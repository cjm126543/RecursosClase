class planificadorControllerAjax {

  inicio;
  ini_txt;
  destino;
  fin_txt;
  tipo_viaje;
  homeModel;

  constructor() {
    this.tipo_viaje = "P";
    this.homeModel = new homeModelAjax();
  }

  setInicio() {
    this.ini_txt = document.getElementById("p_inicio").value;
    let t = this.ini_txt.replace(/\s+/g, '+');
    this.inicio = t + "+Madrid+España";
  }
  
  setDestino() {
    this.fin_txt = document.getElementById("p_fin").value;
    let t = this.fin_txt.replace(/\s+/g, '+');
    this.destino = t + "+Madrid+España";
  }

  async getCoordinates() {
    const self = this; 
    const res_ini = await $.ajax({
      url: "https://nominatim.openstreetmap.org/search?format=geojson&limit=3&q=" + self.inicio,
      method: "GET",

      success: function(response) {
        return response
      },
      error: function(xhr, state, error) {
        // handle error
        console.log(error);
      }
    });

    const res_fin = await $.ajax({
      url: "https://nominatim.openstreetmap.org/search?format=geojson&limit=3&q=" + self.destino,
      method: "GET",

      success: function(response) {
        return response
      },
      error: function(xhr, state, error) {
        // handle error
        console.log(error);
      }
    });

    let coords = new Array();
    coords[0] = res_ini.features[0].geometry.coordinates[1];
    coords[1] = res_ini.features[0].geometry.coordinates[0];
    coords[2] = res_fin.features[0].geometry.coordinates[1];
    coords[3] = res_fin.features[0].geometry.coordinates[0];

    return coords;
  }

  async sendRouteForm() {
    this.setInicio();
    this.setDestino();
    const self = this;
    const coords = await this.getCoordinates();
    console.log(coords);
    const token = await this.homeModel.getToken();
    const res = await $.ajax({
      url: "https://openapi.emtmadrid.es/v1/transport/busemtmad/travelplan/",
      method: "POST",
      contentType: "application/json",
      headers: {
        'accessToken': token
      },
      data: {
        routeType: self.tipo_viaje,
        itinerary: true,
        coordinateXFrom: coords[1],
        coordinateYFrom: coords[0],
        coordinateXTo: coords[3],
        coordinateYTo: coords[2],
        originName: self.ini_txt,
        destinationName: self.fin_txt,
        culture: "ES",
        allowBus: true
      },

      success: function(response) {
        console.log(response);
        return response;
      },
      error: function(xhr, state, error) {
        // handle error
        console.log(error);
      }
    });
  }
}