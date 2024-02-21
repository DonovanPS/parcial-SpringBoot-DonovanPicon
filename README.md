# parcial-SpringBoot-DonovanPicon

# Gestión de Ventas, Productos y Clientes en Spring Boot

Este proyecto es una aplicación web desarrollada en Spring Boot que proporciona funcionalidades para gestionar ventas, productos y clientes.

## Endpoints Disponibles

### Clientes

- **GET /customers:** Obtiene todos los clientes registrados.
- **GET /customers/{id}:** Obtiene los detalles de un cliente específico por su ID.
- **POST /customers:** Crea un nuevo cliente.
- **PUT /customers/{id}:** Actualiza los detalles de un cliente existente por su ID.
- **DELETE /customers/{id}:** Elimina un cliente existente por su ID.

### Productos

- **GET /products:** Obtiene todos los productos registrados.
- **POST /products:** Crea un nuevo producto.

### Ventas

- **GET /sales:** Obtiene todas las ventas registradas.
- **GET /sales/{id}:** Obtiene los detalles de una venta específica por su ID.
- **POST /sales:** Crea una nueva venta.
- **GET /sales/customers/{customerId}/sales:** Obtiene todas las ventas asociadas a un cliente específico por su ID.

## Documentado con swagger
/docs

## Ejemplos de Uso

### Guardar ventas
el stoke aqui es la cantidad de la venta
```json
{
  "date_sale": "2024-02-21",
  "products": [
    {
      "id": 2,
      "stock": 1
    },
    {
      "id": 3,
      "stock": 1
    }
  ],
  "customer": {
    "id": 2
  }
}
```

![Diagrama de Clases](https://drive.google.com/file/d/1fA2eg94nkKioerlr4Ln3WpGHbQcQ2tu8/view?usp=sharing)
url:https://drive.google.com/file/d/1fA2eg94nkKioerlr4Ln3WpGHbQcQ2tu8/view?usp=sharing