CREATE TABLE dispositivos (
    idDispositivo BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    procesador VARCHAR(255) NOT NULL,
    so VARCHAR(255) NOT NULL,
    ram INT NOT NULL,
    almacenamiento INT NOT NULL,
    pantalla VARCHAR(255) NOT NULL,
    otros VARCHAR(255),
    idPedido BIGINT NOT NULL, -- Columna para la relación con la tabla pedidos
    FOREIGN KEY (idPedido) REFERENCES pedidos(idPedido) 
);