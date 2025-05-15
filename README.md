# 🏢 Company API

Este proyecto es una API REST construida con Java y Spring Boot que gestiona empleados. Sigue una arquitectura limpia (hexagonal) y aplica principios SOLID, AOP, validaciones personalizadas y manejo centralizado de errores.

## 📦 Funcionalidades

- Crear empleado
- Consultar empleado por ID
- Consultar todos los empleados
- Actualizar parcialmente (PATCH)
- Eliminar empleado
- Validación personalizada (nombre, edad, fecha de nacimiento, etc.)

---

## 🚀 Cómo ejecutar

```bash
mvn spring-boot:run

##curls

-crear un empleado

curl --location 'http://localhost:8080/api/v1/employees/createEmployee' \
--header 'Content-Type: application/json' \
--data '{
    "firstName": "Maciel",
    "midName": "Marilyn",
    "fatherLastName": "Garcia",
    "motherLastName": "Mendez",
    "age": 18,
    "gender": "MALE",
    "birthdate": "2006-05-13",
    "position": "Developer"
}'

-obtener un empleado por id

curl --location 'http://localhost:8080/api/v1/employees/1'

-obtener todos los empleados

curl --location 'http://localhost:8080/api/v1/employees'


-elimina empleados por id

curl --location --request DELETE 'http://localhost:8080/api/v1/employees/1'


-actualiza parcialmente un empleado

curl --location --request PATCH 'http://localhost:8080/api/v1/employees/1' \
--header 'Content-Type: application/json' \
--data '{
  "position": "Tech Lead",
  "firstName": "Gustavo"
}'





