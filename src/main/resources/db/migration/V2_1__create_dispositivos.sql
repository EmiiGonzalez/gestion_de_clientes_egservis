CREATE TABLE dispositivos (
    id_dispositivo BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    procesador VARCHAR(255),
    so VARCHAR(255),
    ram INT,
    almacenamiento INT,
    pantalla VARCHAR(255),
    otros VARCHAR(255),
    id_cliente BIGINT,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);
