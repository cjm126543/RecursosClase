/* create schema tabernaUser; */

create table tabernaUser.tipo (
    idTipo int not null primary key
        generated always as identity
        (start with 1, increment by 1),
    nombre varchar(10) not null
);

create table tabernaUser.item (
    idItem int not null primary key
        generated always as identity
        (start with 1, increment by 1),
    nombre varchar(20) not null,
    precioUd decimal(4, 2),
    stock int not null,
    cantPreparando int,
    tipo_fg int not null,
    idHorno int,
    idArcon int,
    idSeccionAlmacen int,
    preparando boolean,
    enUso boolean,
    foreign key(tipo_fg) references tabernaUser.tipo(idTipo)
);

create table tabernaUser.direccion (
    idDireccion int not null primary key
        generated always as identity
        (start with 1, increment by 1),
    nombre varchar(50) not null,
    calle varchar(75) not null,
    cPostal varchar(5) not null
);	

create table tabernaUser.articuloPedido	 (
    idLinea int not null primary key
        generated always as identity
        (start with 1, increment by 1),
    idArticulo_fg int not null,
    cantidadArticulo int not null,
    foreign key(idArticulo_fg) references tabernaUser.item(idItem)
);

create table tabernaUser.pedido (
    idPedido int not null primary key
        generated always as identity
        (start with 1, increment by 1),
    direccionEntrega_fg int not null,
    foreign key(direccionEntrega_fg) references tabernaUser.direccion(idDireccion)
);

create table tabernaUser.lineasPedido (
	idLinea int not null,
	idPedido int not null,
	constraint pk_lineas_pedido primary key(idLinea, idPedido),
	foreign key(idLinea) references tabernaUser.articuloPedido(idLinea),
	foreign key(idPedido) references tabernaUser.pedido(idPedido)
);
