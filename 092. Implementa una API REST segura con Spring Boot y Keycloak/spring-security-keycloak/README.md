
# Spring Security: Keycloak con keycloak-spring-boot-starter

* Admin console: GUI para configuraciones
* Users: entidades que pueden realizar login en un sistema protegido
* Role: tipo o categoría de usuario para limitar el acceso a recursos
* Groups: agrupación de usuarios, se puede asignar roles a un grupo
* Realm: agrupación de usuarios, roles, grupos y aplicaciones, un realm puede estar aislado de otros
* Client: entidades que pueden acceder a un sistema protegido
* Identity token: token que contiene información del usuario
* Access Token: token que se utiliza en las peticiones HTTP para acceder a recursos protegidos


Pasos:

https://www.keycloak.org/getting-started/getting-started-docker

1. Crear contenedor docker con keycloak
2. Entrar en localhost:8090
3. Crear realm: realm1
4. Crear cliente: (client1) añadir url de redirección http://localhost:8080/*
5. Crear usuario: user1