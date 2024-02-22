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

  
## Rutas de la api de dispositivos

### Guardar un nuevo dispositivo y asignarle un pedido

**Descripción:** Este endpoint guarda un nuevo dispositivo y le asigna un pedido al mismo.

```http
  POST /api/v1/dispositivo/new
```

#### Cuerpo de la solicitud 

```json
{
    "idCliente": 0,
    "presupuesto": 0,
    "servicio": "string",
    "marca": "string",
    "modelo": "string",
    "procesador": "string",
    "so": "string",
    "ram": 0,
    "almacenamiento": 0,
    "pantalla": "string",
    "otros": "string"
}

``` 

| Atributo      | Requisitos                                           |
|---------------|------------------------------------------------------|
| "idCliente"   | El ID del cliente es obligatorio y no puede ser nulo|
| "presupuesto" | El presupuesto es obligatorio y no puede ser nulo   |
| "servicio"    | El servicio es obligatorio y no puede estar en blanco. Además, debe seguir el patrón: DIAGNOSTICO, FORMATEO, MANTENIMIENTO, REPARACION |
| "marca"       | La marca es obligatoria y no puede estar en blanco  |
| "modelo"      | El modelo es obligatorio y no puede estar en blanco |
| "procesador"  | El procesador es obligatorio y no puede estar en blanco |
| "so"          | El sistema operativo es obligatorio y no puede estar en blanco |
| "ram"         | La RAM es obligatoria y no puede ser nula           |
| "almacenamiento" | El almacenamiento es obligatorio y no puede ser nulo|
| "pantalla"    | La pantalla es obligatoria y no puede estar en blanco|
| "otros"       | Otros detalles son opcionales                        |


- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 201 Created
  - Cuerpo de respuesta: 
  ```json
    {
        "id": 0,
        "propietario": "string",
        "dni": "string",
        "marca": "string",
        "modelo": "string",
        "procesador": "string",
        "so": "string",
        "ram": 0,
        "almacenamiento": 0,
        "pantalla": "string",
        "otros": "string"
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

### Guardar un dispositivo

**Descripción:** Este endpoint guarda un nuevo dispositivo.

```http
  POST /api/v1/dispositivo/save
```

#### Cuerpo de la solicitud 

```json
{
    "idCliente": 0,
    "marca": "string",
    "modelo": "string",
    "procesador": "string",
    "so": "string",
    "ram": 0,
    "almacenamiento": 0,
    "pantalla": "string",
    "otros": "string"
}


``` 

| Atributo         | Requisitos                                                     |
|------------------|----------------------------------------------------------------|
| "idCliente"      | El ID del cliente es obligatorio y no puede ser nulo          |
| "marca"          | La marca es obligatoria y no puede estar en blanco             |
| "modelo"         | El modelo es obligatorio y no puede estar en blanco            |
| "procesador"     | El procesador es obligatorio y no puede estar en blanco        |
| "so"             | El sistema operativo es obligatorio y no puede estar en blanco |
| "ram"            | La RAM es obligatoria y no puede ser nula                      |
| "almacenamiento" | El almacenamiento es obligatorio y no puede ser nulo            |
| "pantalla"       | La pantalla es obligatoria y no puede estar en blanco          |
| "otros"          | Otros detalles son opcionales                                  |



- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 201 Created
  - Cuerpo de respuesta: 
  ```json
    {
        "id": 0,
        "propietario": "string",
        "dni": "string",
        "marca": "string",
        "modelo": "string",
        "procesador": "string",
        "so": "string",
        "ram": 0,
        "almacenamiento": 0,
        "pantalla": "string",
        "otros": "string"
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


### Obtener todos los dispositivos

**Descripción:** Recupera una lista paginada de todos los dispositivos registrados en el sistema

```http
  GET /api/v1/dispositivo/get
```
| Parámetros de ruta |  Descripcion  |           
| :-------- |:-------------------------|
| `page` | (Opcional) Número de página para la paginación, comenzando desde 0. Por defecto: 0.| 
| `size` | (Opcional) Tamaño de la página para la paginación. Por defecto: 10.|

#### Cuerpo de la solicitud 

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 OK
  - Cuerpo de respuesta: 
  ```json
  {
  "content": [
            {
                "id": 0,
                "propietario": "string",
                "dni": "string",
                "marca": "string",
                "modelo": "string",
                "procesador": "string",
                "so": "string",
                "ram": 0,
                "almacenamiento": 0,
                "pantalla": "string",
                "otros": "string"
            }
    ]
  }
  ``` 



### Obtener dispositivo por ID

**Descripción:** Recupera los detalles de un dispositivo específico según su ID.
.

```http
  GET /api/v1/dispositivo/get/id/{id}
```

| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{id}` | El ID del dispositivo |


- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 OK
  - Cuerpo de respuesta: 
  ```json
    {
        "id": 0,
        "propietario": "string",
        "dni": "string",
        "marca": "string",
        "modelo": "string",
        "procesador": "string",
        "so": "string",
        "ram": 0,
        "almacenamiento": 0,
        "pantalla": "string",
        "otros": "string"
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

### Obtener dispositivos por ID de cliente

**Descripción:** Recupera una lista paginada de dispositivos asociados a un cliente según su ID.

```http
  GET /api/v1/dispositivo/get/cliente/id/{id}
```

| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{id}` | ID del cliente cuyos dispositivos se desean recuperar.
| `page` | (Opcional) Número de página para la paginación, comenzando desde 0. Por defecto: 0.| 
| `size` | (Opcional) Tamaño de la página para la paginación. Por defecto: 10.|

#### Cuerpo de la solicitud 

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 OK
  - Cuerpo de respuesta: 
  ```json
  {
  "content": [
            {
                "id": 0,
                "propietario": "string",
                "dni": "string",
                "marca": "string",
                "modelo": "string",
                "procesador": "string",
                "so": "string",
                "ram": 0,
                "almacenamiento": 0,
                "pantalla": "string",
                "otros": "string"
            }
    ]
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

### Obtener dispositivos por ID de cliente

**Descripción:** Recupera una lista paginada de dispositivos asociados a un cliente según su DNI.

```http
  GET /api/v1/dispositivo/get/cliente/dni/{dni}
```

| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{dni}` | DNI del cliente cuyos dispositivos se desean recuperar.
| `page` | (Opcional) Número de página para la paginación, comenzando desde 0. Por defecto: 0.| 
| `size` | (Opcional) Tamaño de la página para la paginación. Por defecto: 10.|

#### Cuerpo de la solicitud 

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 OK
  - Cuerpo de respuesta: 
  ```json
  {
  "content": [
            {
                "id": 0,
                "propietario": "string",
                "dni": "string",
                "marca": "string",
                "modelo": "string",
                "procesador": "string",
                "so": "string",
                "ram": 0,
                "almacenamiento": 0,
                "pantalla": "string",
                "otros": "string"
            }
    ]
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

### Actualizar dispositivo existente

**Descripción:** Este endpoint actualiza la información de un dispositivo existente según su ID.

```http
  PUT /api/v1/dispositivo/update/{id}
```
| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{id}` | El Id del dispositivo |

#### Cuerpo de la solicitud 

*Se permite enviar solo un atributo para ahorrar recursos en la peticion*

```json
{
    "marca": "string",
    "modelo": "string",
    "procesador": "string",
    "so": "string",
    "ram": 0,
    "almacenamiento": 0,
    "pantalla": "string",
    "otros": "string"
}

``` 

- **Encabezados de solicitud aceptados:** `Accept=application/json`
- **Respuesta exitosa:**
  - Código de estado: 200 Ok
  - Cuerpo de respuesta: 
  ```json
    {
        "id": 0,
        "propietario": "string",
        "dni": "string",
        "marca": "string",
        "modelo": "string",
        "procesador": "string",
        "so": "string",
        "ram": 0,
        "almacenamiento": 0,
        "pantalla": "string",
        "otros": "string"
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

### Eliminar dispositivo

**Descripción:** Elimina un dispositivo del sistema según su ID.

```http
  DELETE /api/v1/dispositivo/delete/{id}
```
| Parámetros de ruta |  Descripcion             | 
| :-------- |:------------------------- | 
| `{id}` | El Id del dispositivo |

#### Cuerpo de la solicitud 

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
