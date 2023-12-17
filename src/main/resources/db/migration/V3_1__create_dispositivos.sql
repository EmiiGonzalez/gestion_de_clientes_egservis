CREATE TABLE dispositivos (
    idDispositivo BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    procesador VARCHAR(255),
    so VARCHAR(255),
    ram INT,
    almacenamiento INT,
    pantalla VARCHAR(255),
    otros VARCHAR(255),
    idPedido BIGINT, -- Columna para la relación con la tabla pedidos
    FOREIGN KEY (idPedido) REFERENCES pedidos(idPedido)
);