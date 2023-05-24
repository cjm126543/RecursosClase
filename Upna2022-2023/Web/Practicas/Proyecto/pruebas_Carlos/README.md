# Transporte_Madrid


## Motivo
Aplicación de consulta de rutas de transporte público urbano (asíncrono y a tiempo real).
Se puede acceder clickando [aquí](http://webalumnos.tlm.unavarra.es:10731/pruebas_Carlos/)

## Funcionalidades
- [x] Mapa interactivo.
- [x] Lista de lineas de transporte.
- [x] Lista de sentidos.
- [x] Login / registro de usuarios.
- [x] Tabla de incidencias por linea.

## Implementación
Aspectos a implementar especificados en la [rúbrica de clase.](https://miaulario.unavarra.es/access/content/group/2022_0_240603_1_G/Practica/Criterios%20de%20correccion%20-%20Trabajo%20final.pdf)
- [x] División de usuarios.
- [ ] Generación de PDFs y CSVs.
- [x] Recogida de datos desde la [EMTMADRID-MobilityLabs API.](https://apidocs.emtmadrid.es/)
- [x] Organización y parseo de datos (manejo de JSONs).
- [x] Representación de resultados (gráficos, tablas, ...).
- [x] Conexión de datos y aplicación mediante AJAX.
- [x] CRUD usuarios (faltan admins)

### Frontend
Aspectos visuales y representativos de la aplicación.
- [x] Los usuarios anónimos pueden consultar rutas.
- [x] Los usuarios registrados pueden consultar eventos especiales que puedan alterar la ruta (cortes en el tráfico, suspensiones, ...).
- [x] Los usuarios registrados pueden realizar las funciones de los anónimos.

### Backend
Aspectos más técnicos y correspondientes a funcionalidad integra de la aplicación.
- [x] Los usuarios administradores pueden gestionar las credenciales de los usuarios.

## Manipulación de datos de la API
Aquí podemos registrar como se tratan y estructuran los diferentes datos en la API.
- Para los buses:   Se organizan en lineas.
                    Para backend las lineas son "line" para frontend son "label".

## Referencias
[Tutoriales sobre como implementar Leaflet a un mapa interactivo.](https://leafletjs.com/examples/quick-start/)
[Mapas opensource.](https://www.openstreetmap.org/#map=16/40.4167/-3.7033)
[Documentación sobre Leaflet.](https://leafletjs.com/reference.html) 
[Cómo comenzar con jQuery.](https://www.w3schools.com/jquery/jquery_get_started.asp)
[Documentación de Ajax.](http://api.jquery.com/jquery.ajax/)
[Cómo utilizar Bootstrap](https://getbootstrap.com/docs/5.3/getting-started/introduction/)


#### Comentarios de desarrollo
##### Corregir
- [ ] Buscador de paradas, integrado en la página de usuarios registrados pero no funciona.
        Recomiendo pasarlo a una página aparte y poder buscar también por nombre.
- [ ] El desplegable react del menu principal y subpáginas no funciona.
##### Pendiente
- [ ] Buscador de rutas (sólo de bus) para los usuarios registrados.
- [ ] Generar PDFs y CSVs sobre las incidencias y PDF con imagen de la ruta calculada.
- [x] Gestión de usuarios y CRUD de/por usuarios administradores.
##### Novedades
- (Feb/Mar 2023)   [Ivan]    Diseño de la página web, estilos implementados de bootstrap así como los contenedores y diferentes elementos react
                             de la web.
- (Feb/Mar 2023)   [Ivan]    Implementación de la base de datos a la página web para el CRUD de usuarios. Implementado mediante encriptación de
                             contraseñas. Conexión de la página web con la base de datos.
- (18/04/23 18:33) [Carlos]  Guardado las líneas de bus con éxito en la base de datos.
- (22/05/23 21:40) [Carlos]  Cambiados los colores de rutas de ida y vuelta enseñadas en el mapa. Cambiados las palabras "IDA" y "VUELTA" por
                             "Hacia: " y el lugar de destino del sentido.
- (23/05/23 23:57) [Carlos]  Integrado el buscador de paradas por número. La integración ha provocado el cese de funcionamiento.
                             Añadida la funcionalidad de seleccionar líneas para consultar su tabla de incidencias (aconsejable informar de cuando no hay).
                             Añadidas las opciones de consultar direcciones para una ruta e información de las paradas (bien clickando en el desplegable suyo
                             en el mapa o buscandola en el buscador) (AÑADIDAS LAS OPCIONES, NO FUNCIONAN, RESTA IMPLEMENTARLAS).
- (24/05/23 23:36) [Carlos]  Añadida la página de planificación de rutas. Recoge los datos de inicio y destino pero parece ser que la API no devuelve
                             respuesta válida. Completada la funcionalidad del desplegable de la parada, ver mas muestra otras lineas que transcurren y ocultar
                             devuelve el pop-up a su estado original.
- (24/05/23 23:36) [Ivan]    Añadido generador de CSV y PDF funcional sobre los datos de la parada resultante en el buscador.
- (24/05/23 23:36) [Ivan]    Estilado algunas de las páginas web para tener un estilo visual más limpio.
- (24/05/23 23:36) [Ivan]    Creado el apartado de administradores (accesible a traves de admin@admin.com) donde se pueden borrar usuarios y crear nuevos de la
                             base de datos. (Resta poder modificar bien correo/contraseña para completar CRUD).
- (24/05/23 23:36) [Carlos]  Reintegración de los tres componentes anteriores. (El buscador sigue sin funcionar en el servidor, movería el buscador a una nueva página
                             accesible desde el desplegable de información).
