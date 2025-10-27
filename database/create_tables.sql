CREATE DATABASE IF NOT EXISTS reservas_lavadero;
USE reservas_lavadero;

DROP TABLE IF EXISTS RESERVAS;
DROP TABLE IF EXISTS AUTOS;
DROP TABLE IF EXISTS CLIENTES;


CREATE TABLE CLIENTES (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    dni VARCHAR(8) NOT NULL,        
    telefono VARCHAR(9) NOT NULL,          
    PRIMARY KEY (id),
    UNIQUE KEY uk_dni (dni)        
);


CREATE TABLE AUTOS (
    id INT NOT NULL AUTO_INCREMENT,
    patente VARCHAR(7) NOT NULL,   
    tipo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_patente (patente) 
);


CREATE TABLE RESERVAS (
    id INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL, 
    id_auto INT NOT NULL,     
    turno DATETIME NOT NULL,
    tipo_lavado VARCHAR(50) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) REFERENCES CLIENTES(id) ON DELETE CASCADE,
    FOREIGN KEY (id_auto) REFERENCES AUTOS(id) ON DELETE CASCADE
);


INSERT INTO CLIENTES (nombre, dni, telefono) VALUES
('Emmanuel', '45500223', '223620263'),
('Lorenzo', '46879458', '223458788'),
('Iñaqui', '45632124', '223621264');

INSERT INTO AUTOS (patente, tipo) VALUES
('AB123AS', 'Sedán'),
('BA321SA', 'Camioneta'),
('CA456AC', 'Sedán');

INSERT INTO RESERVAS (id_cliente, id_auto, turno, tipo_lavado, precio) VALUES
(
    1,                                 
    1,                                 
    '2025-10-28 10:00:00',             
    'STANDARD',                      
    1000.00                            
);
