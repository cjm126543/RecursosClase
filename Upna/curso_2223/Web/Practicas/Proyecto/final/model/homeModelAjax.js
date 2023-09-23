class homeModelAjax {

    constructor() { }

    setToken() {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: "https://openapi.emtmadrid.es/v1/mobilitylabs/user/login/",
                method: "GET",
                headers: {
                    'email': 'carlos.jimenom@gmail.com',
                    'password': 'S1Wgrup031'
                },
    
                success: function(response) {
                    resolve(response.data[0].accessToken);
                },
                error: function(xhr, state, error) {
                    // handle error
                    reject(error);
                }
            });
        });
    }

    async getToken() {
        return this.setToken()
            .then(function(response) {
                return response;
            })
            .catch(function(error) {
                // handle error
                console.error("la peticion AJAX fallo: ", error);
            });
    }
}