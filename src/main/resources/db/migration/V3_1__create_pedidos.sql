CREATE TABLE pedidos (
    id_pedido BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_ingreso DATE,
    fecha_entrega DATE,
    presupuesto DECIMAL(19, 2),
    estado VARCHAR(255),
    servicio VARCHAR(255),
    activo BOOLEAN,
    id_dispositivo BIGINT,
    FOREIGN KEY (id_dispositivo) REFERENCES dispositivos(id_dispositivo)
);
