CREATE TABLE pedidos (
    idPedido BIGINT AUTO_INCREMENT PRIMARY KEY,
    fechaIngreso DATE,
    fechaEntrega DATE,
    presupuesto DECIMAL(19, 2),
    estado VARCHAR(255),
    servicio VARCHAR(255),
    idCliente BIGINT, -- Columna para la relación con la tabla clientes
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente)
);