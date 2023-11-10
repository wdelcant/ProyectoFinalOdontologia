create table
IF NOT EXISTS
domicilios(
id bigint auto_increment primary key,
calle varchar(255),
numero varchar (255),
localidad varchar (255),
provincia varchar (255));

create table
IF NOT EXISTS
pacientes(
id bigint auto_increment primary key,
nombre varchar(255),
apellido varchar (255),
dni varchar (255),
fecha_alta TIMESTAMP WITHOUT TIME ZONE,
domicilio_id int);

create table
IF NOT EXISTS
odontologos(
id bigint auto_increment primary key,
nombre varchar(255),
apellido varchar (255),
matricula int);

create table
IF NOT EXISTS
turnos(id bigint auto_increment primary key,
paciente_id bigint not null,
odontologo_id bigint not null,
fecha_hora timestamp not null);


