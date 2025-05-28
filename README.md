# 🏢 Company API

API RESTful para la gestión de empleados, desarrollada con Java y Spring Boot, siguiendo los principios 
de Arquitectura Limpia. El proyecto incorpora seguridad con Spring Security, documentación interactiva 
con Swagger, pruebas unitarias y un banner personalizado al iniciar la aplicación.

---

## 🚀 Tecnologías y Herramientas

- **Lenguaje:** Java 17
- **Framework:** Spring Boot 3.x
- **Arquitectura:** Clean Architecture / Hexagonal
- **Persistencia:** Spring Data JPA, H2 (modo desarrollo)
- **Seguridad:** Spring Security con JWT
- **Documentación:** Swagger (OpenAPI 3)
- **Pruebas:** JUnit 5, Mockito
- **Otros:** Lombok, MapStruct, Feign Client

---

## 🧱 Arquitectura del Proyecto

El proyecto está estructurado siguiendo los principios de Arquitectura Limpia, promoviendo una separación
clara de responsabilidades y facilitando la escalabilidad y mantenibilidad.

├── application
│ ├── port
│ │ ├── in # Interfaces de entrada (casos de uso)
│ │ └── out # Interfaces de salida (repositorios, servicios externos)
│ └── usecase # Implementaciones de casos de uso
├── domain
│ ├── model # Entidades del dominio
│ ├── valueObject # Objetos de valor inmutables
│ └── service # Servicios de dominio
├── infrastructure
│ ├── aop # Aspectos transversales (logging, métricas)
│ ├── config # Configuraciones generales y de seguridad
│ ├── exception # Manejo centralizado de excepciones
│ ├── persistence
│ │ ├── mapper # Mapeadores entre entidades y DTOs
│ │ ├── repository # Implementaciones de repositorios
│ │ └── feignclient # Clientes Feign para servicios externos
│ └── web
│ ├── controller # Controladores REST
│ └── dto # Objetos de transferencia de datos
├── shared
│ ├── constants # Constantes reutilizables
│ ├── enums # Enumeraciones compartidas
│ └── validator # Validadores personalizados
└── resources
├── static # Recursos estáticos
└── application.properties # Configuraciones de la aplicación


---

## 🔐 Seguridad

La aplicación incorpora seguridad mediante **Spring Security**, utilizando JWT para la autenticación y autorización de usuarios. Actualmente, se está trabajando en la integración completa de la seguridad, incluyendo:

- Configuración de filtros de seguridad
- Generación y validación de tokens JWT
- Protección de endpoints según roles y permisos

---

## 📄 Documentación con Swagger

La API está documentada utilizando **Swagger (OpenAPI 3)**, lo que permite una exploración interactiva de los endpoints disponibles. Una vez que la aplicación esté en ejecución, puedes acceder a la documentación en:

http://localhost:8080/swagger-ui.html


---

## 🧪 Pruebas

Se han implementado pruebas unitarias para los casos de uso y servicios principales, utilizando **JUnit 5** y **Mockito**. Las pruebas cubren escenarios clave y validan la lógica de negocio.

Para ejecutar las pruebas:

mvn test

---

## 🖼️ Banner Personalizado

Al iniciar la aplicación, se muestra un banner personalizado en la consola, brindando una identidad visual única al proyecto.

---

## ⚙️ Configuración y Ejecución

Prerrequisitos
Java 17
Maven 3.8.x o superior
Pasos para ejecutar la aplicación

1.- Clona el repositorio:

git clone https://github.com/denisgustavo14/Company.git
cd Company

2.- Construye el proyecto:

mvn clean install

3.- Ejecuta la aplicación:

mvn spring-boot:run

---

## 📬 Endpoints Principales

* GET /employees: Obtiene la lista de empleados
* GET /employees/{id}: Obtiene un empleado por ID
* POST /employees: Crea un nuevo empleado
* PATCH /employees/{id}: Actualiza parcialmente un empleado
* DELETE /employees/{id}: Elimina un empleado

Nota: La documentación completa de los endpoints está disponible en Swagger.

---

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Si deseas colaborar, por favor sigue los siguientes pasos:

* Haz un fork del repositorio
* Crea una nueva rama (git checkout -b feature/nueva-funcionalidad)
* Realiza tus cambios y haz commit (git commit -am 'Agrega nueva funcionalidad')
* Haz push a la rama (git push origin feature/nueva-funcionalidad)
* Abre un Pull Request


##  📄 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.

---


---

Si necesitas personalizar alguna sección o agregar ejemplos específicos de request/response, ¡dímelo!
