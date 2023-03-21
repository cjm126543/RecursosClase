/* TABLA TIPO */
insert into tabernaUser.tipo(nombre) values('Pan');
insert into tabernaUser.tipo(nombre) values('Reposteria');
insert into tabernaUser.tipo(nombre) values('Lacteo');
insert into tabernaUser.tipo(nombre) values('Refresco');
insert into tabernaUser.tipo(nombre) values('Snack');
insert into tabernaUser.tipo(nombre) values('Cafe');
insert into tabernaUser.tipo(nombre) values('Extra');

/* DIRECCION */
insert into tabernaUser.direccion(nombre, calle, cPostal) values('Casa', 'Calle san francisco Javier 5', '31191');
insert into tabernaUser.direccion(nombre, calle, cPostal) values('Trabajo', 'Avenida de los castros 12', '39005');
insert into tabernaUser.direccion(nombre, calle, cPostal) values('Ocio', 'Bajada del labrit S/N', '11111');

/* ITEM */
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Chapata', 1.20, 10, 1);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Baguette', 0.90, 7, 1);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Bollo', 0.75, 3, 2);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Croissant', 1.10, 15, 2);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Leche entera', 0.50, 1, 3);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Leche de avellana', 1.15, 20, 3);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Coca cola', 2.50, 17, 4);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Nestea', 2.50, 4, 4);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Pipas', 0.99, 4, 5);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Nescafe', 1.35, 2, 6);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Pepinillos', 1.60, 50, 7);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Lomo', 3.90, 12, 7);
insert into tabernaUser.item(nombre, precioUd, stock, tipo_fg) values('Miel', 1.20, 9, 7);

/* ARTICULOPEDIDO */
insert into tabernaUser.articuloPedido(idArticulo_fg, cantidadArticulo) values(1, 3);
insert into tabernaUser.articuloPedido(idArticulo_fg, cantidadArticulo) values(5, 1);
insert into tabernaUser.articuloPedido(idArticulo_fg, cantidadArticulo) values(6, 10);
insert into tabernaUser.articuloPedido(idArticulo_fg, cantidadArticulo) values(10, 2);

/* PEDIDO */
insert into tabernaUser.pedido(direccionEntrega_fg) values(1);
insert into tabernaUser.pedido(direccionEntrega_fg) values(2);

/* LINEASPEDIDO */
insert into tabernaUser.lineasPedido(idLinea, idPedido) values(1, 1);
insert into tabernaUser.lineasPedido(idLinea, idPedido) values(2, 1);
insert into tabernaUser.lineasPedido(idLinea, idPedido) values(3, 1);
insert into tabernaUser.lineasPedido(idLinea, idPedido) values(4, 2);

