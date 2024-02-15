# Proyecto de Gestión de Clientes y Pedidos

Este proyecto es un sistema de gestión de clientes y pedidos para una empresa de servicios. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre clientes y pedidos, así como autenticación de usuarios para acceder al sistema.

## Rutas de la api de Autenticación

### Registrar un nuevo usuario

**Descripción:** Este endpoint guarda un nuevo usuario y devuelve un token, la clave del usuario es encriptada por el algoritmo bcrypt.

```http
  POST /api/v1/auth/register
```

#### Cuerpo de la solicitud 

```json
{
    "login" : "string",
    "clave" : "string"
}
``` 

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 201 Created
  - Cuerpo de respuesta: 
  ```json
    {
        "token" : "string"
    }
  ``` 
- **Respuesta fallida:**
  - Código de estado: 409 Conflict
  - Cuerpo de respuesta: 
  ```json
  [
    {
        "message": "string",
        "errorCode": Integer
    }
  ]
  ``` 
### Login

**Descripción:** Este endpoint se usa para obtener el token de Authenticacion por usuario.

```http
  POST /api/v1/auth/login
```

#### Cuerpo de la solicitud 

```json
{
    "login" : "string",
    "clave" : "string"
}
``` 

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 201 Created
  - Cuerpo de respuesta: 
  ```json
    {
        "token" : "string"
    }
  ``` 
- **Respuesta fallida:**
  - Código de estado: 401 Unauthorized

## Rutas de la API Clientes

### Obtener todos los clientes activos

**Descripción:** Este endpoint devuelve una lista paginada de todos los clientes activos.

```http
  GET /api/v1/clientes/get
```

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 OK
  - Cuerpo de respuesta: 
  ```json
  [
    {
        "id": "long",
        "dni": "string",
        "nombre": "string",
        "apellido": "string",
        "numTelefono": "string"
    }
  ]
  ``` 



### Obtener todos los clientes

**Descripción:** Este endpoint devuelve una lista paginada de todos los clientes, activos o inactivos.

```http
  GET /api/v1/clientes/get/all
```

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 OK
  - Cuerpo de respuesta: 
  ```json
    {
        "id": "long",
        "dni": "string",
        "nombre": "string",
        "apellido": "string",
        "numTelefono": "string"
    }
  ``` 

### Obtener cliente por DNI

**Descripción:** Este endpoint devuelve un cliente según su número de DNI.

```http
  GET /api/v1/clientes/get/dni/{dni}
```

| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{dni}` | El DNI del cliente |


- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 OK
  - Cuerpo de respuesta: 
  ```json
    {
        "id": "long",
        "dni": "string",
        "nombre": "string",
        "apellido": "string",
        "numTelefono": "string"
    }
  ``` 


### Obtener cliente por ID

**Descripción:** Este endpoint devuelve un cliente según su ID.

```http
  GET /api/v1/clientes/get/dni/{id}
```

| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{id}` | El DNI del cliente |


- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 OK
  - Cuerpo de respuesta: 
  ```json
    {
        "id": "long",
        "dni": "string",
        "nombre": "string",
        "apellido": "string",
        "numTelefono": "string"
    }
  ``` 


### Guardar un nuevo cliente

**Descripción:** Este endpoint guarda un nuevo cliente.

```http
  POST /api/v1/clientes/save
```

#### Cuerpo de la solicitud 

```json
{
    "dni": "string",
    "nombre": "string",
    "apellido": "string",
    "numTelefono": "string"
}
``` 

| Atributo | Patron |
|--|--|
| "dni" | El DNI debe tener entre 7 y 8 dígitos numéricos, no puede estar en blanco  |
| "nombre"| El nombre no puede estar en blanco |
| "apellido"| El apellido no puede estar en blanco |
| "numTelefono"| El número de teléfono no puede estar en blanco |

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 201 Created
  - Cuerpo de respuesta: 
  ```json
    {
        "id": "long",
        "dni": "string",
        "nombre": "string",
        "apellido": "string",
        "numTelefono": "string"
    }
  ``` 
- **Respuesta fallida:**
  - Código de estado: 400 Bad Request
  - Cuerpo de respuesta: 
  ```json
  [
    {
        "message": "string",
        "errorCode": Integer
    }
  ]
  ``` 

### Actualizar un cliente existente

**Descripción:** Este endpoint actualiza la información de un cliente existente.

```http
  PUT /api/v1/clientes/update/{id}
```
| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{id}` | El Id del cliente |

#### Cuerpo de la solicitud 

*Se permite enviar solo un atributo para ahorrar recursos en la peticion*

```json
{
    "dni": "string",
    "nombre": "string",
    "apellido": "string",
    "numTelefono": "string"
}
``` 

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 Ok
  - Cuerpo de respuesta: 
  ```json
    {
        "id": "long",
        "dni": "string",
        "nombre": "string",
        "apellido": "string",
        "numTelefono": "string"
    }
  ``` 


### Desactivar lógicamente un cliente

**Descripción:** Este endpoint desactiva lógicamente un cliente existente.

```http
  DELETE /api/v1/clientes/delete/{id}
```
| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{id}` | El Id del cliente |

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 Ok
  - Cuerpo de respuesta: 
  ```json
    {
        "message": "string",
        "errorCode": Integer
    }
  ``` 

- **Respuesta fallida:**
  - Código de estado: 404 Not Found
  - Cuerpo de respuesta: 
  ```json
  [
    {
        "message": "string",
        "errorCode": Integer
    }
  ]
  ``` 