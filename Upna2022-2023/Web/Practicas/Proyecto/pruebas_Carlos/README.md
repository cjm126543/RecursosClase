# Transporte_Madrid


## Motivo
Aplicación de consulta de rutas de transporte público urbano (asíncrono y a tiempo real).\
Se puede acceder clickando [aquí](http://webalumnos.tlm.unavarra.es:10731/pruebas_Carlos/)

## Funcionalidades
- [x] Mapa interactivo.
- [x] Lista de lineas de transporte.
- [x] Lista de sentidos.
- [x] Login / registro de usuarios.
- [x] Tabla de incidencias por linea.

## Implementación
Aspectos a implementar especificados en la [rúbrica de clase.](https://miaulario.unavarra.es/access/content/group/2022_0_240603_1_G/Practica/Criterios%20de%20correccion%20-%20Trabajo%20final.pdf)
- [ ] División de usuarios.
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
- [ ] Los usuarios administradores pueden gestionar las credenciales y favoritos de los usuarios.
- [ ] Los usuarios administradores pueden lanzar mensajes avisando de eventos especiales que afecten a las rutas.
- (...)

## Manipulación de datos de la API
Aquí podemos registrar como se tratan y estructuran los diferentes datos en la API.
- Para los buses:   Se organizan en lineas.
                    Para backend las lineas son "line" para frontend son "label".

## Referencias
[Tutoriales sobre como implementar Leaflet a un mapa interactivo.](https://leafletjs.com/examples/quick-start/)\
[Mapas opensource.](https://www.openstreetmap.org/#map=16/40.4167/-3.7033)\
[Documentación sobre Leaflet.](https://leafletjs.com/reference.html) \
[Cómo comenzar con jQuery.](https://www.w3schools.com/jquery/jquery_get_started.asp)\
[Documentación de Ajax.](http://api.jquery.com/jquery.ajax/)\
[Cómo utilizar Bootstrap](https://getbootstrap.com/docs/5.3/getting-started/introduction/)


#### Comentarios de desarrollo
##### Corregir
- [ ] Buscador de paradas, integrado en la página de usuarios registrados pero no funciona.\
      Recomiendo pasarlo a una página aparte y poder buscar también por nombre.
##### Pendiente
- [ ] Buscador de rutas (sólo de bus) para los usuarios registrados.
- [ ] Generar PDFs y CSVs sobre las incidencias y PDF con imagen de la ruta calculada.
- [ ] Gestión de usuarios y CRUD de/por usuarios administradores.
##### Novedades
- (18/04/23 18:33)  Guardado las líneas de bus con éxito en la base de datos.
- (22/05/23 21:40)  Cambiados los colores de rutas de ida y vuelta enseñadas en el mapa. Cambiados las palabras "IDA" y "VUELTA" por\
                    "Hacia: " y el lugar de destino del sentido.
- (23/05/23 23:57)  Integrado el buscador de paradas por número. La integración ha provocado el cese de funcionamiento.\
                    Añadida la funcionalidad de seleccionar líneas para consultar su tabla de incidencias (aconsejable informar de cuando no hay).\
                    Añadidas las opciones de consultar direcciones para una ruta e información de las paradas (bien clickando en el desplegable suyo\
                    en el mapa o buscandola en el buscador) (AÑADIDAS LAS OPCIONES, NO FUNCIONAN, RESTA IMPLEMENTARLAS).
