class homeControllerAjax {

    linea;
    direccion;
    homeModel;
    map;

    constructor() {
        this.homeModel = new homeModelAjax();
        if (document.getElementById("custom_map") != null) {
            this.map = L.map("custom_map").setView([40.4167, -3.7033], 14);
            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 19,
                attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
            }).addTo(this.map);
        }
    }

    setLinea() {
        let lin = document.getElementById("lin");
        this.linea = lin.value;
        let texto_linea = lin.options[lin.selectedIndex].text.match(/: (.+?) \/ (.+)/);
        document.getElementById("ida").textContent = "Hacia: " + texto_linea[2].trim();
        document.getElementById("vuelta").textContent = "Hacia: " + texto_linea[1].trim();
    }

    setDireccion() {
        this.direccion = document.getElementById("way").value;
    }
    
    // Dibuja sobre el iframe de U-map las paradas de la linea escogida
    dibujaParadas(paradas, col) {
        if (typeof this.map != 'undefined') this.map.remove();
        this.map = L.map("custom_map").setView([40.4167, -3.7033], 14);
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(this.map);

        var listaParadas = [];
        for (var i = 0; i < paradas.length; ++i) {
            let x = paradas[i].geometry.coordinates[1];
            let y = paradas[i].geometry.coordinates[0];
            let name = paradas[i].name;
            listaParadas.push([x, y]);
            let marcador = L.marker([x, y]).addTo(this.map);
            let markerId = L.stamp(marcador);
            let pop = marcador.bindPopup("P" + (i + 1) + ": " + name + "\n" + 
                        '<a id="show-hide" href="#" onclick="homeControllerJs.getStopInfo(' + paradas[i].stop + 
                        ', ' + markerId + ');">Ver mas</a>');
            pop.addTo(this.map);
        }
        let grafo = L.polyline(listaParadas, {color: col, weight: 3}).addTo(this.map);
        this.map.fitBounds(grafo.getBounds())
    }

    // Peticion de las paradas de la linea
    async getRoute() {
        var color;
        var lineaId = this.linea;
        const token = await this.homeModel.getToken();
        if (this.direccion == '1') {
            color = 'blue';
        } else {
            color = 'red';
        }
        const res = await $.ajax({
            url: "https://openapi.emtmadrid.es/v1/transport/busemtmad/lines/" + lineaId + "/stops/" + this.direccion + "/",
            method: "GET",
            headers: {
                'accessToken': token
            },

            success: function(response) {
                return response;
            },
            error: function(xhr, state, error) {
                // handle error
                console.log(error);
            }
        });

        this.dibujaParadas(res.data[0].stops, color);
    }

    async getStopInfo(stopId, markerId) {
        const token = await this.homeModel.getToken();
        const res = await $.ajax({
            url: "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/" + stopId + "/detail/",
            method: "GET",
            headers: {
                'accessToken': token
            },

            success: function(response) {
                return response;
            },
            error: function(xhr, state, error) {
                // handle error
                console.log(error);
            }
        });

        var labelArr = new Array();
        var headerA_arr = new Array();
        var headerB_arr = new Array();
        var cPostal = res.data[0].stops[0].postalAddress;

        for (var i = 0; i < res.data[0].stops[0].dataLine.length; i++) {
            labelArr[i] = res.data[0].stops[0].dataLine[i].label;
            headerA_arr[i] = res.data[0].stops[0].dataLine[i].headerA;
            headerB_arr[i] = res.data[0].stops[0].dataLine[i].headerB;
        }

        let marker = this.map._layers[markerId];
        let popup = marker.getPopup();
        let popupContent = popup.getContent();
        popupContent += "<br>Direccion postal: " + cPostal;
        popupContent += "<br>Lineas en esta parada: "
        for (var j = 0; j < labelArr.length; j++) popupContent += "<br>" + labelArr[j] 
                                                                + " " + headerA_arr[j]
                                                                + " / " + headerB_arr[j];
        popup.setContent(popupContent);

        if (popup.isOpen()) {
            let tempElement = document.createElement('div');
            tempElement.innerHTML = popup.getContent();
            let anchor = tempElement.querySelector('a');
            if (anchor.innerText == 'Ver mas') {
                anchor.innerText = 'Ocultar';
                anchor.setAttribute('onclick', 'homeControllerJs.cleanPopUp(' + markerId + ', ' + stopId + ');');
                popup.setContent(tempElement.innerHTML);
            }
        }
    }

    cleanPopUp(markerId, stopId) {
        let popup = this.map._layers[markerId].getPopup();
        let popupContent = popup.getContent();
        popup.setContent(popupContent.substring(0, popupContent.indexOf("Ocultar") + "Ocultar".length));
        let tempElement = document.createElement('div');
        tempElement.innerHTML = popup.getContent();
        let anchor = tempElement.querySelector('a');
        if (anchor.innerText == 'Ocultar') {
            anchor.innerText = 'Ver mas';
            anchor.setAttribute('onclick', 'homeControllerJs.getStopInfo(' + stopId + ', ' + markerId + ');');
            popup.setContent(tempElement.innerHTML);
        }
    }
}