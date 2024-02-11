CREATE TABLE pedidos (
    id_pedido BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_ingreso DATE NOT NULL,
    fecha_entrega DATE,
    presupuesto DECIMAL(19, 2) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    servicio VARCHAR(255) NOT NULL,
    id_cliente BIGINT NOT NULL, -- Columna para la relación con la tabla clientes
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente) 
);