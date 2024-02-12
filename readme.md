# Proyecto de Gestión de Clientes y Pedidos

Este proyecto es un sistema de gestión de clientes y pedidos para una empresa de servicios. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre clientes y pedidos, así como autenticación de usuarios para acceder al sistema.

## Rutas de la API

### Clientes

| Ruta                                      | Método | Parámetros de Entrada                            | Respuesta                                            |
|-------------------------------------------|--------|--------------------------------------------------|------------------------------------------------------|
| `/api/v1/clientes/get`                    | GET    | Ninguno                                          | Lista paginada de clientes                          |
| `/api/v1/clientes/save`                   | POST   | Objeto JSON (`dni`, `nombre`, `apellido`, `numTelefono`) | Mensaje de éxito o error                            |
| `/api/v1/clientes/update`                 | PUT    | Objeto JSON (`id`, `dni`, `nombre`, `apellido`, `numTelefono`) | Mensaje de éxito o error                            |
| `/api/v1/clientes/delete/{id}`            | DELETE | ID del cliente a eliminar                        | Mensaje de éxito o error                            |

### Autenticación

| Ruta                                      | Método | Parámetros de Entrada                            | Respuesta                                            |
|-------------------------------------------|--------|--------------------------------------------------|------------------------------------------------------|
| `/api/v1/auth/login`                      | POST   | Objeto JSON (`login`, `clave`)                   | Token JWT o mensaje de error                        |

## Notas

- Se utiliza paginación para gestionar la cantidad de clientes devueltos en la respuesta.
- La autenticación se realiza utilizando el método de inicio de sesión y se genera un token JWT para el acceso seguro a las otras partes del sistema.
- Se manejan diferentes códigos de estado HTTP para respuestas exitosas y errores, junto con mensajes descriptivos.
- Se realizan validaciones de datos tanto en el frontend como en el backend para garantizar la integridad de los datos del cliente.
