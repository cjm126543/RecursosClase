alert ("estoy aqui");		


		function validar() {
			var nom = document.getElementById("nombre");
			var ape1 = document.getElementById("apellido1");
			var ape2 = document.getElementById("apellido2");

			var error = false;

			if (nom.value.length < 1) {
				error = true;
				nom.style.backgroundColor = "red";
			} else {
				nom.style.backgroundColor = "#ffffff";
			}

			if (ape1.value.length < 1) {
				error = true;
				ape1.style.backgroundColor = "red";
			} else {
				ape1.style.backgroundColor = "#ffffff";
			}

			if (ape2.value.length < 1) {
				error = true;
				ape2.style.backgroundColor = "red";
			} else {
				ape2.style.backgroundColor = "#ffffff";
			}

			if (error) {
				alert("Hay errores en el formulario");
			} else {
				//Enviar el formulario
				document.getElementById("formulario").submit();
			}
		}
