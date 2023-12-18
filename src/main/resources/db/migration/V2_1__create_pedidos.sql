CREATE TABLE pedidos (
    idPedido BIGINT AUTO_INCREMENT PRIMARY KEY,
    fechaIngreso DATE NOT NULL,
    fechaEntrega DATE,
    presupuesto DECIMAL(19, 2) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    servicio VARCHAR(255) NOT NULL,
    idCliente BIGINT NOT NULL, -- Columna para la relación con la tabla clientes
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente) 
);